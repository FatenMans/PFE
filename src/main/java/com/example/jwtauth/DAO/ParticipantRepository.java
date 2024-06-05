package com.example.jwtauth.DAO;

import com.example.jwtauth.Entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant,Long> {
    Optional<Participant> findByMatricule(int matricule);
}
