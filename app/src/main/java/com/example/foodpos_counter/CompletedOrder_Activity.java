package com.example.foodpos_counter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.Presentation;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodpos_counter.Adapter.CompleteOrderAdapter;
import com.example.foodpos_counter.Adapter.TodayFoodAdapter;
import com.example.foodpos_counter.Api.Api;
import com.example.foodpos_counter.Interface.complete_inter;
import com.example.foodpos_counter.Model.CounterFoodlist;
import com.example.foodpos_counter.Model.GetCounterPrint;
import com.example.foodpos_counter.Model.GetSelfkiosk;
import com.example.foodpos_counter.Model.TodayFoodView;
import com.example.foodpos_counter.Model.TodayOrder;
import com.example.foodpos_counter.Model.TodayOrderList;
import com.example.foodpos_counter.Model.TodayOrderView;
import com.example.foodpos_counter.Notification.ConnectivityReceiver;
import com.example.foodpos_counter.Pref.Pref;
import com.example.foodpos_counter.Utils.AnotherScreen;
import com.example.foodpos_counter.Utils.TestService;
import com.example.foodpos_counter.db.ViewModel;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.imin.printerlib.IminPrintUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompletedOrder_Activity extends AppCompatActivity implements complete_inter {

    Pref pref;
    ViewModel viewModel;
    RecyclerView completeorder_recycler;
    RecyclerView today_recycler;
    TextView subtotal;
    TextView service_change;
    TextView service_tax;
    TextView grandtotal;
    TextView paid_amt;
    TextView change_due;

    TextView total;

    CompleteOrderAdapter completeorderAdapter;

    TodayFoodAdapter todayFoodAdapter;
    AppCompatButton close_arrow;
    ArrayList<TodayOrderList> todayOrderLists;
    ArrayList<TodayFoodView> todayFoodViewArrayList;

    private IminPrintUtils mIminPrintUtils;

    private static final String TAG = "display_demo";
    private Presentation presentation;

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
        setContentView(R.layout.activity_completed_order);

//        if (mIminPrintUtils == null) {
//            mIminPrintUtils = IminPrintUtils.getInstance(CompletedOrder_Activity.this);
//        }
//        mIminPrintUtils.resetDevice();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(!Settings.canDrawOverlays(this)){
                // Toast.makeText(this,"请同意显示窗口权限",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION));
            }
        }

        pref = new Pref(getApplicationContext());
        completeorder_recycler = (RecyclerView) findViewById(R.id.completeorder_recycler);

        close_arrow = (AppCompatButton) findViewById(R.id.close_arrow);
        total = (TextView) findViewById(R.id.total);

        todayOrderLists = new ArrayList<>();
        todayFoodViewArrayList = new ArrayList<>();

        if(presentation != null){
            presentation.cancel();
        }
//                showSecondByMediaRouter(MainActivity.this);
        showSecondByDisplayManager(CompletedOrder_Activity.this);

        if(presentation != null){
            //presentation.cancel();
            //presentation..showTextView("");
            ImageView imageView = presentation.findViewById(R.id.image);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(R.mipmap.dhivyalogo);

        }

        close_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
