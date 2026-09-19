package com.example.projetSoutenance2026PME.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employes")
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String matricule;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false)
    private String telephone;
    @Column(unique = true, nullable = false)
    private String mail;
    @Column(nullable = false)
    private String poste;
    private LocalDate dateEmbauche;
    private boolean actif;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departement_id")
    private Departement departement;

    public Employe() {
    }
    public Employe(Departement departement) {
        this.departement = departement;
    }
    public Employe(String matricule, String nom, String prenom, String telephone, String mail, String poste, Departement departement) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.mail = mail;
        this.poste = poste;
        this.departement = departement;
    }

    @PrePersist
    protected void onCreate(){
        this.actif= true;
        this.dateEmbauche= LocalDate.now();
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

    public Departement getDepartement() {
        return departement;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}
