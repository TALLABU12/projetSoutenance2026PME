package com.example.projetSoutenance2026PME.entity;

import com.example.projetSoutenance2026PME.enumeration.OrigineMouvement;
import com.example.projetSoutenance2026PME.enumeration.TypeMouvement;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "mouvementsstock")
public class MouvementStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeMouvement typeMouvement;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrigineMouvement origineMouvement;
    @Column(nullable = false)
    private int quantite;
    @Column(nullable = false)
    private LocalDateTime dateMouvement;
    @Column(nullable = false)
    private String commentaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit_id")
    private Produit produit;
    public MouvementStock(TypeMouvement typeMouvement, OrigineMouvement origineMouvement, int quantite, String commentaire) {
        this.typeMouvement = typeMouvement;
        this.origineMouvement = origineMouvement;
        this.quantite = quantite;
        this.commentaire = commentaire;
    }
    @PrePersist
    protected void onCreate(){
        this.dateMouvement = LocalDateTime.now();
    }

    public MouvementStock() {
    }

    public Produit getProduit() {
        return produit;
    }

    public Long getId() {
        return id;
    }

    public TypeMouvement getTypeMouvement() {
        return typeMouvement;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public OrigineMouvement getOrigineMouvement() {
        return origineMouvement;
    }

    public int getQuantite() {
        return quantite;
    }

    public LocalDateTime getDateMouvement() {
        return dateMouvement;
    }

    public String getCommentaire() {
        return commentaire;
    }


}
