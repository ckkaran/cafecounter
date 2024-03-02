package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CounterDetails {
    @SerializedName("companyname")
    @Expose
    private String companyname;

    @SerializedName("counterip")
    @Expose
    private String counterip;

    @SerializedName("printname")
    @Expose
    private String printname;

    @SerializedName("companyaddress1")
    @Expose
    private String companyaddress1;

    @SerializedName("companyaddress2")
    @Expose
    private String companyaddress2;

    @SerializedName("companyaddress3")
    @Expose
    private String companyaddress3;


    @SerializedName("companylogo")
    @Expose
    private String companylogo;

    @SerializedName("orderdate")
    @Expose
    private String orderdate;

    @SerializedName("ordertime")
    @Expose
    private String ordertime;

    @SerializedName("customername")
    @Expose
    private String customername;

    @SerializedName("customertype")
    @Expose
    private String customertype;

    @SerializedName("billbyname")
    @Expose
    private String billbyname;

    @SerializedName("countername")
    @Expose
    private String countername;

    @SerializedName("saleinvoice")
    @Expose
    private String saleinvoice;

    @SerializedName("tableno")
    @Expose
    private String tableno;

    @SerializedName("tokenno")
    @Expose
    private String tokenno;

    @SerializedName("foodlist")
    @Expose
    private ArrayList<CounterFoodlist> foodlist;

    @SerializedName("foodtotalamount")
    @Expose
    private String foodtotalamount;

    @SerializedName("taxpercentage")
    @Expose
    private String taxpercentage;

    @SerializedName("taxnumber")
    @Expose
    private String taxnumber;

    @SerializedName("totalvatamount")
    @Expose
    private String totalvatamount;

    @SerializedName("servicecharge")
    @Expose
    private String servicecharge;

    @SerializedName("discount")
    @Expose
    private String discount;

    @SerializedName("grandtotal")
    @Expose
    private String grandtotal;

    @SerializedName("paidamount")
    @Expose
    private String paidamount;

    @SerializedName("changedue")
    @Expose
    private String changedue;

    @SerializedName("totalamount")
    @Expose
    private String totalamount;

    @SerializedName("paymenttype")
    @Expose
    private String paymenttype;

    @SerializedName("paymentstatus")
    @Expose
    private String paymentstatus;

    public CounterDetails(String companyname, String counterip, String printname, String companyaddress1, String companyaddress2, String companyaddress3, String companylogo, String orderdate, String ordertime, String customername, String customertype, String billbyname, String countername, String saleinvoice, String tableno, String tokenno, ArrayList<CounterFoodlist> foodlist, String foodtotalamount, String taxpercentage, String taxnumber, String totalvatamount, String servicecharge, String discount, String grandtotal, String paidamount, String changedue, String totalamount, String paymenttype, String paymentstatus) {
        this.companyname = companyname;
        this.counterip = counterip;
        this.printname = printname;
        this.companyaddress1 = companyaddress1;
        this.companyaddress2 = companyaddress2;
        this.companyaddress3 = companyaddress3;
        this.companylogo = companylogo;
        this.orderdate = orderdate;
        this.ordertime = ordertime;
        this.customername = customername;
        this.customertype = customertype;
        this.billbyname = billbyname;
        this.countername = countername;
        this.saleinvoice = saleinvoice;
        this.tableno = tableno;
        this.tokenno = tokenno;
        this.foodlist = foodlist;
        this.foodtotalamount = foodtotalamount;
        this.taxpercentage = taxpercentage;
        this.taxnumber = taxnumber;
        this.totalvatamount = totalvatamount;
        this.servicecharge = servicecharge;
        this.discount = discount;
        this.grandtotal = grandtotal;
        this.paidamount = paidamount;
        this.changedue = changedue;
        this.totalamount = totalamount;
        this.paymenttype = paymenttype;
        this.paymentstatus = paymentstatus;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCounterip() {
        return counterip;
    }

    public void setCounterip(String counterip) {
        this.counterip = counterip;
    }

    public String getPrintname() {
        return printname;
    }

    public void setPrintname(String printname) {
        this.printname = printname;
    }

    public String getCompanyaddress1() {
        return companyaddress1;
    }

    public void setCompanyaddress1(String companyaddress1) {
        this.companyaddress1 = companyaddress1;
    }

    public String getCompanyaddress2() {
        return companyaddress2;
    }

    public void setCompanyaddress2(String companyaddress2) {
        this.companyaddress2 = companyaddress2;
    }

    public String getCompanyaddress3() {
        return companyaddress3;
    }

    public void setCompanyaddress3(String companyaddress3) {
        this.companyaddress3 = companyaddress3;
    }

    public String getCompanylogo() {
        return companylogo;
    }

    public void setCompanylogo(String companylogo) {
        this.companylogo = companylogo;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomertype() {
        return customertype;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }

    public String getBillbyname() {
        return billbyname;
    }

    public void setBillbyname(String billbyname) {
        this.billbyname = billbyname;
    }

    public String getCountername() {
        return countername;
    }

    public void setCountername(String countername) {
        this.countername = countername;
    }

    public String getSaleinvoice() {
        return saleinvoice;
    }

    public void setSaleinvoice(String saleinvoice) {
        this.saleinvoice = saleinvoice;
    }

    public String getTableno() {
        return tableno;
    }

    public void setTableno(String tableno) {
        this.tableno = tableno;
    }

    public String getTokenno() {
        return tokenno;
    }

    public void setTokenno(String tokenno) {
        this.tokenno = tokenno;
    }

    public ArrayList<CounterFoodlist> getFoodlist() {
        return foodlist;
    }

    public void setFoodlist(ArrayList<CounterFoodlist> foodlist) {
        this.foodlist = foodlist;
    }

    public String getFoodtotalamount() {
        return foodtotalamount;
    }

    public void setFoodtotalamount(String foodtotalamount) {
        this.foodtotalamount = foodtotalamount;
    }

    public String getTaxpercentage() {
        return taxpercentage;
    }

    public void setTaxpercentage(String taxpercentage) {
        this.taxpercentage = taxpercentage;
    }

    public String getTaxnumber() {
        return taxnumber;
    }

    public void setTaxnumber(String taxnumber) {
        this.taxnumber = taxnumber;
    }

    public String getTotalvatamount() {
        return totalvatamount;
    }

    public void setTotalvatamount(String totalvatamount) {
        this.totalvatamount = totalvatamount;
    }

    public String getServicecharge() {
        return servicecharge;
    }

    public void setServicecharge(String servicecharge) {
        this.servicecharge = servicecharge;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(String grandtotal) {
        this.grandtotal = grandtotal;
    }

    public String getPaidamount() {
        return paidamount;
    }

    public void setPaidamount(String paidamount) {
        this.paidamount = paidamount;
    }

    public String getChangedue() {
        return changedue;
    }

    public void setChangedue(String changedue) {
        this.changedue = changedue;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(String paymentstatus) {
        this.paymentstatus = paymentstatus;
    }
}
