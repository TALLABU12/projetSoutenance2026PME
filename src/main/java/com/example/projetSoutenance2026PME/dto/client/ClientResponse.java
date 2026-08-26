package com.example.projetSoutenance2026PME.dto.client;

import java.time.LocalDateTime;

public class ClientResponse {

    private Long id;
    private String code;
    private String nom;
    private String telephone;
    private String email;
    private String adresse;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    private boolean actif;

    public ClientResponse() {
    }

    public ClientResponse(
            Long id,
            String code,
            String nom,
            String telephone,
            String email,
            String adresse,
            LocalDateTime dateCreation,
            LocalDateTime dateModification,
            boolean actif
    ) {
        this.id = id;
        this.code = code;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.actif = actif;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }
}