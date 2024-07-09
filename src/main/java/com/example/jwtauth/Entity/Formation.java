package com.example.jwtauth.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Formation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private int numGroupe;
    private String nomFormation;
    private String dateDebut;
    private String dateFin;

    private String duree;
    private String typeformation; // externe, interne, etc.


    private double fraisTotalFormateur;



    @ManyToOne
    @JoinColumn(name = "formateur_id")
    private Formateur formateur;

    @ManyToOne
    @JoinColumn(name = "lieu_id")
    private Lieu lieu;

    @ManyToMany
    @JoinTable(
            name = "Formation_Participant",
            joinColumns = @JoinColumn(name = "formation_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private Set<Participant> participants;


    @ManyToOne
    @JoinColumn(name = "theme_id")
    @JsonIgnoreProperties("formations")
    private Theme theme;

}
