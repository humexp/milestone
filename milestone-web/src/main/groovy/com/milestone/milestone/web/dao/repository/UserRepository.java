package com.milestone.milestone.web.dao.repository;

import com.milestone.criteria.repository.BasicRepository;
import com.milestone.milestone.web.dao.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BasicRepository<User, String> {
    String entityType = "user";

    Optional<User> findByProviderId(String providerId);
}
