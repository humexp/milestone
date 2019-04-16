package com.milestone.criteria.domain.response;

import lombok.ToString;

import java.util.List;

@ToString
public class ListResponse<T> {
    public List<T> list;
    public Long total;
}
