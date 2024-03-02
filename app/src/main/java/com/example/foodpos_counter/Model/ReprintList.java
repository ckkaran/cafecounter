package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReprintList {

    @SerializedName("kitchenId")
    @Expose
    private String kitchenId;

    @SerializedName("kitchenName")
    @Expose
    private String kitchenName;

    @SerializedName("ip")
    @Expose
    private String ip;

    @SerializedName("port")
    @Expose
    private String port;

    @SerializedName("orderId")
    @Expose
    private String orderId;

    @SerializedName("saleinvoice")
    @Expose
    private String saleinvoice;

    @SerializedName("tablename")
    @Expose
    private String tablename;

    @SerializedName("cutomertypeName")
    @Expose
    private String cutomertypeName;

    @SerializedName("waiter")
    @Expose
    private String waiter;

    @SerializedName("tokenno")
    @Expose
    private String tokenno;

    @SerializedName("orderDate")
    @Expose
    private String orderDate;

    @SerializedName("orderTime")
    @Expose
    private String orderTime;

    @SerializedName("orderItems")
    @Expose
    private ArrayList<ReprintItem> orderItems;

    public ReprintList(String kitchenId, String kitchenName, String ip, String port, String orderId, String saleinvoice, String tablename, String cutomertypeName, String waiter, String tokenno, String orderDate, String orderTime, ArrayList<ReprintItem> orderItems) {
        this.kitchenId = kitchenId;
        this.kitchenName = kitchenName;
        this.ip = ip;
        this.port = port;
        this.orderId = orderId;
        this.saleinvoice = saleinvoice;
        this.tablename = tablename;
        this.cutomertypeName = cutomertypeName;
        this.waiter = waiter;
        this.tokenno = tokenno;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderItems = orderItems;
    }

    public String getKitchenId() {
        return kitchenId;
    }

    public void setKitchenId(String kitchenId) {
        this.kitchenId = kitchenId;
    }

    public String getKitchenName() {
        return kitchenName;
    }

    public void setKitchenName(String kitchenName) {
        this.kitchenName = kitchenName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSaleinvoice() {
        return saleinvoice;
    }

    public void setSaleinvoice(String saleinvoice) {
        this.saleinvoice = saleinvoice;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getCutomertypeName() {
        return cutomertypeName;
    }

    public void setCutomertypeName(String cutomertypeName) {
        this.cutomertypeName = cutomertypeName;
    }

    public String getWaiter() {
        return waiter;
    }

    public void setWaiter(String waiter) {
        this.waiter = waiter;
    }

    public String getTokenno() {
        return tokenno;
    }

    public void setTokenno(String tokenno) {
        this.tokenno = tokenno;
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

    public ArrayList<ReprintItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<ReprintItem> orderItems) {
        this.orderItems = orderItems;
    }
}

