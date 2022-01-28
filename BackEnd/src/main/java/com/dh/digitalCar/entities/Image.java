package com.dh.digitalCar.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "imagesGenerator", sequenceName = "imagesGenerator")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imagesGenerator")
    private Integer id;
    @NotBlank(message = "Title must not be blank")
    private String title;
    @NotNull(message = "Position must not be null")
    private Integer position;
    @NotBlank(message = "Url must not be blank")
    private String url;

    public Image() {
    }

    public Image(String title,Integer position, String url){
        this.title = title;
        this.position = position;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", position=" + position +
                ", url='" + url + '\'' +
                '}';
    }
}
