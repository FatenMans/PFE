package com.example.jwtauth.Controllers;


import com.example.jwtauth.Entity.Plan_Formation;

import com.example.jwtauth.Service.Plan_FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plan-formations")
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
        return planFormation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Plan_Formation createPlanFormation(@RequestBody Plan_Formation planFormation) {
        return planFormationService.createPlanFormation(planFormation);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Plan_Formation> updatePlanFormation(@PathVariable Long id, @RequestBody Plan_Formation planFormationDetails) {
        Plan_Formation updatedPlanFormation = planFormationService.updatePlanFormation(id, planFormationDetails);
        if (updatedPlanFormation != null) {
            return ResponseEntity.ok(updatedPlanFormation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanFormation(@PathVariable Long id) {
        planFormationService.deletePlanFormation(id);
        return ResponseEntity.noContent().build();
    }
}

