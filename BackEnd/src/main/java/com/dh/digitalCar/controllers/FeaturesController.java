package com.dh.digitalCar.controllers;

import com.dh.digitalCar.entities.Feature;
import com.dh.digitalCar.services.servicesInterfaces.IFeatureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping ("/features")
public class FeaturesController {
    private IFeatureService featureService;

    public FeaturesController(IFeatureService featureService){this.featureService = featureService;}

    @GetMapping
    public ResponseEntity<?> getAllFeatures() {
        return ResponseEntity.ok(featureService.findAll());
    }
    @PostMapping
    public ResponseEntity<?> addFeature(@RequestBody @Valid Feature feature) {
        return featureService.save(feature) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}
