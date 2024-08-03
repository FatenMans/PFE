package com.example.jwtauth.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plan_Formation extends  Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code_action;
    private String  groupe_N;
    private String dateDebut;
    private String datefin;
    private String duree;
    private String NbreCandidat;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    private Formation formation;

    @ManyToOne
    @JoinColumn(name = "formateur_id")
    private Formateur formateur;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

    @ManyToMany
    @JoinTable(
            name = "PlanFormation_Participant",
            joinColumns = @JoinColumn(name = "plan_formation_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private Set<Participant> participants;

    @ManyToMany
    @JoinTable(
            name = "PlanFormation_Lieu",
            joinColumns = @JoinColumn(name = "plan_formation_id"),
            inverseJoinColumns = @JoinColumn(name = "lieu_id")
    )
    private Set<Lieu> lieux;

    @ManyToMany
    @JoinTable(
            name = "PlanFormation_LieuHebergement",
            joinColumns = @JoinColumn(name = "plan_formation_id"),
            inverseJoinColumns = @JoinColumn(name = "lieu_hebergement_id")
    )
    private Set<LieuHebergement> lieuxHebergement;

    @ManyToOne
    @JoinColumn(name = "cabinet_formation_id")
    private CabinetFormation cabinetFormation;

    // Other fields can be added as required
}
