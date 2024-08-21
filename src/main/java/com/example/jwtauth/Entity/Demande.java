package com.example.jwtauth.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String experience;
    private String competences;
    private String motivation;
    private String objectifs;
    private String questionnaire; // A

    private String service;
    private String statut;
    private LocalDate dateDemande;
    private boolean validee=false; // Champ pour la validité




    @ManyToOne // Utilisez @ManyToOne si un participant peut avoir plusieurs demandes
    @JoinColumn(name = "participant_id")
    private Participant participant;


    @ManyToOne
    @JoinColumn(name = "theme_id")
    @JsonIgnore
    private Theme theme;



    // Getters et setters
}