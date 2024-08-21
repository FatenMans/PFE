package com.example.jwtauth.Controllers;
import com.example.jwtauth.Entity.Formateur;
import com.example.jwtauth.Entity.Theme;
import com.example.jwtauth.Service.EmailService;
import com.example.jwtauth.Service.FormateurService;
import com.example.jwtauth.Service.ThemeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/formateurs")
public class FormateurController {

    private final FormateurService formateurService;

    @Autowired
    private EmailService emailService;
    @Autowired
    private ThemeService themeService;


    @Autowired
    public FormateurController(FormateurService formateurService) {
        this.formateurService = formateurService;
    }

    @GetMapping("/all/{theme}")
    public ResponseEntity<List<Formateur>> getAllFormateurs(@RequestParam(value = "theme", required = false) String theme) {
        List<Formateur> formateurs;
        if (theme != null) {
            formateurs = formateurService.getAllFormateursByTheme(theme);
        } else {
            formateurs = formateurService.getAllFormateurs();
        }
        return new ResponseEntity<>(formateurs, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Formateur> getFormateurById(@PathVariable("id") Long id) {
        Optional<Formateur> formateur = formateurService.getFormateurById(id);
        return formateur.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create/{themeId}")
    @Operation(summary = "Ajouter un formateur avec un thème")
    public ResponseEntity<?> createFormateur(
            @RequestBody Formateur formateur,
            @PathVariable Long themeId) {
        try {
            // Vérification du duplicata du CIN
            Optional<Formateur> existingFormateur = formateurService.findByCin(formateur.getCin());
            if (existingFormateur.isPresent()) {
                return new ResponseEntity<>("Un formateur avec ce CIN existe déjà.", HttpStatus.BAD_REQUEST);
            }

            // Enregistrement du formateur avec le thème associé
            Formateur newFormateur = formateurService.createFormateurWithTheme(formateur, themeId);
            return new ResponseEntity<>(newFormateur, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    //  @PostMapping("/upload")
   // public ResponseEntity<String> handleFileUpload(@ModelAttribute Formateur formateur, @RequestParam("cvFile") MultipartFile file) {
     //   try {
       //     formateur.setCvFile(file); // Set the uploaded file to the Formateur object
         //   formateurService.save(formateur); // Save the Formateur object with the file information
           // return ResponseEntity.ok("File uploaded successfully.");
        //} catch (Exception e) {
          //  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        //}
    //}

    @PutMapping("/update/{id}")
    public ResponseEntity<Formateur> updateFormateur(
            @PathVariable Long id,
            @RequestBody Formateur formateur,

            @RequestParam(value = "activate", required = false) boolean activate
    ) {

        Formateur updatedFormateur = formateurService.updateFormateur(id, formateur, activate);
        return updatedFormateur != null ? ResponseEntity.ok(updatedFormateur) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormateur(@PathVariable("id") Long id) {
        formateurService.deleteFormateur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/{id}/ajouterThemes")
    public ResponseEntity<Formateur> ajouterThemes(
            @PathVariable Long id, @RequestBody List<Long> themeIds) {
        Formateur formateur = formateurService.ajouterThemesAuFormateur(id, themeIds);
        return ResponseEntity.ok(formateur);
    }
    @GetMapping("/search/{nom}")
    public ResponseEntity<List<Formateur>> searchFormateurs(
            @PathVariable(required = false) String nom)
            {
        List<Formateur> formateurs = formateurService.findByNomContaining(nom);
        return new ResponseEntity<>(formateurs, HttpStatus.OK);
    }
}

