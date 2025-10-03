package com.bookStore.controllers;

import com.bookStore.models.PermissionModel;
import com.bookStore.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionController {

    @Autowired
    private PermissionRepository permissionRepository;

    @GetMapping
    public ResponseEntity<List<PermissionModel>> getAllPermissions() {
        List<PermissionModel> permissions = permissionRepository.findAll();
        return ResponseEntity.ok(permissions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionModel> getPermissionById(@PathVariable UUID id) {
        return permissionRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PermissionModel> createPermission(@RequestBody PermissionModel permission) {
        if (permission.getId() == null) {
            permission.setId(UUID.randomUUID());
        }
        PermissionModel savedPermission = permissionRepository.save(permission);
        return ResponseEntity.ok(savedPermission);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionModel> updatePermission(@PathVariable UUID id, @RequestBody PermissionModel permission) {
        if (!permissionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        permission.setId(id);
        PermissionModel updatedPermission = permissionRepository.save(permission);
        return ResponseEntity.ok(updatedPermission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable UUID id) {
        if (!permissionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        permissionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check")
    public ResponseEntity<List<PermissionModel>> checkPermission(
        @RequestParam UUID userId,
        @RequestParam String action,
        @RequestParam String resource) {
        List<PermissionModel> permissions = permissionRepository.findByUserIdAndActionAndResource(userId, action, resource);
        return ResponseEntity.ok(permissions);
    }
}
