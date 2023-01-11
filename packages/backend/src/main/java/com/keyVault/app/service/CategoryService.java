package com.keyVault.app.service;

import java.io.BufferedReader;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.keyVault.app.dto.CategoryDTO;
import com.keyVault.app.entity.Category;
import com.keyVault.app.exceptions.KeyVaultAppException;
import com.keyVault.app.exceptions.ResourceNotFoundException;
import com.keyVault.app.repository.CategoryRepository;
import com.keyVault.app.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	
	public CategoryDTO createCategory (CategoryDTO categoryDTO, int userId) {
		Category category = mappingEntity(categoryDTO);
		category.setUser(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", userId)));
		Category newCategory = categoryRepository.save(category);
		
		CategoryDTO responseCategory = mappingDTO(newCategory);
		return responseCategory;
	}
	
	public List<CategoryDTO> findAllCategoriesForUser(int userId){
		List<Category> AllCategories = categoryRepository.findByUser_id(userId);
		List<CategoryDTO> AllResponseCategories = AllCategories.stream().map(category -> mappingDTO(category)).collect(Collectors.toList());
		return AllResponseCategories;
	}
	
	public CategoryDTO findCategoryById(int id) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		return mappingDTO(category);
	}
	
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, int id) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		
		category.setName(categoryDTO.getName());
		category.setPreview(category.getPreview());
		
		Category updatedCategory = categoryRepository.save(category);
		return mappingDTO(updatedCategory);
	}
	
	public void deleteCategory(int id) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		categoryRepository.delete(category);
	}
	
	public CategoryDTO mappingDTO (Category category) {
		CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
		return categoryDTO;
	}
	
	public Category mappingEntity (CategoryDTO categoryDTO) {
		Category category = modelMapper.map(categoryDTO, Category.class);
		return category;
	}
}
