package com.dh.digitalCar.services;

import com.dh.digitalCar.entities.Booking;
import com.dh.digitalCar.entities.Car;
import com.dh.digitalCar.entities.User;
import com.dh.digitalCar.repository.BookingRepository;
import com.dh.digitalCar.services.servicesInterfaces.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements IBookingService {
    private BookingRepository repository;
    private UserService userService;
    private CarService carService;

    public BookingService(BookingRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Override
    public Boolean save(Booking booking) {
        Optional<User> userOptional = userService.findByEmail(booking.getUser().getEmail());
        Optional<Car> carOptional = carService.findByID(booking.getCar().getId());

        if (userOptional.isPresent() && carOptional.isPresent()){
            booking.setUser(userOptional.get());
            booking.setCar(carOptional.get());

            repository.save(booking);
            return true;
        }
        return false;
    }

    @Override
    public List<Booking> findByCarId(Integer id) {
        return repository.findAllByCarId(id);
    }

    @Override
    public List<Booking> findByUserId(Integer id) {
        return repository.findAllByUserId(id);
    }

    @Override
    public List<Booking> findByCarIdAndDateAfterNow(Integer id) {
        LocalDate endDate = LocalDate.now().plusDays(-1);//pasar -1 a jpa ?

        return repository.findByCar_IdAndEndDateAfter(id, endDate);
    }

    @Override
    public List<Booking> findByCarIdAndPeriod(Integer id, LocalDate start, LocalDate end) {
        LocalDateTime startDateTime = LocalDateTime.of(start, LocalTime.of(0, 0, 0));
        LocalDateTime endDateTime = LocalDateTime.of(end, LocalTime.of(23, 59, 59));

        return repository.findAllByCarIdAndStartDateTimeBetweenOrCarIdAndEndDateBetweenOrderByStartDateTimeAsc
                (id, startDateTime, endDateTime, id, start, end);
    }
}
