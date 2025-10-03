package com.bookStore.models;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "permissions")
@Getter
@Setter
public class PermissionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID")
    private UUID id;

    @ManyToMany(mappedBy = "permissions")
    private Set<RoleModel> roles = new HashSet<>();

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private String resource;
}