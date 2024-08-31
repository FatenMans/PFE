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
public class  Participant extends  Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

private  String code;
private String cin;
private  String tel;
    private String nom;
    private String autorisation;

    private String prenom;

    private String lieutravail;
    private String email;

    private String datNais;

    private String Affectation;


    private String codGrad;

    private String password;


    private String datEmb;


    private String fonction;


    private double salaire;
    private Boolean isInternalTrainer = false;


    private String sexe;


    private Boolean hebergementNuite;


    private String note;
    private String commentaire;




    private Boolean confirmation;


    private int agePers;


    private String matricule;
    private boolean isEnabled=false;
    private String lastModifiedBy; // Ajout du champ pour la traçabilité


<<<<<<< HEAD
    @ManyToMany(mappedBy = "participants", fetch = FetchType.LAZY)
=======
    @ManyToMany(mappedBy = "participants", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
>>>>>>> 608914a6f82218bcecb16861523bd0f493bd78f8
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

    @OneToMany(mappedBy = "participant")
    private Set<Evaluation> evaluations;


    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Demande> demandes;



    private String cvFileName;


}
