package com.example.jwtauth.Service;

import com.example.jwtauth.DAO.*;
import com.example.jwtauth.Entity.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;
    private final String uploadDir = "uploads/";

    @Autowired
    private LieuHebergementRepository lieuHebergementRepository;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private FormateurRepository formateurRepository;
    @Autowired
    private FormationRepository formationRepository;


    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    public Optional<Participant> getParticipantById(Long id) {
        return participantRepository.findById(id);
    }

    public Optional<Participant> findByemail(String email) {
        return participantRepository.findByEmail(email);
    }

    public Participant createParticipant(Participant participant, Long lieuHebergementId) {
        Optional<Participant> existingParticipant = participantRepository.findByEmail(participant.getEmail());
        ;
        if (existingParticipant.isPresent()) {
            throw new IllegalArgumentException("Une participant avec ce email existe déjà.");
        }
        LieuHebergement lieuHebergement = lieuHebergementRepository.findById(lieuHebergementId)
                .orElseThrow(() -> new EntityNotFoundException("LieuHebergement not found with id: " + lieuHebergementId));

        participant.getLieuxHebergement().add(lieuHebergement);

        User user = new User();
        System.out.println("participant.getNom() = " + participant.getNom());
        System.out.println("participant.getPassword() = " + participant.getPassword());
        user.setUserName(participant.getNom());
        user.setUserPassword(participant.getPassword());
        userService.createNewUser(user);


        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(participant.getEmail());
            mailMessage.setText("Votre compte a été créé avec succès");
            mailMessage.setSubject("Validation de création de compte");

            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            System.out.println("Error sending email: " + e.getMessage());
            // Handle email sending exception
        }

        return participantRepository.save(participant);
    }


    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }

    public Optional<Participant> getParticipantByMatricule(String matricule) {
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

    public Participant updateParticipant(Long id, Participant updatedParticipant, Long lieuHebergementId) {
        Optional<Participant> optionalParticipant = participantRepository.findById(id);
        if (optionalParticipant.isPresent()) {
            Participant participant = optionalParticipant.get();
            participant.setNom(updatedParticipant.getNom());
            participant.setPrenom(updatedParticipant.getPrenom());
            participant.setLieutravail(updatedParticipant.getLieutravail());
            participant.setEmail(updatedParticipant.getEmail());
            participant.setDatNais(updatedParticipant.getDatNais());
            participant.setAffectation(updatedParticipant.getAffectation());
            participant.setCodGrad(updatedParticipant.getCodGrad());
            participant.setDatEmb(updatedParticipant.getDatEmb());
            participant.setFonction(updatedParticipant.getFonction());
            participant.setSalaire(updatedParticipant.getSalaire());
            participant.setSexe(updatedParticipant.getSexe());
            participant.setHebergementNuite(updatedParticipant.getHebergementNuite());
            participant.setConfirmation(updatedParticipant.getConfirmation());
            participant.setAgePers(updatedParticipant.getAgePers());
            participant.setMatricule(updatedParticipant.getMatricule());
            participant.setEnabled(updatedParticipant.isEnabled());
            participant.setCreatedBy(updatedParticipant.getCreatedBy());
            participant.setCreationDate(updatedParticipant.getCreationDate());
            participant.setLastModifiedBy(updatedParticipant.getLastModifiedBy());
            participant.setLastModifiedDate(updatedParticipant.getLastModifiedDate());

            if (lieuHebergementId != null) {
                LieuHebergement lieuHebergement = lieuHebergementRepository.findById(lieuHebergementId)
                        .orElseThrow(() -> new RuntimeException("LieuHebergement not found"));
                participant.getLieuxHebergement().add(lieuHebergement);
            }

            return participantRepository.save(participant);
        } else {
            return null;
        }
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void becomeInternalTrainer(Long id) {
        Participant participant = participantRepository.findById(id).orElseThrow(() -> new RuntimeException("Participant not found"));
        participant.setIsInternalTrainer(true);
        participantRepository.save(participant);
    }

    // Other s
    public Optional<Participant> getParticipantByEmail(String email) {
        return participantRepository.findByEmail(email);
    }


    public Optional<Participant> getParticipantByNom(String email) {
        return participantRepository.findByNom(email);
    }
    public Participant updateParticipantAndAddFile(String nom, MultipartFile file) throws IOException {
        Optional<Participant> optionalParticipant = participantRepository.findByNom(nom);
        if (optionalParticipant.isPresent()) {
            Participant participant = optionalParticipant.get();
            participant.setCvFileName(file.getOriginalFilename());
            File uploadFile = new File(uploadDir + file.getOriginalFilename());
            try (FileOutputStream fos = new FileOutputStream(uploadFile)) {
                fos.write(file.getBytes());
            }

            return participantRepository.save(participant);
        }
        return null;
    }
    @Transactional
    public void convertParticipantToFormateur(Long participantId) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new RuntimeException("Participant not found"));



        // Create Formateur from Participant
        Formateur formateur = new Formateur();
        formateur.setTypeFormateur("Interne");
        formateur.setCode(participant.getCode());
        formateur.setCin(participant.getCin());
        formateur.setTel(participant.getTel());
        formateur.setAutorisation("autorisation Interne");
        formateur.setNom(participant.getNom());
        formateur.setPrenom(participant.getPrenom());
        formateur.setMail(participant.getEmail());
        formateur.setMatricule(participant.getMatricule());
        formateur.setCvFileName(participant.getCvFileName());
        formateur.setEnabled(true);  // Enable the formateur
// Check if demandes is not null and has at least one element
        Set<Demande> demandesSet = participant.getDemandes();

        if (demandesSet != null && !demandesSet.isEmpty()) {
            // Iterate through the Set
            Demande firstDemande = demandesSet.iterator().next();
            Theme theme = firstDemande.getTheme();
            formateur.setThemes(Set.of(theme));
            // Use the theme as needed
        } else {
            // Handle the case where demandes is null or empty
            System.out.println("No demandes available");
        }

        // Save the Formateur entity
        formateurRepository.save(formateur);

        User user=userDAO.findByUserName(participant.getNom());
        Set<Role>roles=new HashSet<>();
        roles.add(new Role("Formateur","Formateur"));
        user.setRole(roles);

        userDAO.save(user);


        // Delete the Participant entity since they are now a formateur
        participantRepository.delete(participant);
    }
    public List<Participant> findByFormationId(Long formationId) {
        return participantRepository.findByFormationsId(formationId);
    }


}
