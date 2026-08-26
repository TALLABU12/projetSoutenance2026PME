package com.example.projetSoutenance2026PME.controller;

import com.example.projetSoutenance2026PME.dto.categorie.CategorieRequest;
import com.example.projetSoutenance2026PME.dto.categorie.CategorieResponse;
import com.example.projetSoutenance2026PME.service.CategorieService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {
    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @PostMapping
    public CategorieResponse ajouterCategorie(@Valid @RequestBody CategorieRequest request){
        return categorieService.ajouterCategorie(request);
    }

    @GetMapping
    public List<CategorieResponse> listerCategories(){
        return categorieService.listerCategorie();
    }
    @GetMapping("/{id}")
    public CategorieResponse rechercherCategorieId(@PathVariable Long id){
        return categorieService.rechercherById(id);
    }

    @PutMapping("/{id}")
    public CategorieResponse modifierCategorie(@PathVariable Long id, @Valid @RequestBody CategorieRequest request){
        return categorieService.modifierCategorie(id,request);
    }

    @DeleteMapping
    public void supprimerCategorie(@PathVariable Long id){
         categorieService.supprimerCategorie(id);
    }
}
