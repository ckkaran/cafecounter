package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TodayOrder {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("grandtotal")
    @Expose
    private String grandtotal;

    @SerializedName("data")
    @Expose
    private ArrayList<TodayOrderList> data;

    public TodayOrder(String status, String message, String grandtotal, ArrayList<TodayOrderList> data) {
        this.status = status;
        this.message = message;
        this.grandtotal = grandtotal;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(String grandtotal) {
        this.grandtotal = grandtotal;
    }

    public ArrayList<TodayOrderList> getData() {
        return data;
    }

    public void setData(ArrayList<TodayOrderList> data) {
        this.data = data;
    }
}
