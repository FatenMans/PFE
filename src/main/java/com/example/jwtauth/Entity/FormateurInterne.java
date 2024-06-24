package com.example.jwtauth.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@Entity
public class FormateurInterne extends Formateur {
    private String codeFormateurInterne;

    public FormateurInterne(Long id, String codeFormateurInterne) {
        super();
        this.setId(id);
        this.codeFormateurInterne = codeFormateurInterne;
    }

    public FormateurInterne() {
        super();
    }
}
