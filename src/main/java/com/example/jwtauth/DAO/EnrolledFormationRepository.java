package com.example.jwtauth.DAO;

import com.example.jwtauth.Entity.EnrolledFormation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EnrolledFormationRepository extends JpaRepository<EnrolledFormation, Long> {
    List<EnrolledFormation> findByParticipantId(Long participantId);
}

