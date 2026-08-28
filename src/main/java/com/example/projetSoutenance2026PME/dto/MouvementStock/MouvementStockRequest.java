package com.example.projetSoutenance2026PME.dto.MouvementStock;

import com.example.projetSoutenance2026PME.enumeration.OrigineMouvement;
import com.example.projetSoutenance2026PME.enumeration.TypeMouvement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class MouvementStockRequest {
    @NotNull(message = "Le type de mouvement est obligatoire")
    private TypeMouvement typeMouvement;
    @NotNull(message = "L'origine du mouvement est obligatoire")
    private OrigineMouvement origineMouvement;
    @Positive(message = "La quantité doit être supérieure à 0")
    private int quantite;
    @NotBlank(message = "commentaire obligatoire")
    private String commentaire;
    @NotNull(message = "Le produit est obligatoire")
    @Positive(message = "L'identifiant du produit doit être positif")
    private Long produitId;

    public MouvementStockRequest(TypeMouvement typeMouvement, OrigineMouvement origineMouvement, int quantite, LocalDateTime dateMouvement, String commentaire,Long produitId) {
        this.typeMouvement = typeMouvement;
        this.origineMouvement = origineMouvement;
        this.quantite = quantite;
        this.commentaire = commentaire;
        this.produitId = produitId;
    }

    public MouvementStockRequest() {
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


    public String getCommentaire() {
        return commentaire;
    }

    public Long getProduitId() {
        return produitId;
    }
}
