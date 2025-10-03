package com.bookStore.repositories;


import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bookStore.models.PermissionModel;

public interface PermissionRepository extends JpaRepository<PermissionModel, UUID> {
    @Query("SELECT p FROM Permission p JOIN p.roles r JOIN User u WHERE r MEMBER OF u.roles AND u.id = :userId AND p.action = :action AND p.resource = :resource")
    List<PermissionModel> findByUserIdAndActionAndResource(UUID userId, String action, String resource);
}