package com.dh.digitalCar.services;

import com.dh.digitalCar.entities.Role;
import com.dh.digitalCar.repository.RoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleServiceTest {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleRepository roleRepository;

    Role role1;

    @BeforeEach
    void setUp() {
        roleRepository.deleteAll();

        role1 = new Role("TEST_ROLE");
    }

//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void save() {
        Optional<Role> roleOptional = roleService.save(role1);

        assertTrue(roleOptional.isPresent());
        assertEquals(role1, roleOptional.get());

        roleOptional = roleService.save(role1);
        assertTrue(roleOptional.isEmpty());
    }

//    @Test
//    void findByName() {
//    }
}