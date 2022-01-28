package com.dh.digitalCar.services;

import com.dh.digitalCar.entities.Politics;
import com.dh.digitalCar.repository.PoliticsRepository;
import com.dh.digitalCar.services.servicesInterfaces.IPoliticsService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliticsService implements IPoliticsService {
    private static Logger logger = Logger.getLogger(PoliticsService.class);

    private PoliticsRepository repository;

    public PoliticsService(PoliticsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Politics> find() {
        List<Politics> politicsList = repository.findAll();
        if (politicsList.size() > 0)
            return Optional.of(politicsList.get(0));
        else
            return Optional.empty();
    }

    @Override
    public Boolean update(Politics politics) {
        List<Politics> politicsList = repository.findAll();
        if (politicsList.size() > 0)
            politics.setId(politicsList.get(0).getId());

        repository.save(politics);
        return true;
    }
}
