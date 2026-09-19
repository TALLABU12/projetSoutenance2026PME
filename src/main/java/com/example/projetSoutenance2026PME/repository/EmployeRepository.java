package com.example.projetSoutenance2026PME.repository;

import com.example.projetSoutenance2026PME.entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe,Long> {
    public boolean existsByMatriculeAndIdNot(String matricule,Long id);
    public boolean existsByMailAndIdNot(String mail,Long id);

    public boolean existsByMatricule(String matricule);
    public boolean existsByMail(String mail);
    @Query("""
    SELECT e
    FROM Employe e
    JOIN FETCH e.departement
    WHERE e.actif = :actif
""")
    List<Employe> findAllByActifWithDepartement(@Param("actif") boolean actif);
    @Query("SELECT e FROM Employe e JOIN FETCH e.departement")
    public List<Employe> findAllWithDepartement();
}
