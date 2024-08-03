package com.example.jwtauth.Service;
import com.example.jwtauth.DAO.ParticipantRepository;
import com.example.jwtauth.DAO.ThemeRepository;
import com.example.jwtauth.Entity.Formateur;
import com.example.jwtauth.Entity.Formation;
import com.example.jwtauth.Entity.Participant;
import com.example.jwtauth.Entity.Theme;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private ParticipantRepository participantRepository;

    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    public Optional<Theme> getThemeById(Long id) {
        return themeRepository.findById(id);
    }
    public Optional<Theme> findByCodeTheme(String codeTheme) {
        return themeRepository.findByCodeTheme(codeTheme);
    }

    public Theme saveTheme(Theme theme) {
        Optional<Theme> existingTheme = themeRepository.findByCodeTheme(theme.getCodeTheme());
        if (existingTheme.isPresent() && !existingTheme.get().getId().equals(theme.getId())) {
            throw new IllegalArgumentException("Un thème avec ce code existe déjà.");
        }
        return themeRepository.save(theme);
    }
    public Theme createTheme(Theme theme) {
        return themeRepository.save(theme);
    }



    public void deleteTheme(Long id) {
        Theme theme = themeRepository.findById(id).orElseThrow(() -> new RuntimeException("Theme not found"));
        themeRepository.delete(theme);
    }

    public Theme updateTheme(Long id, Theme theme) {
        Theme existingTheme = themeRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        existingTheme.setTheme(theme.getTheme());
        existingTheme.setCodeTheme(theme.getCodeTheme());
        existingTheme.setTypeThemeIntraInter(theme.getTypeThemeIntraInter());
        existingTheme.setAccord(theme.getAccord());
        existingTheme.setDocuments(theme.getDocuments());
        existingTheme.setCodeFormatExterne(theme.getCodeFormatExterne());
        existingTheme.setCodeFormateurInterne(theme.getCodeFormateurInterne());
        existingTheme.setFraisFormateurInterne(theme.getFraisFormateurInterne());
        existingTheme.setCreatedBy(theme.getCreatedBy());
        existingTheme.setCreationDate(theme.getCreationDate());
        existingTheme.setLastModifiedBy(theme.getLastModifiedBy());
        existingTheme.setLastModifiedDate(theme.getLastModifiedDate());

        return themeRepository.save(existingTheme);
    }

    public void uploadDocument(Long themeId, String document) {
        Optional<Theme> theme = themeRepository.findById(themeId);
        if (theme.isPresent()) {
            Theme t = theme.get();
            t.setDocuments(document);
            themeRepository.save(t);
        }
    }

}
