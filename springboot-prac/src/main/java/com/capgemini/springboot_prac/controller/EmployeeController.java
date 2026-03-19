package com.capgemini.springboot_prac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.springboot_prac.entity.Employee;
import com.capgemini.springboot_prac.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService service;

    // Add Employee
    
    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee emp) {
        return service.addEmployee(emp);
    }

    // Fetch All Employees
    @GetMapping("/all")
    public List<Employee> getEmployees() {
        return service.getAllEmployees();
    }
}