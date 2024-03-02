package com.example.foodpos_counter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.foodpos_counter.Adapter.DisplayAdapter;
import com.example.foodpos_counter.Model.GetUnpaid_bill;
import com.example.foodpos_counter.Notification.ConnectivityReceiver;

import android.app.Dialog;
import android.app.Presentation;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodpos_counter.Adapter.BankAdapter;
import com.example.foodpos_counter.Adapter.CardAdapter;
import com.example.foodpos_counter.Adapter.OnGoingAdapter;
import com.example.foodpos_counter.Adapter.PaymentMethodAdapter;
import com.example.foodpos_counter.Api.Api;
import com.example.foodpos_counter.Interface.Ongoing_inter;
import com.example.foodpos_counter.Model.BankList;
import com.example.foodpos_counter.Model.CancelOrder;
import com.example.foodpos_counter.Model.CardList;
import com.example.foodpos_counter.Model.CounterFoodlist;
import com.example.foodpos_counter.Model.GetBank;
import com.example.foodpos_counter.Model.GetCard;
import com.example.foodpos_counter.Model.GetCounterPrint;
import com.example.foodpos_counter.Model.GetOngoingList;
import com.example.foodpos_counter.Model.GetPayment;
import com.example.foodpos_counter.Model.IPAddress;
import com.example.foodpos_counter.Model.OngoingList;
import com.example.foodpos_counter.Model.PaymentResult;
import com.example.foodpos_counter.Model.Paymenttypes;
import com.example.foodpos_counter.Pref.Pref;
import com.example.foodpos_counter.Utils.AnotherScreen;
import com.example.foodpos_counter.Utils.DifferentDisplay;
import com.example.foodpos_counter.Utils.TestService;
import com.example.foodpos_counter.db.ViewModel;
import com.google.gson.Gson;
import com.imin.printerlib.IminPrintUtils;

import net.posprinter.posprinterface.IMyBinder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OnGoingOrder_Activity extends AppCompatActivity implements Ongoing_inter {

    Pref pref;
    ViewModel viewModel;

    RecyclerView ongoing_recycler;
    OnGoingAdapter ongoingAdapter;
    AppCompatButton close_arrow;

    EditText payment_method;
    EditText card_terminal;
    EditText select_bank;
    EditText pin;
    LinearLayout card_layout;
    LinearLayout pin_layout;

    private SwipeRefreshLayout swiperefreshlayout;

    BankAdapter bankAdapter;
    CardAdapter cardAdapter;
    PaymentMethodAdapter paymentMethodAdapter;

    ArrayList<OngoingList> ongoingListArrayList;
    ArrayList<Paymenttypes> paymenttypesArrayList;


    String paymentId_str;
    String paymentType_str;

    float total_flt;
    float paid_flt;

    int finalI;
    int finalI1;

   // public static IMyBinder myBinder;
    Handler handler;
    boolean ip_status;
    boolean txt_amt;
    float tot_bal;

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
        setContentView(R.layout.activity_on_going_order);

        viewModel = ViewModelProviders.of(this).get(ViewModel.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(!Settings.canDrawOverlays(this)){
               // Toast.makeText(this,"请同意显示窗口权限",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION));
            }
        }


        pref = new Pref(getApplicationContext());
        ongoing_recycler = (RecyclerView) findViewById(R.id.ongoing_recycler);
        close_arrow = (AppCompatButton) findViewById(R.id.close_arrow);
        swiperefreshlayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);

        ongoingListArrayList = new ArrayList<>();
        paymenttypesArrayList = new ArrayList<>();

        getPaymenttype();

        if(presentation != null){
            presentation.cancel();
        }
