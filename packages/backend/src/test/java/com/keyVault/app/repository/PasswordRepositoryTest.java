package com.keyVault.app.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatReflectiveOperationException;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keyVault.app.entity.Category;
import com.keyVault.app.entity.Icon;
import com.keyVault.app.entity.Password;
import com.keyVault.app.entity.User;
import com.keyVault.app.exceptions.ResourceNotFoundException;

@SpringBootTest
public class PasswordRepositoryTest {

    @Autowired
    private PasswordRepository passwordRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private IconRepository iconRepository;

    private Password password;
    
    private User user;
    
    private Category category;
    
    private Icon icon;

    @BeforeEach
    void setup(){
    	passwordRepository.deleteAll();
    	iconRepository.deleteAll();
    	categoryRepository.deleteAll();
    	userRepository.deleteAll();
    	user = new User("test@test.com", "test", new Date(), true);
    	category = new Category("test","test");
    	icon = new Icon("test","test");
    	userRepository.save(user);
    	iconRepository.save(icon);
    	categoryRepository.save(category);
    	password = new Password("test","test","test", new Date(),"test");
    	password.setUser(userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("Password", "id", user.getId())));
    	password.setIcon(iconRepository.findById(icon.getId()).orElseThrow(() -> new ResourceNotFoundException("Icon", "id", icon.getId())));
    	password.setCategory(categoryRepository.findById(category.getId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", category.getId())));
    	passwordRepository.save(password);
    }
    @Test
    void testSavePassword(){
        //given
    	
        //when
        Password passSave = passwordRepository.save(password);

        //then
        assertThat(passSave).isNotNull();
        assertThat(passSave.getId()).isGreaterThan(0);
    }
    @Test
    void testListPasswords(){
        //given
    	User user1 = new User("test2@test.com", "test2", new Date(), true);
    	Category category1 = new Category("test2","test2");
    	Icon icon1 = new Icon("test2","test2");
    	categoryRepository.save(category1);
    	userRepository.save(user1);
        iconRepository.save(icon1);
    	Password password1 = new Password("test2","test2","test2", new Date(),"test2");
        password1.setUser(user);
    	password1.setIcon(icon);
    	password1.setCategory(category);
    	passwordRepository.save(password1);
    	passwordRepository.save(password);

        //when
        List<Password> passList = passwordRepository.findAll();

        //then
        assertThat(passList).isNotNull();
        assertThat(passList.size()).isEqualTo(2);
    }

    @Test
    void testGetPasswordForId(){
        passwordRepository.save(password);

        //when
        Password passDB = passwordRepository.findById(password.getId()).get();

        //then
        assertThat(passDB).isNotNull();
    }
    @Test
    void testUpdatePassword(){
        //given
    	passwordRepository.save(password);
        //when
        Password passwordSave = passwordRepository.findById(password.getId()).get();
    	Category category1 = new Category("test2","test2");
    	Icon icon1 = new Icon("test2","test2");
    	categoryRepository.save(category1);
        iconRepository.save(icon1);
    	passwordSave.setIcon(icon);
    	passwordSave.setCategory(category);
    	passwordSave.setContent("test2");
    	passwordSave.setName("test2");
    	passwordSave.setSeenqty(2);
    	passwordSave.setUrl("test2");
    	passwordSave.setUserOrMail("test2");
    	Password passwordUpdate = passwordRepository.save(passwordSave);
        //then
        assertThat(passwordUpdate.getContent()).isEqualTo("test2");
        assertThat(passwordUpdate.getName()).isEqualTo("test2");
        assertThat(passwordUpdate.getSeenqty()).isEqualTo(2);
        assertThat(passwordUpdate.getUrl()).isEqualTo("test2");
        assertThat(passwordUpdate.getUserOrMail()).isEqualTo("test2");
        
    }
    
    @Test
    void testDeletePassword(){
        //given 
    	passwordRepository.save(password);
        //when
        passwordRepository.deleteById(password.getId());
        Optional<Password> passOp = passwordRepository.findById(password.getId());
        
        //then
        assertThat(passOp).isEmpty();
    }
}
