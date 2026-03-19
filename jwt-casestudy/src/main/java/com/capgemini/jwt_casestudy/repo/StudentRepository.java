package com.capgemini.jwt_casestudy.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.jwt_casestudy.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}
