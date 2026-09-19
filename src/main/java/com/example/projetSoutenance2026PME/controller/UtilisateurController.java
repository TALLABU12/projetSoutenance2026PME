package com.example.projetSoutenance2026PME.controller;

import com.example.projetSoutenance2026PME.dto.security.UtilisateurRequest;
import com.example.projetSoutenance2026PME.dto.security.UtilisateurResponse;
import com.example.projetSoutenance2026PME.service.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping
    public ResponseEntity<UtilisateurResponse> creerUtilisateur(@Valid @RequestBody UtilisateurRequest request){
        UtilisateurResponse response = utilisateurService.creerUtilisateur(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UtilisateurResponse>> listerUtilisateur(@RequestParam(required = false) Boolean actif){
        return ResponseEntity.ok(
                utilisateurService.listerUtilisateur(actif)
        );
    }

    @GetMapping("/{username}")
    public ResponseEntity<UtilisateurResponse> rechercherUtilisateur( @PathVariable String username){
        return ResponseEntity.ok(
                utilisateurService.rechercherUtilisateur(username)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurResponse> modifierUtilisateur( @PathVariable Long id, @Valid @RequestBody UtilisateurRequest request){
        return ResponseEntity.ok(
                utilisateurService.modifierUtilisateur(id,request)
        );
    }

    @DeleteMapping("/{username}")
    public void supprimerUtilisateur( @PathVariable String username){
                utilisateurService.supprimerUtilisateur(username);
    }
}
