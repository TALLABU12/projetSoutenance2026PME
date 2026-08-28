package com.example.projetSoutenance2026PME.service;

import com.example.projetSoutenance2026PME.dto.MouvementStock.MouvementStockRequest;
import com.example.projetSoutenance2026PME.dto.MouvementStock.MouvementStockResponse;
import com.example.projetSoutenance2026PME.dto.categorie.CategorieResponse;
import com.example.projetSoutenance2026PME.dto.produit.ProduitResponse;
import com.example.projetSoutenance2026PME.entity.MouvementStock;
import com.example.projetSoutenance2026PME.entity.Produit;
import com.example.projetSoutenance2026PME.enumeration.OrigineMouvement;
import com.example.projetSoutenance2026PME.enumeration.TypeMouvement;
import com.example.projetSoutenance2026PME.exception.MouvementStockIncoherentException;
import com.example.projetSoutenance2026PME.exception.ResourceNotFoundException;
import com.example.projetSoutenance2026PME.exception.StockInsuffisantException;
import com.example.projetSoutenance2026PME.repository.MouvementStockRepository;
import com.example.projetSoutenance2026PME.repository.ProduitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class MouvementStockService {
    private final MouvementStockRepository mouvementStockRepository;
    private final ProduitRepository produitRepository;

    public MouvementStockService(MouvementStockRepository mouvementStockRepository, ProduitRepository produitRepository) {
        this.mouvementStockRepository = mouvementStockRepository;
        this.produitRepository = produitRepository;
    }

    public MouvementStockResponse toResponse(MouvementStock ms) {
        CategorieResponse categorieResponse = new CategorieResponse(
                ms.getProduit().getCategorie().getId(),
                ms.getProduit().getCategorie().getNom(),
                ms.getProduit().getCategorie().getDescription()
        );
        ProduitResponse produitResponse = new ProduitResponse(
                ms.getProduit().getId(),
                ms.getProduit().getReference(),
                ms.getProduit().getNom(),
                ms.getProduit().getDescription(),
                ms.getProduit().getPrixUnitaire(),
                ms.getProduit().getQuantiteStock(),
                ms.getProduit().getSeuilAlert(),
                ms.getProduit().isActif(),
                categorieResponse
        );
        return new MouvementStockResponse(
                ms.getId(),
                ms.getTypeMouvement(),
                ms.getOrigineMouvement(),
                ms.getQuantite(),
                ms.getDateMouvement(),
                ms.getCommentaire(),
                produitResponse
        );
    }

    @Transactional
    public List<MouvementStockResponse> listerMouvementStock() {
        return mouvementStockRepository.findAllWithProduit().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public List<MouvementStockResponse> listerMouvementStockParProduits(Long produitId){
        Produit produit = produitRepository.findById(produitId).orElseThrow(
                ()-> new ResourceNotFoundException("Produit introuvable")
        );
        List<MouvementStock> allByProduit = mouvementStockRepository.findAllByProduit(produitId);
        return allByProduit.stream().map(this::toResponse).toList();
    }
    private void verifierCoherenceMouvement(TypeMouvement type, OrigineMouvement origine) {
        if (origine == OrigineMouvement.ACHAT && type != TypeMouvement.ENTREE) {
            throw new MouvementStockIncoherentException("Un achat doit être une entrée de stock");
        }
        if (origine == OrigineMouvement.CASSE && type != TypeMouvement.SORTIE) {
            throw new MouvementStockIncoherentException("Une casse doit être une sortie de stock");
        }
        if (origine == OrigineMouvement.VENTE && type != TypeMouvement.SORTIE) {
            throw new MouvementStockIncoherentException("Une vente doit être une sortie de stock");
        }
    }

    @Transactional
    public MouvementStockResponse ajouterMouvementStock(MouvementStockRequest request) {
        verifierCoherenceMouvement(request.getTypeMouvement(), request.getOrigineMouvement());
        Produit produit = produitRepository.findById(request.getProduitId()).orElseThrow(
                () -> new ResourceNotFoundException("Produit introuvable")
        );
        int stock_actuel = produit.getQuantiteStock();
        int quantite = request.getQuantite();

        if (request.getTypeMouvement() == TypeMouvement.ENTREE) {
            produit.setQuantiteStock(stock_actuel + quantite);
        } else if (request.getTypeMouvement() == TypeMouvement.SORTIE) {
            if (quantite > stock_actuel) {
                throw new StockInsuffisantException(
                        "Stock insuffisant pour le produit : "
                                + produit.getNom()
                );
            }
            produit.setQuantiteStock(stock_actuel - quantite);
        }
        MouvementStock ms = new MouvementStock(
                request.getTypeMouvement(),
                request.getOrigineMouvement(),
                request.getQuantite(),
                request.getCommentaire()
        );
        ms.setProduit(produit);
        MouvementStock mouvementStock = mouvementStockRepository.save(ms);
        return toResponse(mouvementStock);
    }

    @Transactional
    public MouvementStockResponse rechercherMouvementStock(Long id) {
        MouvementStock ms = mouvementStockRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Ce mouvement de stock est introuvable")
        );
        return toResponse(ms);
    }

    @Transactional
    public Optional<MouvementStockResponse> effectuerInventaire(MouvementStockRequest request) {
        Produit produit = produitRepository.findById(request.getProduitId()).orElseThrow(
                () -> new ResourceNotFoundException("Produit introuvable")
        );
        int stockTheorique = produit.getQuantiteStock();
        int stockReel = request.getQuantite();
        int ecart = stockReel - stockTheorique;
        if (ecart == 0){
            return Optional.empty();
        }

        TypeMouvement typeMouvement;
        if (ecart > 0){
            typeMouvement = TypeMouvement.ENTREE;
        }else {
            typeMouvement = TypeMouvement.SORTIE;
        }
        int quantiteMouvement = Math.abs(ecart);
        produit.setQuantiteStock(stockReel);
        MouvementStock ms = new MouvementStock(
                typeMouvement,
                OrigineMouvement.INVENTAIRE,
                quantiteMouvement,
                request.getCommentaire()
        );
        ms.setProduit(produit);
        MouvementStock stockSave = mouvementStockRepository.save(ms);
        return Optional.of(toResponse(stockSave));
    }
}


