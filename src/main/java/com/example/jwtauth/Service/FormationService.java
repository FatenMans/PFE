package com.example.jwtauth.Service;

import com.example.jwtauth.DAO.*;
import com.example.jwtauth.Entity.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormationService {

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private FormateurRepository formateurRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private LieuRepository lieuRepository;

    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }

    public Optional<Formation> getFormationById(Long id) {
        return formationRepository.findById(id);
    }
    public Optional<Formation> findByNomFormation(String NomFormation) {
        return formationRepository.findByNomFormation(NomFormation);
    }

    public Formation createFormation(Formation formation, Long formateurId, Long themeId) {
        Optional<Formation> existingFormation = formationRepository.findByNomFormation(formation.getNomFormation());
        if (existingFormation.isPresent()) {
            throw new IllegalArgumentException("Une formation avec ce nom existe déjà.");
        }

        Theme theme = themeRepository.findById(themeId).orElseThrow(EntityNotFoundException::new);
        formation.setTheme(theme);

        Formateur formateur = formateurRepository.findById(formateurId).orElseThrow(EntityNotFoundException::new);
        formation.setFormateur(formateur);

        return formationRepository.save(formation);
    }

    public Formation updateFormation(Long id, Formation updatedFormation, Long idFormateur, Long idTheme) {
        Formation existingFormation = formationRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        existingFormation.setNomFormation(updatedFormation.getNomFormation());
        existingFormation.setNumGroupe(updatedFormation.getNumGroupe());
        existingFormation.setDateDebut(updatedFormation.getDateDebut());
        existingFormation.setDateFin(updatedFormation.getDateFin());
        existingFormation.setDuree(updatedFormation.getDuree());
        existingFormation.setTypeformation(updatedFormation.getTypeformation());
        existingFormation.setFraisTotalFormateur(updatedFormation.getFraisTotalFormateur());
        existingFormation.setDureeParJour(updatedFormation.getDureeParJour());
        existingFormation.setCreatedBy(updatedFormation.getCreatedBy());
        existingFormation.setCreationDate(updatedFormation.getCreationDate());
        existingFormation.setLastModifiedBy(updatedFormation.getLastModifiedBy());
        existingFormation.setLastModifiedDate(updatedFormation.getLastModifiedDate());

        Formateur formateur = formateurRepository.findById(idFormateur).orElseThrow(EntityNotFoundException::new);
        existingFormation.setFormateur(formateur);

        Theme theme = themeRepository.findById(idTheme).orElseThrow(EntityNotFoundException::new);
        existingFormation.setTheme(theme);

        return formationRepository.save(existingFormation);
    }



    public void addLieuToFormation(Long formationId, Long lieuId) {
        Optional<Formation> formationOptional = formationRepository.findById(formationId);
        Optional<Lieu> lieuOptional = lieuRepository.findById(lieuId);

        if (formationOptional.isPresent() && lieuOptional.isPresent()) {
            Formation formation = formationOptional.get();
            Lieu lieu = lieuOptional.get();

            formation.setLieu(lieu);

            formationRepository.save(formation);
        } else {
            throw new IllegalArgumentException("Formation or Lieu not found");
        }
    }

    public void deleteFormation(Long id) {
        formationRepository.deleteById(id);
    }

    public List<Formation> getFormationsByParticipant(Long participantId) {
        return formationRepository.findByParticipantsId(participantId);
    }
    public void addParticipantToFormation(Long formationId, String participantnom) {
        // Retrieve formation by ID
        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new EntityNotFoundException("Formation not found"));

        // Retrieve participant by name
        Participant participant = participantRepository.findByNom(participantnom)
                .orElseThrow(() -> new EntityNotFoundException("Participant not found"));

        // Add participant to formation
        formation.getParticipants().add(participant);

        // Save the updated formation
        formationRepository.save(formation);
    }
    public List<Formation> getFormationsByParticipantId(Long id) {
        return formationRepository.findByParticipantsId(id);
    }

}
