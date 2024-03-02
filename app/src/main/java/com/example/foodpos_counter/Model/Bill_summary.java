package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bill_summary {

    @SerializedName("billpaidcount")
    @Expose
    private String billpaidcount;

    @SerializedName("billpaidamount")
    @Expose
    private String billpaidamount;

    @SerializedName("billcancelcount")
    @Expose
    private String billcancelcount;

    @SerializedName("billcancelamount")
    @Expose
    private String billcancelamount;

    @SerializedName("billonholdcount")
    @Expose
    private String billonholdcount;

    @SerializedName("billonholdamount")
    @Expose
    private String billonholdamount;

    @SerializedName("billonordercount")
    @Expose
    private String billonordercount;

    @SerializedName("billonorderamount")
    @Expose
    private String billonorderamount;

    @SerializedName("totalbill")
    @Expose
    private String totalbill;


    public Bill_summary(String billpaidcount, String billpaidamount, String billcancelcount, String billcancelamount, String billonholdcount, String billonholdamount, String billonordercount, String billonorderamount, String totalbill) {
        this.billpaidcount = billpaidcount;
        this.billpaidamount = billpaidamount;
        this.billcancelcount = billcancelcount;
        this.billcancelamount = billcancelamount;
        this.billonholdcount = billonholdcount;
        this.billonholdamount = billonholdamount;
        this.billonordercount = billonordercount;
        this.billonorderamount = billonorderamount;
        this.totalbill = totalbill;
    }

    public String getBillpaidcount() {
        return billpaidcount;
    }

    public void setBillpaidcount(String billpaidcount) {
        this.billpaidcount = billpaidcount;
    }

    public String getBillpaidamount() {
        return billpaidamount;
    }

    public void setBillpaidamount(String billpaidamount) {
        this.billpaidamount = billpaidamount;
    }

    public String getBillcancelcount() {
        return billcancelcount;
    }

    public void setBillcancelcount(String billcancelcount) {
        this.billcancelcount = billcancelcount;
    }

    public String getBillcancelamount() {
        return billcancelamount;
    }

    public void setBillcancelamount(String billcancelamount) {
        this.billcancelamount = billcancelamount;
    }

    public String getBillonholdcount() {
        return billonholdcount;
    }

    public void setBillonholdcount(String billonholdcount) {
        this.billonholdcount = billonholdcount;
    }

    public String getBillonholdamount() {
        return billonholdamount;
    }

    public void setBillonholdamount(String billonholdamount) {
        this.billonholdamount = billonholdamount;
    }

    public String getBillonordercount() {
        return billonordercount;
    }

    public void setBillonordercount(String billonordercount) {
        this.billonordercount = billonordercount;
    }

    public String getBillonorderamount() {
        return billonorderamount;
    }

    public void setBillonorderamount(String billonorderamount) {
        this.billonorderamount = billonorderamount;
    }

    public String getTotalbill() {
        return totalbill;
    }

    public void setTotalbill(String totalbill) {
        this.totalbill = totalbill;
    }
}
