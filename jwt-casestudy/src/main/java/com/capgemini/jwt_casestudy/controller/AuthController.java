package com.capgemini.jwt_casestudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.jwt_casestudy.entity.Student;
import com.capgemini.jwt_casestudy.repo.StudentRepository;
import com.capgemini.jwt_casestudy.service.JwtService;



@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private StudentRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public String register(@RequestBody Student student) {
        student.setPassword(encoder.encode(student.getPassword()));
        repo.save(student);
        return "User Registered";
    }

//    @PostMapping("/login")
//    public String login(@RequestBody Student student) {
//        var user = repo.findByEmail(student.getEmail()).orElseThrow();
//
//        if (encoder.matches(student.getPassword(), user.getPassword())) {
//            return jwtService.generateToken(user.getEmail());
//        }
//        return "Invalid Credentials";
//    } 
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Student student) {

        var user = repo.findByEmail(student.getEmail()).orElseThrow();

        if (encoder.matches(student.getPassword(), user.getPassword())) {

            String token = jwtService.generateToken(user.getEmail());

            return ResponseEntity.ok()
                    .header("Authorization", token)
                    .body("Login Successful");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
    }
}