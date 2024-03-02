package com.example.foodpos_counter.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodpos_counter.db.Entity.Add_ons_cart;
import com.example.foodpos_counter.db.Entity.CartTable;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DAO {

    @Insert
    void insert (CartTable cartTable);
    @Query("Select * From add_cart Order by id")
    LiveData<List<CartTable>> getcart();

    //cart item....................................

    @Query("Select * From add_cart WHERE productsId = :itemid ORDER BY id ASC")
    LiveData<List<CartTable>> getOneCartItem(String itemid);

    @Query("SELECT qty FROM add_cart WHERE productsId = :product_id")
    LiveData<List<String>> getqty(String product_id);

    @Query("UPDATE add_cart SET qty = :q WHERE productsId = :id")
    void updateQuantity(String id ,String q);

    @Query("UPDATE add_cart SET addons = :addons , addons_status = :addons_status WHERE productsId = :product_id")
    void updateAddonsQuantity(ArrayList<Add_ons_cart> addons, String addons_status, String product_id);

    @Query("UPDATE add_cart SET addons = :addons WHERE productsId = :product_id")
    void updateAddonsQuantitys(ArrayList<Add_ons_cart> addons,  String product_id);

    @Query("UPDATE add_cart SET notes = :notes WHERE productsId = :product_id")
    void UpdateNotes(String notes, String product_id);

    @Query("UPDATE add_cart SET qty = qty + 1 WHERE productsId = :product_id")
    void AddOneQuantity(String product_id);

    @Query("UPDATE add_cart SET qty = qty - 1 WHERE productsId = :product_id")
    void ReducesQuantity(String product_id);


    @Query("DELETE from add_cart WHERE productsId = :product_id")
    void deletesingledata(String product_id);

    @Query("DELETE from add_cart WHERE uid = :uid")
    void deleteall(int uid);

    @Delete
    void delete(CartTable cartTable);

}

