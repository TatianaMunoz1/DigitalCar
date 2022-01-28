package com.dh.digitalCar.services;

import com.dh.digitalCar.dtos.UserLoginDto;
import com.dh.digitalCar.entities.Role;
import com.dh.digitalCar.entities.User;
import com.dh.digitalCar.repository.RoleRepository;
import com.dh.digitalCar.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    User user1;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        user1 = new User("Juan", "Perez", "pass", "juan.perez@gmail.com", null);
    }

//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void save() {
        Optional<User> userOptional = userService.save(user1);

        assertTrue(userOptional.isPresent());
        assertNotNull(userOptional.get().getRole());
    }

    @Test
    void login() {
        user1 = userRepository.save(user1);

        User user2 = new User();
        user2.setEmail(user1.getEmail());

        Optional<UserLoginDto> userLoginDtoOptional = userService.login(user2);

        assertTrue(userLoginDtoOptional.isPresent());

        UserLoginDto userLoginDto = userLoginDtoOptional.get();

        assertTrue(!userLoginDto.getName().isEmpty() && !userLoginDto.getLastName().isEmpty()
                && !userLoginDto.getToken().isEmpty());
    }
}