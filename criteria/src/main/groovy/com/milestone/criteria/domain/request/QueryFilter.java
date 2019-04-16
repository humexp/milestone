package com.milestone.criteria.domain.request;

import lombok.ToString;

@ToString
public class QueryFilter {
    public String property;
    public String operator;
    public Object value;
}
