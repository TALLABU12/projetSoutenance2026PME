package com.example.projetSoutenance2026PME.dto.employe;

import jakarta.validation.constraints.NotBlank;


public class EmployeRequest {
    @NotBlank(message = "Le matricule est obligatoire")
    private String matricule;
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;
    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;
    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    private String telephone;
    @NotBlank(message = "Le mail est obligatoire")
    private String mail;
    @NotBlank(message = "Le poste est obligatoire")
    private String poste;
    private Long departementId;

    public EmployeRequest(String matricule, String nom, String prenom, String telephone, String mail, String poste, Long departementId) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.mail = mail;
        this.poste = poste;
        this.departementId = departementId;
    }

    public EmployeRequest() {
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMail() {
        return mail;
    }

    public String getPoste() {
        return poste;
    }

    public Long getDepartementId() {
        return departementId;
    }
}

