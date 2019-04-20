package com.milestone.milestone.web.api.provider

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper

@Slf4j
@CompileStatic
class ExceptionHandler implements ExceptionMapper<Exception> {
    @Override
    Response toResponse(Exception exception) {
        log.error('Error', exception)

        Response.status(Response.Status.BAD_REQUEST)
                .entity([ message: exception.message ])
                .type(MediaType.APPLICATION_JSON)
                .build()
    }
}
