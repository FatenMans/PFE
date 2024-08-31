package com.example.jwtauth.Service;

import com.example.jwtauth.DAO.EvaluationRepository;
import com.example.jwtauth.DAO.FormationRepository;
import com.example.jwtauth.DAO.ParticipantRepository;
import com.example.jwtauth.Entity.Evaluation;
import com.example.jwtauth.Entity.Formation;
import com.example.jwtauth.Entity.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.event.PaintEvent;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EvaluationService {
    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    public void createEvaluation(Long participantId, Long formationId, Evaluation evaluation) {
        // Set participantId and formationId in the evaluation entity if necessary
        // For example:
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new NoSuchElementException("Participant not found"));
        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new NoSuchElementException("Formation not found"));

         evaluation.setParticipant(participant);
         evaluation.setFormation(formation);

        evaluationRepository.save(evaluation);
    }



    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }


    public void deleteEvaluation(Long id) {
        evaluationRepository.deleteById(id);
    }
}

