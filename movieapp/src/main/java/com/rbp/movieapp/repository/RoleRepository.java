package com.rbp.movieapp.repository;

import java.util.Optional;

import com.rbp.movieapp.models.ERole;
import com.rbp.movieapp.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}