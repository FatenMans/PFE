package com.example.jwtauth.Service;

import com.example.jwtauth.DAO.LieuHebergementRepository;
import com.example.jwtauth.DAO.ParticipantRepository;
import com.example.jwtauth.Entity.LieuHebergement;
import com.example.jwtauth.Entity.Participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    @Autowired
    private LieuHebergementRepository lieuHebergementRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    public Optional<Participant> getParticipantById(Long id) {
        return participantRepository.findById(id);
    }

    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public Participant updateParticipant(Long id, Participant participant) {
        if (participantRepository.existsById(id)) {
            participant.setId(id);
            return participantRepository.save(participant);
        }
        return null; // or throw an exception indicating participant not found
    }

    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }

    public Optional<Participant> getParticipantByMatricule(int matricule) {
        return participantRepository.findByMatricule(matricule);
    }

    public Participant addLieuHebergementToParticipant(Long participantId, Long lieuHebergementId) {
        Optional<Participant> participantOptional = participantRepository.findById(participantId);
        Optional<LieuHebergement> lieuHebergementOptional = lieuHebergementRepository.findById(lieuHebergementId);

        if (participantOptional.isPresent() && lieuHebergementOptional.isPresent()) {
            Participant participant = participantOptional.get();
            LieuHebergement lieuHebergement = lieuHebergementOptional.get();
            participant.getLieuxHebergement().add(lieuHebergement);
            return participantRepository.save(participant);
        } else {
            throw new RuntimeException("Participant or LieuHebergement not found");
        }
    }

}

