package com.dh.digitalCar.repository;

import com.dh.digitalCar.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByName(String name);
    List<Car> findAllByCategoryTitle(String title);
    List<Car> findAllByCityName(String name);
    List<Car> findAllByCityNameAndCategoryTitle(String name, String title);
    @Query("FROM Car c left join c.bookings b on c.id = b.car.id WHERE " +
            "b.id is null " +
            "or b.startDateTime BETWEEN :startDateTime AND :endDateTime " +
            "or b.endDate BETWEEN :startDate AND :endDate " +
            "or b.startDateTime > :endDateTime2 " +
            "or b.endDate < :startDate"
//            FROM Cars c left join c.bookings b on cars.id=bookings.car_id where
//            bookings.id is null or
//            bookings.start_date_time between "2021-12-27 23:59:00" and "2021-12-31 23:59:00" or
//            bookings.end_date between "2021-12-27" and "2021-12-30" or
//            bookings.start_date_time > "2021-12-31 23:59:00" or
//            bookings.end_date < "2021-12-27"
    )
    List<Car> findByDate(LocalDateTime startDateTime, LocalDateTime endDateTime,
                         LocalDate startDate, LocalDate endDate, LocalDateTime endDateTime2);

    @Query("FROM Car c left join c.bookings b on c.id = b.car.id WHERE " +
            "(b.id is null " +
            "or b.startDateTime BETWEEN :startDateTime AND :endDateTime " +
            "or b.endDate BETWEEN :startDate AND :endDate " +
            "or b.startDateTime > :endDateTime2 " +
            "or b.endDate < :startDate) and c.city.name = :city"
    )
    List<Car> findByDateAndCity(LocalDateTime startDateTime, LocalDateTime endDateTime,
                         LocalDate startDate, LocalDate endDate, LocalDateTime endDateTime2, String city);
}