//                showSecondByMediaRouter(MainActivity.this);
        showSecondByDisplayManager2(OnGoingOrder_Activity.this);

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
            //    viewModel.deleteall(1);
                pref.setPrint_refresh("yes");
            }
        });

        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullAndRefresh();
            }
        });

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(), 6);
        ongoing_recycler.setLayoutManager(linearLayoutManager);

        ongoingAdapter = new OnGoingAdapter(OnGoingOrder_Activity.this, ongoingListArrayList);
        ongoing_recycler.setAdapter(ongoingAdapter);

        getOngoingOrder();

    }

    private void getOngoingOrder() {
//        ProgressDialog dialog = new ProgressDialog(this);
//        dialog.setMessage("Loading");
//        dialog.setCancelable(false);
//        dialog.show();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<GetOngoingList> call = api.getOngoingList();

        call.enqueue(new Callback<GetOngoingList>() {
            @Override
            public void onResponse(Call<GetOngoingList> call, Response<GetOngoingList> response) {
            //    dialog.dismiss();
                try {
                    if (response.body().getStatus().equals("true")) {
                        ongoingListArrayList.removeAll(ongoingListArrayList);
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            String orderid = response.body().getData().get(i).getOrderid();
                            String customertypeid = response.body().getData().get(i).getCustomertypeid();
                            String customerid = response.body().getData().get(i).getCustomerid();
                            String orderdate = response.body().getData().get(i).getOrderdate();
                            String ordertime = response.body().getData().get(i).getOrdertime();
                            String customername = response.body().getData().get(i).getCustomername();
                            String customeremail = response.body().getData().get(i).getCustomeremail();
                            String customerphone = response.body().getData().get(i).getCustomerphone();
                            String customertype = response.body().getData().get(i).getCustomertype();
                            String saleinvoice = response.body().getData().get(i).getSaleinvoice();
                            String totalamount = response.body().getData().get(i).getTotalamount();
                            String totaldueamount = response.body().getData().get(i).getTotaldueamount();
                            String tokenno = response.body().getData().get(i).getTokenno();
                            String tableid = response.body().getData().get(i).getTableid();
                            String tablename = response.body().getData().get(i).getTablename();
                            String waitername = response.body().getData().get(i).getWaitername();
                            String countername = response.body().getData().get(i).getCountername();

                            ongoingListArrayList.add(new OngoingList(orderid,
                                    customertypeid,
                                    customerid,
                                    orderdate,
                                    ordertime,
                                    customername,
                                    customeremail,
                                    customerphone,
                                    customertype,
                                    saleinvoice,
                                    totalamount,
                                    totaldueamount,
                                    tokenno,
                                    tableid,
                                    tablename,
                                    waitername,
                                    countername));
                        }
                        ongoingAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetOngoingList> call, Throwable t) {
                //dialog.dismiss();
            }
        });
    }

    @Override
    public void cancel(String order_id) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.cancel_layout);
        Window window = dialog.getWindow();
        window.setLayout(800, AbsListView.LayoutParams.WRAP_CONTENT);

        ImageView back = (ImageView) dialog.findViewById(R.id.back);
        EditText reason = (EditText) dialog.findViewById(R.id.reason);
        AppCompatButton cancel = (AppCompatButton) dialog.findViewById(R.id.cancel);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reason.getText().toString().equals("")) {
                    Toast.makeText(OnGoingOrder_Activity.this, "Enter the Reason", Toast.LENGTH_SHORT).show();
                } else {
                    getCancel(order_id, reason.getText().toString(), dialog);
                }
            }
        });

        dialog.show();
    }

    private void getCancel(String order_id, String reason, Dialog dialog) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<CancelOrder> call = api.getCancel(order_id, reason);

        call.enqueue(new Callback<CancelOrder>() {
            @Override
            public void onResponse(Call<CancelOrder> call, Response<CancelOrder> response) {
                try {
                    if (response.body().getStatus().equals("true")) {
                        dialog.dismiss();
                        getOngoingOrder();
                        getCancelDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CancelOrder> call, Throwable t) {

            }
        });
    }

    @Override
    public void complete(String order_id, String total_amt, String total_due, String table_no, String order_no) {
        getPlaceDialog(order_id, total_amt, total_due, table_no, order_no);

        if(presentation != null){
            presentation.cancel();
        }
//                showSecondByMediaRouter(MainActivity.this);
        showSecondByDisplayManager(OnGoingOrder_Activity.this);

        if(presentation != null){
            //presentation.cancel();
            //presentation..showTextView("");
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();

            Api api = retrofit.create(Api.class);

            Call<GetCounterPrint> call = api.getCounterPrint(order_id);

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


                            TextView subtotal = presentation.findViewById(R.id.subtotal);
                            subtotal.setText(foodtotalamount);

                            TextView service_charge = presentation.findViewById(R.id.service_charge);
                            service_charge.setText(servicecharge);

                            TextView tax_amt = presentation.findViewById(R.id.tax_amt);
                            tax_amt.setText(totalvatamount);

                            TextView total = presentation.findViewById(R.id.total);
                            total.setText(grandtotal);


                            RecyclerView recycle = presentation.findViewById(R.id.recycle);

                            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recycle.setLayoutManager(layoutManager);

                            DisplayAdapter displayAdapter = new DisplayAdapter(OnGoingOrder_Activity.this, foodlistArrayList);
                            recycle.setAdapter(displayAdapter);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<GetCounterPrint> call, Throwable t) {
                    // Toast.makeText(OnGoingOrder_Activity.this, t.toString() + "", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    private void showSecondByDisplayManager(Context context) {
        DisplayManager mDisplayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = mDisplayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION);
        if (displays != null && getPresentationDisplays() != null) {
            presentation = new DifferentDisplay(context, getPresentationDisplays());
            presentation.show();
        }else {
            Toast.makeText(OnGoingOrder_Activity.this,getString(R.string.no_second_screen),Toast.LENGTH_SHORT);
        }
        /*副屏的Window*/
    }

    private void showSecondByDisplayManager2(Context context) {
        DisplayManager mDisplayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = mDisplayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION);
        if (displays != null && getPresentationDisplays() != null) {
            presentation = new AnotherScreen(context, getPresentationDisplays());
            presentation.show();
        }else {
            Toast.makeText(OnGoingOrder_Activity.this,getString(R.string.no_second_screen),Toast.LENGTH_SHORT);
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

    @Override
    public void reomveprinter() {
//        for (int j = 0; j < myBinder.GetPrinterInfoList().size(); j++) {
//            myBinder.RemovePrinter(myBinder.GetPrinterInfoList().get(j).printerName, new TaskCallback() {
//                @Override
//                public void OnSucceed() {
//                  //  Toast.makeText(OnGoingOrder_Activity.this, "Removed", Toast.LENGTH_SHORT).show();
//
//                }
//
//                @Override
//                public void OnFailed() {
//
//                }
//            });
//        }
    }

    @Override
    public void delete() {
        viewModel.deleteall(1);
    }

    private void getPlaceDialog(String Place_order_id, String total_amt, String total_due_str, String table_no, String order_no) {
        Dialog dialog = new Dialog(OnGoingOrder_Activity.this);
        dialog.setContentView(R.layout.placeorder);
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT);

     //   mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.USB);

        ImageView placeorder_close = (ImageView) dialog.findViewById(R.id.placeorder_close);
        TextView grand_total = (TextView) dialog.findViewById(R.id.grand_total);
        TextView total_due = (TextView) dialog.findViewById(R.id.total_due);
        TextView discount_txt = (TextView) dialog.findViewById(R.id.discount_txt);
        payment_method = (EditText) dialog.findViewById(R.id.payment_method);
        card_terminal = (EditText) dialog.findViewById(R.id.card_terminal);
        select_bank = (EditText) dialog.findViewById(R.id.select_bank);
        pin = (EditText) dialog.findViewById(R.id.pin);
        card_layout = (LinearLayout) dialog.findViewById(R.id.card_layout);
        pin_layout = (LinearLayout) dialog.findViewById(R.id.pin_layout);
        LinearLayout layout = (LinearLayout) dialog.findViewById(R.id.layout);
        LinearLayout discount_layout = (LinearLayout) dialog.findViewById(R.id.discount_layout);
        RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
        AppCompatButton pay_btn = (AppCompatButton) dialog.findViewById(R.id.pay_btn);
        AppCompatButton unpaid_btn = (AppCompatButton) dialog.findViewById(R.id.unpaid_btn);
        AppCompatButton cancel = (AppCompatButton) dialog.findViewById(R.id.cancel);
        LinearLayout paid_layout = (LinearLayout) dialog.findViewById(R.id.paid_layout);
        LinearLayout change_layout = (LinearLayout) dialog.findViewById(R.id.change_layout);
        EditText paid_txt = (EditText) dialog.findViewById(R.id.paid_txt);
        TextView change_txt = (TextView) dialog.findViewById(R.id.change_txt);
        TextView table_name = (TextView) dialog.findViewById(R.id.table_name);
        TextView order_name = (TextView) dialog.findViewById(R.id.order_no);
        AppCompatButton btn1 = (AppCompatButton) dialog.findViewById(R.id.btn1);
        AppCompatButton btn2 = (AppCompatButton) dialog.findViewById(R.id.btn2);
        AppCompatButton btn3 = (AppCompatButton) dialog.findViewById(R.id.btn3);
        AppCompatButton btn4 = (AppCompatButton) dialog.findViewById(R.id.btn4);
        AppCompatButton btn5 = (AppCompatButton) dialog.findViewById(R.id.btn5);
        AppCompatButton btn6 = (AppCompatButton) dialog.findViewById(R.id.btn6);
        AppCompatButton btn7 = (AppCompatButton) dialog.findViewById(R.id.btn7);
        AppCompatButton btn8 = (AppCompatButton) dialog.findViewById(R.id.btn8);
        AppCompatButton btn9 = (AppCompatButton) dialog.findViewById(R.id.btn9);
        AppCompatButton btn0 = (AppCompatButton) dialog.findViewById(R.id.btn0);
        AppCompatButton clear = (AppCompatButton) dialog.findViewById(R.id.clear);
        AppCompatButton back = (AppCompatButton) dialog.findViewById(R.id.back);


        card_layout.setVisibility(View.GONE);
        pin_layout.setVisibility(View.GONE);
        cancel.setVisibility(View.GONE);
       // paymentId_str = "";

        payment_method.setText(paymentType_str);

        paid_layout.setVisibility(View.VISIBLE);
        change_layout.setVisibility(View.VISIBLE);

        table_name.setText(table_no);
        order_name.setText(order_no);

        discount_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_amt = true;
            }
        });

        paid_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_amt = false;
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_amt){
                    discount_txt.setText(discount_txt.getText().toString()+"1");
                }else {
                    paid_txt.setText(paid_txt.getText().toString() + "1");
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_amt){
                    discount_txt.setText(discount_txt.getText().toString()+"2");
                }else {
                    paid_txt.setText(paid_txt.getText().toString() + "2");
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_amt){
                    discount_txt.setText(discount_txt.getText().toString()+"3");
                }else {
                    paid_txt.setText(paid_txt.getText().toString() + "3");
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_amt){
                    discount_txt.setText(discount_txt.getText().toString()+"4");
                }else {
                    paid_txt.setText(paid_txt.getText().toString() + "4");
                }

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_amt){
                    discount_txt.setText(discount_txt.getText().toString()+"5");
                }else {
                    paid_txt.setText(paid_txt.getText().toString() + "5");
                }
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_amt){
                    discount_txt.setText(discount_txt.getText().toString()+"6");
                }else {
                    paid_txt.setText(paid_txt.getText().toString() + "6");
                }
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_amt){
                    discount_txt.setText(discount_txt.getText().toString()+"7");
                }else {
                    paid_txt.setText(paid_txt.getText().toString() + "7");
                }

            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_amt){
                    discount_txt.setText(discount_txt.getText().toString()+"8");
                }else {
                    paid_txt.setText(paid_txt.getText().toString() + "8");
                }

            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_amt){
                    discount_txt.setText(discount_txt.getText().toString()+"9");
                }else {
                    paid_txt.setText(paid_txt.getText().toString() + "9");
                }

            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_amt){
                    discount_txt.setText(discount_txt.getText().toString()+"0");
                }else {
                    paid_txt.setText(paid_txt.getText().toString() + "0");
                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_amt){
                    discount_txt.setText(discount_txt.getText().toString() + ".");
                }else {
                    paid_txt.setText(paid_txt.getText().toString() + ".");
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txt_amt){
                    if (discount_txt.getText().length() > 0) {
                        StringBuilder stringBuilder1 = new StringBuilder(discount_txt.getText());
                        stringBuilder1.deleteCharAt(discount_txt.getText().length() - 1);
                        String newString = stringBuilder1.toString();
                        discount_txt.setText(newString);
                    }
                    else{
                        discount_txt.setText("0");
                    }
                }else {
                    if (paid_txt.getText().length() > 0) {
                        StringBuilder stringBuilder = new StringBuilder(paid_txt.getText());
                        stringBuilder.deleteCharAt(paid_txt.getText().length() - 1);
                        String newString = stringBuilder.toString();
                        paid_txt.setText(newString);
                    }
                    else{
                        paid_txt.setText("0");
                    }
                }

            }
        });

        placeorder_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tot_bal = 0;
                dialog.dismiss();

                if(presentation != null){
                    presentation.cancel();
                }

                showSecondByDisplayManager2(OnGoingOrder_Activity.this);
                if(presentation != null){
                    //presentation.cancel();
                    //presentation..showTextView("");
                    ImageView imageView = presentation.findViewById(R.id.image);
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageResource(R.mipmap.dhivyalogo);

                }
            }
        });

        grand_total.setText("RM " + total_amt);
        total_due.setText("RM " + total_due_str);

        float Tot_flt1 = Float.parseFloat(total_amt);
        total_flt = Float.parseFloat(total_amt);

        GridLayoutManager layoutManager = new GridLayoutManager(dialog.getContext(), 4);
        recyclerView.setLayoutManager(layoutManager);

        paymentMethodAdapter = new PaymentMethodAdapter(OnGoingOrder_Activity.this, paymenttypesArrayList);
        recyclerView.setAdapter(paymentMethodAdapter);
        paymentMethodAdapter.setOnItemClickListener(new PaymentMethodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ArrayList<Paymenttypes> paymenttypesArrayList, int position) {
                paymentId_str = paymenttypesArrayList.get(position).getPaymentId();
                paymentType_str = paymenttypesArrayList.get(position).getPaymentType();
                payment_method.setText(paymentType_str);
                if (paymentId_str.equals("4")) {
                    paid_layout.setVisibility(View.VISIBLE);
                    change_layout.setVisibility(View.VISIBLE);
                } else {
                    paid_layout.setVisibility(View.GONE);
                    change_layout.setVisibility(View.GONE);
                    paid_txt.setText("0");
                    change_txt.setText("0");
                }
            }
        });

        discount_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                float dis_flt;
                if (discount_txt.getText().toString().equals("")){

                }else{
                    dis_flt = Float.parseFloat(discount_txt.getText().toString());
                    if(dis_flt >= Tot_flt1) {
                        discount_txt.setText("0");
                    }
                    else{

                            tot_bal = Tot_flt1 - dis_flt;
                            String bal_str = String.format("%.2f", tot_bal);
                            grand_total.setText("RM " +bal_str);

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        paid_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                float amt_flt;
                float Tot_flt;
                if (tot_bal == 0){
                    Tot_flt = Tot_flt1;
                }
                else{
                    Tot_flt = tot_bal;
                }

                if (paid_txt.getText().toString().equals("")){
                    amt_flt = 0;
                    change_txt.setText("0");
                }else {
                    amt_flt = Float.parseFloat(paid_txt.getText().toString());
                    paid_flt = Float.parseFloat(paid_txt.getText().toString());
                    if (amt_flt >= Tot_flt) {
                        if (amt_flt == 0) {
                            paid_txt.setText("0");
                            change_txt.setText("0");
                        } else {
                            float bal = amt_flt - Tot_flt;
                            String bal_str = String.format("%.2f", bal);
                            change_txt.setText(bal_str);
                        }
                    }
                    else{
                        change_txt.setText("0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (paymentId_str.equals("")) {
//                    payment_method.setError("Select the Payment method");
//                } else {
//                    if (paymentId_str.equals("1")) {
//                        if (card_terminal.getText().toString().equals("")) {
//                            card_terminal.setError("Select the card terminal");
//                        } else if (select_bank.getText().toString().equals("")) {
//                            select_bank.setError("Select the Bank");
//                        } else if (pin.getText().toString().equals("")) {
//                            pin.setError("Enter the Last 4 Digit");
//                        } else {
//                            getPaymentApi(dialog, Place_order_id, total_amt, paid_txt.getText().toString(), change_txt.getText().toString());
//
//                        }
//                    } else {
//                        getPaymentApi(dialog, Place_order_id, total_amt, paid_txt.getText().toString(), change_txt.getText().toString());
//
//                    }
//                }

                if (paymentId_str.equals("")) {
                    getSelectPaymentMethod();
                }
                else {
                    if (paymentId_str.equals("4")) {
                        if (paid_txt.getText().toString().equals("")) {
                            paid_txt.setError("Enter the Customer Paid Amount");
                        } else {

                            if (paid_flt < total_flt){
                                getCustomerPaid();
                            }
                            else {
                                getPaymentApi(dialog, Place_order_id, String.valueOf(tot_bal) , grand_total.getText().toString(), paid_txt.getText().toString(), change_txt.getText().toString());
                            }
                        }
                    }
                    else{
                        getPaymentApi(dialog, Place_order_id, String.valueOf(tot_bal), grand_total.getText().toString(), paid_txt.getText().toString(), change_txt.getText().toString());
                    }
//                    else {
//                        if (paymentId_str.equals("1")) {
//                            if (card_terminal.getText().toString().equals("")) {
//                                card_terminal.setError("Select the card terminal");
//                            }
//                            else if (select_bank.getText().toString().equals("")) {
//                                select_bank.setError("Select the Bank");
//                            }
//                            else if (pin.getText().toString().equals("")) {
//                                pin.setError("Enter the Last 4 Digit");
//                            }
//                            else {
//                                getPaymentApi(dialog, Place_order_id, total_amt, paid_txt.getText().toString(), change_txt.getText().toString());
//
//                            }
//                        } else {
//                            getPaymentApi(dialog, Place_order_id, total_amt, paid_txt.getText().toString(), change_txt.getText().toString());
//
//                        }
//                    }
                }
            }
        });

        unpaid_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUnpaidPrinter(Place_order_id);
            }
        });

        dialog.show();
    }

    private void getUnpaidPrinter(String place_order_id) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<GetUnpaid_bill> call = api.getUnpaidBill(place_order_id);

        call.enqueue(new Callback<GetUnpaid_bill>() {
            @Override
            public void onResponse(Call<GetUnpaid_bill> call, Response<GetUnpaid_bill> response) {
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

                        Intent i = new Intent(OnGoingOrder_Activity.this, TestService.class);
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

                        if(presentation != null){
                            presentation.cancel();
                        }

                        showSecondByDisplayManager2(OnGoingOrder_Activity.this);
                        if(presentation != null){
                            //presentation.cancel();
                            //presentation..showTextView("");
                            ImageView imageView = presentation.findViewById(R.id.image);
                            imageView.setVisibility(View.VISIBLE);
                            imageView.setImageResource(R.mipmap.dhivyalogo);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<GetUnpaid_bill> call, Throwable t) {

            }
        });
    }

    private void getCustomerPaid() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.update_dialog);
        Window window = dialog.getWindow();
        window.setLayout(600, AbsListView.LayoutParams.WRAP_CONTENT);

        TextView text = (TextView) dialog.findViewById(R.id.text);
        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        AppCompatButton confirm = (AppCompatButton) dialog.findViewById(R.id.confirm);

        text.setText("Enter the Correct Paid Amount");

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void getSelectPaymentMethod() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.update_dialog);
        Window window = dialog.getWindow();
        window.setLayout(600, AbsListView.LayoutParams.WRAP_CONTENT);

        TextView text = (TextView) dialog.findViewById(R.id.text);
        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        AppCompatButton confirm = (AppCompatButton) dialog.findViewById(R.id.confirm);

        text.setText("Select the Payment Method");

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
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

                            paymentId_str = response.body().getData().get(0).getPaymentId();
                            paymentType_str = response.body().getData().get(0).getPaymentType();

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

    private void getPaymentApi(Dialog dialog, String place_order_id, String discount, String total_amt, String paid, String change) {
        ProgressDialog dialog1 = new ProgressDialog(this);
        dialog1.setCancelable(false);
        dialog1.setMessage("Loading...");
        dialog1.show();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<PaymentResult> call = api.getPayment(place_order_id, pref.getCounterId(), discount, total_amt, paymentId_str, "", "", "", paid, change);

        call.enqueue(new Callback<PaymentResult>() {
            @Override
            public void onResponse(Call<PaymentResult> call, Response<PaymentResult> response) {
                dialog1.dismiss();
                try {
                    if (response.body().getStatus().equals("true")) {
                        dialog.dismiss();
                        tot_bal = 0;
                        getOngoingOrder();
                        getCompleteDialog(place_order_id);
                        getNextPage(place_order_id);
                        // getPrinter(place_order_id);
                        //getBillingGenarate(place_order_id);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<PaymentResult> call, Throwable t) {
                dialog1.dismiss();
            }
        });
    }

    private void getCompleteDialog(String place_order_id) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.empty_dialog);
        Window window = dialog.getWindow();
        window.setLayout(800, AbsListView.LayoutParams.WRAP_CONTENT);

        TextView text = (TextView) dialog.findViewById(R.id.text);
        ImageView image = (ImageView) dialog.findViewById(R.id.tic);
        AppCompatButton confirm = (AppCompatButton) dialog.findViewById(R.id.confirm);

        text.setText("Payment Successful");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
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

                        Intent i = new Intent(OnGoingOrder_Activity.this, TestService.class);
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


                        if(presentation != null){
                            presentation.cancel();
                        }

                        showSecondByDisplayManager2(OnGoingOrder_Activity.this);

                        if(presentation != null){
                            //presentation.cancel();
                            //presentation..showTextView("");
                            ImageView imageView = presentation.findViewById(R.id.image);
                            imageView.setVisibility(View.VISIBLE);
                            imageView.setImageResource(R.mipmap.dhivyalogo);

                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetCounterPrint> call, Throwable t) {
               // Toast.makeText(OnGoingOrder_Activity.this, t.toString() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void pullAndRefresh() {
        swipeprocess(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getOngoingOrder();
                swipeprocess(false);
            }
        }, 3000);

    }

    private void swipeprocess(boolean show) {
        if (!show) {
            swiperefreshlayout.setRefreshing(show);
            return;
        }
        swiperefreshlayout.post(new Runnable() {
            @Override
            public void run() {
                swiperefreshlayout.setRefreshing(show);
            }
        });
    }

    private void getCancelDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.update_dialog);
        Window window = dialog.getWindow();
        window.setLayout(600, AbsListView.LayoutParams.WRAP_CONTENT);

        TextView text = (TextView) dialog.findViewById(R.id.text);
        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        AppCompatButton confirm = (AppCompatButton) dialog.findViewById(R.id.confirm);

        text.setText("Order Cancelled Successfully");

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presentation != null){
            presentation.cancel();
        }
    }
}