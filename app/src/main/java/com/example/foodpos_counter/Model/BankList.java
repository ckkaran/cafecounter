package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankList {
    @SerializedName("bankid")
    @Expose
    private String bankid;

    @SerializedName("bankname")
    @Expose
    private String bankname;


    public BankList(String bankid, String bankname) {
        this.bankid = bankid;
        this.bankname = bankname;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
}
