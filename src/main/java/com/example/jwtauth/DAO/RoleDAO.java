package com.example.jwtauth.DAO;

import com.example.jwtauth.Entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleDAO extends CrudRepository<Role, String> {
}
