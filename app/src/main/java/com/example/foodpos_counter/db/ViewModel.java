package com.example.foodpos_counter.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.foodpos_counter.db.Entity.CartTable;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<CartTable>> tb_cart;
    public ViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
        this.tb_cart = repository.getCartItem();
    }

    //..............................................................................................

    public LiveData<List<CartTable>> getTb_cart() {
        return tb_cart;
    }

    public LiveData<List<CartTable>> getOneCartItem(String itemId){
        return repository.getOneCartItem(itemId);
    }
    public LiveData<List<String>> getQty(String product_id){
        return repository.getqty(product_id);
    }

    //..............................................................................................

    public void insert(CartTable cartTable){
        repository.insert(cartTable);
    }

    public void addOnesItem(String product_id) {
        repository.addOnesItem(product_id);
    }


    public void updateQuantity(int id, int q) {
        repository.updateQuantity(id, q);
    }

    public void UpdateNotes(String notes, String product_id) {
        repository.UpdateNotes(notes, product_id);
    }

    public void reducesItem(String product_id) {
        repository.reducesItem(product_id);
    }

    public void deletesingaldata(String product_id){
        repository.deletesingleitem(product_id);
    }

    public void deleteall(int uid){
        repository.deleteall(uid);
    }

    public void delete(CartTable cartTable) {
        repository.delete(cartTable);
    }
}

