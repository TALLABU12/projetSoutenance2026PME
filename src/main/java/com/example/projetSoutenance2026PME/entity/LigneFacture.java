package com.example.projetSoutenance2026PME.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "lignesfacture")
public class LigneFacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int quantite;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal prixUnitaire;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal montantLigne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facture_id", nullable = false)
    private Facture facture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit_id")
    private Produit produit;

    public LigneFacture(int quantite, BigDecimal prixUnitaire, BigDecimal montantLigne, Produit produit) {
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.montantLigne = montantLigne;
        this.produit = produit;
    }

    public LigneFacture() {
    }

    public Produit getProduit() {
        return produit;
    }

    public Long getId() {
        return id;
    }

    public int getQuantite() {
        return quantite;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public BigDecimal getMontantLigne() {
        return montantLigne;
    }


    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

}

