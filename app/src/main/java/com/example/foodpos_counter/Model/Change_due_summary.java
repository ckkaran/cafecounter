package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Change_due_summary {
    @SerializedName("totalcustomerpaidamount")
    @Expose
    private String totalcustomerpaidamount;

    @SerializedName("totalcustomerchangeamount")
    @Expose
    private String totalcustomerchangeamount;

    @SerializedName("totalbillamount")
    @Expose
    private String totalbillamount;

    public Change_due_summary(String totalcustomerpaidamount, String totalcustomerchangeamount, String totalbillamount) {
        this.totalcustomerpaidamount = totalcustomerpaidamount;
        this.totalcustomerchangeamount = totalcustomerchangeamount;
        this.totalbillamount = totalbillamount;
    }

    public String getTotalcustomerpaidamount() {
        return totalcustomerpaidamount;
    }

    public void setTotalcustomerpaidamount(String totalcustomerpaidamount) {
        this.totalcustomerpaidamount = totalcustomerpaidamount;
    }

    public String getTotalcustomerchangeamount() {
        return totalcustomerchangeamount;
    }

    public void setTotalcustomerchangeamount(String totalcustomerchangeamount) {
        this.totalcustomerchangeamount = totalcustomerchangeamount;
    }

    public String getTotalbillamount() {
        return totalbillamount;
    }

    public void setTotalbillamount(String totalbillamount) {
        this.totalbillamount = totalbillamount;
    }
}
