package com.example.jwtauth.Service;
import com.example.jwtauth.DAO.FormateurRepository;
import com.example.jwtauth.Entity.Formateur;
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
import java.util.List;
import java.util.Optional;

@Service
public class FormateurService {

    private final FormateurRepository formateurRepository;


    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    @Autowired
    public FormateurService(FormateurRepository formateurRepository) {
        this.formateurRepository = formateurRepository;
    }

    public List<Formateur> getAllFormateurs() {
        return formateurRepository.findAll();
    }

    public Optional<Formateur> getFormateurById(Long id) {
        return formateurRepository.findById(id);
    }

    public Formateur saveFormateur(Formateur formateur) {


        return formateurRepository.save(formateur);
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



}
