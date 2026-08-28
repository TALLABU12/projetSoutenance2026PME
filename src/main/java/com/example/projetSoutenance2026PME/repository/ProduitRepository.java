package com.example.projetSoutenance2026PME.repository;

import com.example.projetSoutenance2026PME.entity.MouvementStock;
import com.example.projetSoutenance2026PME.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
   public boolean  existsByReference(String reference);

   @Query("SELECT p  FROM Produit p JOIN FETCH p.categorie")
   public List<Produit> findAllWithCategorie();

}
