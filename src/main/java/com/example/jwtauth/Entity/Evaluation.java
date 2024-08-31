package com.example.jwtauth.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties({"evaluations", "formations", "participants", "formateur"})
    @JoinColumn(name = "formation_id")

    private Formation formation;

    @ManyToOne
    @JsonIgnoreProperties({"evaluations", "formations", "participants", "formateur"})
    @JoinColumn(name = "participant_id")
    private Participant participant;

    private int note;
    private String commentaire;
private String CommentVous_Evaluez_Votre_Experience;
private String CommentVous_Evaluez_Votre_Formateur;

    // Other fields
}