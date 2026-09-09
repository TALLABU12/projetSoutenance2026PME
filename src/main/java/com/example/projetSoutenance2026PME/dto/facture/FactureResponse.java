package com.example.projetSoutenance2026PME.dto.facture;

import com.example.projetSoutenance2026PME.dto.LigneFacture.LigneFactureResponse;
import com.example.projetSoutenance2026PME.dto.client.ClientResponse;
import com.example.projetSoutenance2026PME.enumeration.StatusFacture;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FactureResponse {

    private Long id;
    private String numero;
    private LocalDate dateFacture;
    private StatusFacture status;
    private BigDecimal montantHT;
    private BigDecimal tauxTva;
    private BigDecimal montantTTC;
    private BigDecimal montantTva;

    private List<LigneFactureResponse> ligneFacture = new ArrayList<>();

    private ClientResponse client;

    public FactureResponse(Long id, String numero, LocalDate dateFacture, StatusFacture status, BigDecimal montantHT, BigDecimal tauxTva, BigDecimal montantTTC, BigDecimal montantTva, List<LigneFactureResponse> ligneFacture, ClientResponse client) {
        this.id = id;
        this.numero = numero;
        this.dateFacture = dateFacture;
        this.status = status;
        this.montantHT = montantHT;
        this.tauxTva = tauxTva;
        this.montantTTC = montantTTC;
        this.montantTva = montantTva;
        this.ligneFacture = ligneFacture;
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

    public List<LigneFactureResponse> getLigneFacture() {
        return ligneFacture;
    }

    public ClientResponse getClient() {
        return client;
    }
}
