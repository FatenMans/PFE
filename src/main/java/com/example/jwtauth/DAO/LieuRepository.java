package com.example.jwtauth.DAO;

import com.example.jwtauth.Entity.Lieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LieuRepository extends JpaRepository<Lieu,Long> {
    Optional<Lieu> findByLieu(String lieu);
}
