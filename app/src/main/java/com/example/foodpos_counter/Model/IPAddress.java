package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IPAddress {

    @SerializedName("kitchenid")
    @Expose
    private String kitchenid;

    @SerializedName("kitchenname")
    @Expose
    private String kitchenname;

    @SerializedName("ip")
    @Expose
    private String ip;

    @SerializedName("port")
    @Expose
    private String port;

    @SerializedName("status")
    @Expose
    private String status;

    public IPAddress(String kitchenid, String kitchenname, String ip, String port, String status) {
        this.kitchenid = kitchenid;
        this.kitchenname = kitchenname;
        this.ip = ip;
        this.port = port;
        this.status = status;
    }


    public String getKitchenid() {
        return kitchenid;
    }

    public void setKitchenid(String kitchenid) {
        this.kitchenid = kitchenid;
    }

    public String getKitchenname() {
        return kitchenname;
    }

    public void setKitchenname(String kitchenname) {
        this.kitchenname = kitchenname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
