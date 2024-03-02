package com.example.foodpos_counter.Reports;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodpos_counter.Adapter.CounterAdapter;
import com.example.foodpos_counter.Api.Api;
import com.example.foodpos_counter.Model.CounterWise;
import com.example.foodpos_counter.Model.CounterWiseList;
import com.example.foodpos_counter.Pref.Pref;
import com.example.foodpos_counter.R;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CounterReportsActivity extends AppCompatActivity {

    Pref pref;
    RecyclerView counter_recycler;
    CounterAdapter counterAdapter;

    EditText from_date;
    EditText to_date;

    private int mDay, mMonth, mYear;

    TextView total;
    AppCompatButton search;
    AppCompatButton close_arrow;

    ArrayList<CounterWiseList> counterWiseLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_reports);

        pref = new Pref(getApplicationContext());

        counter_recycler = (RecyclerView) findViewById(R.id.counter_recycler);
        from_date = (EditText) findViewById(R.id.from_date);
        to_date = (EditText) findViewById(R.id.to_date);
        total = (TextView) findViewById(R.id.total);
        search = (AppCompatButton) findViewById(R.id.search);
        close_arrow = (AppCompatButton) findViewById(R.id.close_arrow);

        counterWiseLists = new ArrayList<>();

        close_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CounterReportsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                if ((monthOfYear + 1) < 10) {
                                    if (dayOfMonth < 10) {
                                        from_date.setText("0" + dayOfMonth + "-" + "0" + (monthOfYear + 1) + "-" + year);
                                    } else {
                                        from_date.setText(dayOfMonth + "-" + "0" + (monthOfYear + 1) + "-" + year);
                                    }
                                } else {
                                    from_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                }
                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.show();
            }
        });

        to_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CounterReportsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                if ((monthOfYear + 1) < 10) {
                                    if (dayOfMonth < 10) {
                                        to_date.setText("0" + dayOfMonth + "-" + "0" + (monthOfYear + 1) + "-" + year);
                                    } else {
                                        to_date.setText(dayOfMonth + "-" + "0" + (monthOfYear + 1) + "-" + year);
                                    }
                                } else {
                                    to_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                }
                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.show();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        counter_recycler.setLayoutManager(layoutManager);

        counterAdapter = new CounterAdapter(CounterReportsActivity.this, counterWiseLists);
        counter_recycler.setAdapter(counterAdapter);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (from_date.getText().toString().equals("")){
                    from_date.setError("Enter the From date");
                }
                else if (to_date.getText().toString().equals("")){
                    to_date.setError("Enter the To date");
                }
                else {
                    getCounterReport();
                }
            }
        });

    }

    private void getCounterReport() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
        dialog.show();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<CounterWise> call = api.getCounterWiseReport(from_date.getText().toString(), to_date.getText().toString(), pref.getCounterId());

        call.enqueue(new Callback<CounterWise>() {
            @Override
            public void onResponse(Call<CounterWise> call, Response<CounterWise> response) {
                dialog.dismiss();
                counterWiseLists.removeAll(counterWiseLists);
                try {
                    if (response.body().getStatus().equals("true")) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            String salesdate = response.body().getData().get(i).getSalesdate();
                            String saleinvoice = response.body().getData().get(i).getSaleinvoice();
                            String countername = response.body().getData().get(i).getCountername();
                            String foodtotalamount = response.body().getData().get(i).getFoodtotalamount();
                            String vatamount = response.body().getData().get(i).getVatamount();
                            String servicecharge = response.body().getData().get(i).getServicecharge();
                            String discount = response.body().getData().get(i).getDiscount();
                            String totalamount = response.body().getData().get(i).getTotalamount();

                            counterWiseLists.add(new CounterWiseList(salesdate,
                                    saleinvoice,
                                    countername,
                                    foodtotalamount,
                                    vatamount,
                                    servicecharge,
                                    discount,
                                    totalamount));
                        }
                        counterAdapter.notifyDataSetChanged();

                        total.setText(response.body().getTodaysaleamount());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CounterWise> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(CounterReportsActivity.this, t.toString() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}