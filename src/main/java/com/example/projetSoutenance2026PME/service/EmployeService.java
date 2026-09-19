package com.example.projetSoutenance2026PME.service;

import com.example.projetSoutenance2026PME.dto.departement.DepartementResponse;
import com.example.projetSoutenance2026PME.dto.employe.EmployeRequest;
import com.example.projetSoutenance2026PME.dto.employe.EmployeResponse;
import com.example.projetSoutenance2026PME.entity.Departement;
import com.example.projetSoutenance2026PME.entity.Employe;
import com.example.projetSoutenance2026PME.exception.ResourceFoundException;
import com.example.projetSoutenance2026PME.exception.ResourceNotFoundException;
import com.example.projetSoutenance2026PME.repository.DepartementRepository;
import com.example.projetSoutenance2026PME.repository.EmployeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeService {
    private final EmployeRepository employeRepository;
    private final DepartementRepository departementRepository;

    public EmployeService(EmployeRepository employeRepository, DepartementRepository departementRepository) {
        this.employeRepository = employeRepository;
        this.departementRepository = departementRepository;
    }

    public EmployeResponse toConversion(Employe employe){
        DepartementResponse response = new DepartementResponse(
                employe.getDepartement().getId(),
                employe.getDepartement().getNom(),
                employe.getDepartement().getDescription()
        );
        return new EmployeResponse(
                employe.getId(),
                employe.getMatricule(),
                employe.getNom(),
                employe.getPrenom(),
                employe.getTelephone(),
                employe.getMail(),
                employe.getPoste(),
                employe.getDateEmbauche(),
                employe.isActif(),
                response
        );
    }

    @Transactional
    public EmployeResponse ajouterEmploye(EmployeRequest request){
        if(employeRepository.existsByMatricule(request.getMatricule())){
            throw new ResourceFoundException("Cet employe existe deja");
        }
        if (employeRepository.existsByMail(request.getMail())) {
            throw new ResourceFoundException("Cette adresse mail est déjà utilisée");
        }
        Departement departement = departementRepository.findById(request.getDepartementId()).orElseThrow(
                ()-> new ResourceNotFoundException("Le departement est introuvable")
        );
        Employe employe = new Employe(
                request.getMatricule(),
                request.getNom(),
                request.getPrenom(),
                request.getTelephone(),
                request.getMail(),
                request.getPoste(),
                departement
        );
        Employe saved = employeRepository.save(employe);
        return toConversion(saved);

    }

    @Transactional
    public EmployeResponse modifierEmploye(Long id, EmployeRequest request){
        if (employeRepository.existsByMatriculeAndIdNot(request.getMatricule(), id)){
            throw new ResourceFoundException("Ce matricule est déjà utilisé");
        }
        if (employeRepository.existsByMailAndIdNot(request.getMail(), id)){
            throw new ResourceFoundException("Cette adresse mail est déjà utilisée");
        }
        Departement departement = departementRepository.findById(request.getDepartementId()).orElseThrow(
                ()-> new ResourceNotFoundException(
                        "Le département est introuvable"
                )
        );
        Employe employe = employeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        "L'employé est introuvable"
                )
        );
        employe.setMatricule(request.getMatricule());
        employe.setNom(request.getNom());
        employe.setPrenom(request.getPrenom());
        employe.setTelephone(request.getTelephone());
        employe.setMail(request.getMail());
        employe.setPoste(request.getPoste());
        employe.setDepartement(departement);
        return toConversion(employe);
    }

    @Transactional
    public void supprimerEmploye(Long id){
        Employe employe = employeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("L'employe est introuvable")
        );
        employe.setActif(false);
    }

    public List<EmployeResponse> listerEmploye(Boolean actif){
        List<Employe> employes;
        if (actif == null){
            employes = employeRepository.findAllWithDepartement();
        }else {
            employes = employeRepository.findAllByActifWithDepartement(actif);
        }
        return employes.stream().map(this::toConversion).toList();
    }

    @Transactional
    public EmployeResponse rechercheEmploye(Long id){
        Employe employe = employeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("L'employe est introuvable")
        );
        return toConversion(employe);
    }
}
