package com.example.jwtauth.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @JoinColumn(name = "formation_id")

    private Formation formation;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "participant_id")
    private Participant participant;

    private String Comments;

    private String YourOpinion;

    // Other fields
}