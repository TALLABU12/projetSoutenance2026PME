package com.example.projetSoutenance2026PME.controller;

import com.example.projetSoutenance2026PME.dto.produit.ProduitRequest;
import com.example.projetSoutenance2026PME.dto.produit.ProduitResponse;
import com.example.projetSoutenance2026PME.service.ProduitService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {
    private final ProduitService produitService;
    public ProduitController(ProduitService produitService){
        this.produitService = produitService;
    }

    @PostMapping
    public ProduitResponse ajouterProduit(@Valid @RequestBody ProduitRequest request){
        return produitService.ajouterProduit(request);
    }

    @PutMapping("/{id}")
    public ProduitResponse modifierProduit(@PathVariable Long id ,@Valid @RequestBody ProduitRequest request){
        return produitService.modifierProduit(id,request);
    }

    @GetMapping
    public List<ProduitResponse> listerProduit(){
        return produitService.listerProduits();
    }

    @GetMapping("/{id}")
    public ProduitResponse chercherProduit(@PathVariable Long id){
        return produitService.rechercherProduit(id);
    }

    @DeleteMapping("/{id}")
    public void supprimerProduit(@PathVariable Long id){
        produitService.supprimerProduit(id);
    }
}
