package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TodayOrderDetails {
    @SerializedName("orderid")
    @Expose
    private String orderid;

    @SerializedName("saleinvoice")
    @Expose
    private String saleinvoice;

    @SerializedName("foodlist")
    @Expose
    private ArrayList<TodayFoodView> foodlist;

    @SerializedName("subtotal")
    @Expose
    private String subtotal;

    @SerializedName("servicecharge")
    @Expose
    private String servicecharge;

    @SerializedName("servicetax")
    @Expose
    private String servicetax;

    @SerializedName("totalvat")
    @Expose
    private String totalvat;

    @SerializedName("grandtotal")
    @Expose
    private String grandtotal;

    @SerializedName("customerpaid")
    @Expose
    private String customerpaid;

    @SerializedName("dueamount")
    @Expose
    private String dueamount;

    public TodayOrderDetails(String orderid, String saleinvoice, ArrayList<TodayFoodView> foodlist, String subtotal, String servicecharge, String servicetax, String totalvat, String grandtotal, String customerpaid, String dueamount) {
        this.orderid = orderid;
        this.saleinvoice = saleinvoice;
        this.foodlist = foodlist;
        this.subtotal = subtotal;
        this.servicecharge = servicecharge;
        this.servicetax = servicetax;
        this.totalvat = totalvat;
        this.grandtotal = grandtotal;
        this.customerpaid = customerpaid;
        this.dueamount = dueamount;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getSaleinvoice() {
        return saleinvoice;
    }

    public void setSaleinvoice(String saleinvoice) {
        this.saleinvoice = saleinvoice;
    }

    public ArrayList<TodayFoodView> getFoodlist() {
        return foodlist;
    }

    public void setFoodlist(ArrayList<TodayFoodView> foodlist) {
        this.foodlist = foodlist;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getServicecharge() {
        return servicecharge;
    }

    public void setServicecharge(String servicecharge) {
        this.servicecharge = servicecharge;
    }

    public String getServicetax() {
        return servicetax;
    }

    public void setServicetax(String servicetax) {
        this.servicetax = servicetax;
    }

    public String getTotalvat() {
        return totalvat;
    }

    public void setTotalvat(String totalvat) {
        this.totalvat = totalvat;
    }

    public String getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(String grandtotal) {
        this.grandtotal = grandtotal;
    }

    public String getCustomerpaid() {
        return customerpaid;
    }

    public void setCustomerpaid(String customerpaid) {
        this.customerpaid = customerpaid;
    }

    public String getDueamount() {
        return dueamount;
    }

    public void setDueamount(String dueamount) {
        this.dueamount = dueamount;
    }
}
