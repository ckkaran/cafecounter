package com.example.foodpos_counter.Interface;

public interface Ongoing_inter {

    void cancel(String order_id);

    void complete(String order_id, String total_amt, String total_due , String table_no, String order_no);

    void reomveprinter();

    void delete();
}
