package com.dh.digitalCar.services;

import com.dh.digitalCar.entities.Image;
import com.dh.digitalCar.repository.ImageRepository;
import com.dh.digitalCar.services.servicesInterfaces.IImageService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService implements IImageService {
    private static Logger logger = Logger.getLogger(ImageService.class);

    private ImageRepository repository;

    public ImageService(ImageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean save(Image image) {
        Optional<Image> imageOptional = repository.findByTitleAndUrl(image.getTitle(), image.getUrl());
        if (imageOptional.isEmpty()){
            repository.save(image);
            return true;
        }else {
            logger.debug("Trying to add a repeated image");
            return false;
        }
    }

    @Override
    public Optional<Image> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Image> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Image image) {
        repository.delete(image);
    }
}
