package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CounterWise {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private ArrayList<CounterWiseList> data;

    @SerializedName("todaysaleamount")
    @Expose
    private String todaysaleamount;

    public CounterWise(String status, String message, ArrayList<CounterWiseList> data, String todaysaleamount) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.todaysaleamount = todaysaleamount;
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

    public ArrayList<CounterWiseList> getData() {
        return data;
    }

    public void setData(ArrayList<CounterWiseList> data) {
        this.data = data;
    }

    public String getTodaysaleamount() {
        return todaysaleamount;
    }

    public void setTodaysaleamount(String todaysaleamount) {
        this.todaysaleamount = todaysaleamount;
    }
}
