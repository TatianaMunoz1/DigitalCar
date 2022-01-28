package com.dh.digitalCar.services.servicesInterfaces;

import com.dh.digitalCar.dtos.UserLoginDto;
import com.dh.digitalCar.entities.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String pass);
    Optional<UserLoginDto> login(User user);
}
