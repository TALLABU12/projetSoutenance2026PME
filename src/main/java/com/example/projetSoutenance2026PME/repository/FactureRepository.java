package com.example.projetSoutenance2026PME.repository;

import com.example.projetSoutenance2026PME.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FactureRepository extends JpaRepository<Facture, Long> {

    @Query("""
            SELECT DISTINCT fac
            FROM Facture fac
            JOIN FETCH fac.ligneFacture lf
            JOIN FETCH lf.produit
            JOIN FETCH fac.client
            """)
    List<Facture> findAllWithClients();
}