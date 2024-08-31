package com.example.jwtauth.Controllers;


import com.example.jwtauth.Entity.Formation;
import com.example.jwtauth.Entity.Participant;
import com.example.jwtauth.Service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/formations")
public class FormationController {

    @Autowired
    private FormationService formationService;


    @GetMapping("/")
    public List<Formation> getAllFormations() {
        return formationService.getAllFormations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formation> getFormationById(@PathVariable Long id) {
        Optional<Formation> formation = formationService.getFormationById(id);
        return formation.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/{formateurId}/{themeId}")
    public ResponseEntity<Formation> createFormation(@RequestBody Formation formation, @PathVariable Long formateurId,@PathVariable Long themeId){

        Formation createdFormation = formationService.createFormation(formation,formateurId,themeId);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdFormation);
    }

    @PutMapping("/{id}/{idFormateur}/{idTheme}")
    public ResponseEntity<Formation> updateFormation(@PathVariable Long id, @RequestBody Formation updatedFormation,@PathVariable Long idFormateur,@PathVariable Long idTheme) {
        Formation formation = formationService.updateFormation(id, updatedFormation,idFormateur,idTheme);
        if (formation != null) {
            return ResponseEntity.ok().body(formation);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormation(@PathVariable Long id) {
        formationService.deleteFormation(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{formationId}/participants/{participantnom}")
    public ResponseEntity<Void> addParticipantToFormation(@PathVariable Long formationId, @PathVariable String participantnom) {
        formationService.addParticipantToFormation(formationId, participantnom);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{formationId}/lieux/{lieuId}")
    public ResponseEntity<String> addLieuToFormation(@PathVariable Long formationId, @PathVariable Long lieuId) {
        formationService.addLieuToFormation(formationId, lieuId);
        return ResponseEntity.ok("Lieu added to formation successfully.");
    }


    @GetMapping("/by-participant/{nom}")
    public ResponseEntity<List<Formation>> getFormationsByParticipant(@PathVariable String nom) {
        List<Formation> formations = formationService.getFormationsByParticipantId(nom);
        return ResponseEntity.ok(formations);
    }
    @PostMapping("/{formationId}/invite/{participantId}")
    public ResponseEntity<Formation> inviteParticipant(@PathVariable Long formationId, @PathVariable Long participantId) {
        Formation formation = formationService.inviteParticipant(formationId, participantId);
        return ResponseEntity.ok(formation);
    }

    @GetMapping("/{formationId}/participants")
    public ResponseEntity<Set<Participant>> getFormationParticipants(@PathVariable Long formationId) {
        Set<Participant> participants = formationService.getFormationParticipants(formationId);
        return ResponseEntity.ok(participants);
    }
    @GetMapping("/search/{theme}")
    public List<Formation> findByTheme(@PathVariable("theme") String theme) {
        return formationService.findByTheme(theme);
    }
}