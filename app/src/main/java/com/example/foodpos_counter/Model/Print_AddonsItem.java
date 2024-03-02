package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Print_AddonsItem {
    @SerializedName("addOnId")
    @Expose
    private String addOnId;

    @SerializedName("addOnName")
    @Expose
    private String addOnName;

    @SerializedName("addOnPrice")
    @Expose
    private String addOnPrice;

    @SerializedName("addOnQty")
    @Expose
    private String addOnQty;

    public Print_AddonsItem(String addOnId, String addOnName, String addOnPrice, String addOnQty) {
        this.addOnId = addOnId;
        this.addOnName = addOnName;
        this.addOnPrice = addOnPrice;
        this.addOnQty = addOnQty;
    }

    public String getAddOnId() {
        return addOnId;
    }

    public void setAddOnId(String addOnId) {
        this.addOnId = addOnId;
    }

    public String getAddOnName() {
        return addOnName;
    }

    public void setAddOnName(String addOnName) {
        this.addOnName = addOnName;
    }

    public String getAddOnPrice() {
        return addOnPrice;
    }

    public void setAddOnPrice(String addOnPrice) {
        this.addOnPrice = addOnPrice;
    }

    public String getAddOnQty() {
        return addOnQty;
    }

    public void setAddOnQty(String addOnQty) {
        this.addOnQty = addOnQty;
    }
}
