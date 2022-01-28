package com.dh.digitalCar.controllers;

import com.dh.digitalCar.dtos.UserLoginDto;
import com.dh.digitalCar.entities.User;
import com.dh.digitalCar.services.servicesInterfaces.IUserService;
import com.dh.digitalCar.services.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin
public class IndexController {
    private IUserService userService;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    public IndexController(IUserService userService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        Optional<User> userOptional = userService.save(user);

        if (userOptional.isPresent()) {
            Optional<UserLoginDto> userLoginDto = userService.login(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userLoginDto.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(user.getEmail(),
                user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Optional<UserLoginDto> userLoginDto = userService.login(user);
        return userLoginDto.isPresent() ? ResponseEntity.ok(userLoginDto.get()) : ResponseEntity.badRequest().build();
    }
}
