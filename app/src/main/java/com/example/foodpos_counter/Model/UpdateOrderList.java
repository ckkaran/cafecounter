package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UpdateOrderList {
    @SerializedName("customerid")
    @Expose
    private String customerid;

    @SerializedName("orderid")
    @Expose
    private String orderid;

    @SerializedName("customertype")
    @Expose
    private String customertype;

    @SerializedName("waiterid")
    @Expose
    private String waiterid;

    @SerializedName("counterid")
    @Expose
    private String counterid;

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
    private ArrayList<UpdateFoodlist> foodlist;

    public UpdateOrderList(String customerid, String orderid, String customertype, String waiterid, String counterid, String tableid, String totalprice, String totalfoodamount, String totalvatamount, String seat, ArrayList<UpdateFoodlist> foodlist) {
        this.customerid = customerid;
        this.orderid = orderid;
        this.customertype = customertype;
        this.waiterid = waiterid;
        this.counterid = counterid;
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

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
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

    public ArrayList<UpdateFoodlist> getFoodlist() {
        return foodlist;
    }

    public void setFoodlist(ArrayList<UpdateFoodlist> foodlist) {
        this.foodlist = foodlist;
    }
}
