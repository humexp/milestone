package com.milestone.criteria.repository

import com.milestone.criteria.criteria.CriteriaRequest
import com.milestone.criteria.domain.response.ListResponse
import com.milestone.criteria.domain.request.QueryFilter
import com.milestone.criteria.domain.request.QuerySorter
import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository

import javax.persistence.EntityManager

@CompileStatic
class CustomBasicRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BasicRepository<T, ID> {
    private final EntityManager entityManager
    private final JpaEntityInformation entityInformation

    CustomBasicRepository(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager)

        this.entityManager = entityManager
        this.entityInformation = entityInformation
    }

    @Override
    ListResponse findBy(List<QueryFilter> filter, List<QuerySorter> sorter, Integer page) {
        CriteriaRequest.builder()
                .setEntityInformation(entityInformation)
                .setEntityManager(entityManager)
                .setFilter(filter)
                .setSorter(sorter)
                .setPage(page)
                .build()
                .find()
    }
}


