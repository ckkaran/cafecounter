package com.example.foodpos_counter.Interface;

public interface Self_inter {

    void accept(String order_id, String customer_type,String customer_id, String table_id, String table_name);

    void cancel(String order_id);

    void addPrinter();
}
