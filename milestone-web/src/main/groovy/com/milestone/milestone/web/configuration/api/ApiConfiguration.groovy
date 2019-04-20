package com.milestone.milestone.web.configuration.api

import com.milestone.criteria.provider.FilterProvider
import com.milestone.criteria.provider.SortProvider
import com.milestone.milestone.web.api.controller.ProjectController
import com.milestone.milestone.web.api.controller.SystemController
import com.milestone.milestone.web.api.provider.CorsFilter
import com.milestone.milestone.web.api.provider.ExceptionHandler
import groovy.transform.CompileStatic
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.context.annotation.Configuration

@Configuration
@CompileStatic
class ApiConfiguration extends ResourceConfig {
    ApiConfiguration() {
        registerControllers()
        registerComponents()
        registerProviders()
    }

    private void registerControllers() {
        register(SystemController.class)
        register(ProjectController.class)
    }

    private void registerComponents() {
        register(ExceptionHandler.class)
        register(CorsFilter.class)
    }

    private void registerProviders() {
        register(FilterProvider.class)
        register(SortProvider.class)
    }
}
