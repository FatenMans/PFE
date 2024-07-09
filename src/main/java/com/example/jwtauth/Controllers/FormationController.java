package com.example.jwtauth.Controllers;


import com.example.jwtauth.DAO.FormationRepository;
import com.example.jwtauth.DAO.ThemeRepository;
import com.example.jwtauth.Entity.Formation;
import com.example.jwtauth.Entity.Theme;
import com.example.jwtauth.Service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @PostMapping("/formations/{formationId}/participants/{participantId}")
    public ResponseEntity<String> addParticipantToFormation(@PathVariable Long formationId, @PathVariable Long participantId) {
        formationService.addParticipantToFormation(formationId, participantId);
        return ResponseEntity.ok("Participant added to formation successfully.");
    }
    @PostMapping("/{formationId}/lieux/{lieuId}")
    public ResponseEntity<String> addLieuToFormation(@PathVariable Long formationId, @PathVariable Long lieuId) {
        formationService.addLieuToFormation(formationId, lieuId);
        return ResponseEntity.ok("Lieu added to formation successfully.");
    }

    @GetMapping("/participant")
    public List<Formation> getFormationsByParticipant(@RequestParam Long participantId) {
        // Récupérer les formations par participant
        return formationService.getFormationsByParticipant(participantId);
    }

}

