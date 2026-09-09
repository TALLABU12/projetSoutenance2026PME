package com.example.projetSoutenance2026PME.dto.LigneFacture;

import com.example.projetSoutenance2026PME.dto.produit.ProduitResponse;
import com.example.projetSoutenance2026PME.entity.LigneFacture;


import java.math.BigDecimal;

public class LigneFactureResponse {
    private Long id;
    private int quantite;
    private BigDecimal prixUnitaire;
    private BigDecimal montantLigne;

    private ProduitResponse produit;

    public LigneFactureResponse(Long id, int quantite, BigDecimal prixUnitaire, BigDecimal montantLigne, ProduitResponse produit) {
        this.id = id;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.montantLigne = montantLigne;
        this.produit = produit;
    }

    public LigneFactureResponse() {
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

    public ProduitResponse getProduit() {
        return produit;
    }
}
