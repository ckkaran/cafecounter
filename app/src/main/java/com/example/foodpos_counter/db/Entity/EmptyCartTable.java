package com.example.foodpos_counter.db.Entity;

import java.util.ArrayList;

public class EmptyCartTable {

    private int id;
    private String productsId;
    private String categoryId;
    private String productName;
    private String productImage;
    private String varientId;
    private String vatCharge;
    private String varientName;
    private String price;
    private String kitchenId;
    private String kitchenName;
    private String kitchenIpAdrees;
    private String kitchenPort;
    private ArrayList<Add_ons_cart> addons;
    private String qty;
    private String notes;
    private String status;
    private String addons_status;
    private String uid;


    public EmptyCartTable(String productsId, String categoryId, String productName, String productImage, String varientId, String vatCharge, String varientName, String price, String kitchenId, String kitchenName, String kitchenIpAdrees, String kitchenPort, ArrayList<Add_ons_cart> addons, String qty,
                          String notes, String status, String addons_status, String uid) {
        this.productsId = productsId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.productImage = productImage;
        this.varientId = varientId;
        this.vatCharge = vatCharge;
        this.varientName = varientName;
        this.price = price;
        this.kitchenId = kitchenId;
        this.kitchenName = kitchenName;
        this.kitchenIpAdrees = kitchenIpAdrees;
        this.kitchenPort = kitchenPort;
        this.addons = addons;
        this.qty = qty;
        this.notes = notes;
        this.status = status;
        this.addons_status = addons_status;
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductsId() {
        return productsId;
    }

    public void setProductsId(String productsId) {
        this.productsId = productsId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getVarientId() {
        return varientId;
    }

    public void setVarientId(String varientId) {
        this.varientId = varientId;
    }

    public String getVatCharge() {
        return vatCharge;
    }

    public void setVatCharge(String vatCharge) {
        this.vatCharge = vatCharge;
    }

    public String getVarientName() {
        return varientName;
    }

    public void setVarientName(String varientName) {
        this.varientName = varientName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getKitchenIpAdrees() {
        return kitchenIpAdrees;
    }

    public void setKitchenIpAdrees(String kitchenIpAdrees) {
        this.kitchenIpAdrees = kitchenIpAdrees;
    }

    public String getKitchenPort() {
        return kitchenPort;
    }

    public void setKitchenPort(String kitchenPort) {
        this.kitchenPort = kitchenPort;
    }

    public ArrayList<Add_ons_cart> getAddons() {
        return addons;
    }

    public void setAddons(ArrayList<Add_ons_cart> addons) {
        this.addons = addons;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddons_status() {
        return addons_status;
    }

    public void setAddons_status(String addons_status) {
        this.addons_status = addons_status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
