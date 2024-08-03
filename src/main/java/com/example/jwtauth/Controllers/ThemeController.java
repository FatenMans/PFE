package com.example.jwtauth.Controllers;
import com.example.jwtauth.Entity.Theme;
import com.example.jwtauth.Service.ThemeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/themes")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @GetMapping
    public List<Theme> getAllThemes() {
        return themeService.getAllThemes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theme> getThemeById(@PathVariable Long id) {
        Optional<Theme> theme = themeService.getThemeById(id);
        return theme.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/with-document")
    public ResponseEntity<Theme> createThemeWithDocument(@RequestParam("file") MultipartFile file,
                                                         @RequestParam("themeData") String themeData) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Theme theme = objectMapper.readValue(themeData, Theme.class);

            // Enregistrez le fichier PDF dans un répertoire spécifique ou traitez-le selon vos besoins
            // Par exemple, vous pouvez le sauvegarder sur le disque ou le stocker en base64 dans la base de données

            // Exemple de sauvegarde du fichier sur le disque
            String filePath = "path/to/save/directory/" + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            theme.setDocuments(filePath); // Enregistrez le chemin du fichier dans votre entité Theme

            Theme createdTheme = themeService.createTheme(theme);
            return ResponseEntity.ok(createdTheme);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/create")
    @Operation(summary = "Ajouter un thème")
    public ResponseEntity<?> createTheme(@RequestBody Theme theme) {
        try {
            Theme newTheme = themeService.saveTheme(theme);
            return new ResponseEntity<>(newTheme, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheme(@PathVariable Long id) {
        themeService.deleteTheme(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Theme> updateTheme(@PathVariable Long id, @RequestBody Theme theme) {
        Theme updatedTheme = themeService.updateTheme(id, theme);
        return updatedTheme != null ? ResponseEntity.ok(updatedTheme) : ResponseEntity.notFound().build();
    }
    @GetMapping("/document/{filename:.+}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String filename) {
        try {
            File file = new File("path/to/pdf/directory/" + filename);
            FileInputStream fis = new FileInputStream(file);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);
            return new ResponseEntity<>(new InputStreamResource(fis), headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

