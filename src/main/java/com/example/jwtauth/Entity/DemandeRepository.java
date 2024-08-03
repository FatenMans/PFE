package com.example.jwtauth.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DemandeRepository extends JpaRepository<Demande, Long>{
        void deleteByValideeFalseAndDateDemandeBefore(LocalDate thresholdDate);
}
