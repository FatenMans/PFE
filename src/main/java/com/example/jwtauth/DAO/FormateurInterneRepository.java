package com.example.jwtauth.DAO;

import com.example.jwtauth.Entity.FormateurInterne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormateurInterneRepository extends JpaRepository<FormateurInterne,Long> {
}
