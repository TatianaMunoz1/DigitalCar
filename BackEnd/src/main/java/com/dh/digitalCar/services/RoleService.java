package com.dh.digitalCar.services;

import com.dh.digitalCar.entities.Role;
import com.dh.digitalCar.repository.RoleRepository;
import com.dh.digitalCar.services.servicesInterfaces.IRoleService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    private static Logger logger = Logger.getLogger(RoleService.class);

    private RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Role> save(Role role) {
        Optional<Role> roleOptional = repository.findByName(role.getName());
        if (roleOptional.isEmpty()){
            return Optional.of(repository.save(role));
        }else {
            logger.debug("Trying to add a repeated role");
            return Optional.empty();
        }
    }

    @Override
    public Optional<Role> findByName(String name) {
        return repository.findByName(name);
    }
}
