package com.dh.digitalCar.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "bookingsGenerator", sequenceName = "bookingsGenerator")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookingsGenerator")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private LocalDateTime startDateTime;
    private LocalDate endDate;

    public Booking() {
    }

    public Booking(Car car, User user, LocalDateTime startDateTime, LocalDate endDate) {
        this.car = car;
        this.user = user;
        this.startDateTime = startDateTime;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", car=" + car +
                ", user=" + user +
                ", startDateTime=" + startDateTime +
                ", endDate=" + endDate +
                '}';
    }
}
