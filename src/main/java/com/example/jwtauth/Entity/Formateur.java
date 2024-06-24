package com.example.jwtauth.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private String mail;

    private boolean isEnabled=false;

    private String CvFileName;



    @ManyToOne
    private CabinetFormation cabinetFormation;

    @OneToMany(mappedBy = "formateur")
    @JsonIgnoreProperties("formateur")
    private Set<Formation> formations;


}
