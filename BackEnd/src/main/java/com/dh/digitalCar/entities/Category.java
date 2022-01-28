package com.dh.digitalCar.entities;

import com.dh.digitalCar.dtos.CategoryDto;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "categoryGenerator", sequenceName = "categoryGenerator")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoryGenerator")
    private Integer id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String imgUrl;

    public Category() {
    }

    public Category(String title, String description, String imgUrl) {
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
    }

    public void clone(CategoryDto categoryDto){
        this.title = categoryDto.getTitle();
        this.description = categoryDto.getDescription();
        this.imgUrl = categoryDto.getImgUrl();
    }

    @Profile("test")
    public void setId(Integer id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(title, category.title) && Objects.equals(description, category.description) && Objects.equals(imgUrl, category.imgUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, imgUrl);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
