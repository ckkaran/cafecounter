package com.example.foodpos_counter;

import androidx.appcompat.app.AppCompatActivity;
import com.example.foodpos_counter.Notification.ConnectivityReceiver;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodpos_counter.Api.Api;
import com.example.foodpos_counter.Model.cate_food;
import com.example.foodpos_counter.Model.login;
import com.example.foodpos_counter.Pref.Pref;
import com.imin.printerlib.IminPrintUtils;
import com.onesignal.OSDeviceState;
import com.onesignal.OneSignal;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    AppCompatButton login_btn;
    EditText email, password;
    Pref pref;

    OSDeviceState device = OneSignal.getDeviceState();
    String playerID;


    @Override
    protected void onResume() {
        super.onResume();
        boolean isConnected = ConnectivityReceiver.isConnected();

        if (!isConnected) {
            startActivity(new Intent(this, NoNetworkActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = new Pref(getApplicationContext());

        playerID = device.getUserId();


        login_btn = (AppCompatButton) findViewById(R.id.login_btn);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Menu_Activity.class);
//                startActivity(intent);

                if (email.getText().toString().isEmpty()) {
                    email.setError("Enter the User Name");
                   // Toast.makeText(MainActivity.this, "Enter the User Name", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Enter the Password");
                    //Toast.makeText(MainActivity.this, "Enter the password", Toast.LENGTH_SHORT).show();
                } else {

                    getlogin();

                }
            }
        });


    }

    private void getlogin() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<login> call = api.getlogin(email.getText().toString(), password.getText().toString());

        call.enqueue(new Callback<login>() {
            @Override
            public void onResponse(Call<login> call, Response<login> response) {
                progressDialog.dismiss();
               // Toast.makeText(MainActivity.this, response+"", Toast.LENGTH_SHORT).show();
                try {
                    if (response.body().getStatus().equals("true")) {
                        pref.setLogin("success");
                        pref.setEmail(email.getText().toString());
                        pref.setCounterId(response.body().getData().getCounterId());

                        Intent intent = new Intent(MainActivity.this, Menu_Activity.class);
                        intent.putExtra("order_id", "");
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Email Address or Password", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<login> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something want wrong", Toast.LENGTH_SHORT).show();


            }
        });
    }
}