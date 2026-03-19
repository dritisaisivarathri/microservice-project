package com.capgemini.springboot_prac.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.springboot_prac.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
