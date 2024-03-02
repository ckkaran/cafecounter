package com.example.foodpos_counter.db.Entity;

import androidx.room.ColumnInfo;

public class Add_ons_cart {

    @ColumnInfo(name = "addons_id")
    private String addons_id;

    @ColumnInfo(name = "addons_name")
    private String addons_name;

    @ColumnInfo(name = "addons_price")
    private String addons_price;

    @ColumnInfo(name = "addons_qty")
    private String addons_qty;

    public Add_ons_cart(String addons_id, String addons_name, String addons_price, String addons_qty) {
        this.addons_id = addons_id;
        this.addons_name = addons_name;
        this.addons_price = addons_price;
        this.addons_qty = addons_qty;
    }

    public String getAddons_id() {
        return addons_id;
    }

    public void setAddons_id(String addons_id) {
        this.addons_id = addons_id;
    }

    public String getAddons_name() {
        return addons_name;
    }

    public void setAddons_name(String addons_name) {
        this.addons_name = addons_name;
    }

    public String getAddons_price() {
        return addons_price;
    }

    public void setAddons_price(String addons_price) {
        this.addons_price = addons_price;
    }

    public String getAddons_qty() {
        return addons_qty;
    }

    public void setAddons_qty(String addons_qty) {
        this.addons_qty = addons_qty;
    }
}

