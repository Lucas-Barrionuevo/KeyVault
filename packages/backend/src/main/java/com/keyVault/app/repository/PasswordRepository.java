package com.keyVault.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyVault.app.entity.Password;

public interface PasswordRepository extends JpaRepository<Password, Integer>{

}
