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

import com.example.foodpos_counter.Adapter.CounterAdapter;
import com.example.foodpos_counter.Adapter.FoodAdapter;
import com.example.foodpos_counter.Api.Api;
import com.example.foodpos_counter.Model.CounterWise;
import com.example.foodpos_counter.Model.FoodWise;
import com.example.foodpos_counter.Model.FoodWiseList;
import com.example.foodpos_counter.Pref.Pref;
import com.example.foodpos_counter.R;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodReportActivity extends AppCompatActivity {

    Pref pref;
    RecyclerView food_recycler;
    FoodAdapter foodAdapter;

    EditText from_date;
    EditText to_date;

    private int mDay, mMonth, mYear;

    TextView total;
    AppCompatButton search;
    AppCompatButton close_arrow;

    ArrayList<FoodWiseList> foodWiseLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_report);


        pref = new Pref(getApplicationContext());

        food_recycler = (RecyclerView) findViewById(R.id.food_recycler);
        from_date = (EditText) findViewById(R.id.from_date);
        to_date = (EditText) findViewById(R.id.to_date);
        total = (TextView) findViewById(R.id.total);
        search = (AppCompatButton) findViewById(R.id.search);
        close_arrow = (AppCompatButton) findViewById(R.id.close_arrow);

        foodWiseLists = new ArrayList<>();

        close_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                // pref.setPrint_refresh("yes");
            }
        });

        from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(FoodReportActivity.this,
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(FoodReportActivity.this,
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
        food_recycler.setLayoutManager(layoutManager);

        foodAdapter = new FoodAdapter(FoodReportActivity.this, foodWiseLists);
        food_recycler.setAdapter(foodAdapter);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (from_date.getText().toString().equals("")) {
                    from_date.setError("Enter the From date");
                } else if (to_date.getText().toString().equals("")) {
                    to_date.setError("Enter the To date");
                } else {
                    getFoodReport();
                }
            }
        });
    }

    private void getFoodReport() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
        dialog.show();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<FoodWise> call = api.getFoodWiseReport(from_date.getText().toString(), to_date.getText().toString(), pref.getCounterId());

        call.enqueue(new Callback<FoodWise>() {
            @Override
            public void onResponse(Call<FoodWise> call, Response<FoodWise> response) {
                dialog.dismiss();
                foodWiseLists.removeAll(foodWiseLists);

                try {
                    if (response.body().getStatus().equals("true")) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            String orderdate = response.body().getData().get(i).getOrderdate();
                            String ProductName = response.body().getData().get(i).getProductName();
                            String variantName = response.body().getData().get(i).getVariantName();
                            String qty = response.body().getData().get(i).getQty();
                            String price = response.body().getData().get(i).getPrice();
                            String totalprice = response.body().getData().get(i).getTotalprice();

                            foodWiseLists.add(new FoodWiseList(orderdate,
                                    ProductName,
                                    variantName,
                                    qty,
                                    price,
                                    totalprice));
                        }
                        foodAdapter.notifyDataSetChanged();

                        total.setText("RM " + response.body().getTotalamount());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<FoodWise> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }
}