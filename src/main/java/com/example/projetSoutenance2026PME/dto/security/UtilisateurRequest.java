package com.example.projetSoutenance2026PME.dto.security;

import com.example.projetSoutenance2026PME.enumeration.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class UtilisateurRequest {

    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    @Size(
            min = 3,
            max = 50,
            message = "Le nom d'utilisateur doit contenir entre 3 et 50 caractères"
    )
    private String username;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(
            min = 8,
            message = "Le mot de passe doit contenir au moins 8 caractères"
    )
    private String motDePasse;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String email;

    @NotEmpty(message = "Au moins un rôle est obligatoire")
    private Set<Role> roles;

    public UtilisateurRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}