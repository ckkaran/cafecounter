package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Items_summary {

    @SerializedName("fooddetails")
    @Expose
    private ArrayList<Fooddetails> fooddetails;

    @SerializedName("totalitemscount")
    @Expose
    private String totalitemscount;

    @SerializedName("totalitemsamount")
    @Expose
    private String totalitemsamount;


    public Items_summary(ArrayList<Fooddetails> fooddetails, String totalitemscount, String totalitemsamount) {
        this.fooddetails = fooddetails;
        this.totalitemscount = totalitemscount;
        this.totalitemsamount = totalitemsamount;
    }

    public ArrayList<Fooddetails> getFooddetails() {
        return fooddetails;
    }

    public void setFooddetails(ArrayList<Fooddetails> fooddetails) {
        this.fooddetails = fooddetails;
    }

    public String getTotalitemscount() {
        return totalitemscount;
    }

    public void setTotalitemscount(String totalitemscount) {
        this.totalitemscount = totalitemscount;
    }

    public String getTotalitemsamount() {
        return totalitemsamount;
    }

    public void setTotalitemsamount(String totalitemsamount) {
        this.totalitemsamount = totalitemsamount;
    }
}
