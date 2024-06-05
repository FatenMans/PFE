package com.example.jwtauth.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Formateur {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String nom;
    private String prenom;
    private String cin;
    private String matricule;
    private String tel;
    private String etatique_privé;
    private Integer montant;
    private Double moyenne;
    private Double rang;
    private String autorisation;
    private  String typeFormateur;
    private String dtype;
    @Transient // If using JPA, this annotation is used to indicate that the field is not persisted in the database
    private MultipartFile cvFile;


    @ManyToMany(mappedBy = "formateurs")

    private Set<CabinetFormation> cabinetFormations;

    @OneToMany(mappedBy = "formateur")
    private Set<Formation> formations;


}
