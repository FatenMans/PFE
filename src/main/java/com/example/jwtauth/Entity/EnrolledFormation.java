package com.example.jwtauth.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class EnrolledFormation {
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

    private LocalDate enrollmentDate;

    // Getters and setters
}
