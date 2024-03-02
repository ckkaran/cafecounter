package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paymenttypes {

    @SerializedName("paymentId")
    @Expose
    private String paymentId;

    @SerializedName("paymentType")
    @Expose
    private String paymentType;

    @SerializedName("paymentTypeImage")
    @Expose
    private String paymentTypeImage;

    public Paymenttypes(String paymentId, String paymentType, String paymentTypeImage) {
        this.paymentId = paymentId;
        this.paymentType = paymentType;
        this.paymentTypeImage = paymentTypeImage;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentTypeImage() {
        return paymentTypeImage;
    }

    public void setPaymentTypeImage(String paymentTypeImage) {
        this.paymentTypeImage = paymentTypeImage;
    }
}
