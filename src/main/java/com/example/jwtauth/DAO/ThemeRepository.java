package com.example.jwtauth.DAO;

import com.example.jwtauth.Entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme,Long> {
    Optional<Theme> findByCodeTheme(String codeTheme);
}
