package com.milestone.criteria.domain.request;

import lombok.ToString;

import javax.validation.constraints.PositiveOrZero;
import javax.ws.rs.QueryParam;
import java.util.List;

@ToString
public class SearchParams {
    @QueryParam("filter")
    public List<QueryFilter> filter;

    @QueryParam("sort")
    public List<QuerySorter> sort;

    @PositiveOrZero
    @QueryParam("page")
    public Integer page;
}
