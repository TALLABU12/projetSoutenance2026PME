package com.example.projetSoutenance2026PME.dto.produit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class ProduitRequest {

    @NotBlank(message = "Le reference est obligatoire")
    private String reference;
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;
    @NotBlank(message = "La description est obligatoire")
    private String description;
    @Positive @NotNull
    private BigDecimal prixUnitaire;
    @PositiveOrZero
    private int quantiteStock;
    @Positive
    private int seuilAlert;
    @NotNull
    private Long categorieId;

    public ProduitRequest() {
    }
    public ProduitRequest(String reference, String nom, String description, BigDecimal prixUnitaire, int quantiteStock, int seuilAlert,Long categorieId) {
        this.reference = reference;
        this.nom = nom;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
        this.quantiteStock = quantiteStock;
        this.seuilAlert = seuilAlert;
        this.categorieId = categorieId;
    }


    public String getReference() {
        return reference;
    }

    public Long getCategorieId() {
        return categorieId;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public int getQuantiteStock() {
        return quantiteStock;
    }

    public int getSeuilAlert() {
        return seuilAlert;
    }


    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public void setQuantiteStock(int quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public void setSeuilAlert(int seuilAlert) {
        this.seuilAlert = seuilAlert;
    }


}
