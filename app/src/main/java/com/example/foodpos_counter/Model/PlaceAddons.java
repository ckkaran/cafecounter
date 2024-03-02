package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceAddons {
    @SerializedName("addonsid")
    @Expose
    private String addonid;

    @SerializedName("addonsqty")
    @Expose
    private String qty;

    public PlaceAddons(String addonid, String qty) {
        this.addonid = addonid;
        this.qty = qty;
    }

    public String getAddonid() {
        return addonid;
    }

    public void setAddonid(String addonid) {
        this.addonid = addonid;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
