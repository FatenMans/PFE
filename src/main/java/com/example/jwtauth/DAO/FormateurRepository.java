package com.example.jwtauth.DAO;

import com.example.jwtauth.Entity.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormateurRepository extends JpaRepository<Formateur,Long> {
}
