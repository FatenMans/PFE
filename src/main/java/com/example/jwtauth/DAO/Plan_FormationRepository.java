package com.example.jwtauth.DAO;

import com.example.jwtauth.Entity.Plan_Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Plan_FormationRepository extends JpaRepository<Plan_Formation, Long> {
    @Query("SELECT pf FROM Plan_Formation pf WHERE pf.theme.theme = :theme")

    List<Plan_Formation> findByTheme(@Param("theme") String theme);



}
