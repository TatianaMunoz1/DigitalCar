package com.dh.digitalCar.services.servicesInterfaces;


import com.dh.digitalCar.entities.Image;

import java.util.List;
import java.util.Optional;

public interface IImageService {
    Boolean save (Image image);
    Optional<Image> findById(Integer id);
    List<Image> findAll();
    void delete(Image image);

}
