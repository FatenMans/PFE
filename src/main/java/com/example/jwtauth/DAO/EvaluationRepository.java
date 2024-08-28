package com.example.jwtauth.DAO;

import com.example.jwtauth.Entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    List<Evaluation> findByFormationId(Long formationId);
}
