package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WaiterList {

    @SerializedName("employeeId")
    @Expose
    private String employeeId;

    @SerializedName("employeeName")
    @Expose
    private String employeeName;

    public WaiterList(String employeeId, String employeeName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
