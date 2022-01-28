package com.dh.digitalCar.services;


import com.dh.digitalCar.entities.Feature;
import com.dh.digitalCar.repository.FeatureRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FeatureServiceTest {

    @Autowired
    FeatureRepository featureRepository;
    @Autowired
    FeatureService featureService;

    Feature feature1;
    Feature feature2;

    @BeforeEach
    void setUp() {
        feature1 = new Feature("Transmision","icon1");
        feature2 = new Feature("Combustible", "icon2");
    }

    @AfterEach
    void tearDown() {
        featureRepository.deleteAll();
    }

    @Test
    void delete() {
        Feature featureAdded = featureRepository.save(feature1);
        featureService.delete(featureAdded);
        Optional<Feature> optionalFeature = featureRepository.findById(featureAdded.getId());

        assertTrue(optionalFeature.isEmpty());
    }

    @Test
    void findById() {
        Feature featureAdded = featureRepository.save(feature1);
        Optional<Feature> optionalFeature = featureService.findById(featureAdded.getId());

        assertTrue(optionalFeature.isPresent());
        assertEquals(featureAdded.getId(), optionalFeature.get().getId());
    }


}
