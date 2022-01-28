package com.dh.digitalCar.dtos;

import com.dh.digitalCar.entities.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class CarDto {
    private Integer id;
    private String name;
    private String description;
    private Set<Feature> features;
    private List<Image> images;
    private Category category;
    private City city;
    private Set<Rating> ratings;
    private String address;

    private List<LocalDate> datesExcluded;

    public CarDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<LocalDate> getDatesExcluded() {
        return datesExcluded;
    }

    public void setDatesExcluded(List<LocalDate> datesExcluded) {
        this.datesExcluded = datesExcluded;
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", features=" + features +
                ", images=" + images +
                ", category=" + category +
                ", city=" + city +
                ", ratings=" + ratings +
                ", address='" + address + '\'' +
                ", datesExcluded=" + datesExcluded +
                '}';
    }
}
