package com.bookStore.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.bookStore.repositories.PermissionRepository;
import com.bookStore.models.PermissionModel;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication == null || permission == null) {
            return false;
        }
        String userIdStr = authentication.getName();
        UUID userId;
        try {
            userId = UUID.fromString(userIdStr);
        } catch (IllegalArgumentException e) {
            return false; // Invalid UUID
        }
        String action = permission.toString();
        String resource = targetDomainObject != null ? targetDomainObject.toString() : "unknown";
        return hasPermission(userId, action, resource);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if (authentication == null || permission == null || targetType == null) {
            return false;
        }
        String userIdStr = authentication.getName();
        UUID userId;
        try {
            userId = UUID.fromString(userIdStr);
        } catch (IllegalArgumentException e) {
            return false; // Invalid UUID
        }
        String action = permission.toString();
        String resource = targetType;
        return hasPermission(userId, action, resource);
    }

    private boolean hasPermission(UUID userId, String action, String resource) {
        if (userId == null || action == null || resource == null) {
            return false;
        }
        List<PermissionModel> permissions = permissionRepository.findByUserIdAndActionAndResource(
            userId, action.toUpperCase(), resource.toUpperCase()
        );
        return !permissions.isEmpty();
    }
}
