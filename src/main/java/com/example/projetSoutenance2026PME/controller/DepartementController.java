package com.example.projetSoutenance2026PME.controller;

import com.example.projetSoutenance2026PME.dto.departement.DepartementRequest;
import com.example.projetSoutenance2026PME.dto.departement.DepartementResponse;
import com.example.projetSoutenance2026PME.service.DepartementService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departements")
public class DepartementController {
    private final DepartementService departementService;

    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    @PostMapping
    public DepartementResponse ajouterDepartement(@Valid @RequestBody DepartementRequest request){
        return departementService.ajouterDepartement(request);
    }

    @GetMapping("/{id}")
    public DepartementResponse rechercherDepartement(@PathVariable Long id){
        return departementService.rechercherDepartement(id);
    }

    @GetMapping
    public List<DepartementResponse> listerDepartement(){
        return departementService.listerDepartement();
    }

    @PutMapping("/{id}")
    public DepartementResponse modifierDepartement(@PathVariable Long id,@Valid @RequestBody DepartementRequest request){
        return departementService.modifierDepartement(id,request);
    }

    @DeleteMapping("/{id}")
    public void supprierDepartement(@PathVariable Long id){
         departementService.supprimerDepartement(id);
    }
    }
