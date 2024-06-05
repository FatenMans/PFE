package com.example.jwtauth.Controllers;
import com.example.jwtauth.Entity.Theme;
import com.example.jwtauth.Service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Theme createTheme(@RequestBody Theme theme) {
        return themeService.createTheme(theme);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheme(@PathVariable Long id) {
        themeService.deleteTheme(id);
        return ResponseEntity.noContent().build();
    }
}

