package com.example.jwtauth.Service;

import com.example.jwtauth.DAO.CabinetFormationRepository;
import com.example.jwtauth.DAO.FormateurRepository;
import com.example.jwtauth.Entity.CabinetFormation;
import com.example.jwtauth.Entity.Formateur;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CabinetFormationService {

    @Autowired
    private CabinetFormationRepository cabinetFormationRepository;
    @Autowired
    private FormateurRepository formateurRepository;

    public List<CabinetFormation> getAllCabinetFormations() {
        return cabinetFormationRepository.findAll();
    }

    public CabinetFormation getCabinetFormationById(Long id) {
        return cabinetFormationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CabinetFormation not found with id: " + id));
    }

    public CabinetFormation createCabinetFormation(CabinetFormation cabinetFormation) {
        return cabinetFormationRepository.save(cabinetFormation);
    }

    public CabinetFormation updateCabinetFormation(Long id, CabinetFormation updatedCabinetFormation) {
        CabinetFormation existingCabinetFormation = getCabinetFormationById(id);
        existingCabinetFormation.setAdress(updatedCabinetFormation.getAdress());
        existingCabinetFormation.setContact(updatedCabinetFormation.getContact());
        existingCabinetFormation.setTel(updatedCabinetFormation.getTel());
        existingCabinetFormation.setEmail(updatedCabinetFormation.getEmail());
        existingCabinetFormation.setLieu(updatedCabinetFormation.getLieu());

        // Set other fields as needed
        return cabinetFormationRepository.save(existingCabinetFormation);
    }

    public void deleteCabinetFormation(Long id) {
        CabinetFormation cabinetFormation = getCabinetFormationById(id);
        cabinetFormationRepository.delete(cabinetFormation);
    }

    public void addFormateurToCabinet(Long cabinetId, Long formateurId) {
        Optional<CabinetFormation> cabinetOpt = cabinetFormationRepository.findById(cabinetId);
        Optional<Formateur> formateurOpt = formateurRepository.findById(formateurId);

        if (cabinetOpt.isPresent() && formateurOpt.isPresent()) {
            CabinetFormation cabinet = cabinetOpt.get();
            Formateur formateur = formateurOpt.get();

            formateur.setCabinetFormation(cabinet);
            formateurRepository.save(formateur);
        }
    }
}


