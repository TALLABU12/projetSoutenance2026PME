package com.example.projetSoutenance2026PME.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClientRequest {

    @NotBlank(message = "Le code est obligatoire")
    private String code;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    private String telephone;

    @Email(message = "L'adresse email est invalide")
    private String email;

    private String adresse;

    public ClientRequest() {
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
}