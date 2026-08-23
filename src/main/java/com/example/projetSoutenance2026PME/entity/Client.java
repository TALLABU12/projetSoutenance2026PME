package com.example.projetSoutenance2026PME.entity;
import jakarta.persistence.*;

import java.time.LocalDate;

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
    @Column(unique = true, nullable=false)
    private String email;
    @Column(nullable=false)
    private String adresse;
    private LocalDate dateCreation;
    private Boolean actif;
}
