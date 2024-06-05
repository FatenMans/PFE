package com.example.jwtauth.Controllers;
import com.example.jwtauth.Entity.Participant;
import com.example.jwtauth.Service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService participantService;

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


    @PostMapping("/")
    public ResponseEntity<Participant> createParticipant(@RequestBody Participant participant) {
        Participant createdParticipant = participantService.createParticipant(participant);
        return new ResponseEntity<>(createdParticipant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participant> updateParticipant(@PathVariable Long id, @RequestBody Participant participant) {
        Participant updatedParticipant = participantService.updateParticipant(id, participant);
        if (updatedParticipant != null) {
            return new ResponseEntity<>(updatedParticipant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/profile")
    public ResponseEntity<Participant> getParticipantProfile(@RequestParam int matricule) {
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

