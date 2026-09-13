package com.example.projetSoutenance2026PME.service;

import com.example.projetSoutenance2026PME.entity.Utilisateur;
import com.example.projetSoutenance2026PME.exception.ResourceNotFoundException;
import com.example.projetSoutenance2026PME.repository.UtilisateurRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UtilisateurRepository utilisateurRepository;

    public CustomUserDetailsService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username).orElseThrow(
                ()-> new ResourceNotFoundException(
                        "Utilisateur introuvable : " + username
                )
        );

        Set<GrantedAuthority> authorities = utilisateur.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toSet());

        return User.builder()
                .username(utilisateur.getUsername())
                .password(utilisateur.getMotDePasse())
                .authorities(authorities)
                .disabled(!utilisateur.isActif())
                .build();

    }
}
