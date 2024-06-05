package com.example.jwtauth.DAO;

import com.example.jwtauth.Entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme,Long> {
}
