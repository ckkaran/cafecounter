package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PlaceOrderList {

    @SerializedName("customerid")
    @Expose
    private String customerid;

    @SerializedName("customertype")
    @Expose
    private String customertype;

    @SerializedName("waiterid")
    @Expose
    private String waiterid;

    @SerializedName("counterid")
    @Expose
    private String counterid;

    @SerializedName("grandtotal")
    @Expose
    private String grandtotal;

    @SerializedName("paymenttype")
    @Expose
    private String paymenttype;

    @SerializedName("card_terminal")
    @Expose
    private String card_terminal;

    @SerializedName("bank")
    @Expose
    private String bank;

    @SerializedName("last4digit")
    @Expose
    private String last4digit;

    @SerializedName("customer_paid_amount")
    @Expose
    private String customer_paid_amount;

    @SerializedName("change_due")
    @Expose
    private String change_due;

    @SerializedName("tableid")
    @Expose
    private String tableid;

    @SerializedName("totalprice")
    @Expose
    private String totalprice;

    @SerializedName("totalfoodamount")
    @Expose
    private String totalfoodamount;

    @SerializedName("totalvatamount")
    @Expose
    private String totalvatamount;

    @SerializedName("seat")
    @Expose
    private String seat;

    @SerializedName("foodlist")
    @Expose
    private ArrayList<PlaceOrderFood> foodlist;

    public PlaceOrderList(String customerid, String customertype, String waiterid, String counterid, String grandtotal,
                          String paymenttype,
                          String card_terminal,
                          String bank,
                          String last4digit,
                          String customer_paid_amount,
                          String change_due, String tableid, String totalprice,
                          String totalfoodamount, String totalvatamount, String seat, ArrayList<PlaceOrderFood> foodlist) {
        this.customerid = customerid;
        this.customertype = customertype;
        this.waiterid = waiterid;
        this.counterid = counterid;
        this.grandtotal = grandtotal;
        this.paymenttype = paymenttype;
        this.card_terminal = card_terminal;
        this.bank = bank;
        this.last4digit = last4digit;
        this.customer_paid_amount = customer_paid_amount;
        this.change_due = change_due;
        this.tableid = tableid;
        this.totalprice = totalprice;
        this.totalfoodamount = totalfoodamount;
        this.totalvatamount = totalvatamount;
        this.seat = seat;
        this.foodlist = foodlist;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }


    public String getCustomertype() {
        return customertype;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }

    public String getWaiterid() {
        return waiterid;
    }

    public void setWaiterid(String waiterid) {
        this.waiterid = waiterid;
    }

    public String getCounterid() {
        return counterid;
    }

    public void setCounterid(String counterid) {
        this.counterid = counterid;
    }

    public String getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(String grandtotal) {
        this.grandtotal = grandtotal;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getCard_terminal() {
        return card_terminal;
    }

    public void setCard_terminal(String card_terminal) {
        this.card_terminal = card_terminal;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getLast4digit() {
        return last4digit;
    }

    public void setLast4digit(String last4digit) {
        this.last4digit = last4digit;
    }

    public String getCustomer_paid_amount() {
        return customer_paid_amount;
    }

    public void setCustomer_paid_amount(String customer_paid_amount) {
        this.customer_paid_amount = customer_paid_amount;
    }

    public String getChange_due() {
        return change_due;
    }

    public void setChange_due(String change_due) {
        this.change_due = change_due;
    }

    public String getTableid() {
        return tableid;
    }

    public void setTableid(String tableid) {
        this.tableid = tableid;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }


    public String getTotalfoodamount() {
        return totalfoodamount;
    }

    public void setTotalfoodamount(String totalfoodamount) {
        this.totalfoodamount = totalfoodamount;
    }

    public String getTotalvatamount() {
        return totalvatamount;
    }

    public void setTotalvatamount(String totalvatamount) {
        this.totalvatamount = totalvatamount;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public ArrayList<PlaceOrderFood> getFoodlist() {
        return foodlist;
    }

    public void setFoodlist(ArrayList<PlaceOrderFood> foodlist) {
        this.foodlist = foodlist;
    }
}
