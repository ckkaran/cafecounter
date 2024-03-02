package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodWiseList {

    @SerializedName("orderdate")
    @Expose
    private String orderdate;

    @SerializedName("ProductName")
    @Expose
    private String ProductName;

    @SerializedName("variantName")
    @Expose
    private String variantName;

    @SerializedName("qty")
    @Expose
    private String qty;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("totalprice")
    @Expose
    private String totalprice;

    public FoodWiseList(String orderdate, String productName, String variantName, String qty, String price, String totalprice) {
        this.orderdate = orderdate;
        ProductName = productName;
        this.variantName = variantName;
        this.qty = qty;
        this.price = price;
        this.totalprice = totalprice;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }
}
