package com.dh.digitalCar.services;

import com.dh.digitalCar.dtos.CarDto;
import com.dh.digitalCar.entities.*;
import com.dh.digitalCar.repository.CarRepository;
import com.dh.digitalCar.services.servicesInterfaces.IBookingService;
import com.dh.digitalCar.services.servicesInterfaces.ICarService;
import com.dh.digitalCar.services.servicesInterfaces.IFeatureService;
import com.dh.digitalCar.services.servicesInterfaces.IImageService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class CarService implements ICarService {
    private static Logger logger = Logger.getLogger(CarService.class);

    private CarRepository repository;
    private CategoryService categoryService;
    private CityService cityService;
    private IFeatureService featureService;
    private IImageService imageService;
    private IBookingService bookingService;
    private ModelMapper modelMapper;

    public CarService(CarRepository repository, CategoryService categoryService, CityService cityService, IFeatureService featureService, IImageService imageService, IBookingService bookingService, ModelMapper modelMapper) {
        this.repository = repository;
        this.categoryService = categoryService;
        this.cityService = cityService;
        this.featureService = featureService;
        this.imageService = imageService;
        this.bookingService = bookingService;
        this.modelMapper = modelMapper;
    }

    private CarDto carToCarDto(Car car) {
        CarDto carDto = modelMapper.map(car, CarDto.class);

        List<Booking> bookingList = bookingService.findByCarIdAndDateAfterNow(car.getId());
        List<LocalDate> datesExcluded = new ArrayList<>();

        for (Booking booking : bookingList) {
            for (LocalDate date = booking.getStartDateTime().toLocalDate();
                 date.isBefore(booking.getEndDate().plusDays(1));
                 date = date.plusDays(1)) {
                datesExcluded.add(date);
            }
        }

        carDto.setDatesExcluded(datesExcluded);

        return carDto;
    }

    private List<CarDto> filterRepeatedCars(List<Car> carList, LocalDate startDate, LocalDate endDate) {
        List<List<Car>> list = new ArrayList<>();

        Boolean found = false;
        for (Car car : carList) {
            found = false;
            for (List<Car> cars : list) {
                if (cars == null) break;
                if (cars.contains(car)) {
                    cars.add(car);
                    found = true;
                    break;
                }
            }
            if (!found) {
                List<Car> aux = new ArrayList<>();
                aux.add(car);
                list.add(aux);
            }
        }

        List<CarDto> carDtoFiltered = new ArrayList<>();
        for (List<Car> cars : list) {
                List<Booking> bookingList = bookingService.findByCarIdAndPeriod(cars.get(0).getId(),
                        startDate, endDate);

                if (bookingList.size() < 2){
                    carDtoFiltered.add(carToCarDto(cars.get(0)));
                }
                else {
                    Booking bookingBefore;
                    Booking bookingAfter;
                    for (int i = 0; i < bookingList.size() - 1; i++) {
                        bookingBefore = bookingList.get(i);
                        bookingAfter = bookingList.get(i + 1);

                        if (DAYS.between(bookingBefore.getEndDate(), bookingAfter.getStartDateTime().toLocalDate()) > 1) {
                            carDtoFiltered.add(carToCarDto(cars.get(0)));
                            break;
                        }
                    }
                }

        }

        return carDtoFiltered;
    }

    @Override
    public Boolean save(Car car) {
        Optional<Car> carOptional = repository.findByName(car.getName());
        if (carOptional.isEmpty()) {
            Optional<Category> categoryOptional = categoryService.findById(car.getCategory().getId());
            if (categoryOptional.isEmpty()) {
                logger.warn("Category doesnt exist when save a car. Id: " + car.getCategory().getId());
                return false;
            }

            Optional<City> cityOptional = cityService.findById(car.getCity().getId());
            if (cityOptional.isEmpty()) {
                logger.warn("City doesnt exist when save a car. Id: " + car.getCity().getId());
                return false;
            }

            Set<Feature> featureSet = new HashSet<>();
            for (Feature feature : car.getFeatures()) {
                Optional<Feature> featureOptional = featureService.findByNameAndIcon(feature.getName(), feature.getIcon());
                if (featureOptional.isPresent()) {
                    featureSet.add(featureOptional.get());
                } else {
                    logger.warn("Feature doesnt exist when save a car. Id: " + feature.getId());
                    return false;
                }
            }

            car.setCategory(categoryOptional.get());
            car.setCity(cityOptional.get());
            car.setFeatures(featureSet);

            repository.save(car);
            return true;

        } else {
            logger.debug("Trying to add a repeated car");
            return false;
        }
    }

    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Car> findByID(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<CarDto> findByIdDto(Integer id) {
        Optional<Car> carOptional = repository.findById(id);

        return carOptional.isPresent() ? Optional.of(carToCarDto(carOptional.get())) : Optional.empty();
    }

    @Override
    public List<Car> findAllByCity(String cityName) {
        return repository.findAllByCityName(cityName);
    }

    @Override
    public List<Car> findAllByCategory(String categoryTitle) {
        return repository.findAllByCategoryTitle(categoryTitle);
    }

    @Override
    public List<Car> findAllByCityAndCategory(String cityName, String categoryTitle) {
        return repository.findAllByCityNameAndCategoryTitle(cityName, categoryTitle);
    }

    @Override
    public List<CarDto> findAllByDate(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(23, 59, 59));
        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.of(23, 59, 59));
        LocalDateTime endDateTime2 = endDateTime.plusHours(23).plusMinutes(59).plusSeconds(59);

        List<Car> carList = repository.findByDate(startDateTime, endDateTime,
                startDate, endDate.plusDays(-1), endDateTime2);

        return filterRepeatedCars(carList, startDate, endDate);
    }

    @Override
    public List<CarDto> findAllByDateAndCity(LocalDate startDate, LocalDate endDate, String city) {
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(23, 59, 59));
        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.of(23, 59, 59));
        LocalDateTime endDateTime2 = endDateTime.plusHours(23).plusMinutes(59).plusSeconds(59);

        List<Car> carList = repository.findByDateAndCity(startDateTime, endDateTime,
                startDate, endDate.plusDays(-1), endDateTime2, city);

        return filterRepeatedCars(carList, startDate, endDate);
    }

    @Override
    public Boolean update(Car car) {
        Optional<Car> carOptional = repository.findByName(car.getName());
        if (carOptional.isPresent()) {
            Car carUpdated = repository.save(car);
            return true;
        }
        return false;
    }
}
