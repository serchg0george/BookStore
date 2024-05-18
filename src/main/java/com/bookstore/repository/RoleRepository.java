package com.bookstore.repository;

import com.bookstore.entity.authorization.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RolesEntity, Long> {
    Optional<RolesEntity> findByName(String role);
}
