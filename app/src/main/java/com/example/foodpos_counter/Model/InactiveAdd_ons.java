package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InactiveAdd_ons {

    @SerializedName("addonid")
    @Expose
    private String addonsid;
    @SerializedName("addonname")
    @Expose
    private String addonsname;
    @SerializedName("addonprice")
    @Expose
    private String addonsprice;

    public InactiveAdd_ons(String addonsid, String addonsname, String addonsprice) {
        this.addonsid = addonsid;
        this.addonsname = addonsname;
        this.addonsprice = addonsprice;
    }

    public String getAddonsid() {
        return addonsid;
    }

    public void setAddonsid(String addonsid) {
        this.addonsid = addonsid;
    }

    public String getAddonsname() {
        return addonsname;
    }

    public void setAddonsname(String addonsname) {
        this.addonsname = addonsname;
    }

    public String getAddonsprice() {
        return addonsprice;
    }

    public void setAddonsprice(String addonsprice) {
        this.addonsprice = addonsprice;
    }
}
