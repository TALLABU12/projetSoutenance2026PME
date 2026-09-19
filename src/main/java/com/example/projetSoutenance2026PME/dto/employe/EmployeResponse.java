package com.example.projetSoutenance2026PME.dto.employe;

import com.example.projetSoutenance2026PME.dto.departement.DepartementResponse;

import java.time.LocalDate;

public class EmployeResponse {
    private Long id;
    private String matricule;
    private String nom;
    private String prenom;
    private String telephone;
    private String mail;
    private String poste;
    private LocalDate dateEmbauche;
    private boolean actif;

    private DepartementResponse departement;

    public EmployeResponse(Long id, String matricule, String nom, String prenom, String telephone, String mail, String poste, LocalDate dateEmbauche, boolean actif, DepartementResponse departement) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.mail = mail;
        this.poste = poste;
        this.dateEmbauche = dateEmbauche;
        this.actif = actif;
        this.departement = departement;
    }

    public EmployeResponse() {
    }

    public Long getId() {
        return id;
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

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public boolean isActif() {
        return actif;
    }

    public DepartementResponse getDepartement() {
        return departement;
    }
}
