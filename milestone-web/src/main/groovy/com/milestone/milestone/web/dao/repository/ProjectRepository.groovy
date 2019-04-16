package com.milestone.milestone.web.dao.repository

import com.milestone.criteria.repository.BasicRepository
import com.milestone.milestone.web.dao.entity.Project
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository extends BasicRepository<Project, String> {
    String entityType = 'project'
}
