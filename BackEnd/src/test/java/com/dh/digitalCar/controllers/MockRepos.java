package com.dh.digitalCar.controllers;

import com.dh.digitalCar.repository.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Component;

@Component
public class MockRepos {
    @MockBean
    private CityRepository cityRepository;
    @MockBean
    private CategoryRepository categoryRepository;
    @MockBean
    private FeatureRepository featureRepository;
    @MockBean
    private ImageRepository imageRepository;
    @MockBean
    private CarRepository carRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private BookingRepository bookingRepository;
    @MockBean
    private PoliticsRepository politicsRepository;
}
