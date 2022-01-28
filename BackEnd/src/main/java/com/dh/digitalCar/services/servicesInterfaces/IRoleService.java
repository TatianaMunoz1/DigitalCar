package com.dh.digitalCar.services.servicesInterfaces;

import com.dh.digitalCar.entities.Role;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> save(Role role);
    Optional<Role> findByName(String name);
}
