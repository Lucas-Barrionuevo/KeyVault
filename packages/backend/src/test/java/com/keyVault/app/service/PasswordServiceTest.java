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

import com.keyVault.app.dto.IconDTO;
import com.keyVault.app.dto.PasswordDTO;
import com.keyVault.app.dto.PasswordResponse;
import com.keyVault.app.dto.PasswordResponse2;
import com.keyVault.app.dto.UserDTO;
import com.keyVault.app.entity.Category;
import com.keyVault.app.entity.Icon;
import com.keyVault.app.entity.Password;
import com.keyVault.app.entity.User;
import com.keyVault.app.exceptions.ResourceNotFoundException;
import com.keyVault.app.repository.CategoryRepository;
import com.keyVault.app.repository.IconRepository;
import com.keyVault.app.repository.PasswordRepository;
import com.keyVault.app.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PasswordServiceTest {
	@Mock
	private ModelMapper modelMapper;
	
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private CategoryRepository categoryRepository;
    
    @Mock 
    private IconRepository iconRepository;
    
    @Mock
    private PasswordRepository passwordRepository;
    
    @Autowired
    private IconRepository iconRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private CategoryRepository categoryRepo;
    
    @Autowired
    private PasswordRepository passwordRepo;
    
    
    @InjectMocks
    private PasswordService passwordService;

    private Password password;
    
    private User user;
    
    private Category category;
    
    private Icon icon;
    
    private PasswordDTO passwordDTO;
    

    @BeforeEach
    void setup(){
    	passwordRepo.deleteAll();
    	iconRepo.deleteAll();
    	categoryRepo.deleteAll();
    	userRepo.deleteAll();
    	user = new User("test@test.com", "test", new Date(), true);
    	category = new Category("test","test");
    	icon = new Icon("test","test");
    	userRepo.save(user);
    	iconRepo.save(icon);
    	categoryRepo.save(category);
    	password = new Password("test","test","test", new Date(),"test");
    	passwordDTO = new PasswordDTO("test","test","test", new Date(),category.getName(),"test");
    	password.setUser(user).orElseThrow(() -> new ResourceNotFoundException("Password", "id", user.getId()))));
    	password.setIcon(iconRepository.findById(icon.getId()).orElseThrow(() -> new ResourceNotFoundException("Icon", "id", icon.getId())));
    	password.setCategory(categoryRepository.findById(category.getId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", category.getId())));
       	passwordDTO.setUser(userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("Password", "id", user.getId())));
    	passwordDTO.setIcon(iconRepository.findById(icon.getId()).orElseThrow(() -> new ResourceNotFoundException("Icon", "id", icon.getId())));
    	passwordDTO.setCategory(categoryRepository.findById(category.getId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", category.getId())));
    }
    
    @Test
    void testSavePassword(){
        //given
        given(passwordRepository.save(password)).willReturn(password);
        given(modelMapper.map(password, PasswordDTO.class)).willReturn(passwordDTO);
    	given(modelMapper.map(passwordDTO, Password.class)).willReturn(password);
        //when
        PasswordResponse2 passwordSave = passwordService.createPassword(passwordDTO, user.getId());

        //then
        assertThat(passwordSave).isNotNull();
    }

    @Test
    void testListPasswords(){
        //given
    	Password password1 = new Password("test2","test2","test2", new Date(),"test2");
    	password1.setUser(user).orElseThrow(() -> new ResourceNotFoundException("Password", "id", user.getId()))));
    	password1.setIcon(iconRepository.findById(icon.getId()).orElseThrow(() -> new ResourceNotFoundException("Icon", "id", icon.getId())));
    	password1.setCategory(categoryRepository.findById(category.getId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", category.getId())));
    	passwordRepo.save(password);
    	passwordRepo.save(password1);
        given(passwordRepository.findByUser_id(user.getId())).willReturn(List.of(password,password1));

        //when
        List<PasswordResponse2> passwords = passwordService.findAllPasswordsForUser(user.getId());

        //then
        assertThat(passwords).isNotNull();
        assertThat(passwords.size()).isEqualTo(2);
    }


    @Test
    void testGetPasswordForId(){
        //given
    	given(modelMapper.map(password, PasswordDTO.class)).willReturn(passwordDTO);
    	passwordRepo.save(password);
        given(passwordRepository.findById(password.getId())).willReturn(Optional.of(password));

        //when
        PasswordResponse passwordSave = passwordService.findPasswordById(password.getId());

        //then
        assertThat(passwordSave).isNotNull();
    }

    @Test
    void testUpdatePassword(){
        //given
    	given(modelMapper.map(password, PasswordDTO.class)).willReturn(passwordDTO);
        given(passwordRepository.save(password)).willReturn(password);
        given(passwordRepository.findById(password.getId())).willReturn(Optional.of(password));
        
        password.setContent("test2");
        password.setName("test2");
        password.setSeenqty(2);
        password.setUrl("test2");
        password.setUserOrMail("test2");
        passwordDTO.setContent("test2");
        passwordDTO.setName("test2");
        passwordDTO.setUrl("test2");
        passwordDTO.setUserOrMail("test2");
        //when
        PasswordResponse passwordUpdate  = passwordService.updatePassword(passwordDTO, passwordDTO.getId());

        //then
        assertThat(passwordUpdate.getContent()).isEqualTo("test2");
        assertThat(passwordUpdate.getName()).isEqualTo("test2");
        assertThat(passwordUpdate.getUrl()).isEqualTo("test2");
        assertThat(passwordUpdate.getUserOrMail()).isEqualTo("test2");
    }

    @Test
    void testDeletePassword(){
    	//given
		int id = password.getId();
		 willDoNothing().given(passwordRepository).delete(password);
		 given(passwordRepository.findById(password.getId())).willReturn(Optional.of(password));
		 
		 //when
		 passwordService.deletePassword(id);;
		
		 //then
		 verify(passwordRepository,times(1)).delete(password);
    }
}
