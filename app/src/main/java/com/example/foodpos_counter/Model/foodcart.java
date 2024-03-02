package com.example.foodpos_counter.Model;

public class foodcart {
    String image;
    String name;
    String rate;
    String rate1;

    public foodcart(String image, String name, String rate, String rate1) {
        this.image = image;
        this.name = name;
        this.rate = rate;
        this.rate1 = rate1;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRate1() {
        return rate1;
    }

    public void setRate1(String rate1) {
        this.rate1 = rate1;
    }
}
