package com.milestone.milestone.web.api.controller;

import com.milestone.criteria.domain.request.SearchParams;
import com.milestone.criteria.domain.response.ListResponse;
import com.milestone.milestone.web.crud.ProjectCrud;
import com.milestone.milestone.web.dao.entity.Project;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("api/project")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProjectController {
    @Inject
    private ProjectCrud projectCrud;

    @PUT
    @Path("create")
    public Project create(Project project) {
        return projectCrud.create(project);
    }

    @GET
    public ListResponse<Project> find(@Valid @BeanParam SearchParams searchParams) {
        return projectCrud.find(searchParams);
    }
}
