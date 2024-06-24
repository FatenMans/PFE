package com.example.jwtauth.Service;



import com.example.jwtauth.DAO.FormateurInterneRepository;
import com.example.jwtauth.Entity.FormateurInterne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormateurInterneService {

    private final FormateurInterneRepository formateurInterneRepository;

    @Autowired
    public FormateurInterneService(FormateurInterneRepository formateurInterneRepository) {
        this.formateurInterneRepository = formateurInterneRepository;
    }

    public List<FormateurInterne> getAllFormateurInternes() {
        return formateurInterneRepository.findAll();
    }

    public Optional<FormateurInterne> getFormateurInterneById(Long id) {
        return formateurInterneRepository.findById(id);
    }

    public FormateurInterne saveFormateurInterne(FormateurInterne formateurInterne) {
        return formateurInterneRepository.save(formateurInterne);
    }

    public void deleteFormateurInterne(Long id) {
        formateurInterneRepository.deleteById(id);
    }
}
