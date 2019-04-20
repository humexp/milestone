package com.milestone.milestone.web.dao.entity

import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = 'tasks')
@Canonical
@CompileStatic
@ToString(includeNames = true, includeFields = true)
class Task {
    @Id
    String id
    String parentTask
    String title
    String body
    Integer expectedEffort
    String project
}
