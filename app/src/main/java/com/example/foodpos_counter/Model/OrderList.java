package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderList {
    @SerializedName("orderid")
    @Expose
    private String orderid;

    @SerializedName("saleinvoice")
    @Expose
    private String saleinvoice;

    @SerializedName("tokenno")
    @Expose
    private String tokenno;

    @SerializedName("cutomertype")
    @Expose
    private String cutomertype;

    @SerializedName("cutomertypeName")
    @Expose
    private String cutomertypeName;

    @SerializedName("cutomerId")
    @Expose
    private String cutomerId;

    @SerializedName("cuntomerNo")
    @Expose
    private String cuntomerNo;

    @SerializedName("customerName")
    @Expose
    private String customerName;

    @SerializedName("customerEmail")
    @Expose
    private String customerEmail;

    @SerializedName("customerNumber")
    @Expose
    private String customerNumber;

    @SerializedName("waiterid")
    @Expose
    private String waiterid;

    @SerializedName("waitername")
    @Expose
    private String waitername;

    @SerializedName("orderDate")
    @Expose
    private String orderDate;

    @SerializedName("orderTime")
    @Expose
    private String orderTime;

    @SerializedName("tableId")
    @Expose
    private String tableId;

    @SerializedName("tableName")
    @Expose
    private String tableName;

    @SerializedName("personCapicity")
    @Expose
    private String personCapicity;

    @SerializedName("totalamount")
    @Expose
    private String totalamount;

    @SerializedName("totalfoodamount")
    @Expose
    private String totalfoodamount;

    @SerializedName("totalvatamount")
    @Expose
    private String totalvatamount;

    @SerializedName("foodList")
    @Expose
    private ArrayList<Order_FoodList> foodList;

    @SerializedName("foodcount")
    @Expose
    private String foodcount;

    public OrderList(String orderid, String saleinvoice, String tokenno, String cutomertype, String cutomertypeName, String cutomerId, String cuntomerNo, String customerName, String customerEmail, String customerNumber, String waiterid, String waitername, String orderDate, String orderTime, String tableId, String tableName, String personCapicity, String totalamount, String totalfoodamount, String totalvatamount, ArrayList<Order_FoodList> foodList, String foodcount) {
        this.orderid = orderid;
        this.saleinvoice = saleinvoice;
        this.tokenno = tokenno;
        this.cutomertype = cutomertype;
        this.cutomertypeName = cutomertypeName;
        this.cutomerId = cutomerId;
        this.cuntomerNo = cuntomerNo;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerNumber = customerNumber;
        this.waiterid = waiterid;
        this.waitername = waitername;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.tableId = tableId;
        this.tableName = tableName;
        this.personCapicity = personCapicity;
        this.totalamount = totalamount;
        this.totalfoodamount = totalfoodamount;
        this.totalvatamount = totalvatamount;
        this.foodList = foodList;
        this.foodcount = foodcount;
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

    public String getTokenno() {
        return tokenno;
    }

    public void setTokenno(String tokenno) {
        this.tokenno = tokenno;
    }

    public String getCutomertype() {
        return cutomertype;
    }

    public void setCutomertype(String cutomertype) {
        this.cutomertype = cutomertype;
    }

    public String getCutomertypeName() {
        return cutomertypeName;
    }

    public void setCutomertypeName(String cutomertypeName) {
        this.cutomertypeName = cutomertypeName;
    }

    public String getCutomerId() {
        return cutomerId;
    }

    public void setCutomerId(String cutomerId) {
        this.cutomerId = cutomerId;
    }

    public String getCuntomerNo() {
        return cuntomerNo;
    }

    public void setCuntomerNo(String cuntomerNo) {
        this.cuntomerNo = cuntomerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getWaiterid() {
        return waiterid;
    }

    public void setWaiterid(String waiterid) {
        this.waiterid = waiterid;
    }

    public String getWaitername() {
        return waitername;
    }

    public void setWaitername(String waitername) {
        this.waitername = waitername;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPersonCapicity() {
        return personCapicity;
    }

    public void setPersonCapicity(String personCapicity) {
        this.personCapicity = personCapicity;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
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

    public ArrayList<Order_FoodList> getFoodList() {
        return foodList;
    }

    public void setFoodList(ArrayList<Order_FoodList> foodList) {
        this.foodList = foodList;
    }

    public String getFoodcount() {
        return foodcount;
    }

    public void setFoodcount(String foodcount) {
        this.foodcount = foodcount;
    }
}
