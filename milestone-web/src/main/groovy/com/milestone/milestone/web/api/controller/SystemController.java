package com.milestone.milestone.web.api.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("api/system")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SystemController {
    @GET
    @Path("test")
    public String find() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        return "OK";
    }
}
