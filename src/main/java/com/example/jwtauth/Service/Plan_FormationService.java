package com.example.jwtauth.Service;



import com.example.jwtauth.DAO.Plan_FormationRepository;
import com.example.jwtauth.Entity.Plan_Formation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Plan_FormationService {

    @Autowired
    private Plan_FormationRepository planFormationRepository;

    public List<Plan_Formation> getAllPlanFormations() {
        return planFormationRepository.findAll();
    }

    public Optional<Plan_Formation> getPlanFormationById(Long id) {
        return planFormationRepository.findById(id);
    }

    public Plan_Formation createPlanFormation(Plan_Formation planFormation) {
        return planFormationRepository.save(planFormation);
    }

    public Plan_Formation updatePlanFormation(Long id, Plan_Formation planFormationDetails) {
        Plan_Formation planFormation = planFormationRepository.findById(id).orElse(null);
        if (planFormation != null) {
            planFormation.setFormation(planFormationDetails.getFormation());
            planFormation.setFormateur(planFormationDetails.getFormateur());
            planFormation.setTheme(planFormationDetails.getTheme());
            planFormation.setParticipants(planFormationDetails.getParticipants());
            planFormation.setLieux(planFormationDetails.getLieux());
            planFormation.setLieuxHebergement(planFormationDetails.getLieuxHebergement());
            planFormation.setCabinetFormation(planFormationDetails.getCabinetFormation());
            return planFormationRepository.save(planFormation);
        } else {
            return null;
        }
    }

    public void deletePlanFormation(Long id) {
        planFormationRepository.deleteById(id);
    }
}
