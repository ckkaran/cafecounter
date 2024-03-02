package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sales_summary {
    @SerializedName("grossfoodamount")
    @Expose
    private String grossfoodamount;

    @SerializedName("grossfoodcount")
    @Expose
    private String grossfoodcount;

    @SerializedName("itemsdiscount")
    @Expose
    private String itemsdiscount;

    @SerializedName("billdiscount")
    @Expose
    private String billdiscount;

    @SerializedName("salesreturn")
    @Expose
    private String salesreturn;

    @SerializedName("netsalesbefore")
    @Expose
    private String netsalesbefore;

    @SerializedName("servicecharge")
    @Expose
    private String servicecharge;

    @SerializedName("vat")
    @Expose
    private String vat;

    @SerializedName("roundind")
    @Expose
    private String roundind;

    @SerializedName("netsalesafter")
    @Expose
    private String netsalesafter;

    public String getGrossfoodamount() {
        return grossfoodamount;
    }

    public void setGrossfoodamount(String grossfoodamount) {
        this.grossfoodamount = grossfoodamount;
    }

    public String getGrossfoodcount() {
        return grossfoodcount;
    }

    public void setGrossfoodcount(String grossfoodcount) {
        this.grossfoodcount = grossfoodcount;
    }

    public String getItemsdiscount() {
        return itemsdiscount;
    }

    public void setItemsdiscount(String itemsdiscount) {
        this.itemsdiscount = itemsdiscount;
    }

    public String getBilldiscount() {
        return billdiscount;
    }

    public void setBilldiscount(String billdiscount) {
        this.billdiscount = billdiscount;
    }

    public String getSalesreturn() {
        return salesreturn;
    }

    public void setSalesreturn(String salesreturn) {
        this.salesreturn = salesreturn;
    }

    public String getNetsalesbefore() {
        return netsalesbefore;
    }

    public void setNetsalesbefore(String netsalesbefore) {
        this.netsalesbefore = netsalesbefore;
    }

    public String getServicecharge() {
        return servicecharge;
    }

    public void setServicecharge(String servicecharge) {
        this.servicecharge = servicecharge;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getRoundind() {
        return roundind;
    }

    public void setRoundind(String roundind) {
        this.roundind = roundind;
    }

    public String getNetsalesafter() {
        return netsalesafter;
    }

    public void setNetsalesafter(String netsalesafter) {
        this.netsalesafter = netsalesafter;
    }
}
