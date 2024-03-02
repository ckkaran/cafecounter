package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OngoingList {

    @SerializedName("orderid")
    @Expose
    private String orderid;

    @SerializedName("customertypeid")
    @Expose
    private String customertypeid;

    @SerializedName("customerid")
    @Expose
    private String customerid;

    @SerializedName("orderdate")
    @Expose
    private String orderdate;

    @SerializedName("ordertime")
    @Expose
    private String ordertime;

    @SerializedName("customername")
    @Expose
    private String customername;

    @SerializedName("customeremail")
    @Expose
    private String customeremail;

    @SerializedName("customerphone")
    @Expose
    private String customerphone;

    @SerializedName("customertype")
    @Expose
    private String customertype;

    @SerializedName("saleinvoice")
    @Expose
    private String saleinvoice;

    @SerializedName("totalamount")
    @Expose
    private String totalamount;

    @SerializedName("totaldueamount")
    @Expose
    private String totaldueamount;

    @SerializedName("tokenno")
    @Expose
    private String tokenno;

    @SerializedName("tableid")
    @Expose
    private String tableid;

    @SerializedName("tablename")
    @Expose
    private String tablename;

    @SerializedName("waitername")
    @Expose
    private String waitername;

    @SerializedName("countername")
    @Expose
    private String countername;

    public OngoingList(String orderid, String customertypeid, String customerid, String orderdate, String ordertime, String customername, String customeremail, String customerphone, String customertype, String saleinvoice, String totalamount, String totaldueamount, String tokenno, String tableid, String tablename, String waitername, String countername) {
        this.orderid = orderid;
        this.customertypeid = customertypeid;
        this.customerid = customerid;
        this.orderdate = orderdate;
        this.ordertime = ordertime;
        this.customername = customername;
        this.customeremail = customeremail;
        this.customerphone = customerphone;
        this.customertype = customertype;
        this.saleinvoice = saleinvoice;
        this.totalamount = totalamount;
        this.totaldueamount = totaldueamount;
        this.tokenno = tokenno;
        this.tableid = tableid;
        this.tablename = tablename;
        this.waitername = waitername;
        this.countername = countername;
    }


    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getCustomertypeid() {
        return customertypeid;
    }

    public void setCustomertypeid(String customertypeid) {
        this.customertypeid = customertypeid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
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

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomeremail() {
        return customeremail;
    }

    public void setCustomeremail(String customeremail) {
        this.customeremail = customeremail;
    }

    public String getCustomerphone() {
        return customerphone;
    }

    public void setCustomerphone(String customerphone) {
        this.customerphone = customerphone;
    }

    public String getCustomertype() {
        return customertype;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }

    public String getSaleinvoice() {
        return saleinvoice;
    }

    public void setSaleinvoice(String saleinvoice) {
        this.saleinvoice = saleinvoice;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public String getTotaldueamount() {
        return totaldueamount;
    }

    public void setTotaldueamount(String totaldueamount) {
        this.totaldueamount = totaldueamount;
    }

    public String getTokenno() {
        return tokenno;
    }

    public void setTokenno(String tokenno) {
        this.tokenno = tokenno;
    }

    public String getTableid() {
        return tableid;
    }

    public void setTableid(String tableid) {
        this.tableid = tableid;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getWaitername() {
        return waitername;
    }

    public void setWaitername(String waitername) {
        this.waitername = waitername;
    }

    public String getCountername() {
        return countername;
    }

    public void setCountername(String countername) {
        this.countername = countername;
    }
}
