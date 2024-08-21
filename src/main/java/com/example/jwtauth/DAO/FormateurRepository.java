package com.example.jwtauth.DAO;

import com.example.jwtauth.Entity.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormateurRepository extends JpaRepository<Formateur,Long> {
    Optional<Formateur> findByCin(String cin);
    @Query("SELECT f FROM Formateur f JOIN f.themes t WHERE t.theme = :theme")
    List<Formateur> findByTheme(@Param("theme") String theme);

    Optional<Formateur> findByMatricule(String matricule);

    @Query("SELECT f FROM Formateur f JOIN f.themes t WHERE t.id = :themeId")
    List<Formateur> findByThemeId(@Param("themeId") Long themeId);
    @Query("SELECT f FROM Formateur f " +
            "JOIN f.themes t " +
            "WHERE (:themeId IS NULL OR t.id = :themeId) " +
            "AND (:nomFormation IS NULL OR f.nom LIKE %:nomFormation%) " )
    List<Formateur> findByCriteria(
            @Param("themeId") Long themeId,
            @Param("nomFormation") String nomFormation);



    List<Formateur>findByNomContaining(String nom);
}