package com.example.projetSoutenance2026PME.dto.departement;

public class DepartementResponse {
    private Long id;
    private String nom;
    private String description;

    public DepartementResponse(Long id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }
}
