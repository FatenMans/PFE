package com.example.jwtauth.Service;
import com.example.jwtauth.DAO.ParticipantRepository;
import com.example.jwtauth.DAO.ThemeRepository;
import com.example.jwtauth.Entity.Formateur;
import com.example.jwtauth.Entity.Participant;
import com.example.jwtauth.Entity.Theme;
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
        Theme theme = themeRepository.findById(id).orElseThrow(() -> new RuntimeException("Theme not found"));
        themeRepository.delete(theme);
    }

}
