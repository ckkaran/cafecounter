package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.ArrayList;

public class UpdateOrderResult {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("orderid")
    @Expose
    private String orderid;
    @SerializedName("printerList")
    @Expose
    private ArrayList<Print_Details> printerlist;

    public UpdateOrderResult(String status, String message, String orderid, ArrayList<Print_Details> printerlist) {
        this.status = status;
        this.message = message;
        this.orderid = orderid;
        this.printerlist = printerlist;
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

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public ArrayList<Print_Details> getPrinterlist() {
        return printerlist;
    }

    public void setPrinterlist(ArrayList<Print_Details> printerlist) {
        this.printerlist = printerlist;
    }
}
