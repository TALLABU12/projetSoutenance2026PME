package com.example.projetSoutenance2026PME.security;

import com.example.projetSoutenance2026PME.entity.Utilisateur;
import com.example.projetSoutenance2026PME.enumeration.Role;
import com.example.projetSoutenance2026PME.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.util.Set;


@Component
public class AdminInitializer implements CommandLineRunner{
    @Value("${app.admin.username}")
    private String username;
    @Value("${app.admin.password}")
    private String password;
    @Value("${app.admin.email}")
    private String email;

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args){
        if (utilisateurRepository.count() > 0){
            return;
        }
        Utilisateur admin = new Utilisateur();
        admin.setUsername(username);

        String motDePasse = passwordEncoder.encode(password);
        admin.setMotDePasse(motDePasse);
        admin.setEmail(email);
        admin.setRoles(Set.of(Role.ROLE_ADMIN));

        utilisateurRepository.save(admin);

        System.out.println("======================================");
        System.out.println("ADMIN INITIAL CREE AVEC SUCCES");
        System.out.println("Username : " + username);
        System.out.println("Role     : ROLE_ADMIN");
        System.out.println("======================================");
    }
}
