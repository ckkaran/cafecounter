package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Collection_summary {

    @SerializedName("collectiondetails")
    @Expose
    private ArrayList<Collectiondetails> collectiondetails;

    @SerializedName("totalcollectionamount")
    @Expose
    private String totalcollectionamount;

    @SerializedName("totalcreditcardamount")
    @Expose
    private String totalcreditcardamount;

    public Collection_summary(ArrayList<Collectiondetails> collectiondetails, String totalcollectionamount, String totalcreditcardamount) {
        this.collectiondetails = collectiondetails;
        this.totalcollectionamount = totalcollectionamount;
        this.totalcreditcardamount = totalcreditcardamount;
    }

    public ArrayList<Collectiondetails> getCollectiondetails() {
        return collectiondetails;
    }

    public void setCollectiondetails(ArrayList<Collectiondetails> collectiondetails) {
        this.collectiondetails = collectiondetails;
    }

    public String getTotalcollectionamount() {
        return totalcollectionamount;
    }

    public void setTotalcollectionamount(String totalcollectionamount) {
        this.totalcollectionamount = totalcollectionamount;
    }

    public String getTotalcreditcardamount() {
        return totalcreditcardamount;
    }

    public void setTotalcreditcardamount(String totalcreditcardamount) {
        this.totalcreditcardamount = totalcreditcardamount;
    }
}
