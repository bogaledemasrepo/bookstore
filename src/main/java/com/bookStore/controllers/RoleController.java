package com.bookStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.bookStore.models.RoleModel;
import com.bookStore.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    @PreAuthorize("hasPermission(null, 'ROLE', 'READ')")
    public List<RoleModel> getAllRoles() {
        return roleRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission(null, 'ROLE', 'READ')")
    public ResponseEntity<RoleModel> getRoleById(@PathVariable UUID id) {
        Optional<RoleModel> role = roleRepository.findById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasPermission(null, 'ROLE', 'CREATE')")
    public RoleModel createRole(@RequestBody RoleModel role) {
        role.setId(UUID.randomUUID());
        return roleRepository.save(role);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasPermission(null, 'ROLE', 'UPDATE')")
    public ResponseEntity<RoleModel> updateRole(@PathVariable UUID id, @RequestBody RoleModel updatedRole) {
        Optional<RoleModel> existingRoleOpt = roleRepository.findById(id);
        if (existingRoleOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        RoleModel existingRole = existingRoleOpt.get();
        existingRole.setName(updatedRole.getName());
        existingRole.setPermissions(updatedRole.getPermissions());
        return ResponseEntity.ok(roleRepository.save(existingRole));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission(null, 'ROLE', 'DELETE')")
    public ResponseEntity<Void> deleteRole(@PathVariable UUID id) {
        if (!roleRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        roleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}