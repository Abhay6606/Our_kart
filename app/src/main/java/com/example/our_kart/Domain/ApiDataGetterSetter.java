package com.example.our_kart.Domain;

import java.util.ArrayList;

public class ApiDataGetterSetter {
    String title;
    String price;
    String description;
    String rating;
    String review;

    public ApiDataGetterSetter() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ApiDataGetterSetter(String title, String price, String description, String rating, String review, String image) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.review = review;
        this.image = image;
    }

    String image;


}
