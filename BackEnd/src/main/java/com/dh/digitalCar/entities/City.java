package com.dh.digitalCar.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "Cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "citiesGenerator", sequenceName = "citiesGenerator")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "citiesGenerator")
    private Integer id;
    @NotBlank(message = "Type must not be blank")
    private String type;
    @NotBlank(message = "Name must not be blank")
    private String name;
    @NotBlank(message = "Country must not be blank")
    private String country;
    @NotNull(message = "Latitude must not be null")
    private Double latitude;
    @NotNull(message = "Longitude must not be null")
    private Double longitude;

    public City() {
    }

    public City(String type, String name, String country, Double latitude, Double longitude) {
        this.type = type;
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
//        this.cars = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

     public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public Double getLongitude(){
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(type, city.type) && Objects.equals(name, city.name) && Objects.equals(country, city.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, country);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
