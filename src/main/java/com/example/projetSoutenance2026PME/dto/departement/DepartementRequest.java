package com.example.projetSoutenance2026PME.dto.departement;
import jakarta.validation.constraints.NotBlank;

public class DepartementRequest {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;
    @NotBlank(message = "La description est obligatoire")
    private String description;

    public DepartementRequest() {
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
