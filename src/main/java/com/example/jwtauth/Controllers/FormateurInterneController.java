package com.example.jwtauth.Controllers;

import com.example.jwtauth.Entity.FormateurInterne;
import com.example.jwtauth.Service.FormateurInterneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/formateurinterne")
public class FormateurInterneController {

    private final FormateurInterneService formateurInterneService;

    @Autowired
    public FormateurInterneController(FormateurInterneService formateurInterneService) {
        this.formateurInterneService = formateurInterneService;
    }

    @GetMapping
    public ResponseEntity<List<FormateurInterne>> getAllFormateurInternes() {
        List<FormateurInterne> formateurInternes = formateurInterneService.getAllFormateurInternes();
        return ResponseEntity.ok(formateurInternes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormateurInterne> getFormateurInterneById(@PathVariable Long id) {
        Optional<FormateurInterne> formateurInterne = formateurInterneService.getFormateurInterneById(id);
        return formateurInterne.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FormateurInterne> createFormateurInterne(@RequestBody FormateurInterne formateurInterne) {
        FormateurInterne createdFormateurInterne = formateurInterneService.saveFormateurInterne(formateurInterne);
        return ResponseEntity.ok(createdFormateurInterne);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormateurInterne(@PathVariable Long id) {
        formateurInterneService.deleteFormateurInterne(id);
        return ResponseEntity.noContent().build();
    }
}

