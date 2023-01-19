package com.keyVault.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyVault.app.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	List<Category> findByUser_id (int user_id);
	
	Optional <Category> findOneByUserIdAndName(int userId, String name);	
}
