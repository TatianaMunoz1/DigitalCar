package com.dh.digitalCar.repository;

import com.dh.digitalCar.entities.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Integer> {
    Optional<Feature> findByNameAndIcon(String name, String icon);
}
