package com.dh.digitalCar.services;

import com.dh.digitalCar.entities.*;
import com.dh.digitalCar.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingServiceTest {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private RoleRepository roleRepository;

    Booking booking1;
    Category category1;
    Feature feature;
    City city1;
    Car car1;
    User user1;

    @BeforeEach
    void setUp() {
        bookingRepository.deleteAll();
        carRepository.deleteAll();
        userRepository.deleteAll();
        cityRepository.deleteAll();
        categoryRepository.deleteAll();
        featureRepository.deleteAll();
        roleRepository.deleteAll();

        category1 = new Category();
        category1.setTitle("Economic");
        category1 = categoryRepository.save(category1);

        city1 = new City("aeropuerto", "San Juan Aeropuerto (UAQ)", "Argentina", -31.571389, -68.418333);
        city1 = cityRepository.save(city1);

        feature = new Feature("Feature 1", "Feature ico 1");
        feature = featureRepository.save(feature);
        feature.setName("");
        feature.setIcon("");

        car1 = new Car("Toyota Hilux", "4x4");
        car1.setCity(city1);
        car1.setCategory(category1);
//        car1.setFeature(feature);

        car1 = carRepository.save(car1);

        Role role1 = new Role("user");
        role1 = roleRepository.save(role1);
        user1 = new User("Carlos", "Perez", "pass", "carlos.perez@gmail.com", role1);
        user1.setPassword("123123");
        user1 = userRepository.save(user1);
    }

    @AfterEach
    void tearDown() {
        bookingRepository.deleteAll();
        carRepository.deleteAll();
        userRepository.deleteAll();
        cityRepository.deleteAll();
        categoryRepository.deleteAll();
        featureRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    void save() {
        booking1 = new Booking(car1, user1, LocalDateTime.now().plusDays(1), LocalDate.now().plusDays(2));
        Boolean boolActual = bookingService.save(booking1);

        assertTrue(boolActual);
    }

    @Test
    void findByCarId() {
        booking1 = new Booking(car1, user1, LocalDateTime.now().plusDays(1), LocalDate.now().plusDays(2));
        booking1 = bookingRepository.save(booking1);

        List<Booking> bookingOptional = bookingService.findByCarId(booking1.getCar().getId());

        assertTrue(bookingOptional.size() > 0);
    }

    @Test
    void findByDateBetween() {
        Car car2 = new Car("Toyota Hilux", "4x4");
        car2.setCity(city1);
        car2.setCategory(category1);
//        car2.setFeature(feature);
        car2 = carRepository.save(car2);

        Car car3 = new Car("Honda", "4x4");
        car3.setCity(city1);
        car3.setCategory(category1);
//        car3.setFeature(feature);
        car3 = carRepository.save(car3);

        Car car4 = new Car("S10", "4x4");
        car4.setCity(city1);
        car4.setCategory(category1);
//        car4.setFeature(feature);
        car4 = carRepository.save(car4);

        booking1 = new Booking(car1, user1, LocalDateTime.now().plusDays(-1), LocalDate.now().plusDays(1));
        Booking booking2 = new Booking(car2, user1, LocalDateTime.now().plusDays(1), LocalDate.now().plusDays(2));
        Booking booking3 = new Booking(car3, user1, LocalDateTime.now().plusDays(2), LocalDate.now().plusDays(4));

        booking1 = bookingRepository.save(booking1);
        booking2 = bookingRepository.save(booking2);
        booking3 = bookingRepository.save(booking3);

//        List<Booking> bookingList = bookingService.findByDateBetween(LocalDate.now(), LocalDate.now().plusDays(3));
//        System.out.println(bookingList);
    }
}