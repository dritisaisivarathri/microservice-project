package com.capgemini.jwt_casestudy.controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.jwt_casestudy.entity.Student;
import com.capgemini.jwt_casestudy.repo.StudentRepository;



@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository repo;

    @GetMapping("/profile")
    public List<Student> getAll() {
        return repo.findAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "Deleted";
    }
}