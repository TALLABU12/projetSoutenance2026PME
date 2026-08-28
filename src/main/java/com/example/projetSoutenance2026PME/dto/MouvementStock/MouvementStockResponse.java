package com.example.projetSoutenance2026PME.dto.MouvementStock;

import com.example.projetSoutenance2026PME.dto.produit.ProduitResponse;
import com.example.projetSoutenance2026PME.enumeration.OrigineMouvement;
import com.example.projetSoutenance2026PME.enumeration.TypeMouvement;

import java.time.LocalDateTime;

public class MouvementStockResponse {
    private Long id;
    private TypeMouvement typeMouvement;
    private OrigineMouvement origineMouvement;
    private int quantite;
    private LocalDateTime DateMouvement;
    private String commentaire;
    private ProduitResponse produit;

    public MouvementStockResponse(Long id,TypeMouvement typeMouvement, OrigineMouvement origineMouvement, int quantite, LocalDateTime dateMouvement, String commentaire,ProduitResponse produit) {
        this.id = id;
        this.typeMouvement = typeMouvement;
        this.origineMouvement = origineMouvement;
        this.quantite = quantite;
        this.DateMouvement = dateMouvement;
        this.commentaire = commentaire;
        this.produit = produit;
    }

    public Long getId() {
        return id;
    }

    public ProduitResponse getProduit() {
        return produit;
    }

    public TypeMouvement getTypeMouvement() {
        return typeMouvement;
    }

    public OrigineMouvement getOrigineMouvement() {
        return origineMouvement;
    }

    public int getQuantite() {
        return quantite;
    }

    public LocalDateTime getDateMouvement() {
        return DateMouvement;
    }

    public String getCommentaire() {
        return commentaire;
    }


}
