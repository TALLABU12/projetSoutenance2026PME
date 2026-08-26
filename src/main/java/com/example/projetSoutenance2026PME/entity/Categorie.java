package com.example.projetSoutenance2026PME.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String nom;
    @Column(nullable = false)
    private String description;

    public Categorie(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public Categorie() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getNom() {
        return nom;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
