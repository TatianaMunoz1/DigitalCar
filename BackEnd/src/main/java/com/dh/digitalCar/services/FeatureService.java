package com.dh.digitalCar.services;

import com.dh.digitalCar.entities.Feature;
import com.dh.digitalCar.repository.FeatureRepository;
import com.dh.digitalCar.services.servicesInterfaces.IFeatureService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeatureService implements IFeatureService {
    private static Logger logger = Logger.getLogger(FeatureService.class);

    private FeatureRepository repository;

    public FeatureService(FeatureRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean save(Feature feature) {
        Optional<Feature> featureOptional = repository.findByNameAndIcon(feature.getName(), feature.getIcon());
        if (featureOptional.isEmpty()){
            repository.save(feature);
            return true;
        }else {
            logger.debug("Trying to add a repeated feature");
            return false;
        }
    }

    @Override
    public Optional<Feature> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Feature> findByNameAndIcon(String name, String icon) {
        return repository.findByNameAndIcon(name, icon);
    }

    @Override
    public List<Feature> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Feature feature){repository.delete(feature);}
}
