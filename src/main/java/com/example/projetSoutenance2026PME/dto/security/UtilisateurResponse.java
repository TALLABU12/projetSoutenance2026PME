package com.example.projetSoutenance2026PME.dto.security;

import com.example.projetSoutenance2026PME.entity.Employe;
import com.example.projetSoutenance2026PME.enumeration.Role;

import java.time.LocalDateTime;
import java.util.Set;

public class UtilisateurResponse {

    private Long id;
    private String username;
    private String email;
    private boolean actif;
    private LocalDateTime dateCreation;
    private Set<Role> roles;
    private Long employeId;
    public UtilisateurResponse() {
    }

    public UtilisateurResponse(
            Long id,
            String username,
            String email,
            LocalDateTime dateCreation,
            boolean actif,
            Set<Role> roles,
            Long employeId
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.actif = actif;
        this.dateCreation = dateCreation;
        this.roles = roles;
        this.employeId = employeId;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActif() {
        return actif;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
