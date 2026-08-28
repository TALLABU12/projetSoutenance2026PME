package com.example.projetSoutenance2026PME.controller;

import com.example.projetSoutenance2026PME.dto.MouvementStock.MouvementStockRequest;
import com.example.projetSoutenance2026PME.dto.MouvementStock.MouvementStockResponse;
import com.example.projetSoutenance2026PME.service.MouvementStockService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mouvementstock")
public class MouvementStockController {
    private final MouvementStockService mouvementStockService;
    public MouvementStockController(MouvementStockService mouvementStockService){
        this.mouvementStockService = mouvementStockService;
    }

    @PostMapping("/inventaire")
    public ResponseEntity<MouvementStockResponse> effectuerInventaire( @Valid @RequestBody  MouvementStockRequest request){
        Optional<MouvementStockResponse> response = mouvementStockService.effectuerInventaire(request);
        return response.map(ResponseEntity::ok).orElseGet(
                ()-> ResponseEntity.noContent().build()
        );
    }

    @PostMapping
    public MouvementStockResponse ajouterMouvementStock(@Valid @RequestBody MouvementStockRequest request){
        return mouvementStockService.ajouterMouvementStock(request);
    }

    @GetMapping
    public List<MouvementStockResponse>  listerMouvementStock(){
        return mouvementStockService.listerMouvementStock();
    }

    @GetMapping("/ProduitId/{id}")
    public List<MouvementStockResponse>  listerMouvementStockParProduits(@PathVariable Long id){
        return mouvementStockService.listerMouvementStockParProduits(id);
    }

    @GetMapping("/{id}")
    public MouvementStockResponse  rechercherMouvementStock(@PathVariable  Long id){
        return mouvementStockService.rechercherMouvementStock(id);
    }
}
