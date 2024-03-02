package com.example.foodpos_counter.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllReport {
    @SerializedName("storename")
    @Expose
    private String storename;

    @SerializedName("companylogo")
    @Expose
    private String companylogo;

    @SerializedName("companyaddress1")
    @Expose
    private String companyaddress1;

    @SerializedName("companyaddress2")
    @Expose
    private String companyaddress2;

    @SerializedName("companyaddress3")
    @Expose
    private String companyaddress3;

    @SerializedName("printname")
    @Expose
    private String printname;

    @SerializedName("printdate")
    @Expose
    private String printdate;

    @SerializedName("timein")
    @Expose
    private String timein;

    @SerializedName("timeout")
    @Expose
    private String timeout;

    @SerializedName("bill_no")
    @Expose
    private String bill_no;

    @SerializedName("token_no")
    @Expose
    private String token_no;

    @SerializedName("sales_summary")
    @Expose
    private Sales_summary sales_summary;

    @SerializedName("collection_summary")
    @Expose
    private Collection_summary collection_summary;

    @SerializedName("bill_summary")
    @Expose
    private Bill_summary bill_summary;

    @SerializedName("items_summary")
    @Expose
    private Items_summary items_summary;

    @SerializedName("change_due_summary")
    @Expose
    private Change_due_summary change_due_summary;

    @SerializedName("printeddate")
    @Expose
    private String printeddate;


    public AllReport(String storename, String companylogo, String companyaddress1, String companyaddress2, String companyaddress3, String printname, String printdate, String timein, String timeout, String bill_no, String token_no, Sales_summary sales_summary, Collection_summary collection_summary, Bill_summary bill_summary, Items_summary items_summary, Change_due_summary change_due_summary, String printeddate) {
        this.storename = storename;
        this.companylogo = companylogo;
        this.companyaddress1 = companyaddress1;
        this.companyaddress2 = companyaddress2;
        this.companyaddress3 = companyaddress3;
        this.printname = printname;
        this.printdate = printdate;
        this.timein = timein;
        this.timeout = timeout;
        this.bill_no = bill_no;
        this.token_no = token_no;
        this.sales_summary = sales_summary;
        this.collection_summary = collection_summary;
        this.bill_summary = bill_summary;
        this.items_summary = items_summary;
        this.change_due_summary = change_due_summary;
        this.printeddate = printeddate;
    }


    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getCompanylogo() {
        return companylogo;
    }

    public void setCompanylogo(String companylogo) {
        this.companylogo = companylogo;
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

    public String getPrintname() {
        return printname;
    }

    public void setPrintname(String printname) {
        this.printname = printname;
    }

    public String getPrintdate() {
        return printdate;
    }

    public void setPrintdate(String printdate) {
        this.printdate = printdate;
    }

    public String getTimein() {
        return timein;
    }

    public void setTimein(String timein) {
        this.timein = timein;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getBill_no() {
        return bill_no;
    }

    public void setBill_no(String bill_no) {
        this.bill_no = bill_no;
    }

    public String getToken_no() {
        return token_no;
    }

    public void setToken_no(String token_no) {
        this.token_no = token_no;
    }

    public Sales_summary getSales_summary() {
        return sales_summary;
    }

    public void setSales_summary(Sales_summary sales_summary) {
        this.sales_summary = sales_summary;
    }

    public Collection_summary getCollection_summary() {
        return collection_summary;
    }

    public void setCollection_summary(Collection_summary collection_summary) {
        this.collection_summary = collection_summary;
    }

    public Bill_summary getBill_summary() {
        return bill_summary;
    }

    public void setBill_summary(Bill_summary bill_summary) {
        this.bill_summary = bill_summary;
    }

    public Items_summary getItems_summary() {
        return items_summary;
    }

    public void setItems_summary(Items_summary items_summary) {
        this.items_summary = items_summary;
    }

    public Change_due_summary getChange_due_summary() {
        return change_due_summary;
    }

    public void setChange_due_summary(Change_due_summary change_due_summary) {
        this.change_due_summary = change_due_summary;
    }

    public String getPrinteddate() {
        return printeddate;
    }

    public void setPrinteddate(String printeddate) {
        this.printeddate = printeddate;
    }

}
