package com.example.jwtauth.Controllers;

import com.example.jwtauth.Entity.Evaluation;
import com.example.jwtauth.Service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluations")
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("/create/{participantId}/{formationId}")
    public ResponseEntity<Evaluation> createEvaluation(
            @RequestParam Long participantId,
            @RequestParam Long formationId,
            @RequestBody Evaluation evaluation) {
        Evaluation createdEvaluation = evaluationService.createEvaluation(participantId, formationId, evaluation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvaluation);
    }
}
