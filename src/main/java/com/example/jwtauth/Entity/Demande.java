package com.example.jwtauth.Entity;

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




    private String service;
    private String statut;
    private LocalDate dateDemande;

    @ManyToOne // Utilisez @ManyToOne si un participant peut avoir plusieurs demandes
    @JoinColumn(name = "participant_id")
    private Participant participant;



    // Getters et setters
}