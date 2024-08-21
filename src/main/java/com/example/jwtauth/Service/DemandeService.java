package com.example.jwtauth.Service;

import com.example.jwtauth.DAO.ThemeRepository;
import com.example.jwtauth.Entity.Demande;
import com.example.jwtauth.Entity.DemandeRepository;
import com.example.jwtauth.Entity.Participant;
import com.example.jwtauth.Entity.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private ParticipantService participantService;
    @Autowired
    private ThemeRepository themeRepository;

    public Demande createDemande(Demande demande, String nom, Long themeId) {
        Theme theme= themeRepository.findById(themeId).orElseThrow(()->new RuntimeException("Theme not found"));
        demande.setTheme(theme);
        Optional<Participant> participant = participantService.getParticipantByNom(nom);
        if (participant.isPresent()) {
            demande.setParticipant(participant.get());
            return demandeRepository.save(demande);
        }
        throw new IllegalArgumentException("Participant not found with nom: " + nom);
    }

    public Demande createDemande(Demande demande, String nom) {
        Optional<Participant> participant = participantService.getParticipantByNom(nom);
        if (participant.isPresent()) {
            demande.setParticipant(participant.get());
            return demandeRepository.save(demande);
        }
        throw new IllegalArgumentException("Participant not found with nom: " + nom);
    }


    public List<Demande> getAllDemandes() {


        return demandeRepository.findByValideeFalse();
    }

    public void acceptDemande(Long id) {
        // Récupérer la demande existante
        Demande demande = demandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        demande.setValidee(true);
        demandeRepository.save(demande);

        // Implement acceptance logic if needed
    }

    public void refuserDemande(Long id) {
        // Récupérer la demande existante
        Demande demande = demandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));

        // Marquer la demande comme refusée (non validée)
        demande.setValidee(false);

        // Vous pouvez aussi définir une logique pour la suppression automatique si nécessaire
        // Par exemple, supprimer les demandes non validées après 30 jours

        // Sauvegarder la demande mise à jour
        demandeRepository.save(demande);


    }
}


