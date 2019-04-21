package com.milestone.milestone.web.dao.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.milestone.milestone.web.util.IdGenerator
import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.ToString
import org.hibernate.annotations.GenericGenerator

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = 'users')
@Canonical
@CompileStatic
@ToString(includeNames = true, includeFields = true)
class User {
    @Id
    @GenericGenerator(name = 'id', strategy = 'com.milestone.milestone.web.util.IdGenerator')
    @GeneratedValue(generator = 'id')
    String id

    @Column(nullable = false)
    String name

    // TODO: If use multiple providers, can use one approved email for matching
    String email


    String password

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    AuthProvider authProvider

    @NotNull
    @Column(nullable = false)
    String providerId
}
