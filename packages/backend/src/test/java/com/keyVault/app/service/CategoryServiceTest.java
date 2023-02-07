package com.keyVault.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keyVault.app.dto.CategoryDTO;
import com.keyVault.app.entity.Category;
import com.keyVault.app.entity.User;
import com.keyVault.app.repository.CategoryRepository;
import com.keyVault.app.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CategoryServiceTest {
	@Mock
	private ModelMapper modelMapper;
	
    @Mock
    private CategoryRepository categoryRepository;
    
    @Mock
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepository categoryRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @InjectMocks
    private CategoryService categoryService;

    private Category category;
    
    private CategoryDTO categoryDTO;
    
    private User user;
    
    @BeforeEach
    void setup(){
    	categoryRepo.deleteAll();
    	userRepo.deleteAll();
    	user = new User("test@test.com", "test", new Date(), true);
    	userRepo.save(user);
    	category = new Category("test","test");
    	category.setUser(user);
    	categoryDTO = new CategoryDTO("test","test");
    }
    
    @Test
    void testSaveCategory(){
        //given
        given(categoryRepository.save(category)).willReturn(category);
        given(modelMapper.map(category, CategoryDTO.class)).willReturn(categoryDTO);
    	given(modelMapper.map(categoryDTO, Category.class)).willReturn(category);
    	given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        //when
        CategoryDTO categorySave = categoryService.createCategory(categoryDTO, user.getId());

        //then
        assertThat(categorySave).isNotNull();
    }

    @Test
    void testListCategories(){
        //given
    	given(modelMapper.map(category, CategoryDTO.class)).willReturn(categoryDTO);
    	User user1= new User("test2@test.com", "test2", new Date(), true);
    	userRepo.save(user1);
    	Category category1 = new Category("test2","test2");
    	category1.setUser(user1);
    	categoryRepo.save(category);
    	categoryRepo.save(category1);
        given(categoryRepository.findByUser_id(user.getId())).willReturn(List.of(category,category1));

        //when
        List<CategoryDTO> categories = categoryService.findAllCategoriesForUser(user.getId());

        //then
        assertThat(categories).isNotNull();
        assertThat(categories.size()).isEqualTo(2);
    }


    @Test
    void testGetCategoryForId(){
        //given
    	given(modelMapper.map(category, CategoryDTO.class)).willReturn(categoryDTO);
    	categoryRepo.save(category);
        given(categoryRepository.findById(category.getId())).willReturn(Optional.of(category));

        //when
        CategoryDTO categorySave = categoryService.findCategoryById(category.getId());

        //then
        assertThat(categorySave).isNotNull();
    }

    @Test
    void testUpdateCategory(){
        //given
    	given(modelMapper.map(category, CategoryDTO.class)).willReturn(categoryDTO);
        given(categoryRepository.save(category)).willReturn(category);
        category.setName("test2");
        category.setPreview("test2");
        categoryDTO.setName("test2");
        categoryDTO.setPreview("test2");
        categoryRepo.save(category);
        given(categoryRepository.findById(category.getId())).willReturn(Optional.of(category));
        //when
        CategoryDTO categoryUpdate  = categoryService.updateCategory(categoryDTO, category.getId());

        //then
        assertThat(categoryUpdate.getName()).isEqualTo("test2");
        assertThat(categoryUpdate.getPreview()).isEqualTo("test2");
    }

    @Test
    void testDeleteCategory(){
    	//given
		int id = category.getId();
		 willDoNothing().given(categoryRepository).delete(category);
		 given(categoryRepository.findById(category.getId())).willReturn(Optional.of(category));
		 
		 //when
		 categoryService.deleteCategory(id);;
		
		 //then
		 verify(categoryRepository,times(1)).delete(category);
    }
}
