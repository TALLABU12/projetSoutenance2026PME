package com.example.projetSoutenance2026PME.controller;

import com.example.projetSoutenance2026PME.dto.facture.FactureRequest;
import com.example.projetSoutenance2026PME.dto.facture.FactureResponse;
import com.example.projetSoutenance2026PME.service.FactureService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factures")
public class FactureController {
    private final FactureService factureService;

    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    @PostMapping
    public FactureResponse creerBrouillon(@Valid @RequestBody FactureRequest request){
        return factureService.creerBrouillon(request);
    }

    @PostMapping("/{id}/validation")
    public FactureResponse valider(@PathVariable Long id){
        return factureService.validerFacture(id);
    }

    @GetMapping("/{id}")
    public FactureResponse rechercherfacture(@PathVariable Long id){
        return factureService.rechercherFacture(id);
    }

    @GetMapping
    public List<FactureResponse> listerfacture(){
        return factureService.listerFacture();
    }

    @PostMapping("/{id}/paiement")
    public FactureResponse payer(@PathVariable Long id){
        return factureService.payer(id);
    }

    @PostMapping("/{id}/annulation")
    public FactureResponse annuler(@PathVariable Long id){
        return factureService.annuler(id);
    }

}
