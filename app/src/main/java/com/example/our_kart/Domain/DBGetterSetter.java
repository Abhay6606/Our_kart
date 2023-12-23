package com.example.our_kart.Domain;

public class DBGetterSetter {

   private String Title;
    private String Price;
    private String Image;
    private int itemCount;
    private int SnNo;
    private  double TotalPrice;

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getSnNo() {
        return SnNo;
    }

    public void setSnNo(int snNo) {
        SnNo = snNo;
    }

    public DBGetterSetter() {
    }

    public DBGetterSetter(String title, String price, String image) {
        Title = title;
        Price = price;
        Image = image;
    }
}
