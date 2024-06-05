package com.example.jwtauth.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String dateDebutGroupe;
    private String dateFinGroupe;

    private String dureGroupe;
    private String typeformation; // externe, interne, etc.

    private double cout;

    private double tva;
    private double mtTva;
    private double fraisTotalFormateur;
    private String nomPers;
    private String matPers;
    private String libFonct;
    private String libServ;


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

    @ManyToMany
    @JoinTable(
            name = "formation_lieu",
            joinColumns = @JoinColumn(name = "formation_id"),
            inverseJoinColumns = @JoinColumn(name = "lieu_id")
    )
    private List<Lieu> lieux;
    @ManyToOne
    @JoinColumn(name = "theme_id")
    @JsonIgnore
    private Theme theme;

}
