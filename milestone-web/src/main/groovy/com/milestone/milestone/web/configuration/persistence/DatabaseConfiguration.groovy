package com.milestone.milestone.web.configuration.persistence

import com.milestone.criteria.repository.CustomBasicRepository
import com.zaxxer.hikari.HikariDataSource
import groovy.transform.CompileStatic
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

import javax.sql.DataSource

@CompileStatic
@Configuration
@EntityScan('com.milestone.milestone.web.dao.entity')
@EnableJpaRepositories(basePackages = 'com.milestone.milestone.web.dao.repository',
        repositoryBaseClass = CustomBasicRepository.class)
@ConfigurationProperties(prefix = 'db')
class DatabaseConfiguration {
    String url
    String user
    String password

    @Bean
    DataSource dataSource() {
        DataSource dataSource = new HikariDataSource()
        dataSource.setJdbcUrl(url)
        dataSource.setUsername(user)
        dataSource.setPassword(password)
        dataSource
    }
}