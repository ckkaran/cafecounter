package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Addons {

    @SerializedName("addonid")
    @Expose
    private String addonid;

    @SerializedName("addonname")
    @Expose
    private String addonname;

    @SerializedName("addonprice")
    @Expose
    private String addonprice;

    public Addons(String addonid, String addonname, String addonprice) {
        this.addonid = addonid;
        this.addonname = addonname;
        this.addonprice = addonprice;
    }

    public String getAddonid() {
        return addonid;
    }

    public void setAddonid(String addonid) {
        this.addonid = addonid;
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
}
