package com.example.jwtauth.Service;

import com.example.jwtauth.DAO.EvaluationRepository;
import com.example.jwtauth.DAO.FormationRepository;
import com.example.jwtauth.DAO.ParticipantRepository;
import com.example.jwtauth.Entity.Evaluation;
import com.example.jwtauth.Entity.Formation;
import com.example.jwtauth.Entity.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class EvaluationService {
    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    public Evaluation createEvaluation(Long participantId, Long formationId, Evaluation evaluation) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new NoSuchElementException("Participant not found with id " + participantId));
        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new NoSuchElementException("Formation not found with id " + formationId));

        evaluation.setParticipant(participant);
        evaluation.setFormation(formation);

        return evaluationRepository.save(evaluation);
    }
}

