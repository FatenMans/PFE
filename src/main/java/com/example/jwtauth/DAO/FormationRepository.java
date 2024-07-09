package com.example.jwtauth.DAO;

import com.example.jwtauth.Entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormationRepository extends JpaRepository<Formation,Long> {
    List<Formation> findByParticipantsId(Long participantId);

}
