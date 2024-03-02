package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Order_FoodList {
    @SerializedName("rowid")
    @Expose
    private String rowid;

    @SerializedName("orderId")
    @Expose
    private String orderId;

    @SerializedName("ProductsId")
    @Expose
    private String ProductsId;

    @SerializedName("categoryId")
    @Expose
    private String categoryId;

    @SerializedName("productName")
    @Expose
    private String productName;

    @SerializedName("productImage")
    @Expose
    private String productImage;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("notes")
    @Expose
    private String notes;

    @SerializedName("menuqty")
    @Expose
    private String menuqty;

    @SerializedName("varientId")
    @Expose
    private String varientId;

    @SerializedName("variantName")
    @Expose
    private String variantName;


    @SerializedName("addons")
    @Expose
    private ArrayList<Order_Addons> addons;

    public Order_FoodList(String rowid, String orderId, String productsId, String categoryId, String productName, String productImage, String price, String notes, String menuqty, String varientId, String variantName, ArrayList<Order_Addons> addons) {
        this.rowid = rowid;
        this.orderId = orderId;
        ProductsId = productsId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.productImage = productImage;
        this.price = price;
        this.notes = notes;
        this.menuqty = menuqty;
        this.varientId = varientId;
        this.variantName = variantName;
        this.addons = addons;
    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductsId() {
        return ProductsId;
    }

    public void setProductsId(String productsId) {
        ProductsId = productsId;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getMenuqty() {
        return menuqty;
    }

    public void setMenuqty(String menuqty) {
        this.menuqty = menuqty;
    }

    public String getVarientId() {
        return varientId;
    }

    public void setVarientId(String varientId) {
        this.varientId = varientId;
    }

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }


    public ArrayList<Order_Addons> getAddons() {
        return addons;
    }

    public void setAddons(ArrayList<Order_Addons> addons) {
        this.addons = addons;
    }
}
