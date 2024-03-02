package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReprintItem {
    @SerializedName("row_id")
    @Expose
    private String row_id;

    @SerializedName("productName")
    @Expose
    private String productName;

    @SerializedName("menuqty")
    @Expose
    private String menuqty;

    @SerializedName("variantName")
    @Expose
    private String variantName;

    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("addons")
    @Expose
    private ArrayList<Reprint_AddonsItem> addons;

    public ReprintItem(String row_id, String productName, String menuqty, String variantName,String notes, ArrayList<Reprint_AddonsItem> addons) {
        this.row_id = row_id;
        this.productName = productName;
        this.menuqty = menuqty;
        this.variantName = variantName;
        this.notes = notes;
        this.addons = addons;
    }

    public String getRow_id() {
        return row_id;
    }

    public void setRow_id(String row_id) {
        this.row_id = row_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMenuqty() {
        return menuqty;
    }

    public void setMenuqty(String menuqty) {
        this.menuqty = menuqty;
    }

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ArrayList<Reprint_AddonsItem> getAddons() {
        return addons;
    }

    public void setAddons(ArrayList<Reprint_AddonsItem> addons) {
        this.addons = addons;
    }
}

