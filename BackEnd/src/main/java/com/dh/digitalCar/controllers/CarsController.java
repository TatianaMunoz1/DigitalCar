package com.dh.digitalCar.controllers;

import com.dh.digitalCar.dtos.CarDto;
import com.dh.digitalCar.entities.Car;
import com.dh.digitalCar.services.servicesInterfaces.ICarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/cars")
public class CarsController {
    private ICarService carService;

    public CarsController(ICarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCars() {
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getCarByCityCategoryPeriod(String city, String category,
                                                        String startDate, String endDate) {
        if(city != null || category != null || startDate != null) {
            if (category != null && city != null)
                return ResponseEntity.ok(carService.findAllByCityAndCategory(city, category));
            if (category != null)
                return ResponseEntity.ok(carService.findAllByCategory(category));
            if (city != null && startDate == null)
                return ResponseEntity.ok(carService.findAllByCity(city));

            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            if (startDate != null &&  city == null)
                return ResponseEntity.ok(carService.findAllByDate(start, end));

            return ResponseEntity.ok(carService.findAllByDateAndCity(start, end, city));

        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarById(@PathVariable Integer id) {
        Optional<CarDto> carOptional = carService.findByIdDto(id);
        return carOptional.isPresent() ? ResponseEntity.ok(carOptional.get()) : ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<?> addCar(@RequestBody @Valid Car car) {
        return carService.save(car) ? ResponseEntity.status(HttpStatus.CREATED).build() :
                ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PutMapping
    public ResponseEntity<?> updateCar(@RequestBody @Valid Car car) {
        return carService.update(car) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
