package com.example.jwtauth.DAO;

import com.example.jwtauth.Entity.Formateur;
import com.example.jwtauth.Entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormationRepository extends JpaRepository<Formation,Long> {
    List<Formation> findByParticipantsId(Long participantId);
    Optional<Formation> findByNomFormation(String cin);


}
