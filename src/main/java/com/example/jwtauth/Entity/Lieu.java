package com.example.jwtauth.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lieu extends  Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lieu ;



    @OneToMany(mappedBy = "lieu")
    @JsonIgnore
    private List<CabinetFormation> cabinetFormationList;


    @OneToMany(mappedBy = "lieu")
    @JsonIgnore
    private List<Formation> formations;
}
