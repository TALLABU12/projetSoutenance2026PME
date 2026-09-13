package com.example.projetSoutenance2026PME.entity;

import com.example.projetSoutenance2026PME.enumeration.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String motDePasse;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private boolean actif;

    @Column(nullable = false)
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;


    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "utilisateur_roles",
            joinColumns = @JoinColumn(name = "utilisateur_id")
    )
    @Column(name = "role", nullable = false)
    private Set<Role> roles = new HashSet<>();

    public Utilisateur() {
    }

    public Utilisateur(String username, String motDePasse, String email) {
        this.username = username;
        this.motDePasse = motDePasse;
        this.email = email;
    }

    @PrePersist
    protected void onCreate() {
        this.actif = true;
        this.dateCreation = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        this.dateModification = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getMotDePasse() {
        return motDePasse;
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

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void ajouterRole(Role role) {
        this.roles.add(role);
    }

    public void retirerRole(Role role) {
        this.roles.remove(role);
    }
}