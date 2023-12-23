package com.example.our_kart.Domain;

public class CartGetterSetter {

    String Image,price,title;

    public CartGetterSetter(String image, String price, String title) {
        Image = image;
        this.price = price;
        this.title = title;
    }

    public CartGetterSetter() {
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
