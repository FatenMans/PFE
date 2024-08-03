package com.example.jwtauth.DAO;

import com.example.jwtauth.Entity.LieuHebergement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LieuHebergementRepository extends JpaRepository<LieuHebergement,Long> {
        Optional<LieuHebergement> findByLieuheb(String lieuheb);
}
