package com.example.jwtauth.Controllers;

import com.example.jwtauth.Entity.CabinetFormation;
import com.example.jwtauth.Entity.Formation;
import com.example.jwtauth.Service.CabinetFormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cabinetformations")
public class CabinetFormationController {

    @Autowired
    private CabinetFormationService cabinetFormationService;

    @GetMapping("/all")
    public List<CabinetFormation> getAllCabinetFormations() {
        return cabinetFormationService.getAllCabinetFormations();
    }

    @GetMapping("/{id}")
    public CabinetFormation getCabinetFormationById(@PathVariable Long id) {
        return cabinetFormationService.getCabinetFormationById(id);
    }

    @PostMapping("/create/{idLieu}")
    public CabinetFormation createCabinetFormation(@RequestBody CabinetFormation cabinetFormation, @PathVariable Long idLieu) {
        return cabinetFormationService.createCabinetFormation(cabinetFormation, idLieu);
    }

    @PutMapping("/{id}")
    public CabinetFormation updateCabinetFormation(@PathVariable Long id, @RequestBody CabinetFormation updatedCabinetFormation) {
        return cabinetFormationService.updateCabinetFormation(id, updatedCabinetFormation);
    }
    @PostMapping("/{cabinetId}/formateurs/{formateurId}")
    public ResponseEntity<Void> addFormateurToCabinet(@PathVariable Long cabinetId, @PathVariable Long formateurId) {
        cabinetFormationService.addFormateurToCabinet(cabinetId, formateurId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void deleteCabinetFormation(@PathVariable Long id) {
        cabinetFormationService.deleteCabinetFormation(id);
    }
}

