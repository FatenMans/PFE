package com.example.jwtauth.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@NoArgsConstructor
@AllArgsConstructor
public class CabinetFormation extends  Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomCabinet;
    private  String contact;
    private String tel;
    private  String email;


    @ManyToOne
    @JsonIgnoreProperties({"cabinetFormationList"})
    @JoinColumn(name = "lieu_id")
    private Lieu lieu;



    @OneToMany(mappedBy = "cabinetFormation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Formateur> formateurs = new HashSet<>();
}
