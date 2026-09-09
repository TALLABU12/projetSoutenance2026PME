package com.example.projetSoutenance2026PME.dto.facture;

import com.example.projetSoutenance2026PME.dto.LigneFacture.LigneFactureRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;


public class FactureRequest {

    @NotNull(message = "Le client est obligatoire")
    private Long clientId;
    @NotEmpty(message = "La facture doit contenir au moins une ligne")
    @Valid
    private List<LigneFactureRequest> lignes = new ArrayList<>();

    public FactureRequest(Long clientId,List<LigneFactureRequest> lignes) {

        this.clientId = clientId;
        this.lignes = lignes;
    }

    public FactureRequest() {
    }

    public Long getClientId() {
        return clientId;
    }

    public List<LigneFactureRequest> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneFactureRequest> lignes) {
        this.lignes = lignes;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
