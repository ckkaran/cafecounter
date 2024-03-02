package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceCharge {
    @SerializedName("servicecharge")
    @Expose
    private String servicecharge;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("typeName")
    @Expose
    private String typeName;

    public ServiceCharge(String servicecharge, String type, String typeName) {
        this.servicecharge = servicecharge;
        this.type = type;
        this.typeName = typeName;
    }

    public String getServicecharge() {
        return servicecharge;
    }

    public void setServicecharge(String servicecharge) {
        this.servicecharge = servicecharge;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
