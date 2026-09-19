package com.example.projetSoutenance2026PME.service;

import com.example.projetSoutenance2026PME.dto.departement.DepartementRequest;
import com.example.projetSoutenance2026PME.dto.departement.DepartementResponse;
import com.example.projetSoutenance2026PME.entity.Departement;
import com.example.projetSoutenance2026PME.exception.ResourceFoundException;
import com.example.projetSoutenance2026PME.exception.ResourceNotFoundException;
import com.example.projetSoutenance2026PME.repository.DepartementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartementService {
    private DepartementRepository departementRepository;

    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public DepartementResponse toConversion(Departement departement){
        return new DepartementResponse(departement.getId(), departement.getNom(), departement.getDescription());
    }

    @Transactional
    public DepartementResponse ajouterDepartement(DepartementRequest request){
        if (departementRepository.existsByNom(request.getNom())){
            throw new ResourceFoundException("Ce departement existe deja");
        }
        Departement departement = new Departement(request.getNom(),request.getDescription());
        Departement saved = departementRepository.save(departement);
        return toConversion(saved);
    }

    @Transactional
    public DepartementResponse modifierDepartement(Long id,DepartementRequest request){
        if (departementRepository.existsByNomAndIdNot(request.getNom(),id)){
            throw new ResourceFoundException("Ce département existe déjà");
        }
        Departement departement = departementRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Le departement est introuvable")
        );

        departement.setNom(request.getNom());
        departement.setDescription(request.getDescription());

        return toConversion(departement);
    }

    public List<DepartementResponse> listerDepartement(){
        return departementRepository.findAll().stream().map(this::toConversion).toList();
    }

    @Transactional
    public DepartementResponse rechercherDepartement(Long id){
        Departement departement = departementRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Le departement est introuvable")
        );
        return toConversion(departement);
    }

    @Transactional
    public void supprimerDepartement(Long id){
        Departement departement = departementRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Le departement est introuvable")
        );
        departementRepository.deleteById(id);
    }
}
