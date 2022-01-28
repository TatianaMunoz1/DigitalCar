package com.dh.digitalCar.controllers;


import com.dh.digitalCar.entities.Image;
import com.dh.digitalCar.services.servicesInterfaces.IImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping ("/images")
public class ImagesController {

    private IImageService imageService;

    public ImagesController(IImageService imageService){this.imageService = imageService;}

    @GetMapping
    public ResponseEntity<?> getAllImages() {
        return ResponseEntity.ok(imageService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> addFeature(@RequestBody @Valid Image image) {
        System.out.println(image);
        return imageService.save(image) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
