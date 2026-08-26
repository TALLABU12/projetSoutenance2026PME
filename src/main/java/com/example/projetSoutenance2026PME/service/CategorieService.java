package com.example.projetSoutenance2026PME.service;
import com.example.projetSoutenance2026PME.dto.categorie.CategorieRequest;
import com.example.projetSoutenance2026PME.dto.categorie.CategorieResponse;
import com.example.projetSoutenance2026PME.entity.Categorie;
import com.example.projetSoutenance2026PME.exception.ResourceFoundException;
import com.example.projetSoutenance2026PME.exception.ResourceNotFoundException;
import com.example.projetSoutenance2026PME.repository.CategorieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategorieService {

    private final CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public CategorieResponse toResponse(Categorie categorie){
         return new CategorieResponse(categorie.getId(), categorie.getNom(), categorie.getDescription());
    }

    @Transactional
    public CategorieResponse ajouterCategorie(CategorieRequest request){
        boolean categorie = categorieRepository.existsByNom(request.getNom());
        if (categorie){
            throw new ResourceFoundException("Categorie deja enregistre");
        }
        Categorie categorie1 = new Categorie(request.getNom(), request.getDescription());
        Categorie save = categorieRepository.save(categorie1);
        return toResponse(save);
    }

    @Transactional
    public List<CategorieResponse> listerCategorie(){
        return categorieRepository.findAll().stream()
                .map(this::toResponse).toList();
    }

    @Transactional
    public CategorieResponse modifierCategorie(Long id, CategorieRequest request){
        Categorie categorie = categorieRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Categorie introuvable")
        );
        categorie.setNom(request.getNom());
        categorie.setDescription(request.getDescription());
        return toResponse(categorie);

    }

    @Transactional
    public CategorieResponse rechercherById(Long id){
        Categorie categorie = categorieRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Categorie introuvable")
        );
        return toResponse(categorie);
    }

    @Transactional
    public void supprimerCategorie(Long id){
        Categorie categorie = categorieRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Categorie introuvable")
        );
        categorieRepository.deleteById(categorie.getId());
    }
}
