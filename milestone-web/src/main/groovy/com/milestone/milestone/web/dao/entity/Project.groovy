package com.milestone.milestone.web.dao.entity

import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.ToString

import javax.persistence.*

@Entity
@Table(name = 'projects')
@Canonical
@CompileStatic
@ToString(includeNames = true, includeFields = true)
class Project {
    @Id
    String id
    String name
}
