package com.milestone.milestone.web.dao.entity

import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = 'user_to_project')
@Canonical
@CompileStatic
@ToString(includeNames = true, includeFields = true)
class UserToProject {
    String userId
    String projectId
}
