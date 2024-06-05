package com.example.jwtauth.Controllers;

import com.example.jwtauth.Entity.CabinetFormation;
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

    @PostMapping("/create")
    public CabinetFormation createCabinetFormation(@RequestBody CabinetFormation cabinetFormation) {
        return cabinetFormationService.createCabinetFormation(cabinetFormation);
    }

    @PutMapping("/{id}")
    public CabinetFormation updateCabinetFormation(@PathVariable Long id, @RequestBody CabinetFormation updatedCabinetFormation) {
        return cabinetFormationService.updateCabinetFormation(id, updatedCabinetFormation);
    }
    @PostMapping("/{cabinetId}/formateurs/{formateurId}")
    public ResponseEntity<Void> addFormateurToCabinet(@PathVariable Long cabinetId, @PathVariable Long formateurId) {
        cabinetFormationService.addFormateurToCabinet(cabinetId, formateurId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCabinetFormation(@PathVariable Long id) {
        cabinetFormationService.deleteCabinetFormation(id);
    }
}

