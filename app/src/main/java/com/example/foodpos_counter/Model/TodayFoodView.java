package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodayFoodView {
    @SerializedName("foodname")
    @Expose
    private String foodname;

    @SerializedName("variantname")
    @Expose
    private String variantname;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("qty")
    @Expose
    private String qty;

    @SerializedName("totalamount")
    @Expose
    private String totalamount;

    public TodayFoodView(String foodname, String variantname, String price, String qty, String totalamount) {
        this.foodname = foodname;
        this.variantname = variantname;
        this.price = price;
        this.qty = qty;
        this.totalamount = totalamount;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getVariantname() {
        return variantname;
    }

    public void setVariantname(String variantname) {
        this.variantname = variantname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }
}
