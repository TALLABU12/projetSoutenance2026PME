package com.example.projetSoutenance2026PME.dto.security;

import com.example.projetSoutenance2026PME.enumeration.Role;

import java.util.Set;

public class LoginResponse {

    private String token;
    private String username;
    private Set<Role> roles;

    public LoginResponse() {
    }

    public LoginResponse(String token, String username, Set<Role> roles) {
        this.token = token;
        this.username = username;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
