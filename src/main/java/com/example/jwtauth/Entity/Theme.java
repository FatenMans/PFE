package com.example.jwtauth.Entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codeTheme;
    private String theme;

    private String typeThemeIntraInter;
    private String accord;


    private String documents;

    private String codeFormatExterne;
    private String codeFormateurInterne;
    private double fraisFormateurInterne;



    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Formation> formations;

}
