package com.example.jwtauth.DAO;

import com.example.jwtauth.Entity.CabinetFormation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CabinetFormationRepository extends JpaRepository<CabinetFormation,Long> {
    Optional<CabinetFormation> findByContact(String contact);
}
