package com.dh.digitalCar.services;

import com.dh.digitalCar.entities.City;
import com.dh.digitalCar.repository.CityRepository;
import com.dh.digitalCar.services.servicesInterfaces.ICityService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService implements ICityService {
    private static Logger logger = Logger.getLogger(CityService.class);

    private CityRepository repository;

    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean save(City city) {
        Optional<City> cityOptional = repository.findByNameAndCountry(city.getName(), city.getCountry());
        if (cityOptional.isEmpty()) {
            repository.save(city);
            return true;
        } else
            logger.debug("Trying to add repeat city");
            return false;
    }

    @Override
    public List<City> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<City> findById(Integer id) {
        return repository.findById(id);
    }
}
