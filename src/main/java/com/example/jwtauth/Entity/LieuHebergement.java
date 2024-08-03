package com.example.jwtauth.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LieuHebergement extends  Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lieuheb;
    @ManyToMany(mappedBy = "lieuxHebergement")
    @JsonIgnoreProperties("{LieuHebergement}")
    private Set<Participant> Participants = new HashSet<>();
}

