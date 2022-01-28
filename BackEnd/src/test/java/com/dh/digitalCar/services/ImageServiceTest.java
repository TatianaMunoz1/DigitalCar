package com.dh.digitalCar.services;

import com.dh.digitalCar.entities.Image;
import com.dh.digitalCar.repository.ImageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ImageServiceTest {
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ImageService imageService;

    Image image1;
    Image image2;

    @BeforeEach
    void setUp() {
        image1 = new Image("Alfa Romeo habitaculo", 1, "www.alfaromeo.com");
        image2 = new Image("Alfa Romeo exterior frente", 1, "www.alfaromeo-frente.com");
    }

    @AfterEach
    void tearDown() {
        imageRepository.deleteAll();
    }

    @Test
    void delete() {
        Image imageAdded = imageRepository.save(image1);
        imageService.delete(imageAdded);
        Optional<Image> optionalImage = imageRepository.findById(imageAdded.getId());

        assertTrue(optionalImage.isEmpty());
    }

    @Test
    void findById() {
        Image imageAdded = imageRepository.save(image1);
        Optional<Image> optionalImage = imageService.findById(imageAdded.getId());

        assertTrue(optionalImage.isPresent());
        assertEquals(imageAdded.getId(), optionalImage.get().getId());
    }
}
