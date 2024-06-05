package com.example.jwtauth.DAO;

import com.example.jwtauth.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, String>{

    User findByUserName(String userName);
}
