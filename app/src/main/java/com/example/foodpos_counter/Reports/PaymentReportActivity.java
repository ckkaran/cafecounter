package com.example.foodpos_counter.Reports;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.foodpos_counter.Adapter.CounterAdapter;
import com.example.foodpos_counter.Adapter.PaymentAdapter;
import com.example.foodpos_counter.Adapter.PaymentMethodAdapter;
import com.example.foodpos_counter.Api.Api;
import com.example.foodpos_counter.Menu_Activity;
import com.example.foodpos_counter.Model.CounterWise;
import com.example.foodpos_counter.Model.CounterWiseList;
import com.example.foodpos_counter.Model.GetPayment;
import com.example.foodpos_counter.Model.PaymentWise;
import com.example.foodpos_counter.Model.PaymentWiseList;
import com.example.foodpos_counter.Model.Paymenttypes;
import com.example.foodpos_counter.Pref.Pref;
import com.example.foodpos_counter.R;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaymentReportActivity extends AppCompatActivity {

    Pref pref;
    RecyclerView payment_recycler;
    PaymentAdapter paymentAdapter;
    PaymentMethodAdapter paymentMethodAdapter;

    EditText from_date;
    EditText to_date;
    EditText payment;

    private int mDay, mMonth, mYear;

    TextView total;
    AppCompatButton search;
    AppCompatButton close_arrow;

    ArrayList<PaymentWiseList> paymentWiseLists;
    ArrayList<Paymenttypes> paymenttypesArrayList;

    String paymentId_str;
    String paymentType_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_report);

        pref = new Pref(getApplicationContext());

        payment_recycler = (RecyclerView) findViewById(R.id.payment_recycler);
        from_date = (EditText) findViewById(R.id.from_date);
        to_date = (EditText) findViewById(R.id.to_date);
        payment = (EditText) findViewById(R.id.payment);
        total = (TextView) findViewById(R.id.total);
        search = (AppCompatButton) findViewById(R.id.search);
        close_arrow = (AppCompatButton) findViewById(R.id.close_arrow);

        paymentWiseLists = new ArrayList<>();
        paymenttypesArrayList = new ArrayList<>();

        getPaymenttype();

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

                DatePickerDialog datePickerDialog = new DatePickerDialog(PaymentReportActivity.this,
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(PaymentReportActivity.this,
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

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPaymentMethodDialog();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        payment_recycler.setLayoutManager(layoutManager);

        paymentAdapter = new PaymentAdapter(PaymentReportActivity.this, paymentWiseLists);
        payment_recycler.setAdapter(paymentAdapter);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (from_date.getText().toString().equals("")){
                    from_date.setError("Enter the From Date");
                }
                else if (to_date.getText().toString().equals("")){
                    to_date.setError("Enter the To date");
                }
                else if(payment.getText().toString().equals("")){
                    payment.setError("Enter the Payment Method");
                }
                else {
                    getPaymentReport();
                }
            }
        });
    }

    private void getPaymenttype() {
//        ProgressDialog dialog = new ProgressDialog(this);
//        dialog.setCancelable(false);
//        dialog.setMessage("Loading...");
//        dialog.show();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<GetPayment> call = api.getPaymentType();

        call.enqueue(new Callback<GetPayment>() {
            @Override
            public void onResponse(Call<GetPayment> call, Response<GetPayment> response) {
                //dialog.dismiss();
                try {
                    paymenttypesArrayList.removeAll(paymenttypesArrayList);
                    if (response.body().getStatus().equals("true")) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            String paymentId = response.body().getData().get(i).getPaymentId();
                            String paymentType = response.body().getData().get(i).getPaymentType();
                            String paymentTypeImage = response.body().getData().get(i).getPaymentTypeImage();

                            paymenttypesArrayList.add(new Paymenttypes(paymentId,
                                    paymentType,
                                    paymentTypeImage));

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetPayment> call, Throwable t) {
                //dialog.dismiss();
            }
        });
    }

    private void getPaymentMethodDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);
        Window window = dialog.getWindow();
        window.setLayout(800, AbsListView.LayoutParams.WRAP_CONTENT);

        RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
        ImageButton close = dialog.findViewById(R.id.close);
        TextView text = (TextView) dialog.findViewById(R.id.text);

        text.setText("Payment Method");

        LinearLayoutManager layoutManager = new LinearLayoutManager(dialog.getContext());
        recyclerView.setLayoutManager(layoutManager);

        paymentMethodAdapter = new PaymentMethodAdapter(PaymentReportActivity.this, paymenttypesArrayList);
        recyclerView.setAdapter(paymentMethodAdapter);
        paymentMethodAdapter.setOnItemClickListener(new PaymentMethodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ArrayList<Paymenttypes> paymenttypesArrayList, int position) {
                paymentId_str = paymenttypesArrayList.get(position).getPaymentId();
                paymentType_str = paymenttypesArrayList.get(position).getPaymentType();

                payment.setText(paymentType_str);
                payment.setError(null);

                dialog.dismiss();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void getPaymentReport() {

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
        dialog.show();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);


        Call<PaymentWise> call = api.getPaymentWiseReport(from_date.getText().toString(), to_date.getText().toString(), pref.getCounterId(), paymentId_str);

        call.enqueue(new Callback<PaymentWise>() {
            @Override
            public void onResponse(Call<PaymentWise> call, Response<PaymentWise> response) {
                dialog.dismiss();
                paymentWiseLists.removeAll(paymentWiseLists);
                try {
                    if (response.body().getStatus().equals("true")) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            String salesdate = response.body().getData().get(i).getSalesdate();
                            String saleinvoice = response.body().getData().get(i).getSaleinvoice();
                            String paymentmethod = response.body().getData().get(i).getPaymentmethod();
                            String foodtotalamount = response.body().getData().get(i).getFoodtotalamount();
                            String vatamount = response.body().getData().get(i).getVatamount();
                            String servicecharge = response.body().getData().get(i).getServicecharge();
                            String discount = response.body().getData().get(i).getDiscount();
                            String totalamount = response.body().getData().get(i).getTotalamount();

                            paymentWiseLists.add(new PaymentWiseList(salesdate,
                                    saleinvoice,
                                    paymentmethod,
                                    foodtotalamount,
                                    vatamount,
                                    servicecharge,
                                    discount,
                                    totalamount));
                        }

                        paymentAdapter.notifyDataSetChanged();

                        total.setText(response.body().getTodaysaleamount());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<PaymentWise> call, Throwable t) {
                dialog.dismiss();
            }
        });

    }

}