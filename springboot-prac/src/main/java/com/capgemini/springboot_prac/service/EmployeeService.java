package com.capgemini.springboot_prac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.springboot_prac.entity.Employee;
import com.capgemini.springboot_prac.repo.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    // Add Employee
    public Employee addEmployee(Employee emp) {
        return repository.save(emp);
    }

    // Fetch all employees
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }
}
