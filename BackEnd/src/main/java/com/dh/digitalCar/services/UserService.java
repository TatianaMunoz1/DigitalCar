package com.dh.digitalCar.services;

import com.dh.digitalCar.dtos.UserLoginDto;
import com.dh.digitalCar.dtos.UserTokenDto;
import com.dh.digitalCar.entities.Role;
import com.dh.digitalCar.entities.User;
import com.dh.digitalCar.repository.UserRepository;
import com.dh.digitalCar.services.servicesInterfaces.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    private UserRepository repository;
    private TokenService tokenService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, TokenService tokenService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.tokenService = tokenService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> save(User user) {
        Optional<User> userOptional = repository.findByEmail(user.getEmail());
        if (userOptional.isEmpty()) {
            Optional<Role> roleOptional;
            if (user.getRole() == null) {
                roleOptional = roleService.findByName("user");
                if (roleOptional.isEmpty()) {
                    roleOptional = roleService.save(new Role("user"));
                }

                user.setRole(roleOptional.get());
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            User user2 = repository.save(user);
            return Optional.of(user2);
        } else
            return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    @Override
    public Optional<UserLoginDto> login(User user) {
        Optional<User> userOptional = repository.findByEmail(user.getEmail());
        //if (userOptional.isPresent()){// lo comprueba el security
        User user2 = userOptional.get();

        String token = tokenService.getToken(new UserTokenDto(user2.getId(), user2.getEmail()));

        return Optional.of(new UserLoginDto(user2.getId(), user2.getName(), user2.getLastName(), user2.getEmail(), token, user2.getRole().getName()));
        //}
        //return Optional.empty();
    }
}
