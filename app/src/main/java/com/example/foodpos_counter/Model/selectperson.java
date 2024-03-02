package com.example.foodpos_counter.Model;

public class selectperson {
    int image;
    String tableNo;
    String seatNo;
    String Available;

    public selectperson(int image, String tableNo, String seatNo, String available) {
        this.image = image;
        this.tableNo = tableNo;
        this.seatNo = seatNo;
        Available = available;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getAvailable() {
        return Available;
    }

    public void setAvailable(String available) {
        Available = available;
    }
}
