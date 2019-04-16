package com.milestone.criteria.configuration

import com.milestone.criteria.repository.CustomBasicRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@Configuration
@EnableJpaRepositories(repositoryBaseClass = CustomBasicRepository.class)
class RepositoryConfiguration {
}
