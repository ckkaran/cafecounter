package com.example.foodpos_counter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;

import com.example.foodpos_counter.Notification.ConnectivityReceiver;

public class NoNetworkActivity extends AppCompatActivity {

    AppCompatButton retry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_network);

        retry = findViewById(R.id.retry);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //progressBar.setVisibility(View.GONE);
                retry.setVisibility(View.VISIBLE);

                boolean isConnected = ConnectivityReceiver.isConnected();
                if (isConnected) {
                    onBackPressed();
                    finish();
                }
            }
        });
    }

}