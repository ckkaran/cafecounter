package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Count {

    @SerializedName("ongoingcount")
    @Expose
    private String ongoingcount;

    @SerializedName("selfandqrcount")
    @Expose
    private String selfandqrcount;


    public Count(String ongoingcount, String selfandqrcount) {
        this.ongoingcount = ongoingcount;
        this.selfandqrcount = selfandqrcount;
    }

    public String getOngoingcount() {
        return ongoingcount;
    }

    public void setOngoingcount(String ongoingcount) {
        this.ongoingcount = ongoingcount;
    }

    public String getSelfandqrcount() {
        return selfandqrcount;
    }

    public void setSelfandqrcount(String selfandqrcount) {
        this.selfandqrcount = selfandqrcount;
    }
}
