package com.dh.digitalCar.dtos;

import javax.validation.constraints.NotBlank;

public class CategoryDto {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String imgUrl;

    public CategoryDto() {
    }

    public CategoryDto(String title, String description, String imgUrl) {
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
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
    public String toString() {
        return "CategoryDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
