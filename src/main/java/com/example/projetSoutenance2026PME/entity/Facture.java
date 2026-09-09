package com.example.projetSoutenance2026PME.entity;

import com.example.projetSoutenance2026PME.enumeration.StatusFacture;
import com.example.projetSoutenance2026PME.exception.FactureSansLigneException;
import com.example.projetSoutenance2026PME.exception.ResourceNotFoundException;
import com.example.projetSoutenance2026PME.exception.StatutFactureInvalideException;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "factures")
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String numero;
    @Column(nullable = false)
    private LocalDate dateFacture;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusFacture status;
    @Column(nullable = false, precision = 12,scale = 2)
    private BigDecimal montantHT = BigDecimal.ZERO;
    @Column(nullable = false,precision = 12,scale = 2)
    private BigDecimal tauxTva = BigDecimal.valueOf(18);
    @Column(nullable = false,precision = 12,scale = 2)
    private BigDecimal montantTTC = BigDecimal.ZERO;
    @Column(nullable = false,precision = 12,scale = 2)
    private BigDecimal montantTva = BigDecimal.ZERO;

    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<LigneFacture> ligneFacture = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id" , nullable = false)
    private Client client;

    @PrePersist
    protected void onCreate(){
        this.dateFacture = LocalDate.now();
        this.status = StatusFacture.BROUILLON;
        if (montantHT == null){
            this.montantHT = BigDecimal.ZERO;
        }
        if (montantTTC == null){
            this.montantTTC = BigDecimal.ZERO;
        }
        if (tauxTva == null){
            this.tauxTva = (BigDecimal.valueOf(18)) ;
        }
        if (montantTva == null){
            this.montantTva = BigDecimal.ZERO ;
        }

    }
    public Facture() {
    }


    public void ajouterLigne(LigneFacture ligne){

        if (ligne == null){
            throw new ResourceNotFoundException("Ligne introuvable");
        }
        ligneFacture.add(ligne);
        ligne.setFacture(this);
        calculerMontants();
    }

    public void calculerMontants() {
        BigDecimal totalht = ligneFacture.stream()
                .map(LigneFacture::getMontantLigne)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        this.montantHT = totalht;
        this.montantTva = totalht.multiply(tauxTva).setScale(2,RoundingMode.HALF_UP);
        this.montantTTC = totalht.add(montantTva).setScale(2,RoundingMode.HALF_UP);

    }


    public void valider(){
        if (status != StatusFacture.BROUILLON){
            throw new StatutFactureInvalideException("Seule une facture en brouillon peut être validée");
        }
        if (ligneFacture.isEmpty()){
            throw new FactureSansLigneException("Une facture doit contenir au moins une ligne");
        }
        calculerMontants();
        this.status = StatusFacture.VALIDEE;
    }

    public void payer(){
        if (status != StatusFacture.VALIDEE){
            throw new StatutFactureInvalideException("Seule une facture validée peut être payée");
        }
        this.status = StatusFacture.PAYEE;
    }
    public void annuler(){
        if (status != StatusFacture.BROUILLON){
            throw new StatutFactureInvalideException("Seule une facture en brouilon peut être annulée");
        }
        this.status = StatusFacture.ANNULEE;
    }


    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public LocalDate getDateFacture() {
        return dateFacture;
    }

    public StatusFacture getStatus() {
        return status;
    }

    public BigDecimal getMontantHT() {
        return montantHT;
    }

    public BigDecimal getTauxTva() {
        return tauxTva;
    }

    public BigDecimal getMontantTTC() {
        return montantTTC;
    }

    public BigDecimal getMontantTva() {
        return montantTva;
    }

    public Client getClient() {
        return client;
    }

    public List<LigneFacture> getLigneFacture() {
        return ligneFacture;
    }

}
