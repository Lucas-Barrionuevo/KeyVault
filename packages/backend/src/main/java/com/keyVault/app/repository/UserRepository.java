package com.keyVault.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyVault.app.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
