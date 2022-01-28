package com.dh.digitalCar.controllers;

import com.dh.digitalCar.entities.Booking;
import com.dh.digitalCar.services.servicesInterfaces.IBookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("/booking")
public class BookingController {
    private IBookingService bookingService;

    public BookingController(IBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/find")
    public ResponseEntity<?> getBookingByCarIdOrUserId(Integer car, Integer userId) {
        if (car != null)
            return ResponseEntity.ok(bookingService.findByCarId(car));
        if (userId != null)
            return ResponseEntity.ok(bookingService.findByUserId(userId));
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping
    public ResponseEntity<?> newBooking(@RequestBody Booking booking){
        return bookingService.save(booking) ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
