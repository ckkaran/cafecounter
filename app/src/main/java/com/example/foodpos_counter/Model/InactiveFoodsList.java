package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InactiveFoodsList {

    @SerializedName("ProductsId")
    @Expose
    private String ProductsId;

    @SerializedName("CategoryId")
    @Expose
    private String CategoryId;

    @SerializedName("ProductName")
    @Expose
    private String ProductName;

    @SerializedName("ProductImage")
    @Expose
    private String ProductImage;

    @SerializedName("varientId")
    @Expose
    private String varientId;

    @SerializedName("vatCharge")
    @Expose
    private String vatCharge;

    @SerializedName("varientName")
    @Expose
    private String varientName;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("kitchenId")
    @Expose
    private String kitchenId;

    @SerializedName("kitchenName")
    @Expose
    private String kitchenName;

    @SerializedName("kitchenIpAdrees")
    @Expose
    private String kitchenIpAdrees;

    @SerializedName("kitchenPort")
    @Expose
    private String kitchenPort;

    @SerializedName("addons")
    @Expose
    private ArrayList<InactiveAdd_ons> addons;

    public InactiveFoodsList(String productsId, String categoryId, String productName, String productImage, String varientId, String vatCharge, String varientName, String price, String kitchenId, String kitchenName, String kitchenIpAdrees, String kitchenPort, ArrayList<InactiveAdd_ons> addons) {
        this.ProductsId = productsId;
        this.CategoryId = categoryId;
        this.ProductName = productName;
        this.ProductImage = productImage;
        this.varientId = varientId;
        this.vatCharge = vatCharge;
        this.varientName = varientName;
        this.price = price;
        this.kitchenId = kitchenId;
        this.kitchenName = kitchenName;
        this.kitchenIpAdrees = kitchenIpAdrees;
        this.kitchenPort = kitchenPort;
        this.addons = addons;
    }

    public String getProductsId() {
        return ProductsId;
    }

    public void setProductsId(String productsId) {
        this.ProductsId = productsId;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        this.CategoryId = categoryId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        this.ProductName = productName;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        this.ProductImage = productImage;
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

    public ArrayList<InactiveAdd_ons> getAddons() {
        return addons;
    }

    public void setAddons(ArrayList<InactiveAdd_ons> addons) {
        this.addons = addons;
    }
}
