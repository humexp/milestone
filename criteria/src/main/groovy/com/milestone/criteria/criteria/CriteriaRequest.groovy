package com.milestone.criteria.criteria

import com.milestone.criteria.domain.response.ListResponse
import com.milestone.criteria.domain.request.QueryFilter
import com.milestone.criteria.domain.request.QuerySorter
import groovy.transform.Canonical
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.transform.builder.Builder
import org.springframework.data.jpa.repository.support.JpaEntityInformation

import javax.persistence.EntityManager
import javax.persistence.TypedQuery
import javax.persistence.criteria.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

@Canonical
@Builder(prefix = 'set')
@CompileStatic
class CriteriaRequest {
    private static final int ROWS_PER_PAGE = 100
    private static final ZoneId UTC_ZONE = ZoneId.of('UTC')

    EntityManager entityManager
    JpaEntityInformation entityInformation

    List<QueryFilter> filter
    List<QuerySorter> sorter
    Integer page

    CriteriaBuilder builder
    CriteriaQuery requestQuery
    Root requestRoot
    List<Predicate> requestPredicates
    TypedQuery finalRequestQuery

    ListResponse find() {
        init().modifyFilters()
                .addFilters()
                .addSorters()
                .composeFinalRequestQuery()
                .addPageable()
                .getListResponse()
    }

    private CriteriaRequest init() {
        builder = entityManager.getCriteriaBuilder()
        requestQuery = builder.createQuery(entityInformation.javaType)
        requestRoot = requestQuery.from(entityInformation.javaType)
        this
    }

    private CriteriaRequest modifyFilters() {
        filter?.each {
            Class dataType = composePath(it.property).javaType
            it.value = modifyFilterValue(it.value, dataType)
        }
        this
    }

    private Object modifyFilterValue(Object value, Class dataType) {
        switch (dataType) {
            case Boolean: return Boolean.parseBoolean(value as String)
            case ZonedDateTime: return ZonedDateTime.of(LocalDateTime.parse(value as String), UTC_ZONE)
            case LocalDateTime: return LocalDateTime.parse(value as String)
            case LocalDate: return LocalDate.parse(value as String)
            default: value
        }
    }

    @CompileDynamic
    private CriteriaRequest addFilters() {
        if (filter) {
            requestPredicates = filter.collect { convertCustomFilterToCriteria(it) }
                    .with { add(this.builder.conjunction()); it }

            requestQuery = requestQuery.where(*requestPredicates)
        }
        this
    }

    @CompileDynamic
    private CriteriaRequest addSorters() {
        if (sorter) {
            requestQuery = sorter.collect { convertCustomSorterToCriteria(it) }
                    .with { requestQuery.orderBy(*it) }
        }
        this
    }

    private CriteriaRequest composeFinalRequestQuery() {
        finalRequestQuery = entityManager.createQuery(requestQuery)
        this
    }

    private CriteriaRequest addPageable() {
        if (page) {
            finalRequestQuery.setFirstResult(ROWS_PER_PAGE * (page - 1))
            finalRequestQuery.setMaxResults(ROWS_PER_PAGE)
        }
        this
    }

    private ListResponse getListResponse() {
        new ListResponse(
                list: finalRequestQuery.getResultList(),
                total: getCount()
        )
    }

    @CompileDynamic
    private Long getCount() {
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class)
        countQuery.select(builder.count(countQuery.from(entityInformation.javaType)))
        CriteriaQuery countRequestQuery

        if (requestPredicates) {
            countRequestQuery = countQuery.where(*requestPredicates)
        } else {
            countRequestQuery = countQuery.where()
        }

        entityManager.createQuery(countRequestQuery).getSingleResult() as Long
    }

    @CompileDynamic
    private Predicate convertCustomFilterToCriteria(QueryFilter filter) {
        switch (filter.operator) {
            case '=': return builder.equal(composePath(filter.property), filter.value)
            case '!=': return builder.notEqual(composePath(filter.property), filter.value)
            case '>': return builder.greaterThan(composePath(filter.property), filter.value)
            case '>=': return builder.greaterThanOrEqualTo(composePath(filter.property), filter.value)
            case '<': return builder.lessThan(composePath(filter.property), filter.value)
            case '<=': return builder.lessThanOrEqualTo(composePath(filter.property), filter.value)
            case 'IS NULL': return builder.isNull(composePath(filter.property))
            case 'IS NOT NULL': return builder.isNotNull(composePath(filter.property))
            case 'LIKE': return builder.like(composePath(filter.property), filter.value)
            case 'NOT LIKE': return builder.notLike(composePath(filter.property), filter.value)
            case 'IN': return builder.isTrue(composePath(filter.property).in(filter.value))
            case 'NOT IN': return builder.isTrue(composePath(filter.property).in(filter.value).not())
            default: throw new RuntimeException("Unsupported filter operator { ${filter.operator} }")
        }
    }

    private Order convertCustomSorterToCriteria(QuerySorter sorter) {
        switch (sorter.direction) {
            case 'ASC': return builder.asc(composePath(sorter.property))
            case 'DESC': return builder.desc(composePath(sorter.property))
            default: throw new RuntimeException("Unsupported sorter direction { ${sorter.direction} }")
        }
    }

    private Expression composePath(String rawPath) {
        if (rawPath.contains('.')) {
            rawPath.split('\\.').inject(requestRoot as Path) { Path path, String singleRawPath ->
                path.get(singleRawPath)
            }
        } else {
            requestRoot.get(rawPath)
        }
    }
}
