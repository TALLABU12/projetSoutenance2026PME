package com.example.projetSoutenance2026PME.service;

import com.example.projetSoutenance2026PME.dto.security.UtilisateurRequest;
import com.example.projetSoutenance2026PME.dto.security.UtilisateurResponse;
import com.example.projetSoutenance2026PME.entity.Employe;
import com.example.projetSoutenance2026PME.entity.Utilisateur;
import com.example.projetSoutenance2026PME.exception.ResourceFoundException;
import com.example.projetSoutenance2026PME.exception.ResourceNotFoundException;
import com.example.projetSoutenance2026PME.repository.EmployeRepository;
import com.example.projetSoutenance2026PME.repository.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UtilisateurService {
    private final EmployeRepository employeRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurService(EmployeRepository employeRepository, UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.employeRepository = employeRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UtilisateurResponse toConversion(Utilisateur utilisateur){
        Long employeId = utilisateur.getEmploye() != null ? utilisateur.getEmploye().getId() : null;
        return new UtilisateurResponse(
                utilisateur.getId(),
                utilisateur.getUsername(),
                utilisateur.getEmail(),
                utilisateur.getDateCreation(),
                utilisateur.isActif(),
                utilisateur.getRoles(),
                employeId
        );
    }

    @Transactional
    public UtilisateurResponse creerUtilisateur(UtilisateurRequest request){
        if (utilisateurRepository.existsByUsername(request.getUsername())){
            throw new ResourceNotFoundException("Le nom d'utilisateur est déjà utilisé");
        }
        if (utilisateurRepository.existsByEmail(request.getEmail())){
            throw new ResourceNotFoundException("L'adresse email est déjà utilisée");
        }
        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setUsername(request.getUsername());
        utilisateur.setEmail(request.getEmail());

        String motDePasse = passwordEncoder.encode(request.getMotDePasse());
        utilisateur.setMotDePasse(motDePasse);

        utilisateur.setRoles(request.getRoles());

        if (request.getEmployeId() != null){
            Employe employe = employeRepository
                    .findById(request.getEmployeId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "L'employé est introuvable"
                            )
                    );
            if (utilisateurRepository.findByEmployeId(request.getEmployeId()).isPresent()) {
                throw new ResourceFoundException(
                        "Cet employé possède déjà un compte utilisateur"
                );
            }
            utilisateur.setEmploye(employe);
        }


        Utilisateur saved = utilisateurRepository.save(utilisateur);

        return toConversion(saved);
    }

    @Transactional
    public List<UtilisateurResponse> listerUtilisateur(Boolean actif){
        List<Utilisateur> utilisateurs;
        if (actif == null){
            utilisateurs = utilisateurRepository.findAll();
        }else {
            utilisateurs = utilisateurRepository.findAllByActif(actif);
        }
        return utilisateurs.stream().map(this::toConversion).toList();
    }

    @Transactional
    public UtilisateurResponse rechercherUtilisateur(String username){
        Utilisateur utilisateur =  utilisateurRepository.findByUsername(username).orElseThrow(
                ()-> new ResourceNotFoundException("Le nom d'utilisateur est introuvable")
        );
        return toConversion(utilisateur);
    }

    @Transactional
    public UtilisateurResponse modifierUtilisateur(Long id , UtilisateurRequest request){
        Utilisateur utilisateur =  utilisateurRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("L'utilisateur est introuvable")
        );

        if (utilisateurRepository.existsByUsernameAndIdNot(request.getUsername(), id)){
            throw new ResourceFoundException("Le nom d'utilisateur est déjà utilisé");
        }
        if (utilisateurRepository.existsByEmailAndIdNot(request.getEmail(), id)){
            throw new ResourceFoundException("L'adresse email est déjà utilisée");
        }
        utilisateur.setUsername(request.getUsername());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setRoles(request.getRoles());

        if (request.getEmployeId() != null) {

            Employe employe = employeRepository
                    .findById(request.getEmployeId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("L'employé est introuvable")
                    );

            utilisateurRepository.findByEmployeId(request.getEmployeId())
                    .ifPresent(utilisateurExistant -> {
                        if (!utilisateurExistant.getId().equals(id)) {
                            throw new ResourceFoundException(
                                    "Cet employé possède déjà un compte utilisateur"
                            );
                        }
                    });

            utilisateur.setEmploye(employe);
        }
        if (request.getMotDePasse() != null && !request.getMotDePasse().isBlank()){
            utilisateur.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        }


        Utilisateur saved = utilisateurRepository.save(utilisateur);
        return toConversion(saved);
    }

    @Transactional
    public void supprimerUtilisateur(String username){
        Utilisateur utilisateur =  utilisateurRepository.findByUsername(username).orElseThrow(
                ()-> new ResourceNotFoundException("Le nom d'utilisateur est introuvable")
        );
        utilisateur.setActif(false);
    }
}
