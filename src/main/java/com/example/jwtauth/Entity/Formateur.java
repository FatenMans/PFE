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
public class Formateur extends Auditable{


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

    private String password;

    private Double rang;
    private String autorisation;
    private  String typeFormateur;

    private String mail;

    private boolean isEnabled=false;

    private String CvFileName;
    private boolean interne; // true pour interne, false pour externe




    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cabinet_formation_id")
    private CabinetFormation cabinetFormation;


    @OneToMany(mappedBy = "formateur")
    @JsonIgnoreProperties("formateur")
    private Set<Formation> formations;
    @ManyToMany
    @JoinTable(
            name = "formateur_theme",
            joinColumns = @JoinColumn(name = "formateur_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id"))
    private Set<Theme> themes;

}