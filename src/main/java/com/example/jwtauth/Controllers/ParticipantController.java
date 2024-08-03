package com.example.jwtauth.Controllers;

import com.example.jwtauth.DAO.LieuHebergementRepository;
import com.example.jwtauth.DAO.ParticipantRepository;
import com.example.jwtauth.Entity.Formation;
import com.example.jwtauth.Entity.LieuHebergement;
import com.example.jwtauth.Entity.Participant;
import com.example.jwtauth.Service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService participantService;
    @Autowired
    private LieuHebergementRepository lieuHebergementRepository;
    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Participant>> getAllParticipants() {
        List<Participant> participants = participantService.getAllParticipants();
        return new ResponseEntity<>(participants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable Long id) {
        Optional<Participant> participant = participantService.getParticipantById(id);
        return participant.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    //deLETE/ lieu from add lieu to participant
    @PostMapping("/{lieuHebergementId}")
    public ResponseEntity<Participant> createparticipant(@PathVariable Long lieuHebergementId, @RequestBody Participant participant) {

        Participant createdparticipant = participantService.createParticipant(participant,lieuHebergementId);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdparticipant);
    }

    @PutMapping("/{id}/internal-trainer")
    public ResponseEntity<String> becomeInternalTrainer(@PathVariable Long id) {
        try {
            participantService.becomeInternalTrainer(id);
            return ResponseEntity.ok("Participant updated to internal trainer.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participant> updateParticipant(
            @PathVariable Long id,
            @RequestBody Participant participant,
            @RequestParam Long lieuHebergementId,
            @RequestHeader("X-User-Id") String userId
    ) {
        participant.setLastModifiedBy(userId);
        Participant updatedParticipant = participantService.updateParticipant(id, participant, lieuHebergementId);
        return updatedParticipant != null ? ResponseEntity.ok(updatedParticipant) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @GetMapping("/profile")
    public ResponseEntity<Participant> getParticipantProfile(@RequestParam String matricule) {
        Optional<Participant> participant = participantService.getParticipantByMatricule(matricule);
        return participant.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{participantId}/lieu-hebergement/{lieuHebergementId}")
    public ResponseEntity<Participant> addLieuHebergementToParticipant(@PathVariable Long participantId, @PathVariable Long lieuHebergementId) {
        try {
            Participant participant = participantService.addLieuHebergementToParticipant(participantId, lieuHebergementId);
            return ResponseEntity.ok(participant);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
