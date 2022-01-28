package com.dh.digitalCar.services;


import com.dh.digitalCar.dtos.UserTokenDto;
import com.dh.digitalCar.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;

@Service
public class TokenService {
    private static Key key;

    public TokenService() {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    private String json(UserTokenDto userTokenDto){
        return "{" +
                "id='" + userTokenDto.getId() +
                "', userEmail='" + userTokenDto.getEmail() + '\'' +
                ", expirationTime='" + LocalDateTime.now().plusHours(1) +'\'' +
                '}';
    }

    public String getToken(UserTokenDto userTokenDto) {
        String jws = Jwts.builder().setSubject(json(userTokenDto)).signWith(key).compact();

        return jws;
    }

    public UserTokenDto decodeToken(String jwsString) {
        Jws<Claims> jws;

        jws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwsString);

        String[] data = jws.getBody().toString().split("'");
        UserTokenDto userTokenDto = new UserTokenDto(Integer.parseInt(data[1]), data[3], LocalDateTime.parse(data[5]));

        return userTokenDto;
    }
}