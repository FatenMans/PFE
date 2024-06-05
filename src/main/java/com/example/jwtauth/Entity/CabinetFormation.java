package com.example.jwtauth.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CabinetFormation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adress;
    private  String contact;
    private String tel;
    private  String email;


    @ManyToOne
    @JsonIgnoreProperties({"cabinetFormationList"})
    @JoinColumn(name = "lieu_id")
    private Lieu lieu;


    @ManyToMany
    @JoinTable(
            name = "CabinetFormation_Formateur",
            joinColumns = @JoinColumn(name = "cabinet_formation_id"),
            inverseJoinColumns = @JoinColumn(name = "formateur_id")
    )
    private Set<Formateur> formateurs;

}
