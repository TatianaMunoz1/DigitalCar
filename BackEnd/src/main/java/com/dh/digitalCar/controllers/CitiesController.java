package com.dh.digitalCar.controllers;

import com.dh.digitalCar.entities.City;
import com.dh.digitalCar.services.servicesInterfaces.ICityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/cities")
public class CitiesController {
    private ICityService cityService;

    public CitiesController(ICityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCities(){
        return ResponseEntity.ok(cityService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> addCity(@RequestBody @Valid City city){
        return cityService.save(city) ? ResponseEntity.ok().build(): ResponseEntity.badRequest().build();
    }
}
