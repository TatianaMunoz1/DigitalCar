package com.dh.digitalCar.services.servicesInterfaces;

import com.dh.digitalCar.entities.Feature;


import java.util.List;
import java.util.Optional;

public interface IFeatureService {
    Boolean save(Feature feature);
    Optional<Feature> findById(Integer id);
    Optional<Feature> findByNameAndIcon(String name, String icon);
    List<Feature> findAll();
    void delete(Feature feature);
}
