package com.example.projetSoutenance2026PME.repository;

import com.example.projetSoutenance2026PME.entity.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement,Long> {
    public boolean existsByNom(String nom);
    public boolean existsByNomAndIdNot(String nom,Long id);

}
