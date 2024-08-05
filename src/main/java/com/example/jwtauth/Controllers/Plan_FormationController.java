package com.example.jwtauth.Controllers;

import com.example.jwtauth.Entity.Plan_Formation;
import com.example.jwtauth.Service.Plan_FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/planformations")
public class Plan_FormationController {

    @Autowired
    private Plan_FormationService planFormationService;

    @GetMapping
    public List<Plan_Formation> getAllPlanFormations() {
        return planFormationService.getAllPlanFormations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan_Formation> getPlanFormationById(@PathVariable Long id) {
        Optional<Plan_Formation> planFormation = planFormationService.getPlanFormationById(id);
        if (planFormation.isPresent()) {
            return ResponseEntity.ok(planFormation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Plan_Formation> createPlanFormation(@RequestBody Plan_Formation planFormation,
                                                              @RequestParam Long themeId,
                                                              @RequestParam List<Long> lieuId) {
        try {
            Plan_Formation createdPlanFormation = planFormationService.createPlanFormation(planFormation, themeId, lieuId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPlanFormation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plan_Formation> updatePlanFormation(@PathVariable Long id, @RequestBody Plan_Formation planFormation) {
        Optional<Plan_Formation> updatedPlanFormation = planFormationService.updatePlanFormation(id, planFormation);
        if (updatedPlanFormation.isPresent()) {
            return ResponseEntity.ok(updatedPlanFormation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanFormation(@PathVariable Long id) {
        try {
            planFormationService.deletePlanFormation(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{formationId}/participants/{participantId}")
    public ResponseEntity<Plan_Formation> addParticipantToFormation(@PathVariable Long formationId, @PathVariable Long participantId) {
        Optional<Plan_Formation> updatedPlanFormation = planFormationService.addParticipantToFormation(formationId, participantId);
        if (updatedPlanFormation.isPresent()) {
            return ResponseEntity.ok(updatedPlanFormation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
