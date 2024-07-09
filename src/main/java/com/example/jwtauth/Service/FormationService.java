package com.example.jwtauth.Service;



import com.example.jwtauth.DAO.*;
import com.example.jwtauth.Entity.*;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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

    public Formation createFormation(Formation formation, Long formateurId, Long themeId) {
         Theme theme = themeRepository.findById(themeId).orElseThrow(EntityNotFoundException::new);
         formation.setTheme(theme);

        Formateur formateur =formateurRepository.findById(formateurId).orElseThrow(EntityNotFoundException::new);
        formation.setFormateur(formateur);
        return formationRepository.save(formation);
    }

    public Formation updateFormation(Long id, Formation updatedFormation,Long idFormateur,Long idTheme) {

        if (formationRepository.existsById(id)) {
            Formateur formateur = formateurRepository.findById(idFormateur).orElseThrow(EntityNotFoundException::new);
            updatedFormation.setFormateur(formateur);
            Theme theme = themeRepository.findById(idTheme).orElseThrow(EntityNotFoundException::new);
            updatedFormation.setTheme(theme);
            updatedFormation.setId(id);
            return formationRepository.save(updatedFormation);
        } else {
            return null; // Or throw an exception indicating the formation doesn't exist
        }
    }
    public void addParticipantToFormation(Long formationId, Long participantId) {
        Optional<Formation> formationOptional = formationRepository.findById(formationId);
        Optional<Participant> participantOptional = participantRepository.findById(participantId);

        if (formationOptional.isPresent() && participantOptional.isPresent()) {
            Formation formation = formationOptional.get();
            Participant participant = participantOptional.get();

            formation.getParticipants().add(participant);
            participant.getFormations().add(formation);

            formationRepository.save(formation);
            participantRepository.save(participant);
        } else {
            // Gérer le cas où la formation ou le participant n'existe pas
        }
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
            // Handle case where formation or lieu does not exist
            throw new IllegalArgumentException("Formation or Lieu not found");
        }
    }

    public void deleteFormation(Long id) {
        formationRepository.deleteById(id);
    }


    public List<Formation> getFormationsByParticipant(Long participantId) {
        return formationRepository.findByParticipantsId(participantId);
    }
}

