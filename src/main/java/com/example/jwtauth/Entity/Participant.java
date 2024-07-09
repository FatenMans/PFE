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
public class  Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nom;
    private String prenom;

    private String lieutravail;
    private String email;

    private String datNais;

    private String Affectation;


    private String codGrad;


    private String datEmb;


    private String fonction;


    private double salaire;


    private String sexe;


    private Boolean hebergementNuite;





    private Boolean confirmation;


    private int agePers;


    private int matricule;
    private boolean isEnabled=false;

    @ManyToMany(mappedBy = "participants")
    @JsonIgnore
    private Set<Formation> formations;
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "Participant_lieu_hebergement",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "lieu_hebergement_id")
    )
    private Set<LieuHebergement> lieuxHebergement = new HashSet<>();









}
