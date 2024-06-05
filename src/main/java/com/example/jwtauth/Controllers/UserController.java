package com.example.jwtauth.Controllers;


import com.example.jwtauth.Entity.User;
import com.example.jwtauth.Service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {




    @Autowired
    private UserService userService;


    @PostConstruct
    public void initRolesAndUser(){
        this.userService.initRolesAndUser();
    }
    @PostMapping("/registerNewUser")
    public ResponseEntity<?> registerNewUser(@RequestBody User user) {
        return this.userService.createNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

}
