package com.example.projetSoutenance2026PME.service;

import com.example.projetSoutenance2026PME.dto.LigneFacture.LigneFactureRequest;
import com.example.projetSoutenance2026PME.dto.LigneFacture.LigneFactureResponse;
import com.example.projetSoutenance2026PME.dto.categorie.CategorieResponse;
import com.example.projetSoutenance2026PME.dto.client.ClientResponse;
import com.example.projetSoutenance2026PME.dto.facture.FactureRequest;
import com.example.projetSoutenance2026PME.dto.facture.FactureResponse;
import com.example.projetSoutenance2026PME.dto.produit.ProduitResponse;
import com.example.projetSoutenance2026PME.entity.*;
import com.example.projetSoutenance2026PME.enumeration.StatusFacture;
import com.example.projetSoutenance2026PME.exception.ResourceNotFoundException;
import com.example.projetSoutenance2026PME.exception.StatutFactureInvalideException;
import com.example.projetSoutenance2026PME.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FactureService {
    private final FactureRepository factureRepository;
    private final ProduitRepository produitRepository;
    private final ClientRepository clientRepository;
    private final MouvementStockService mouvementStockService;

    public FactureService(FactureRepository factureRepository, ProduitRepository produitRepository, ClientRepository clientRepository, MouvementStockService mouvementStockService) {
        this.factureRepository = factureRepository;
        this.produitRepository = produitRepository;
        this.clientRepository = clientRepository;
        this.mouvementStockService = mouvementStockService;
    }

    public FactureResponse toConversion(Facture facture){
        ClientResponse clientResponse = null;
        if (facture.getClient() != null){
            Client client = facture.getClient();
            clientResponse = new ClientResponse(client.getId(),client.getCode(),client.getNom(),client.getTelephone(),client.getEmail(),client.getAdresse(),client.getDateCreation(),client.getDateModification(),client.isActif());
        }

        List<LigneFactureResponse> lignesResponse = new ArrayList<>();

        if (facture.getLigneFacture() != null){
            for (LigneFacture ligneFacture : facture.getLigneFacture()){
                Produit produit = ligneFacture.getProduit();
                Categorie categorie = produit.getCategorie();
                CategorieResponse categorieResponse = new CategorieResponse(categorie.getId(),categorie.getNom(), categorie.getDescription());
                ProduitResponse produitResponse = new ProduitResponse(produit.getId(),produit.getReference(), produit.getNom(),produit.getDescription(),produit.getPrixUnitaire(),produit.getQuantiteStock(),produit.getSeuilAlert(),produit.isActif(),categorieResponse);

                LigneFactureResponse ligneResponse = new LigneFactureResponse(ligneFacture.getId(), ligneFacture.getQuantite(), ligneFacture.getPrixUnitaire(),ligneFacture.getMontantLigne(),produitResponse);

                lignesResponse.add(ligneResponse);
            }
        }



        return new FactureResponse(facture.getId(),facture.getNumero(),facture.getDateFacture(),facture.getStatus(),facture.getMontantHT(),facture.getTauxTva(),facture.getMontantTTC(),facture.getMontantTva(),lignesResponse,clientResponse);
    }

    @Transactional
    public FactureResponse creerBrouillon(FactureRequest request){
        Client client = clientRepository.findById(request.getClientId()).orElseThrow(
                ()-> new ResourceNotFoundException("Le client est introuvable")
        );

        Facture facture = new Facture();
        facture.setClient(client);

        List<LigneFactureRequest> lignes = request.getLignes();
            for (LigneFactureRequest ligne : lignes){

                Produit produit = produitRepository.findById(ligne.getProduitId()).orElseThrow(
                        ()-> new ResourceNotFoundException("Le produit est introuvable")
                );
                BigDecimal prixunitaire = produit.getPrixUnitaire();
                BigDecimal montantligne = BigDecimal.valueOf(ligne.getQuantite()).multiply(prixunitaire);

                LigneFacture ligneFacture = new LigneFacture(ligne.getQuantite(),prixunitaire,montantligne,produit);

                facture.ajouterLigne(ligneFacture);

            }


        Facture saved = factureRepository.save(facture);
        return toConversion(saved);
    }

    private String genererNumeroFacture(){
        int annee = LocalDate.now().getYear();
        Long nombre = factureRepository.count() + 1;
        return String.format("FAC-%d-%05d",annee,nombre);
    }
    @Transactional
    public FactureResponse validerFacture(Long factureId){
        Facture facture = factureRepository.findById(factureId).orElseThrow(
                ()-> new ResourceNotFoundException("Facture est introuvable")
        );
        if (facture.getStatus() != StatusFacture.BROUILLON) {
            throw new StatutFactureInvalideException(
                    "Seule une facture en brouillon peut être validée"
            );
        }

        for (LigneFacture ligneFacture : facture.getLigneFacture()){

            Produit produit = ligneFacture.getProduit();
            mouvementStockService.ajouterMouvementVente(produit,facture, ligneFacture.getQuantite());
        }
        facture.setNumero(genererNumeroFacture());

        facture.valider();

        return toConversion(facture);
    }

    @Transactional
    public List<FactureResponse> listerFacture(){
        return factureRepository.findAllWithClients().stream().map(this::toConversion).toList();
    }

    @Transactional
    public FactureResponse rechercherFacture(Long factureId){
        Facture facture = factureRepository.findById(factureId).orElseThrow(
                ()-> new ResourceNotFoundException("Facture est introuvable")
        );
        return toConversion(facture);
    }

    @Transactional
    public FactureResponse payer(Long factureId){
        Facture facture = factureRepository.findById(factureId).orElseThrow(
                ()-> new ResourceNotFoundException("Facture est introuvable")
        );

        facture.payer();

        return toConversion(facture);
    }

    @Transactional
    public FactureResponse annuler(Long factureId){
        Facture facture = factureRepository.findById(factureId).orElseThrow(
                ()-> new ResourceNotFoundException("Facture est introuvable")
        );

        facture.annuler();

        return toConversion(facture);
    }
}
