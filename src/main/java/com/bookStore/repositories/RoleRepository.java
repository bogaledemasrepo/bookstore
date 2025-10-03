package com.bookStore.repositories;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookStore.models.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel, UUID> {
}