package com.milestone.criteria.repository

import com.milestone.criteria.domain.request.SearchParams
import com.milestone.criteria.domain.response.ListResponse
import com.milestone.criteria.domain.request.QueryFilter
import com.milestone.criteria.domain.request.QuerySorter
import com.milestone.criteria.repository.util.RepositoryResolver
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Methods def put and def putAll could be applied for both - direct entity object and entity map
 */

@Component
@CompileStatic
class UniversalCrudRepository {
    @Autowired
    RepositoryResolver repositoryResolver

    ListResponse findByCriteria(String entityType, SearchParams params) {
        findByCriteria(entityType, params.filter, params.sort, params.page)
    }

    ListResponse findByCriteria(String entityType, List<QueryFilter> filter, List<QuerySorter> sorter, Integer page) {
        repositoryResolver.findRepository(entityType)
                .findBy(filter, sorter, page)
    }
}

