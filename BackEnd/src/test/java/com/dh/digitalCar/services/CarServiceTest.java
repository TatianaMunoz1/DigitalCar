package com.dh.digitalCar.services;

import com.dh.digitalCar.FactoryService;
import com.dh.digitalCar.dtos.CarDto;
import com.dh.digitalCar.entities.*;
import com.dh.digitalCar.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class))
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class CarServiceTest {
    private static Logger logger = Logger.getLogger(CarServiceTest.class);
    @Autowired
    private FactoryService factoryService;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarService carService;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private BookingRepository bookingRepository;
    //    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();
    @MockBean
    EmailService emailService;

    Car car1;
    Car car2;

    Category category1;
    City city1;
    Feature feature1;
    Feature feature2;
    Image image1;
    Image image2;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        car1 = new Car("Toyota Hilux", "4x4");

        category1 = new Category("Economic", "desc", "cat.com");
        category1 = categoryRepository.save(category1);

        category1 = objectMapper.readValue("{\"id\": \"" + category1.getId() + "\"}", Category.class);
        car1.setCategory(category1);

        city1 = new City("aeropuerto", "San Juan Aeropuerto (UAQ)", "Argentina", -31.571389, -68.418333);
        city1 = cityRepository.save(city1);

        city1 = objectMapper.readValue("{\"id\": \"" + category1.getId() + "\"}", City.class);
        car1.setCity(city1);

        feature1 = new Feature("Feature 1", "Feature ico 1");
        feature1 = featureRepository.save(feature1);

        feature2 = new Feature("Feature 2", "Feature ico 2");
        feature2 = featureRepository.save(feature2);

        Set<Feature> featureSet = new HashSet<>();
        feature1 = objectMapper.readValue("{\"id\": \"" + feature1.getId() + "\"}", Feature.class);
        featureSet.add(feature1);
        feature2 = objectMapper.readValue("{\"id\": \"" + feature2.getId() + "\"}", Feature.class);
        featureSet.add(feature2);
        car1.setFeatures(featureSet);

        image1 = new Image("imageTitle1", 0, "img.com/1");
        car1.setImage(image1);
        image2 = new Image("imageTitle2", 1, "img.com/2");
        car1.setImage(image2);
    }

