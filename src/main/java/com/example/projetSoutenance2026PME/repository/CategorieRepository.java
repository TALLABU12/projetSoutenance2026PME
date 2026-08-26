package com.example.projetSoutenance2026PME.repository;

import com.example.projetSoutenance2026PME.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {
    public boolean  existsByNom(String nom);

}
