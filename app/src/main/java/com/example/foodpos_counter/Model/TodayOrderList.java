package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodayOrderList {
    @SerializedName("order_id")
    @Expose
    private String order_id;

    @SerializedName("saleinvoice")
    @Expose
    private String saleinvoice;

    @SerializedName("tokenno")
    @Expose
    private String tokenno;

    @SerializedName("totalamount")
    @Expose
    private String totalamount;

    @SerializedName("customertype")
    @Expose
    private String customertype;

    @SerializedName("waiter")
    @Expose
    private String waiter;

    @SerializedName("tablename")
    @Expose
    private String tablename;

    @SerializedName("orderdate")
    @Expose
    private String orderdate;

    @SerializedName("ordertime")
    @Expose
    private String ordertime;

    @SerializedName("paymentmethod")
    @Expose
    private String paymentmethod;

    @SerializedName("countername")
    @Expose
    private String countername;


    public TodayOrderList(String order_id, String saleinvoice, String tokenno, String totalamount, String customertype, String waiter, String tablename, String orderdate, String ordertime, String paymentmethod, String countername) {
        this.order_id = order_id;
        this.saleinvoice = saleinvoice;
        this.tokenno = tokenno;
        this.totalamount = totalamount;
        this.customertype = customertype;
        this.waiter = waiter;
        this.tablename = tablename;
        this.orderdate = orderdate;
        this.ordertime = ordertime;
        this.paymentmethod = paymentmethod;
        this.countername = countername;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getSaleinvoice() {
        return saleinvoice;
    }

    public void setSaleinvoice(String saleinvoice) {
        this.saleinvoice = saleinvoice;
    }

    public String getTokenno() {
        return tokenno;
    }

    public void setTokenno(String tokenno) {
        this.tokenno = tokenno;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public String getCustomertype() {
        return customertype;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }

    public String getWaiter() {
        return waiter;
    }

    public void setWaiter(String waiter) {
        this.waiter = waiter;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public String getCountername() {
        return countername;
    }

    public void setCountername(String countername) {
        this.countername = countername;
    }
}
