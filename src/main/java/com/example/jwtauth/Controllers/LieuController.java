package com.example.jwtauth.Controllers;

import com.example.jwtauth.Entity.Lieu;
import com.example.jwtauth.Service.LieuService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Lieu createLieu(@RequestBody Lieu lieu) {
        return lieuService.createLieu(lieu);
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
