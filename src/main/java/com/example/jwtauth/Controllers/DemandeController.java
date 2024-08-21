package com.example.jwtauth.Controllers;

import com.example.jwtauth.Entity.Demande;
import com.example.jwtauth.Entity.DemandeRepository;
import com.example.jwtauth.Entity.Participant;
import com.example.jwtauth.Service.DemandeService;
import com.example.jwtauth.Service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/demandes")
public class DemandeController {
    @Autowired
    private DemandeService demandeService;
@Autowired
 private ParticipantService participantService;
@Autowired
private DemandeRepository demandeRepository;




    @PostMapping("/create/{themeId}")
    public ResponseEntity<Demande> createDemande(@RequestBody Demande demande, @RequestParam String nom,@PathVariable Long themeId) {
        return ResponseEntity.ok(demandeService.createDemande(demande, nom,themeId));
    }
    @PostMapping("/create")
    public ResponseEntity<Demande> createDemande(@RequestBody Demande demande, @RequestParam String nom) {
        return ResponseEntity.ok(demandeService.createDemande(demande, nom));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Demande>> getAllDemandes() {
        return ResponseEntity.ok(demandeService.getAllDemandes());
    }

    @PostMapping("/accept/{id}")
    public ResponseEntity<Void> acceptDemande(@PathVariable Long id) {
        demandeService.acceptDemande(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/refuser/{id}")
    public ResponseEntity<Void> refuserDemande(@PathVariable Long id) {
        demandeService.refuserDemande(id);
        return ResponseEntity.ok().build();
    }
}