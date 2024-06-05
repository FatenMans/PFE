package com.example.jwtauth.Controllers;

import com.example.jwtauth.Entity.LieuHebergement;
import com.example.jwtauth.Service.LieuHebergementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LieuHebergementController {

    @Autowired
    private LieuHebergementService lieuHebergementService;

    @GetMapping
    public List<LieuHebergement> getAllLieuxHebergement() {
        return lieuHebergementService.getAllLieuxHebergement();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LieuHebergement> getLieuHebergementById(@PathVariable Long id) {
        Optional<LieuHebergement> lieuHebergement = lieuHebergementService.getLieuHebergementById(id);
        return lieuHebergement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public LieuHebergement createLieuHebergement(@RequestBody LieuHebergement lieuHebergement) {
        return lieuHebergementService.saveLieuHebergement(lieuHebergement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLieuHebergement(@PathVariable Long id) {
        lieuHebergementService.deleteLieuHebergement(id);
        return ResponseEntity.noContent().build();
    }
}
