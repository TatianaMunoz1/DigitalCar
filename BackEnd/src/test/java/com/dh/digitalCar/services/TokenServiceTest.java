package com.dh.digitalCar.services;

import com.dh.digitalCar.dtos.UserTokenDto;
import com.dh.digitalCar.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TokenServiceTest {
    @Autowired
    private TokenService tokenService;

    UserTokenDto userTokenDto1;

    @BeforeEach
    void setUp() {
        userTokenDto1 = new UserTokenDto(1, "test@gmail.com");
    }

    @Test
    void getToken() {
        String token = tokenService.getToken(userTokenDto1);

        assertFalse(token.isEmpty());
    }

    @Test
    void decodeToken() {
        String token = tokenService.getToken(userTokenDto1);

        UserTokenDto userTokenDtoActual = tokenService.decodeToken(token);

        assertEquals(userTokenDto1.getId(), userTokenDtoActual.getId());
        assertEquals(userTokenDto1.getEmail(), userTokenDtoActual.getEmail());
        assertNotNull(userTokenDtoActual.getDateTime());
    }
}