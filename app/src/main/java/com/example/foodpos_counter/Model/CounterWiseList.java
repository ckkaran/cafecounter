package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CounterWiseList {

    @SerializedName("salesdate")
    @Expose
    private String salesdate;

    @SerializedName("saleinvoice")
    @Expose
    private String saleinvoice;

    @SerializedName("countername")
    @Expose
    private String countername;

    @SerializedName("foodtotalamount")
    @Expose
    private String foodtotalamount;

    @SerializedName("vatamount")
    @Expose
    private String vatamount;

    @SerializedName("servicecharge")
    @Expose
    private String servicecharge;

    @SerializedName("discount")
    @Expose
    private String discount;

    @SerializedName("totalamount")
    @Expose
    private String totalamount;

    public CounterWiseList(String salesdate, String saleinvoice, String countername, String foodtotalamount, String vatamount, String servicecharge, String discount, String totalamount) {
        this.salesdate = salesdate;
        this.saleinvoice = saleinvoice;
        this.countername = countername;
        this.foodtotalamount = foodtotalamount;
        this.vatamount = vatamount;
        this.servicecharge = servicecharge;
        this.discount = discount;
        this.totalamount = totalamount;
    }

    public String getSalesdate() {
        return salesdate;
    }

    public void setSalesdate(String salesdate) {
        this.salesdate = salesdate;
    }

    public String getSaleinvoice() {
        return saleinvoice;
    }

    public void setSaleinvoice(String saleinvoice) {
        this.saleinvoice = saleinvoice;
    }

    public String getCountername() {
        return countername;
    }

    public void setCountername(String countername) {
        this.countername = countername;
    }

    public String getFoodtotalamount() {
        return foodtotalamount;
    }

    public void setFoodtotalamount(String foodtotalamount) {
        this.foodtotalamount = foodtotalamount;
    }

    public String getVatamount() {
        return vatamount;
    }

    public void setVatamount(String vatamount) {
        this.vatamount = vatamount;
    }

    public String getServicecharge() {
        return servicecharge;
    }

    public void setServicecharge(String servicecharge) {
        this.servicecharge = servicecharge;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }
}
