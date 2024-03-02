package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fooddetails {

    @SerializedName("foodname")
    @Expose
    private String foodname;

    @SerializedName("foodqty")
    @Expose
    private String foodqty;

    @SerializedName("foodprice")
    @Expose
    private String foodprice;


    public Fooddetails(String foodname, String foodqty, String foodprice) {
        this.foodname = foodname;
        this.foodqty = foodqty;
        this.foodprice = foodprice;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFoodqty() {
        return foodqty;
    }

    public void setFoodqty(String foodqty) {
        this.foodqty = foodqty;
    }

    public String getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(String foodprice) {
        this.foodprice = foodprice;
    }
}
