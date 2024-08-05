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

    public Theme createTheme(Theme theme) {
        return themeRepository.save(theme);
    }

    public void deleteTheme(Long id) {
        themeRepository.deleteById(id);
    }

    public Theme updateTheme(Long id, Theme theme) {
        if (themeRepository.existsById(id)) {
            theme.setId(id);
            return themeRepository.save(theme);
        }
        return null;
    }


}
