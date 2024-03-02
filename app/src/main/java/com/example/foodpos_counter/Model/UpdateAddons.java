package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateAddons {
    @SerializedName("addonsid")
    @Expose
    private String addonsid;

    @SerializedName("addonsqty")
    @Expose
    private String addonsqty;

    public UpdateAddons(String addonsid, String addonsqty) {
        this.addonsid = addonsid;
        this.addonsqty = addonsqty;
    }

    public String getAddonsid() {
        return addonsid;
    }

    public void setAddonsid(String addonsid) {
        this.addonsid = addonsid;
    }

    public String getAddonsqty() {
        return addonsqty;
    }

    public void setAddonsqty(String addonsqty) {
        this.addonsqty = addonsqty;
    }
}
