package com.example.jwtauth.DAO;

import com.example.jwtauth.Entity.CabinetFormation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabinetFormationRepository extends JpaRepository<CabinetFormation,Long> {
}
