package com.example.jwtauth.DAO;

import com.example.jwtauth.Entity.Formateur;
import com.example.jwtauth.Entity.Formation;
import com.example.jwtauth.Entity.Participant;
import com.example.jwtauth.Entity.Plan_Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormationRepository extends JpaRepository<Formation,Long> {
    Optional<Formation> findByNomFormation(String cin);

    List<Formation>findByParticipantsNom(String nom);


    @Query("SELECT pf FROM Plan_Formation pf WHERE pf.theme.theme = :theme")

    List<Formation> findByTheme(@Param("theme") String theme);

    boolean existsByIdAndParticipantsContaining(Long id, Participant participant);
}
