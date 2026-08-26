package com.example.projetSoutenance2026PME.dto.categorie;

import jakarta.validation.constraints.NotBlank;

public class CategorieRequest {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;
    @NotBlank(message = "La description est obligatoire")
    private String description;

    public CategorieRequest(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public CategorieRequest() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
