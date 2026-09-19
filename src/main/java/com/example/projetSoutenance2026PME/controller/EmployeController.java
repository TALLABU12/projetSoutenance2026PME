package com.example.projetSoutenance2026PME.controller;

import com.example.projetSoutenance2026PME.dto.employe.EmployeRequest;
import com.example.projetSoutenance2026PME.dto.employe.EmployeResponse;
import com.example.projetSoutenance2026PME.service.EmployeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {
    private final EmployeService employeService;

    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @PostMapping
    public EmployeResponse ajouterEmploye(@Valid @RequestBody EmployeRequest request){
        return employeService.ajouterEmploye(request);
    }

    @GetMapping
    public List<EmployeResponse> listerEmploye(@RequestParam(required = false) Boolean actif){
        return employeService.listerEmploye(actif);
    }

    @GetMapping("/{id}")
    public EmployeResponse rechercheEmploye(@PathVariable Long id){
        return employeService.rechercheEmploye(id);
    }

    @PutMapping("/{id}")
    public EmployeResponse modifierEmploye(@PathVariable Long id, @Valid @RequestBody EmployeRequest request){
        return employeService.modifierEmploye(id,request);
    }

    @DeleteMapping("/{id}")
    public void supprimerEmploye(@PathVariable Long id){
        employeService.supprimerEmploye(id);
    }
}
