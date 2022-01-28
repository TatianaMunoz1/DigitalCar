package com.dh.digitalCar.repository;

import com.dh.digitalCar.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findAllByCarId(Integer id);
    List<Booking> findAllByUserId(Integer id);
    List<Booking> findByCar_IdAndEndDateAfter(Integer id, LocalDate endDate);
    List<Booking> findAllByCarIdAndStartDateTimeBetweenOrCarIdAndEndDateBetweenOrderByStartDateTimeAsc
            (Integer id1, LocalDateTime startDateTime1, LocalDateTime startDateTime2,
             Integer id2, LocalDate endDate1, LocalDate endDate2);
}
