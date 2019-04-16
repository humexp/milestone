package com.milestone.criteria.repository;

import com.milestone.criteria.domain.request.QueryFilter;
import com.milestone.criteria.domain.request.QuerySorter;
import com.milestone.criteria.domain.response.ListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BasicRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    public abstract ListResponse findBy(List<QueryFilter> filter, List<QuerySorter> sorter, Integer page);
}
