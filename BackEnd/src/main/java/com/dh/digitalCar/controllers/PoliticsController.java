package com.dh.digitalCar.controllers;

import com.dh.digitalCar.entities.Politics;
import com.dh.digitalCar.services.servicesInterfaces.IPoliticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/politics")
public class PoliticsController {
    private IPoliticsService politicsService;

    public PoliticsController(IPoliticsService politicsService) {
        this.politicsService = politicsService;
    }

    @GetMapping
    public ResponseEntity<?> getPolitics(){
        Optional<Politics> politicsOptional = politicsService.find();
        return politicsOptional.isPresent() ? ResponseEntity.ok(politicsOptional.get()) : ResponseEntity.ok("null");
    }

    @PutMapping
    public ResponseEntity<?> updatePolitics(@RequestBody @Valid Politics politics){
        return politicsService.update(politics) ? ResponseEntity.ok().build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
