package com.example.projetSoutenance2026PME.repository;

import com.example.projetSoutenance2026PME.entity.MouvementStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MouvementStockRepository extends JpaRepository<MouvementStock,Long> {

    @Query(""" 
               SELECT ms
               FROM MouvementStock ms
               JOIN FETCH ms.produit p
               JOIN FETCH p.categorie
               """)

    public List<MouvementStock> findAllWithProduit();

    @Query(""" 
               SELECT ms
               FROM MouvementStock ms
               JOIN FETCH ms.produit p
               JOIN FETCH p.categorie
               WHERE p.id = :produitId
               ORDER BY ms.dateMouvement DESC
               
               """)

    public List<MouvementStock> findAllByProduit(
            @Param("produitId")Long produitId
    );
}
