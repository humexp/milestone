package com.milestone.milestone.web.crud

import com.milestone.criteria.domain.request.SearchParams
import com.milestone.criteria.domain.response.ListResponse
import com.milestone.criteria.repository.UniversalCrudRepository
import com.milestone.milestone.web.dao.entity.Project
import com.milestone.milestone.web.dao.repository.ProjectRepository
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@CompileStatic
class ProjectCrud {
    @Autowired
    ProjectRepository projectRepository

    @Autowired
    UniversalCrudRepository crudRepository

    Project create(Project project) {
        // TODO: Check project existence
        projectRepository.save(project)
    }

    ListResponse<Project> find(SearchParams searchParams) {
        // TODO: Find only projects with appropriate access rights
        crudRepository.findByCriteria(projectRepository.entityType, searchParams)
    }
}
