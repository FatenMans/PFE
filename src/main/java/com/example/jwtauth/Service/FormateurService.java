package com.example.jwtauth.Service;
import com.example.jwtauth.DAO.FormateurRepository;
import com.example.jwtauth.Entity.Formateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormateurService {

    private final FormateurRepository formateurRepository;

    @Autowired
    public FormateurService(FormateurRepository formateurRepository) {
        this.formateurRepository = formateurRepository;
    }

    public List<Formateur> getAllFormateurs() {
        return formateurRepository.findAll();
    }

    public Optional<Formateur> getFormateurById(Long id) {
        return formateurRepository.findById(id);
    }

    public Formateur saveFormateur(Formateur formateur) {
        return formateurRepository.save(formateur);
    }

    public void deleteFormateur(Long id) {
        formateurRepository.deleteById(id);
    }
    public void save(Formateur formateur) {
        // Process and save the Formateur object using the repository
        formateurRepository.save(formateur);
    }
}