//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void save() {
        logger.info(car1);
        Boolean actualBool = carService.save(car1);
        logger.info(carRepository.findAll());

        assertTrue(actualBool);
    }

    @Test
    void findAll() {
        List<Car> listExpected = new ArrayList<>();

        carRepository.save(car1);
        listExpected.add(car1);

//        car2 = new Car("Toyota Hilux", "4x4");
//        carRepository.save(car2);
//        listExpected.add(car2);

        List<Car> listActual = carService.findAll();

        logger.info(listActual);
        assertEquals(listExpected.toString(), listActual.toString());
    }

    @Test
    void findByID() {
        carRepository.save(car1);

        Optional<Car> carOptional = carService.findByID(car1.getId());

        logger.info(carOptional);
        assertTrue(carOptional.isPresent());
        assertEquals(car1.toString(), carOptional.get().toString());
    }

    @Test
    void findByIdDto() {
        carRepository.save(car1);

        Optional<CarDto> carDtoOptional = carService.findByIdDto(car1.getId());

        logger.info(carDtoOptional);
        assertTrue(carDtoOptional.isPresent());

//        assertEquals(car1.toString(), carOptional.get().toString());
    }

    /////////////
    @Test
    void findAllByCity() {
        City city1 = new City("aeropuerto", "La Rioja Aeropuerto (IRJ)", "Argentina", -29.38055556, -66.79583333);
        cityRepository.save(city1);
        City city2 = new City("aeropuerto", "Termas de Rio Hondo Aeropuerto (RDH)", "Argentina", -27.506411, -64.933113);
        cityRepository.save(city2);

        car1.setCity(city1);
        carRepository.save(car1);

        List<Car> listActual1 = carService.findAllByCity(city1.getName());
        assertEquals(1, listActual1.size());
        assertEquals(car1.toString(), listActual1.get(0).toString());

        List<Car> listActual2 = carService.findAllByCity(city2.getName());
        assertEquals(0, listActual2.size());
    }

    @Test
    void findAllByCategory() {
        Category category1 = new Category();
        category1.setTitle("Economic");
        categoryRepository.save(category1);
        Category category2 = new Category();
        category2.setTitle("Sedan");
        categoryRepository.save(category2);

        car1.setCategory(category1);
        carRepository.save(car1);

        List<Car> listActual1 = carService.findAllByCategory(category1.getTitle());
        assertEquals(1, listActual1.size());
        assertEquals(car1.toString(), listActual1.get(0).toString());

        List<Car> listActual2 = carService.findAllByCategory(category2.getTitle());
        assertEquals(0, listActual2.size());
    }

    @Test
    void findAllByCityAndCategory() {
        City city1 = new City("aeropuerto", "Corrientes Aeropuerto (CNQ)", "Argentina", -27.449066, -58.758526);
        cityRepository.save(city1);
        Category category1 = new Category();
        category1.setTitle("Economic");
        categoryRepository.save(category1);

        car1.setCategory(category1);
        car1.setCity(city1);
        carRepository.save(car1);

        car2 = new Car("Toyota Hilux", "4x4");
        car2.setCategory(category1);
        car2.setCity(city1);
        carRepository.save(car2);

        List<Car> listExpected = new ArrayList<>();
        listExpected.add(car1);
        listExpected.add(car2);

        List<Car> listActual = carService.findAllByCityAndCategory("La Plata", "Economic");

        assertEquals(listExpected.toString(), listActual.toString());
    }

    @Test
    void findAllByDate() {
        List<Booking> bookingList = new ArrayList<>();
        Booking booking1;
        Booking booking2;
        LocalDateTime startDateTime;
        LocalDate endDate;

        booking1 = factoryService.booking(1, true, "2021-12-26T10:00:00", "2021-12-29");
        startDateTime = LocalDateTime.parse("2021-12-20T10:00:00");
        endDate = LocalDate.parse("2021-12-25");
        booking2 = new Booking(booking1.getCar(), booking1.getUser(), startDateTime, endDate);
        booking2 = bookingRepository.save(booking2);

        bookingList.add(booking1);
        bookingList.add(booking2);

        bookingList.add(factoryService.booking(1, true, "2021-12-27T10:00:00", "2021-12-28"));
        bookingList.add(factoryService.booking(1, true, "2021-12-29T10:00:00", "2022-01-01"));
        booking1 = factoryService.booking(1, true, "2021-12-30T10:00:00", "2021-12-31");
        startDateTime = LocalDateTime.parse("2021-12-20T10:00:00");
        endDate = LocalDate.parse("2021-12-25");
        booking2 = new Booking(booking1.getCar(), booking1.getUser(), startDateTime, endDate);
        booking2 = bookingRepository.save(booking2);
        bookingList.add(booking2);

        startDateTime = LocalDateTime.parse("2022-01-01T10:00:00");
        endDate = LocalDate.parse("2022-01-03");
        booking2 = new Booking(booking1.getCar(), booking1.getUser(), startDateTime, endDate);
        booking2 = bookingRepository.save(booking2);

        bookingList.add(booking1);
        bookingList.add(booking2);

        bookingList.add(factoryService.booking(1, true, "2021-12-28T10:00:00", "2021-12-30"));
        bookingList.add(factoryService.booking(1, true, "2022-01-02T10:00:00", "2022-01-04"));
        bookingList.add(factoryService.booking(1, true, "2021-12-25T10:00:00", "2021-12-25"));
        bookingList.add(factoryService.booking(1, true, "2021-12-26T10:00:00", "2021-12-27"));
        bookingList.add(factoryService.booking(1, true, "2021-12-31T10:00:00", "2022-01-01"));
        bookingList.add(factoryService.booking(1, true, "2021-12-26T10:00:00", "2021-12-30"));
        bookingList.add(factoryService.booking(1, true, "2021-12-28T10:00:00", "2022-01-01"));

        booking1 = factoryService.booking(1, true, "2021-12-25T10:00:00", "2021-12-29");

        startDateTime = LocalDateTime.parse("2021-12-30T10:00:00");
        endDate = LocalDate.parse("2022-01-03");
        booking2 = new Booking(booking1.getCar(), booking1.getUser(), startDateTime, endDate);
        booking2 = bookingRepository.save(booking2);

        bookingList.add(booking1);
        bookingList.add(booking2);

        bookingList.add(factoryService.booking(1, true, "2021-12-27T10:00:00", "2021-12-31"));
        bookingList.add(factoryService.booking(1, true, "2021-12-26T10:00:00", "2022-01-01"));


        List<CarDto> carDtoList = carService.findAllByDate(LocalDate.parse("2021-12-27"), LocalDate.parse("2021-12-31"));
        assertEquals(11, carDtoList.size());
        for (CarDto carDto : carDtoList) {
            System.out.println(carDto.getId());
        }
    }
}