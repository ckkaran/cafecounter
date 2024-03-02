package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FoodWise {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private ArrayList<FoodWiseList> data;

    @SerializedName("totalamount")
    @Expose
    private String totalamount;

    public FoodWise(String status, String message, ArrayList<FoodWiseList> data, String totalamount) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.totalamount = totalamount;
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

    public ArrayList<FoodWiseList> getData() {
        return data;
    }

    public void setData(ArrayList<FoodWiseList> data) {
        this.data = data;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }
}
