package com.capgemini.jwt_practice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.jwt_practice.entity.UserInformation;

public interface UserJpaRepository extends JpaRepository<UserInformation, String> {

}
