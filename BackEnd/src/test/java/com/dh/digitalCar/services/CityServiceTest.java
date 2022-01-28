package com.dh.digitalCar.services;

import com.dh.digitalCar.entities.City;
import com.dh.digitalCar.repository.CityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CityServiceTest {
    @Autowired
    CityRepository cityRepository;
    @Autowired
    CityService cityService;

    City city1;
    City city2;

    @BeforeEach
    void setUp() {
        city1 = new City("Ciudad", "San Juan Ciudad", "Argentina",-31.5316976,-68.5676962);

        cityRepository.deleteAll();
    }

//    @AfterEach
//    void tearDown() {
//        cityRepository.deleteAll();
//    }

    @Test
    void findAll() {
        city2 = new City("ciudad", "La Rioja ciudad","Argentina",-29.4133745,-66.8914775);

        List<City> cityListExpected = new ArrayList<>();

        cityRepository.save(city1);
        cityListExpected.add(city1);
        cityRepository.save(city2);
        cityListExpected.add(city2);

        List<City> cityListActual = cityService.findAll();

        assertEquals(cityListExpected.toString(), cityListActual.toString());
    }

    @Test
    void save() {
        Boolean actualBool = cityService.save(city1);

        assertTrue(actualBool);
    }
}