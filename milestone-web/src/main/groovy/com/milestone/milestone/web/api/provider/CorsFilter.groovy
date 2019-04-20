package com.milestone.milestone.web.api.provider

import groovy.transform.CompileStatic

import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerResponseContext
import javax.ws.rs.container.ContainerResponseFilter
import javax.ws.rs.container.PreMatching
import javax.ws.rs.ext.Provider

@Provider
@PreMatching
@CompileStatic
class CorsFilter implements ContainerResponseFilter {
    @Override
    void filter(ContainerRequestContext requestCtx, ContainerResponseContext responseCtx) throws IOException {
        responseCtx.getHeaders().with {
            add('Access-Control-Allow-Origin', '*')
            add('Access-Control-Allow-Credentials', 'true')
            add('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE, OPTIONS, HEAD')
            add('Access-Control-Max-Age', '86400')
            addAll('Access-Control-Allow-Headers', '*')
        }
    }
}
