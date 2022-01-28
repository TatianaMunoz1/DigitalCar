package com.dh.digitalCar.services.servicesInterfaces;

import com.dh.digitalCar.entities.Politics;

import java.util.Optional;

public interface IPoliticsService {
    Optional<Politics> find();
    Boolean update(Politics politics);
}
