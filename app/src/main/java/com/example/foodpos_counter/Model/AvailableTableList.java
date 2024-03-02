package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AvailableTableList {

    @SerializedName("tableid")
    @Expose
    private String tableid;

    @SerializedName("tablename")
    @Expose
    private String tablename;

    @SerializedName("floor")
    @Expose
    private String floor;

    @SerializedName("seat")
    @Expose
    private String seat;

    @SerializedName("availableSeat")
    @Expose
    private String availableSeat;

    @SerializedName("bookingStatus")
    @Expose
    private String bookingStatus;


    public AvailableTableList(String tableid, String tablename, String floor, String seat, String availableSeat, String bookingStatus) {
        this.tableid = tableid;
        this.tablename = tablename;
        this.floor = floor;
        this.seat = seat;
        this.availableSeat = availableSeat;
        this.bookingStatus = bookingStatus;
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

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(String availableSeat) {
        this.availableSeat = availableSeat;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
