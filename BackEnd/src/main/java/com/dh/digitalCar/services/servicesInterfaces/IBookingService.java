package com.dh.digitalCar.services.servicesInterfaces;

import com.dh.digitalCar.entities.Booking;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IBookingService {
    Boolean save(Booking booking);
    List<Booking> findByCarId(Integer id);
    List<Booking> findByUserId(Integer id);
    List<Booking> findByCarIdAndDateAfterNow(Integer id);
    List<Booking> findByCarIdAndPeriod(Integer id, LocalDate start, LocalDate end);
}
