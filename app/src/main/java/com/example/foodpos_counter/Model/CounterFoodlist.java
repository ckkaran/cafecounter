package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CounterFoodlist {
    @SerializedName("foodname")
    @Expose
    private String foodname;

    @SerializedName("foodprice")
    @Expose
    private String foodprice;

    @SerializedName("foodqty")
    @Expose
    private String foodqty;

    @SerializedName("totalamount")
    @Expose
    private String totalamount;

    @SerializedName("addons")
    @Expose
    private ArrayList<CounterAddons> addons;

    public CounterFoodlist(String foodname, String foodprice, String foodqty, String totalamount, ArrayList<CounterAddons> addons) {
        this.foodname = foodname;
        this.foodprice = foodprice;
        this.foodqty = foodqty;
        this.totalamount = totalamount;
        this.addons = addons;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(String foodprice) {
        this.foodprice = foodprice;
    }

    public String getFoodqty() {
        return foodqty;
    }

    public void setFoodqty(String foodqty) {
        this.foodqty = foodqty;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public ArrayList<CounterAddons> getAddons() {
        return addons;
    }

    public void setAddons(ArrayList<CounterAddons> addons) {
        this.addons = addons;
    }
}
