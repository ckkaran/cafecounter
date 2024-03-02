package com.example.foodpos_counter.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.foodpos_counter.db.Entity.CartTable;

import java.util.List;

public class Repository {

    private DAO dao;
    private LiveData<List<CartTable>> cartTable;

    Repository(Application application) {
        RoomDB db = RoomDB.getDatabase(application);
        dao = db.dao();
        cartTable = dao.getcart();
    }


    //cart..........................................
    LiveData<List<CartTable>> getCartItem() {
        return cartTable;
    }

    LiveData<List<CartTable>> getOneCartItem(String itemId) {
        return dao.getOneCartItem(itemId);
    }

    LiveData<List<String>> getqty(String product_id) {
        return dao.getqty(product_id);
    }

    public void insert(CartTable tb_cart){
        new insertAsyncTask(dao).execute(tb_cart);
    }

    public static class insertAsyncTask extends AsyncTask<CartTable , Void,Void> {
        private DAO da;

        public insertAsyncTask(DAO dao){
            this.da = dao;
        }

        @Override
        protected Void doInBackground(CartTable... Params) {
            da.insert(Params[0]);
            return null;
        }
    }


    public void updateQuantity(int id, int q) {   //the value are same datatypes provider
        new updateQuantity(dao).execute( id, q);
    }

    private class updateQuantity extends AsyncTask<Integer, Void, Void> {
        DAO dao;

        public updateQuantity(DAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            dao.updateQuantity(String.valueOf(integers[0]), String.valueOf(integers[1]));
            return null;
        }
    }


    public void addOnesItem(String product_id) {
        new addOnesQuantity(dao). execute(product_id);
    }

    private class addOnesQuantity extends AsyncTask<String, Void, Void> {
        DAO dao;

        public addOnesQuantity(DAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(String... integers) {
            dao.AddOneQuantity(integers[0]);
            return null;
        }
    }


    public void reducesItem( String product_id) {
        new reducesQuantity(dao).execute(product_id);
    }

    private class reducesQuantity extends AsyncTask<String, Void, Void> {
        DAO dao;

        public reducesQuantity(DAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(String... integers) {
            dao.ReducesQuantity(integers[0]);
            return null;
        }
    }

    public void UpdateNotes(String notes ,String product_id) {
        new UpdateNotes(dao).execute(notes,product_id);
    }

    private class UpdateNotes extends AsyncTask<String, Void, Void> {
        DAO dao;

        public UpdateNotes(DAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(String... integers) {
            dao.UpdateNotes(integers[0], integers[1]);
            return null;
        }
    }

    public void deletesingleitem(String product_id) {
        new deletesingleitem(dao).execute(product_id);
    }

    private class deletesingleitem extends AsyncTask<String, Void, Void> {
        DAO dao;

        public deletesingleitem(DAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(String... integers) {
            dao.deletesingledata(integers[0]);
            return null;
        }
    }

    public void deleteall(int uid) {
        new deleteall(dao).execute(uid);
    }

    private class deleteall extends AsyncTask<Integer, Void, Void> {
        DAO dao;

        public deleteall(DAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            dao.deleteall(integers[0]);
            return null;
        }
    }


    public void delete(CartTable cartTable){
        new delete(dao).execute(cartTable);
    }

    private class delete extends AsyncTask<CartTable, Void, Void>{
        DAO dao;
        public delete(DAO dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CartTable... params) {
            dao.delete(params[0]);
            return null;
        }
    }

}
