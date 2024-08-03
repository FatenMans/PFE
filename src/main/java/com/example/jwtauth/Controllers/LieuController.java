package com.example.jwtauth.Controllers;

import com.example.jwtauth.Entity.Lieu;
import com.example.jwtauth.Service.LieuService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lieux")
public class LieuController {

    @Autowired
    private LieuService lieuService;

    @GetMapping("/all")
    public List<Lieu> getAllLieux() {
        return lieuService.getAllLieux();
    }

    @GetMapping("/{id}")
    public Lieu getLieuById(@PathVariable Long id) {
        return lieuService.getLieuById(id);
    }

    @PostMapping("/create")
    @Operation(summary = "Ajouter un lieu")
    public ResponseEntity<?> createLieu(@RequestBody Lieu lieu) {
        try {
            Lieu newLieu = lieuService.saveLieu(lieu);
            return new ResponseEntity<>(newLieu, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public Lieu updateLieu(@PathVariable Long id, @RequestBody Lieu updatedLieu) {
        return lieuService.updateLieu(id, updatedLieu);
    }

    @DeleteMapping("/{id}")
    public void deleteLieu(@PathVariable Long id) {
        lieuService.deleteLieu(id);
    }
}
