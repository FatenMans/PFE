package com.example.jwtauth.Service;

import com.example.jwtauth.DAO.EnrolledFormationRepository;
import com.example.jwtauth.Entity.EnrolledFormation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrolledFormationService {
    @Autowired
    private EnrolledFormationRepository enrolledFormationRepository;

    public List<EnrolledFormation> getFormationsByParticipantId(Long participantId) {
        return enrolledFormationRepository.findByParticipantId(participantId);
    }
}

