package com.keyVault.app.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keyVault.app.entity.User;
@SpringBootTest
public class UserRepositoryTest {
	
    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setup(){
    	userRepository.deleteAll();
    	user = new User("test@test.com","test", new Date(), true);
    }
    @Test
    void testSaveUser(){
        //given

        //when
        User userSave = userRepository.save(user);

        //then
        assertThat(userSave).isNotNull();
        assertThat(userSave.getId()).isGreaterThan(0);
    }
    @Test
    void testListUSers(){
        //given
    	
        User user1 = new User("test2@test.com","test2", new Date(), true);
        userRepository.save(user1);
        userRepository.save(user);

        //when
        List<User> userList = userRepository.findAll();

        //then
        assertThat(userList).isNotNull();
        assertThat(userList.size()).isEqualTo(2);
    }

    @Test
    void testGetUserForId(){
    	userRepository.save(user);

        //when
        User userDB = userRepository.findById(user.getId()).get();

        //then
        assertThat(userDB).isNotNull();
    }
    @Test
    void testUpdateUser(){
    	userRepository.save(user);

        //when
        User userSave = userRepository.findById(user.getId()).get();
        userSave.setEmail("test2@test.com");
        userSave.setEnabled(false);
        userSave.setPassword("test2");
        User iconUpdate = userRepository.save(userSave);

        //then
        assertThat(userSave.getEmail()).isEqualTo("test2@test.com");
        assertThat(userSave.getEnabled()).isEqualTo(false);
        assertThat(userSave.getPassword()).isEqualTo("test2");
    }

    @Test
    void testDeleteUser(){
    	userRepository.save(user);

        //when
    	userRepository.deleteById(user.getId());
        Optional<User> userOp = userRepository.findById(user.getId());

        //then
        assertThat(userOp).isEmpty();
    }
}
