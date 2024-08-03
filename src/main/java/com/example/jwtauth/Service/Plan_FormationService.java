package com.example.jwtauth.Service;

import com.example.jwtauth.DAO.LieuRepository;
import com.example.jwtauth.DAO.Plan_FormationRepository;
import com.example.jwtauth.DAO.ThemeRepository;
import com.example.jwtauth.Entity.Lieu;
import com.example.jwtauth.Entity.Plan_Formation;
import com.example.jwtauth.Entity.Theme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class Plan_FormationService {

    @Autowired
    private Plan_FormationRepository planFormationRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private LieuRepository lieuRepository;

    public List<Plan_Formation> getAllPlanFormations() {
        return planFormationRepository.findAll();
    }

    public Optional<Plan_Formation> getPlanFormationById(Long id) {
        return planFormationRepository.findById(id);
    }

    public Plan_Formation createPlanFormation(Plan_Formation planFormation, Long themeId, List<Long> lieuIds) {
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid theme ID: " + themeId));
        List<Lieu> lieuxList = lieuRepository.findAllById(lieuIds);
        Set<Lieu> lieuxSet = new HashSet<>(lieuxList);
        planFormation.setTheme(theme);
        planFormation.setLieux(lieuxSet);
        return planFormationRepository.save(planFormation);
    }

    public Optional<Plan_Formation> updatePlanFormation(Long id, Plan_Formation planFormation) {
        Optional<Plan_Formation> existingPlanFormation = planFormationRepository.findById(id);
        if (existingPlanFormation.isPresent()) {
            Plan_Formation updatedPlanFormation = existingPlanFormation.get();
            updatedPlanFormation.setCode_action(planFormation.getCode_action());
            updatedPlanFormation.setGroupe_N(planFormation.getGroupe_N());
            updatedPlanFormation.setDateDebut(planFormation.getDateDebut());
            updatedPlanFormation.setDatefin(planFormation.getDatefin());
            updatedPlanFormation.setDuree(planFormation.getDuree());
            updatedPlanFormation.setNbreCandidat(planFormation.getNbreCandidat());
            updatedPlanFormation.setTheme(planFormation.getTheme());
            updatedPlanFormation.setLieux(planFormation.getLieux());
            updatedPlanFormation.setLastModifiedBy(planFormation.getLastModifiedBy());
            updatedPlanFormation.setLastModifiedDate(planFormation.getLastModifiedDate());



            return Optional.of(planFormationRepository.save(updatedPlanFormation));
        }
        return Optional.empty();
    }

    public void deletePlanFormation(Long id) {
        planFormationRepository.deleteById(id);
    }

    public Optional<Plan_Formation> addParticipantToFormation(Long formationId, Long participantId) {
        // Implement this method according to your requirements
        return Optional.empty();
    }
}
