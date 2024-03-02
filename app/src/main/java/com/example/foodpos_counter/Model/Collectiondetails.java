package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Collectiondetails {

    @SerializedName("paymentname")
    @Expose
    private String paymentname;

    @SerializedName("paymentamount")
    @Expose
    private String paymentamount;

    @SerializedName("paymenttotalorder")
    @Expose
    private String paymenttotalorder;

    public Collectiondetails(String paymentname, String paymentamount, String paymenttotalorder) {
        this.paymentname = paymentname;
        this.paymentamount = paymentamount;
        this.paymenttotalorder = paymenttotalorder;
    }

    public String getPaymentname() {
        return paymentname;
    }

    public void setPaymentname(String paymentname) {
        this.paymentname = paymentname;
    }

    public String getPaymentamount() {
        return paymentamount;
    }

    public void setPaymentamount(String paymentamount) {
        this.paymentamount = paymentamount;
    }

    public String getPaymenttotalorder() {
        return paymenttotalorder;
    }

    public void setPaymenttotalorder(String paymenttotalorder) {
        this.paymenttotalorder = paymenttotalorder;
    }
}
