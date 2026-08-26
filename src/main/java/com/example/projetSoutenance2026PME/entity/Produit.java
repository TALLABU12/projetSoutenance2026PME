package com.example.projetSoutenance2026PME.entity;

import com.example.projetSoutenance2026PME.dto.categorie.CategorieResponse;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="produits")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String reference;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false,precision = 12,scale = 2)
    private BigDecimal prixUnitaire;
    @Column(nullable = false)
    private int quantiteStock;
    @Column(nullable = false)
    private int seuilAlert;
    @Column(nullable = false)
    private boolean actif;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    public Produit() {
    }

    public Produit(String reference, String nom, String description, BigDecimal prixUnitaire, int quantiteStock, int seuilAlert, Categorie categorie) {
        this.reference = reference;
        this.nom = nom;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
        this.quantiteStock = quantiteStock;
        this.seuilAlert = seuilAlert;
        this.categorie = categorie;
    }

    public Long getId() {
        return id;
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

    public boolean isActif() {
        return actif;
    }

    public Categorie getCategorie() {
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

    public void isActif(boolean actif) {
        this.actif = actif;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
