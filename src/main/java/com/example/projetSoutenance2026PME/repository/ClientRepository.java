package com.example.projetSoutenance2026PME.repository;

import com.example.projetSoutenance2026PME.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {
    public Optional<Client> findByCode(String code);
    public boolean existsByCode(String code);
}
