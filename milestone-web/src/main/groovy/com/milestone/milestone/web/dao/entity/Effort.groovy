package com.milestone.milestone.web.dao.entity

import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = 'efforts')
@Canonical
@CompileStatic
@ToString(includeNames = true, includeFields = true)
class Effort {
    @Id
    String id
    String task
    Long date
    Integer effort
}
