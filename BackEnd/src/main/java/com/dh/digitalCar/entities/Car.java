package com.dh.digitalCar.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "carsGenerator", sequenceName = "carsGenerator")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carsGenerator")
    private Integer id;
    @NotBlank(message = "name must not be blank")
    private String name;
    @NotBlank(message = "description must not be blank")
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "car_features",
            joinColumns = {@JoinColumn(name = "car_id")},
            inverseJoinColumns = {@JoinColumn(name = "feature_id")})
    private Set<Feature> features;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "carId")
    private List<Image> images;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "carId")
    private Set<Rating> ratings;
    @OneToMany(mappedBy = "car")
    private List<Booking> bookings;
    @NotBlank(message = "address must not be blank")
    private String address;

    public Car() {
    }

    public Car(String name, String description) {
        this.name = name;
        this.description = description;
        this.features = new HashSet<>();
        this.images = new ArrayList<>();
//        this.ratings = new HashSet<>();
    }

    public Integer getId() {
        return id;
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

    public void setFeature(Feature feature) {
        this.features.add(feature);
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImage(Image image) {
        this.images.add(image);
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

//    public void setRatings(Rating rating) {
//        this.ratings.add(rating);
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name) && Objects.equals(description, car.description) && Objects.equals(features, car.features) && Objects.equals(images, car.images) && Objects.equals(category, car.category) && Objects.equals(city, car.city) && Objects.equals(ratings, car.ratings) && Objects.equals(bookings, car.bookings) && Objects.equals(address, car.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, features, images, category, city, ratings, bookings, address);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", features=" + features +
                ", images=" + images +
                ", category=" + category +
                ", city=" + city +
                ", ratings=" + ratings +
                ", bookings=" + bookings +
                ", address='" + address + '\'' +
                '}';
    }
}
