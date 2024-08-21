package com.example.jwtauth.Controllers;

import com.example.jwtauth.Entity.Theme;
import com.example.jwtauth.Service.ThemeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



    @PostMapping("/create")

    public ResponseEntity<?> createTheme(@RequestBody Theme theme) {
        try {
            Theme newTheme = themeService.createTheme(theme);
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


}
