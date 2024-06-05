package com.example.jwtauth.Controllers;


import com.example.jwtauth.Entity.JwtRequest;
import com.example.jwtauth.Entity.JwtResponse;
import com.example.jwtauth.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

@CrossOrigin
public class JwtController {


    @Autowired
    private JwtService jwtService;


    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}
