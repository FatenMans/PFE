package com.example.jwtauth.Controllers;

import com.example.jwtauth.Entity.Evaluation;
import com.example.jwtauth.Service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("/create/{participantNom}/{formationId}")
    public ResponseEntity<?> createEvaluation(
            @PathVariable String participantNom,
            @PathVariable Long formationId,
            @RequestBody Evaluation evaluation
    ) {
        try {
            evaluationService.createEvaluation(participantNom, formationId, evaluation);
            return ResponseEntity.ok("Evaluation submitted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error submitting evaluation");
        }
    }


    @DeleteMapping("/delete/{id}")
    public void deleteEvaluation(@PathVariable Long id) {
            evaluationService.deleteEvaluation(id);

    }


    @GetMapping
    public List<Evaluation> getAllEvaluations() {
        return evaluationService.getAllEvaluations();
    }
}
