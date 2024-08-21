package com.example.jwtauth.Controllers;

import com.example.jwtauth.Entity.EnrolledFormation;
import com.example.jwtauth.Service.EnrolledFormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/enrolled-formations")
public class EnrolledFormationController {
    @Autowired
    private EnrolledFormationService enrolledFormationService;

    @GetMapping("/participant/{participantId}")
    public ResponseEntity<List<EnrolledFormation>> getFormationsByParticipant(@PathVariable Long participantId) {
        List<EnrolledFormation> enrolledFormations = enrolledFormationService.getFormationsByParticipantId(participantId);
        return ResponseEntity.ok(enrolledFormations);
    }
}
