package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CounterAddons {
    @SerializedName("addonname")
    @Expose
    private String addonname;

    @SerializedName("addonprice")
    @Expose
    private String addonprice;

    @SerializedName("addonqty")
    @Expose
    private String addonqty;

    @SerializedName("addontotal")
    @Expose
    private String addontotal;

    public CounterAddons(String addonname, String addonprice, String addonqty, String addontotal) {
        this.addonname = addonname;
        this.addonprice = addonprice;
        this.addonqty = addonqty;
        this.addontotal = addontotal;
    }

    public String getAddonname() {
        return addonname;
    }

    public void setAddonname(String addonname) {
        this.addonname = addonname;
    }

    public String getAddonprice() {
        return addonprice;
    }

    public void setAddonprice(String addonprice) {
        this.addonprice = addonprice;
    }

    public String getAddonqty() {
        return addonqty;
    }

    public void setAddonqty(String addonqty) {
        this.addonqty = addonqty;
    }

    public String getAddontotal() {
        return addontotal;
    }

    public void setAddontotal(String addontotal) {
        this.addontotal = addontotal;
    }
}
