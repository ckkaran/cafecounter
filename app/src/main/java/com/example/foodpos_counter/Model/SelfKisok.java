package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelfKisok {

    @SerializedName("order_id")
    @Expose
    private String order_id;

    @SerializedName("saleinvoice")
    @Expose
    private String saleinvoice;

    @SerializedName("tokenno")
    @Expose
    private String tokenno;

    @SerializedName("tableid")
    @Expose
    private String tableid;

    @SerializedName("tablename")
    @Expose
    private String tablename;

    @SerializedName("orderdate")
    @Expose
    private String orderdate;

    @SerializedName("ordertime")
    @Expose
    private String ordertime;

    @SerializedName("totalamount")
    @Expose
    private String totalamount;

    @SerializedName("customertypeid")
    @Expose
    private String customertypeid;

    @SerializedName("customertype")
    @Expose
    private String customertype;

    @SerializedName("customer_id")
    @Expose
    private String customer_id;

    @SerializedName("customername")
    @Expose
    private String customername;

    @SerializedName("customeremail")
    @Expose
    private String customeremail;

    @SerializedName("customerphone")
    @Expose
    private String customerphone;

    public SelfKisok(String order_id, String saleinvoice, String tokenno, String tableid, String tablename, String orderdate, String ordertime, String totalamount, String customertypeid, String customertype, String customer_id, String customername, String customeremail, String customerphone) {
        this.order_id = order_id;
        this.saleinvoice = saleinvoice;
        this.tokenno = tokenno;
        this.tableid = tableid;
        this.tablename = tablename;
        this.orderdate = orderdate;
        this.ordertime = ordertime;
        this.totalamount = totalamount;
        this.customertypeid = customertypeid;
        this.customertype = customertype;
        this.customer_id = customer_id;
        this.customername = customername;
        this.customeremail = customeremail;
        this.customerphone = customerphone;
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

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public String getCustomertypeid() {
        return customertypeid;
    }

    public void setCustomertypeid(String customertypeid) {
        this.customertypeid = customertypeid;
    }

    public String getCustomertype() {
        return customertype;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
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
}
