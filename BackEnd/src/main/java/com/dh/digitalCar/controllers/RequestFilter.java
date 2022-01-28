package com.dh.digitalCar.controllers;

import com.dh.digitalCar.dtos.UserTokenDto;
import com.dh.digitalCar.entities.User;
import com.dh.digitalCar.services.TokenService;
import com.dh.digitalCar.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class RequestFilter extends OncePerRequestFilter {
    private static Logger logger = Logger.getLogger(RequestFilter.class);
    private TokenService tokenService;
    private UserService userService;
//    @Autowired
    private AuthenticationManager authenticationManager;

    public RequestFilter(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        String token = request.getHeader("Authorization");

        if ((path.contains("/booking") || path.contains("/cars") || path.contains("/politics")) && token != null && !token.isEmpty()) {
            UserTokenDto userTokenDto = null;

            try {
                userTokenDto = tokenService.decodeToken(token);
            } catch (Exception e) {
                logger.warn("Token broken");
            }

            if (userTokenDto != null) {
                Optional<User> userOptional = userService.findByEmail(userTokenDto.getEmail());
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                if (LocalDateTime.now().isBefore(userTokenDto.getDateTime()) && userOptional.isPresent()) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userOptional.get(),
                                    userOptional.get().getPassword(), userOptional.get().getAuthorities());
//                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    Authentication authentication = authenticationManager.authenticate(authenticationToken);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, httpServletResponse);
    }
}