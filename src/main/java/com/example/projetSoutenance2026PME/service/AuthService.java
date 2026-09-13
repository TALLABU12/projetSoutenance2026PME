package com.example.projetSoutenance2026PME.service;

import com.example.projetSoutenance2026PME.dto.security.LoginRequest;
import com.example.projetSoutenance2026PME.dto.security.LoginResponse;
import com.example.projetSoutenance2026PME.entity.Utilisateur;
import com.example.projetSoutenance2026PME.repository.UtilisateurRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UtilisateurRepository utilisateurRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.utilisateurRepository = utilisateurRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

//    public Utilisateur authentifier(LoginRequest request){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getMotDePasse())
//        );
//
//        return utilisateurRepository.findByUsername(authentication.getName()).orElseThrow();
//    }

    public LoginResponse authentifier(LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getMotDePasse())
        );

         Utilisateur utilisateur = utilisateurRepository.findByUsername(authentication.getName()).orElseThrow();

         String token = jwtService.genererToken(utilisateur.getUsername());

         return new LoginResponse(
                 token,
                 utilisateur.getUsername(),
                 utilisateur.getRoles()
         );
    }
}
