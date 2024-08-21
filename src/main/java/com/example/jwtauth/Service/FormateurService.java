package com.example.jwtauth.Service;
import com.example.jwtauth.DAO.FormateurRepository;
import com.example.jwtauth.DAO.ThemeRepository;
import com.example.jwtauth.Entity.Formateur;
import com.example.jwtauth.Entity.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class FormateurService {

    private final FormateurRepository formateurRepository;
    @Autowired
    private ThemeRepository themeRepository;


    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    public FormateurService(FormateurRepository formateurRepository) {
        this.formateurRepository = formateurRepository;
    }


    public List<Formateur> getAllFormateurs() {
        return formateurRepository.findAll();
    }
    public List<Formateur> getAllFormateursByTheme(String theme) {
        return formateurRepository.findByTheme(theme);
    }


    public Optional<Formateur> getFormateurById(Long id) {
        return formateurRepository.findById(id);
    }
    public Optional<Formateur> findByCin(String cin) {
        return formateurRepository.findByCin(cin);
    }

    public Formateur createFormateurWithTheme(Formateur formateur, Long themeId) {
        // Vérification du duplicata du CIN
        Optional<Formateur> existingFormateur = formateurRepository.findByCin(formateur.getCin());
        if (existingFormateur.isPresent()) {
            throw new IllegalArgumentException("Un formateur avec ce CIN existe déjà.");
        }

        // Récupération du thème par son ID
        Optional<Theme> themeOpt = themeRepository.findById(themeId);
        if (!themeOpt.isPresent()) {
            throw new IllegalArgumentException("Le thème avec cet ID n'existe pas.");
        }
        Theme theme = themeOpt.get();

        // Association du thème au formateur
        formateur.setThemes(new HashSet<>(Collections.singletonList(theme)));

        // Enregistrement du formateur avec le thème associé
        return formateurRepository.save(formateur);
    }

    public Formateur updateFormateur(Long id, Formateur updatedFormateur, boolean activate) {
        Optional<Formateur> optionalFormateur = formateurRepository.findById(id);
        if (optionalFormateur.isPresent()) {
            Formateur formateur = optionalFormateur.get();
            formateur.setCode(updatedFormateur.getCode());
            formateur.setNom(updatedFormateur.getNom());
            formateur.setPrenom(updatedFormateur.getPrenom());
            formateur.setCin(updatedFormateur.getCin());
            formateur.setMatricule(updatedFormateur.getMatricule());
            formateur.setTel(updatedFormateur.getTel());
            formateur.setEtatique_privé(updatedFormateur.getEtatique_privé());
            formateur.setMontant(updatedFormateur.getMontant());
            formateur.setRang(updatedFormateur.getRang());
            formateur.setAutorisation(updatedFormateur.getAutorisation());
            formateur.setTypeFormateur(updatedFormateur.getTypeFormateur());
            formateur.setMail(updatedFormateur.getMail());
            formateur.setLastModifiedBy(updatedFormateur.getLastModifiedBy());
            formateur.setLastModifiedDate(updatedFormateur.getLastModifiedDate());
            formateur.setCreatedBy(updatedFormateur.getCreatedBy());
            formateur.setCreationDate(updatedFormateur.getCreationDate());
            formateur.setCabinetFormation(updatedFormateur.getCabinetFormation());
            formateur.setFormations(updatedFormateur.getFormations());
            formateur.setCvFileName(updatedFormateur.getCvFileName());
            formateur.setEnabled(activate);
            return formateurRepository.save(formateur);
        }
        return formateurRepository.save(updatedFormateur);
    }

    public void deleteFormateur(Long id) {
        formateurRepository.deleteById(id);
    }
    public void save(Formateur formateur) {
        // Process and save the Formateur object using the repository

        formateurRepository.save(formateur);
    }

    public void activateAccount(Long formateurId){
        Formateur formateur=formateurRepository.getById(formateurId);
        formateur.setEnabled(true);

        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(formateur.getMail());
            mailMessage.setText("Votre compte a éete activé avec succées");
            mailMessage.setSubject("Activation account");

            // Sending the mail
            javaMailSender.send(mailMessage);
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
    public Formateur ajouterThemesAuFormateur(Long formateurId, List<Long> themeIds) {
        Formateur formateur = formateurRepository.findById(formateurId)
                .orElseThrow(() -> new RuntimeException("Formateur non trouvé"));

        // Trouver les thèmes en fonction des IDs fournis
        List<Theme> themeList = themeRepository.findAllById(themeIds);

        // Convertir la liste des thèmes en ensemble
        Set<Theme> themeSet = new HashSet<>(themeList);

        // Ajouter les thèmes à l'ensemble des thèmes du formateur
        formateur.getThemes().addAll(themeSet);

        // Sauvegarder les modifications
        return formateurRepository.save(formateur);
    }
    public List<Formateur> findByNomContaining(String nom) {
        return formateurRepository.findByNomContaining(nom);
    }
}
