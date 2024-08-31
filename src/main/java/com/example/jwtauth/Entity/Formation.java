package com.example.jwtauth.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)

public class Formation  extends  Auditable{


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
    private Double dureeParJour; // Ajout du champ pour la durée par jour








    @ManyToOne
    @JsonIgnoreProperties({"cabinetFormation","formations","themes"})
    @JoinColumn(name = "formateur_id")
    private Formateur formateur;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "lieu_id")
    private Lieu lieu;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
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

    @OneToMany(mappedBy = "formation")
    private Set<Evaluation> evaluations;

}
