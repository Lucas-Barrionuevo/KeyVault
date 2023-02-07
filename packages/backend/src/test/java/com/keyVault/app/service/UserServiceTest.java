package com.keyVault.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Date;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import com.keyVault.app.dto.UserDTO;
import com.keyVault.app.dto.UserResponse;
import com.keyVault.app.entity.User;
import com.keyVault.app.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest {
	@Mock
	private ModelMapper modelMapper;
	
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private PasswordEncoder passwordEncoder2;
    
    @Autowired
    private UserRepository userRepo;
    
    @InjectMocks
    private UserService userService;

    private User user;
    
    private UserDTO userDTO;

    @BeforeEach
    void setup(){
    	userRepo.deleteAll();
    	user = new User("test@test.com","test", new Date(), true);
    	userDTO = new UserDTO ("test@test.com","test", new Date(), true);
    }
    
    @Test
    void testSaveUser(){
        //given
    	//user.setPassword(passwordEncoder.encode(user.getPassword()));
        given(userRepository.save(user)).willReturn(user);
        given(modelMapper.map(userDTO, User.class)).willReturn(user);
        given(modelMapper.map(user, UserDTO.class)).willReturn(userDTO);
    	given(passwordEncoder.encode(user.getPassword())).willReturn(passwordEncoder2.encode(user.getPassword()));

        //when
        UserResponse userSave = userService.registerUser(userDTO);

        //then
        assertThat(userSave).isNotNull();
    }


    @Test
    void testGetUserForId(){
        //given
    	given(modelMapper.map(user, UserDTO.class)).willReturn(userDTO);
    	userRepo.save(user);
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));

        //when
         UserResponse userSave = userService.findUserById(user.getId());

        //then
        assertThat(userSave).isNotNull();
    }

    @Test
    void testUpdateUser(){
        //given
    	given(modelMapper.map(user, UserDTO.class)).willReturn(userDTO);
        given(userRepository.save(user)).willReturn(user);
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        user.setEmail("test2@gmail.com");
        user.setEnabled(true);
        user.setPassword("test2");
        userDTO.setEmail("test2@gmail.com");
        userDTO.setEnabled(true);
        userDTO.setPassword("test2");
        User user1 = userRepo.save(user);
        //when
        UserResponse userUpdate  = userService.updateUser(userDTO, userDTO.getId());

        //then
        assertThat(userUpdate.getEmail()).isEqualTo("test2@gmail.com");
        assertThat(user1.getEnabled()).isEqualTo(true);
    }

    @Test
    void testDeleteUser(){
    	//given
		int id = user.getId();
		 given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
		 
		 //when
		 userService.deleteUser(id);;
		
		 //then
		 assertThat(user.getEnabled()).isEqualTo(false);
    }
}
