package com.example.jwtauth.DAO;

import com.example.jwtauth.Entity.Plan_Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Plan_FormationRepository extends JpaRepository<Plan_Formation,Long> {
}
