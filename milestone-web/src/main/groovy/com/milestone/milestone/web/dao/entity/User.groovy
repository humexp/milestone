package com.milestone.milestone.web.dao.entity

import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.ToString

import javax.persistence.*

@Entity
@Table(name = 'users')
@Canonical
@CompileStatic
@ToString(includeNames = true, includeFields = true)
class User {
    @Id
    String id
    String name

    @ManyToMany
    @JoinTable(
            name = 'user_to_project',
            joinColumns = @JoinColumn(name = 'user_id'),
            inverseJoinColumns = @JoinColumn(name = 'project_id'))
    Set<Project> projects

    @ManyToMany
    @JoinTable(
            name = 'user_to_task',
            joinColumns = @JoinColumn(name = 'user_id'),
            inverseJoinColumns = @JoinColumn(name = 'task_id'))
    Set<Task> tasks
}
