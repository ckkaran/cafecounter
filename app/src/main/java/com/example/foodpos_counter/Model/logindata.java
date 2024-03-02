package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class logindata {

    @SerializedName("counterId")
    @Expose
    private String counterId;
    @SerializedName("counterName")
    @Expose
    private String counterName;

    public logindata(String counterId, String counterName) {
        this.counterId = counterId;
        this.counterName = counterName;
    }

    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }
}
