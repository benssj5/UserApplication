package com.spring.UserApplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.UserApplication.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {



}
