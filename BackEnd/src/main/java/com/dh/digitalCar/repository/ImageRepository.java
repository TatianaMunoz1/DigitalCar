package com.dh.digitalCar.repository;

import com.dh.digitalCar.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findByTitleAndUrl(String title, String url);
}
