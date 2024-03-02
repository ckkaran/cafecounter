package com.example.foodpos_counter.Interface;

import com.example.foodpos_counter.Model.Addons;

import java.util.ArrayList;

public interface Inter {

    void addCart(String productsId,
                 String categoryId,
                 String productName,
                 String productImage,
                 String varientId,
                 String vatCharge,
                 String varientName,
                 String price,
                 String kitchenId,
                 String kitchenName,
                 String kitchenIpAdrees,
                 String kitchenPort,
                 ArrayList<Addons> addons
    );

    void addOneQuantity(String product_id);

    void reduceOneQuantity(String product_id);
    void deleteSingleItem(String product_id);

    void UpdateNotes(String product_id, String notes);

    void UpdateOrderNotes(String notes);
}
