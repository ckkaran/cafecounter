package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InactiveFoods {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private ArrayList<InactiveFoodsList> data;

    public InactiveFoods(String status, String message, ArrayList<InactiveFoodsList> data) {
        this.status = status;
        this.message = message;
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

    public ArrayList<InactiveFoodsList> getData() {
        return data;
    }

    public void setData(ArrayList<InactiveFoodsList> data) {
        this.data = data;
    }
}
