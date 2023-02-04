package com.keyVault.app.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keyVault.app.entity.Category;
import com.keyVault.app.entity.User;

@SpringBootTest
public class CategoryRepositoryTest {
	@Autowired
    private CategoryRepository categoryRepository;

    private Category category;
    
    private User user;

    @BeforeEach
    void setup(){
    	categoryRepository.deleteAll();
    	user = new User("test@test.com", "test", new Date(), true);
    	category = new Category("test","test",user);
    	System.out.println(category);
    }
    @Test
    void testSaveCategory(){
        //given

        //when
        Category categoryGuardada = categoryRepository.save(category);

        //then
        assertThat(categoryGuardada).isNotNull();
        assertThat(categoryGuardada.getId()).isGreaterThan(0);
    }
    @Test
    void testListCategories(){
        //given
    	User user2 = new User("test2@test.com", "test2", new Date(), true);
    	Category category1 = new Category("test2","test2", user2);
        categoryRepository.save(category1);
        categoryRepository.save(category);

        //when
        List<Category> categoryList = categoryRepository.findAll();

        //then
        assertThat(categoryList).isNotNull();
        assertThat(categoryList.size()).isEqualTo(2);
    }

    @Test
    void testGetCategoryForId(){
    	categoryRepository.save(category);

        //when
        Category categoryDB = categoryRepository.findById(category.getId()).get();

        //then
        assertThat(categoryDB).isNotNull();
    }
    @Test
    void testUpdateCategory(){
    	categoryRepository.save(category);

        //when
        Category categorySave = categoryRepository.findById(category.getId()).get();
        User user2 = new User("test2@test.com", "test2", new Date(), true);
        categorySave.setName("test2");;
        categorySave.setPreview("test2");
        categorySave.setUser(user2);
        Category categoryUpdate = categoryRepository.save(categorySave);

        //then
        assertThat(categoryUpdate.getName()).isEqualTo("test2");
        assertThat(categoryUpdate.getPreview()).isEqualTo("test2");
        assertThat(categoryUpdate.getUser()).isEqualTo(user2);
    }

    @Test
    void testDeleteCategory(){
    	categoryRepository.save(category);

        //when
        categoryRepository.deleteById(category.getId());
        Optional<Category> categoryOP = categoryRepository.findById(category.getId());

        //then
        assertThat(categoryOP).isEmpty();
    }
}
