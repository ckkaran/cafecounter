package com.example.foodpos_counter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.foodpos_counter.Api.Api;
import com.example.foodpos_counter.Model.LogEntry;
import com.example.foodpos_counter.Model.OrderSummary;
import com.example.foodpos_counter.Notification.ConnectivityReceiver;
import com.example.foodpos_counter.Pref.Pref;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashScreen_Activity extends AppCompatActivity {

    Pref pref;

    @Override
    protected void onResume() {
        super.onResume();

//        boolean isConnected = ConnectivityReceiver.isConnected();
//
//        if (!isConnected) {
//            startActivity(new Intent(this, NoNetworkActivity.class));
//        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        pref = new Pref(getApplicationContext());



        getEntry();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //  throw new RuntimeException("Test Crash"); // Force a crash
                if (pref.getLogin().equals("success")) {
                    Intent i = new Intent(SplashScreen_Activity.this, Menu_Activity.class);
                    i.putExtra("order_id","");
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(SplashScreen_Activity.this, MainActivity.class);
                    i.putExtra("order_id","");
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }

            }
        },3000);


//        FirebaseMessaging.getInstance().getToken();
//        getFirebaseMessagingToken();
    }

    private void getFirebaseMessagingToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {

                    }
                });
    }

    private void getEntry() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<LogEntry> call = api.getEntry();

        call.enqueue(new Callback<LogEntry>() {
            @Override
            public void onResponse(Call<LogEntry> call, Response<LogEntry> response) {

            }

            @Override
            public void onFailure(Call<LogEntry> call, Throwable t) {

            }
        });
    }
}