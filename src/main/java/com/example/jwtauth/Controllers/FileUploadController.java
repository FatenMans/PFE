package com.example.jwtauth.Controllers;

import com.example.jwtauth.DAO.FormateurRepository;
import com.example.jwtauth.Entity.Formateur;
import com.example.jwtauth.Service.FileUploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/formateurs")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private FormateurRepository formateurRepository;

    @PostMapping("/{id}/uploadCv")
    public ResponseEntity<String> uploadCv(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            // Sauvegarder le fichier
            String fileName = fileUploadService.saveFile(file);

            // Mettre à jour l'entité Formateur avec le nom du fichier
            Formateur formateur = formateurRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Formateur non trouvé avec id " + id));
            formateur.setCvFileName(fileName);
            formateurRepository.save(formateur);

            return ResponseEntity.ok("Fichier téléchargé avec succès: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec du téléchargement du fichier: " + e.getMessage());
        }
    }
}
