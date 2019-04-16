package com.milestone.criteria.repository.util

import com.milestone.criteria.repository.BasicRepository
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

@Component
@CompileStatic
class RepositoryResolver {
    @Autowired
    List<BasicRepository> repositories

    Map<String, BasicRepository> repositoryMapper

    @PostConstruct
    @CompileDynamic
    void init() {
        repositoryMapper = repositories.collectEntries { [(it.entityType): it] }
    }

    BasicRepository findRepository(String entityType) {
        repositoryMapper.get(entityType)
                .with { checkRepository(it, entityType) }
    }

    private static BasicRepository checkRepository(BasicRepository repository, String entityType) {
        if (!repository) {
            throw new IllegalArgumentException("No repository for target entity=${entityType}")
        }
        repository
    }
}

