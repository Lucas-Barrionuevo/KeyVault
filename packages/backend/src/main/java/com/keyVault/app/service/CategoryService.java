package com.keyVault.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyVault.app.dto.CategoryDTO;
import com.keyVault.app.entity.Category;
import com.keyVault.app.exceptions.ResourceNotFoundException;
import com.keyVault.app.repository.CategoryRepository;
@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CategoryDTO createCategory (CategoryDTO categoryDTO) {
		Category category = mappingEntity(categoryDTO);
		
		Category newCategory = categoryRepository.save(category);
		
		CategoryDTO responseCategory = mappingDTO(newCategory);
		return responseCategory;
	}
	
	public List<CategoryDTO> findAllCategories(){
		List<Category> AllCategories = categoryRepository.findAll();
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
