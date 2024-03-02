package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UpdateFoodlist {
    @SerializedName("productid")
    @Expose
    private String productid;

    @SerializedName("qty")
    @Expose
    private String qty;

    @SerializedName("addons")
    @Expose
    private ArrayList<UpdateAddons> addons;

    @SerializedName("variantid")
    @Expose
    private String variantid;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("notes")
    @Expose
    private String notes;

    public UpdateFoodlist(String productid, String qty, ArrayList<UpdateAddons> addons, String variantid, String price, String notes) {
        this.productid = productid;
        this.qty = qty;
        this.addons = addons;
        this.variantid = variantid;
        this.price = price;
        this.notes = notes;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public ArrayList<UpdateAddons> getAddons() {
        return addons;
    }

    public void setAddons(ArrayList<UpdateAddons> addons) {
        this.addons = addons;
    }

    public String getVariantid() {
        return variantid;
    }

    public void setVariantid(String variantid) {
        this.variantid = variantid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
