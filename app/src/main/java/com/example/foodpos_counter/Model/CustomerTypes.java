package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerTypes {

    @SerializedName("customertypeid")
    @Expose
    private String customertypeid;

    @SerializedName("customertypename")
    @Expose
    private String customertypename;

    public CustomerTypes(String customertypeid, String customertypename) {
        this.customertypeid = customertypeid;
        this.customertypename = customertypename;
    }

    public String getCustomertypeid() {
        return customertypeid;
    }

    public void setCustomertypeid(String customertypeid) {
        this.customertypeid = customertypeid;
    }

    public String getCustomertypename() {
        return customertypename;
    }

    public void setCustomertypename(String customertypename) {
        this.customertypename = customertypename;
    }
}
