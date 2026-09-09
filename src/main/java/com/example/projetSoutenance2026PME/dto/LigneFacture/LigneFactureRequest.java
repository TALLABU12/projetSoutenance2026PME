package com.example.projetSoutenance2026PME.dto.LigneFacture;

import com.example.projetSoutenance2026PME.entity.Facture;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class LigneFactureRequest {

    @Positive(message = "La quantite ne doit pas etre negative")
    private int quantite;
    @NotNull
    private Long produitId;


    public LigneFactureRequest(int quantite, Long produitId) {
        this.quantite = quantite;
        this.produitId = produitId;
    }

    public LigneFactureRequest() {
    }

    public int getQuantite() {
        return quantite;
    }

    public Long getProduitId() {
        return produitId;
    }


}
