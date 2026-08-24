package com.example.projetSoutenance2026PME.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable=false)
    private String code;
    @Column(nullable=false)
    private String nom;
    @Column(nullable=false)
    private String telephone;
    private String email;
    private String adresse;
    @Column(nullable=false)
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;

    @Column(nullable=false)
    private boolean actif;

    public Client() {
    }

    public Client(String code, String nom, String telephone, String email, String adresse) {
        this.code = code;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
    }

    @PrePersist
    protected void onCreate(){
        this.dateCreation = LocalDateTime.now();
        this.actif = true;
    }
    @PreUpdate
    protected void onUpdate(){
        this.dateModification = LocalDateTime.now();
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