//                viewModel.deleteall(1);
                pref.setPrint_refresh("yes");
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        completeorder_recycler.setLayoutManager(linearLayoutManager);

        completeorderAdapter = new CompleteOrderAdapter(CompletedOrder_Activity.this, todayOrderLists);
        completeorder_recycler.setAdapter(completeorderAdapter);

        getTodayOrder();
    }

    private void showSecondByDisplayManager(Context context) {
        DisplayManager mDisplayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = mDisplayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION);
        if (displays != null && getPresentationDisplays() != null) {
            presentation = new AnotherScreen(context, getPresentationDisplays());
            presentation.show();
        }else {
            Toast.makeText(CompletedOrder_Activity.this,getString(R.string.no_second_screen),Toast.LENGTH_SHORT);
        }
        /*副屏的Window*/
    }

    private Display getPresentationDisplays() {
        DisplayManager mDisplayManager= (DisplayManager)getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays =mDisplayManager.getDisplays();
        if (displays != null){
            for(int i=0;  i < displays.length; i++){
                Log.e(TAG,"屏幕==>" +displays[i] + " Flag:==> " + displays[i].getFlags());
                if((displays[i].getFlags() & Display.FLAG_SECURE)!=0
                        &&(displays[i].getFlags() & Display.FLAG_SUPPORTS_PROTECTED_BUFFERS)!=0
                        &&(displays[i].getFlags() & Display.FLAG_PRESENTATION) !=0){
                    Log.e(TAG,"第一个真实存在的副屏屏幕==> " + displays[i]);
                    return displays[i];
                }
            }
        }

        return null;
    }
    private void getTodayOrder() {
//        ProgressDialog dialog = new ProgressDialog(this);
//        dialog.setCancelable(false);
//        dialog.setMessage("Loading....");
//        dialog.show();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<TodayOrder> call = api.getTodayOrder();

        call.enqueue(new Callback<TodayOrder>() {
            @Override
            public void onResponse(Call<TodayOrder> call, Response<TodayOrder> response) {
              //  dialog.dismiss();
                try {
                    if (response.body().getStatus().equals("true")) {
                        for (int i = 0; i < response.body().getData().size(); i++) {

                            String order_id = response.body().getData().get(i).getOrder_id();
                            String saleinvoice = response.body().getData().get(i).getSaleinvoice();
                            String tokenno = response.body().getData().get(i).getTokenno();
                            String totalamount = response.body().getData().get(i).getTotalamount();
                            String customertype = response.body().getData().get(i).getCustomertype();
                            String waiter = response.body().getData().get(i).getWaiter();
                            String tablename = response.body().getData().get(i).getTablename();
                            String orderdate = response.body().getData().get(i).getOrderdate();
                            String ordertime = response.body().getData().get(i).getOrdertime();
                            String paymentmethod = response.body().getData().get(i).getPaymentmethod();
                            String countername = response.body().getData().get(i).getCountername();


                            todayOrderLists.add(new TodayOrderList(order_id,
                                    saleinvoice,
                                    tokenno,
                                    totalamount,
                                    customertype,
                                    waiter,
                                    tablename,
                                    orderdate,
                                    ordertime,
                                    paymentmethod,
                                    countername));
                        }

                        completeorderAdapter.notifyDataSetChanged();
                        total.setText("RM " + response.body().getGrandtotal());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<TodayOrder> call, Throwable t) {
              //  dialog.dismiss();
            }
        });
    }

    @Override
    public void OrderView(String order_id) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.today_order_layout);
        dialog.getWindow().setLayout(1200, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

     //   mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.USB);

        today_recycler = (RecyclerView) dialog.findViewById(R.id.today_recycler);
        subtotal = (TextView) dialog.findViewById(R.id.subtotal);
        service_change = (TextView) dialog.findViewById(R.id.service_change);
        service_tax = (TextView) dialog.findViewById(R.id.service_tax);
        grandtotal = (TextView) dialog.findViewById(R.id.grandtotal);
        paid_amt = (TextView) dialog.findViewById(R.id.paid_amt);
        change_due = (TextView) dialog.findViewById(R.id.change_due);
        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        AppCompatButton accept = (AppCompatButton) dialog.findViewById(R.id.accept);
        AppCompatButton cancel = (AppCompatButton) dialog.findViewById(R.id.cancel);
        LinearLayout confirm_layout = (LinearLayout) dialog.findViewById(R.id.confirm_layout);
        TextInputLayout order_view = (TextInputLayout) dialog.findViewById(R.id.order_view);
        TextInputLayout waiter_view = (TextInputLayout) dialog.findViewById(R.id.waiter_view);

        order_view.setVisibility(View.GONE);
        waiter_view.setVisibility(View.GONE);

        cancel.setText("Close");
        accept.setText("Duplicate Print");

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNextPage(order_id);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        getTodayFood(order_id);

    }

    private void getNextPage(String place_order_id) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<GetCounterPrint> call = api.getCounterPrint(place_order_id);

        call.enqueue(new Callback<GetCounterPrint>() {
            @Override
            public void onResponse(Call<GetCounterPrint> call, Response<GetCounterPrint> response) {
                try {

                    if (response.body().getStatus().equals("true")) {
                        String companyname = response.body().getData().getCompanyname();
                        String counterip = response.body().getData().getCounterip();
                        String printname = response.body().getData().getPrintname();
                        String companyaddress1 = response.body().getData().getCompanyaddress1();
                        String companyaddress2 = response.body().getData().getCompanyaddress2();
                        String companyaddress3 = response.body().getData().getCompanyaddress3();
                        String companylogo = response.body().getData().getCompanylogo();
                        String orderdate = response.body().getData().getOrderdate();
                        String ordertime = response.body().getData().getOrdertime();
                        String customername = response.body().getData().getCustomername();
                        String customertype = response.body().getData().getCustomertype();
                        String billbyname = response.body().getData().getBillbyname();
                        String countername = response.body().getData().getCountername();
                        String saleinvoice = response.body().getData().getSaleinvoice();
                        String tableno = response.body().getData().getTableno();
                        String tokenno = response.body().getData().getTokenno();
                        ArrayList<CounterFoodlist> foodlistArrayList = response.body().getData().getFoodlist();
                        String foodtotalamount = response.body().getData().getFoodtotalamount();
                        String taxpercentage = response.body().getData().getTaxpercentage();
                        String taxnumber = response.body().getData().getTaxnumber();
                        String totalvatamount = response.body().getData().getTotalvatamount();
                        String servicecharge = response.body().getData().getServicecharge();
                        String discount = response.body().getData().getDiscount();
                        String grandtotal = response.body().getData().getGrandtotal();
                        String paidamount = response.body().getData().getPaidamount();
                        String changedue = response.body().getData().getChangedue();
                        String totalamount = response.body().getData().getTotalamount();
                        String paymenttype = response.body().getData().getPaymenttype();
                        String paymentstatus = response.body().getData().getPaymentstatus();

                        Gson gson = new Gson();
                        String arrayListJson = gson.toJson(foodlistArrayList);

                        Intent i = new Intent(CompletedOrder_Activity.this, TestService.class);
                        i.putExtra("connectType", 1);
                        i.putExtra("companyname", companyname);
                        i.putExtra("companyaddress1", companyaddress1);
                        i.putExtra("companyaddress2", companyaddress2);
                        i.putExtra("companyaddress3", companyaddress3);
                        i.putExtra("orderdate", orderdate);
                        i.putExtra("ordertime", ordertime);
                        i.putExtra("customername", customername);
                        i.putExtra("customertype", customertype);
                        i.putExtra("billbyname", billbyname);
                        i.putExtra("countername", countername);
                        i.putExtra("saleinvoice", saleinvoice);
                        i.putExtra("tableno", tableno);
                        i.putExtra("tokenno", tokenno);
                        i.putExtra("jsonArray", arrayListJson);
                        i.putExtra("foodtotalamount", foodtotalamount);
                        i.putExtra("taxpercentage", taxpercentage);
                        i.putExtra("taxnumber", taxnumber);
                        i.putExtra("totalvatamount", totalvatamount);
                        i.putExtra("servicecharge", servicecharge);
                        i.putExtra("discount", discount);
                        i.putExtra("grandtotal", grandtotal);
                        i.putExtra("paidamount", paidamount);
                        i.putExtra("changedue", changedue);
                        i.putExtra("totalamount", totalamount);
                        i.putExtra("paymenttype", paymenttype);
                        i.putExtra("paymentstatus", paymentstatus);
                        startService(i);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetCounterPrint> call, Throwable t) {
                Toast.makeText(CompletedOrder_Activity.this, t.toString() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTodayFood(String order_id) {
//        ProgressDialog dialog = new ProgressDialog(this);
//        dialog.setCancelable(false);
//        dialog.setMessage("Loading....");
//        dialog.show();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<TodayOrderView> call = api.getTodayOrderView(order_id);

        call.enqueue(new Callback<TodayOrderView>() {
            @Override
            public void onResponse(Call<TodayOrderView> call, Response<TodayOrderView> response) {
                //dialog.dismiss();
                try {
                    if (response.body().getStatus().equals("true")) {
                        for (int i = 0; i < response.body().getData().size(); i++) {

                            subtotal.setText(response.body().getData().get(i).getSubtotal());
                            service_change.setText(response.body().getData().get(i).getServicecharge());
                            service_tax.setText(response.body().getData().get(i).getTotalvat());
                            grandtotal.setText(response.body().getData().get(i).getGrandtotal());
                            paid_amt.setText(response.body().getData().get(i).getCustomerpaid());
                            change_due.setText(response.body().getData().get(i).getDueamount());
                            todayFoodViewArrayList = response.body().getData().get(i).getFoodlist();

                            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            today_recycler.setLayoutManager(layoutManager);

                            todayFoodAdapter = new TodayFoodAdapter(CompletedOrder_Activity.this, todayFoodViewArrayList);
                            today_recycler.setAdapter(todayFoodAdapter);

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<TodayOrderView> call, Throwable t) {

            }
        });
    }

    @Override
    public void OrderPrint(String order_id) {

    }

    @Override
    public void OrderToken(String order_id) {

    }
}