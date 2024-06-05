package com.example.jwtauth.Service;


import com.example.jwtauth.DAO.RoleDAO;
import com.example.jwtauth.Entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {



    @Autowired
    private RoleDAO roleDAO;
    public Role createNewRole(Role role) {
        return roleDAO.save(role);
    }
}
