package com.dh.digitalCar.entities;

import javax.persistence.*;

@Entity
@Table(name = "Ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "ratingsGenerator", sequenceName = "ratingsGenerator")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ratingsGenerator")
    private Integer id;
    private Integer userId;

    public Rating() {
    }

}
