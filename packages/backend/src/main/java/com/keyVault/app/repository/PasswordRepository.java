package com.keyVault.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyVault.app.entity.Password;

public interface PasswordRepository extends JpaRepository<Password, Integer>{
	List<Password> findByUser_id (int user_id);
}
