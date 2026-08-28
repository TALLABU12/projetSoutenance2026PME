package com.example.projetSoutenance2026PME.dto.produit;

import com.example.projetSoutenance2026PME.dto.categorie.CategorieResponse;

import java.math.BigDecimal;


public class ProduitResponse {
    private Long id;
    private String reference;
    private String nom;
    private String description;
    private BigDecimal prixUnitaire;
    private int quantiteStock;
    private int seuilAlert;
    private boolean actif;
    private CategorieResponse categorie;

    public ProduitResponse(Long id, int quantiteStock) {
        this.id = id;
        this.quantiteStock = quantiteStock;
    }
    public ProduitResponse(Long id,String reference, String nom, String description, BigDecimal prixUnitaire, int quantiteStock, int seuilAlert, boolean actif,CategorieResponse categorie) {
        this.id = id;
        this.reference = reference;
        this.nom = nom;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
        this.quantiteStock = quantiteStock;
        this.seuilAlert = seuilAlert;
        this.actif = actif;
        this.categorie = categorie;
    }


    public String getReference() {
        return reference;
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

    public Long getId() {
        return id;
    }

    public boolean isActif() {
        return actif;
    }

    public CategorieResponse getCategorie() {
        return categorie;
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
