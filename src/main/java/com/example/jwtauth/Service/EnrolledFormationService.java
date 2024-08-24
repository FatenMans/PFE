package com.example.jwtauth.Service;

import com.example.jwtauth.DAO.EnrolledFormationRepository;
import com.example.jwtauth.DAO.FormationRepository;
import com.example.jwtauth.DAO.ParticipantRepository;
import com.example.jwtauth.Entity.EnrolledFormation;
import com.example.jwtauth.Entity.Formation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrolledFormationService {


    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private EnrolledFormationRepository enrolledFormationRepository;

    public List<EnrolledFormation> getFormationsByParticipantId(Long participantId) {
        return enrolledFormationRepository.findByParticipantId(participantId);
    }


//    public void addEnrolledFormation(Long idForamtion,String nomParticipant) {
//        EnrolledFormation enrolledFormation = new EnrolledFormation();
//        Formation formation = formationRepository.findById(idForamtion).get();
//        enrolledFormation.setFormation(formation);
//        enrolledFormation.setParticipant(participantRepository.findByNom(nomParticipant).get());
//        enrolledFormationRepository.save(enrolledFormation);
//    }
}

