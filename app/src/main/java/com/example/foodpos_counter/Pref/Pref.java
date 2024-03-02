package com.example.foodpos_counter.Pref;

import android.content.Context;
import android.content.SharedPreferences;

public class Pref {
    public SharedPreferences shared;
    public SharedPreferences.Editor editor;

    public String PREF = "pref";
    public String login = "login";
    public String name = "name";
    public String email = "email";
    public String password = "password";
    public String counterId = "counterId";

    public String customer_name = "customer_name";
    public String customer_email = "customer_email";
    public String customer_mobile = "customer_mobile";
    public String mobileno = "mobileno";
    public String waiter_id = "waiter_id";
    public String print_refresh = "print_refresh";

    public Pref(Context mcontext) {
        shared = mcontext.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public void clearData() {
        editor = shared.edit();
        editor.clear().apply();
    }

    //Getter ---------------------------------------------------------------------------

    public String getLogin() {
        return shared.getString(login,"");
    }

    public String getName() {
        return shared.getString(name,"");
    }
    public String getCounterId() {
        return shared.getString(counterId,"");
    }

    public String getEmail() {
        return shared.getString(email,"");
    }

    public String getMobileno() {
        return shared.getString(mobileno,"");
    }

    public String getCustomer_name() {
        return shared.getString(customer_name,"");
    }

    public String getCustomer_email() {
        return shared.getString(customer_email,"");
    }

    public String getCustomer_mobile() {
        return shared.getString(customer_mobile,"");
    }

    public String getPassword() {
        return shared.getString(password,"");
    }

    public String getWaiter_id() {
        return shared.getString(waiter_id,"");
    }
    public String getPrint_refresh() {
        return shared.getString(print_refresh,"");
    }


    // Setter---------------------------------------------------------------------------

    public void setLogin(String val) {
        editor = shared.edit();
        editor.putString(login,val).apply();
    }

    public void setName(String val) {
        editor = shared.edit();
        editor.putString(name,val).apply();
    }

    public void setEmail(String val) {
        editor = shared.edit();
        editor.putString(email,val).apply();
    }

    public void setCounterId(String val) {
        editor = shared.edit();
        editor.putString(counterId,val).apply();
    }

    public void setCustomer_name(String val) {
        editor = shared.edit();
        editor.putString(customer_name,val).apply();
    }

    public void setCustomer_email(String val) {
        editor = shared.edit();
        editor.putString(customer_email,val).apply();
    }

    public void setCustomer_mobile(String val) {
        editor = shared.edit();
        editor.putString(customer_mobile,val).apply();
    }


    public void setMobileno(String val) {
        editor = shared.edit();
        editor.putString(mobileno,val).apply();
    }

    public void setWaiter_id(String val) {
        editor = shared.edit();
        editor.putString(waiter_id,val).apply();
    }
    public void setPrint_refresh(String val) {
        editor = shared.edit();
        editor.putString(print_refresh,val).apply();
    }
}
