package com.example.projetSoutenance2026PME.service;

import com.example.projetSoutenance2026PME.dto.categorie.CategorieResponse;
import com.example.projetSoutenance2026PME.dto.produit.ProduitRequest;
import com.example.projetSoutenance2026PME.dto.produit.ProduitResponse;
import com.example.projetSoutenance2026PME.entity.Categorie;
import com.example.projetSoutenance2026PME.entity.Produit;
import com.example.projetSoutenance2026PME.exception.ResourceFoundException;
import com.example.projetSoutenance2026PME.exception.ResourceNotFoundException;
import com.example.projetSoutenance2026PME.repository.CategorieRepository;
import com.example.projetSoutenance2026PME.repository.ProduitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProduitService {
    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;
    public ProduitService(ProduitRepository produitRepository,CategorieRepository categorieRepository){
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
    }

    public ProduitResponse toResponse(Produit produit){
        CategorieResponse categorieResponse = new CategorieResponse(
                produit.getCategorie().getId(),
                produit.getCategorie().getNom(),
                produit.getCategorie().getDescription()
        );
        return new ProduitResponse(produit.getId(),produit.getReference(), produit.getNom(), produit.getDescription(), produit.getPrixUnitaire(), produit.getQuantiteStock(), produit.getSeuilAlert(), produit.isActif(),categorieResponse);
    }

    @Transactional
    public List<ProduitResponse> listerProduits(){
        return produitRepository.findAllWithCategorie().stream().map(this::toResponse).toList();
    }

    @Transactional
    public ProduitResponse ajouterProduit(ProduitRequest request){
        boolean produit = produitRepository.existsByReference(request.getReference());
        if (produit){
            throw new ResourceFoundException("Produit existe deja");
        }
        Categorie categorie = categorieRepository.findById(request.getCategorieId()).orElseThrow(
                ()-> new ResourceNotFoundException("categorie introuvable")
        );
        Produit produit1 = new Produit(request.getReference(), request.getNom(), request.getDescription(), request.getPrixUnitaire(), request.getQuantiteStock(), request.getSeuilAlert(),categorie );
        Produit p = produitRepository.save(produit1);
        return toResponse(p);
    }

    @Transactional
    public ProduitResponse modifierProduit(Long id, ProduitRequest request){
        Produit p = produitRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Produit introuvable")
        );
        p.setReference(request.getReference());
        p.setNom(request.getNom());
        p.setDescription(request.getDescription());
        p.setPrixUnitaire(request.getPrixUnitaire());
        p.setQuantiteStock(request.getQuantiteStock());
        p.setSeuilAlert(request.getSeuilAlert());
        return toResponse(p);
    }

    @Transactional
    public ProduitResponse rechercherProduit(Long id){
        Produit p = produitRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Produit introuvable")
        );
        return toResponse(p);
    }

    @Transactional
    public void supprimerProduit(Long id){
        Produit p = produitRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Produit introuvable")
        );
        p.isActif(false);
    }
}
