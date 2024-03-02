package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUnpaid_bill {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private UnpaidCounterDetails data;

    public GetUnpaid_bill(String status, String message, UnpaidCounterDetails data) {
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

    public UnpaidCounterDetails getData() {
        return data;
    }

    public void setData(UnpaidCounterDetails data) {
        this.data = data;
    }
}
