package com.example.foodpos_counter;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.foodpos_counter.Adapter.DisplayAdapter;
import com.example.foodpos_counter.Notification.ConnectivityReceiver;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Presentation;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodpos_counter.Adapter.AvailableTableAdapter;
import com.example.foodpos_counter.Adapter.AvailableWaiterAdapter;
import com.example.foodpos_counter.Adapter.BankAdapter;
import com.example.foodpos_counter.Adapter.CardAdapter;
import com.example.foodpos_counter.Adapter.PaymentMethodAdapter;
import com.example.foodpos_counter.Adapter.SelfKioskAdapter;
import com.example.foodpos_counter.Adapter.TodayFoodAdapter;
import com.example.foodpos_counter.Api.Api;
import com.example.foodpos_counter.Interface.Self_inter;
import com.example.foodpos_counter.Model.AvailableTable;
import com.example.foodpos_counter.Model.AvailableTableList;
import com.example.foodpos_counter.Model.BankList;
import com.example.foodpos_counter.Model.CancelOrder;
import com.example.foodpos_counter.Model.CardList;
import com.example.foodpos_counter.Model.ChangeOrderStatus;
import com.example.foodpos_counter.Model.CounterFoodlist;
import com.example.foodpos_counter.Model.GetBank;
import com.example.foodpos_counter.Model.GetCard;
import com.example.foodpos_counter.Model.GetCounterPrint;
import com.example.foodpos_counter.Model.GetPayment;
import com.example.foodpos_counter.Model.GetPrint;
import com.example.foodpos_counter.Model.GetSelfkiosk;
import com.example.foodpos_counter.Model.GetWaiter;
import com.example.foodpos_counter.Model.IPAddress;
import com.example.foodpos_counter.Model.Kitchen_List;
import com.example.foodpos_counter.Model.OngoingList;
import com.example.foodpos_counter.Model.OrderAccept;
import com.example.foodpos_counter.Model.PaymentResult;
import com.example.foodpos_counter.Model.Paymenttypes;
import com.example.foodpos_counter.Model.SelfKisok;
import com.example.foodpos_counter.Model.TodayFoodView;
import com.example.foodpos_counter.Model.TodayOrderView;
import com.example.foodpos_counter.Model.WaiterList;
import com.example.foodpos_counter.Pref.Pref;
import com.example.foodpos_counter.Utils.AnotherScreen;
import com.example.foodpos_counter.Utils.DifferentDisplay;
import com.example.foodpos_counter.Utils.TestService;
import com.example.foodpos_counter.db.ViewModel;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.imin.printerlib.IminPrintUtils;

import net.posprinter.posprinterface.IMyBinder;
import net.posprinter.posprinterface.ProcessData;
import net.posprinter.posprinterface.TaskCallback;
import net.posprinter.service.PosprinterService;
import net.posprinter.utils.DataForSendToPrinterPos80;
import net.posprinter.utils.DataForSendToPrinterTSC;
import net.posprinter.utils.PosPrinterDev;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelfKiosk_Activity extends AppCompatActivity implements Self_inter {

    Pref pref;
    RecyclerView selfkiosk_recycler;

    SelfKioskAdapter selfkioskAdapter;
    AvailableTableAdapter availableTableAdapter;
    AvailableWaiterAdapter availableWaiterAdapter;
    TodayFoodAdapter todayFoodAdapter;

    ArrayList<SelfKisok> selfKisokArrayList;
    ArrayList<AvailableTableList> availableTableLists;
    ArrayList<WaiterList> waiterListArrayList;

    AppCompatButton close_arrow;
    RecyclerView today_recycler;
    TextView subtotal;
    TextView service_change;
    TextView service_tax;
    TextView grandtotal;
    TextView paid_amt;
    TextView change_due;

    AppCompatButton accept;
    AppCompatButton cancel;

    String table_name_str;
    String table_id_str = "";

    String waiter_id_str = "";
    String waiter_name_str;


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
    ArrayList<IPAddress> ipAddressArrayList;
    ArrayList<BankList> bankListArrayList;
    ArrayList<Paymenttypes> paymenttypesArrayList;
    ArrayList<CardList> cardListArrayList;

    String bankid_str;
    String bankname_str;
    String card_terminalid_str;
    String terminal_name_str;
    String paymentId_str;
    String paymentType_str;

    String print_kitchenName;

    ArrayList<TodayFoodView> todayFoodViewArrayList;

    Handler handler = new Handler();
    Runnable runnable;
    int delay = 10000;
    int finalI;
    float total_flt;
    float paid_flt;

    private IminPrintUtils mIminPrintUtils;
    public static IMyBinder myBinder;

    ViewModel viewModel;

    boolean ip_status;
    boolean txt_amt;
    float tot_bal;

    LinearLayout progressbar;

    private static final String TAG = "display_demo";
    private Presentation presentation;
    ServiceConnection mSerconnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (IMyBinder) service;
            Log.e("myBinder", "connect");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("myBinder", "disconnect");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_kiosk);

        pref = new Pref(getApplicationContext());
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);

        Intent intent = new Intent(this, PosprinterService.class);
        bindService(intent, mSerconnection, BIND_AUTO_CREATE);

//        if (mIminPrintUtils == null) {
//            mIminPrintUtils = IminPrintUtils.getInstance(SelfKiosk_Activity.this);
//        }
//        mIminPrintUtils.resetDevice();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(!Settings.canDrawOverlays(this)){
                // Toast.makeText(this,"请同意显示窗口权限",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION));
            }
        }


        selfkiosk_recycler = (RecyclerView) findViewById(R.id.selfkiosk_recycler);
        close_arrow = (AppCompatButton) findViewById(R.id.close_arrow);

        progressbar = (LinearLayout) findViewById(R.id.progressBar);

        finalI = 0;

        close_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                //viewModel.deleteall(1);
                pref.setPrint_refresh("yes");
            }
        });

        getAvailableTable();
        getWaiterApi();
        getCardApi();
        getBankApi();
        getPaymenttype();



        selfKisokArrayList = new ArrayList<>();
        availableTableLists = new ArrayList<>();
        waiterListArrayList = new ArrayList<>();
        bankListArrayList = new ArrayList<>();
        paymenttypesArrayList = new ArrayList<>();
        cardListArrayList = new ArrayList<>();
        ipAddressArrayList = new ArrayList<>();


        if(presentation != null){
            presentation.cancel();
        }
//                showSecondByMediaRouter(MainActivity.this);
        showSecondByDisplayManager(SelfKiosk_Activity.this);

        if(presentation != null){
            //presentation.cancel();
            //presentation..showTextView("");
            ImageView imageView = presentation.findViewById(R.id.image);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(R.mipmap.dhivyalogo);

        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        selfkiosk_recycler.setLayoutManager(linearLayoutManager);

        selfkioskAdapter = new SelfKioskAdapter(SelfKiosk_Activity.this, selfKisokArrayList);
        selfkiosk_recycler.setAdapter(selfkioskAdapter);

        getSelfKisok();
    }

    private void showSecondByDisplayManager(Context context) {
        DisplayManager mDisplayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = mDisplayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION);
        if (displays != null && getPresentationDisplays() != null) {
            presentation = new AnotherScreen(context, getPresentationDisplays());
            presentation.show();
        }else {
            Toast.makeText(SelfKiosk_Activity.this,getString(R.string.no_second_screen),Toast.LENGTH_SHORT);
        }
        /*副屏的Window*/
    }

    private void showSecondByDisplayManager2(Context context) {
        DisplayManager mDisplayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = mDisplayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION);
        if (displays != null && getPresentationDisplays() != null) {
            presentation = new DifferentDisplay(context, getPresentationDisplays());
            presentation.show();
        }else {
            Toast.makeText(SelfKiosk_Activity.this,getString(R.string.no_second_screen),Toast.LENGTH_SHORT);
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

    private void getAvailableTable() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<AvailableTable> call = api.getAvailableTable();

        call.enqueue(new Callback<AvailableTable>() {
            @Override
            public void onResponse(Call<AvailableTable> call, Response<AvailableTable> response) {
                try {
                    availableTableLists.removeAll(availableTableLists);
                    if (response.body().getStatus().equals("true")) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            String tableid = response.body().getData().get(i).getTableid();
                            String tablename = response.body().getData().get(i).getTablename();
                            String floor = response.body().getData().get(i).getFloor();
                            String seat = response.body().getData().get(i).getSeat();
                            String availableSeat = response.body().getData().get(i).getAvailableSeat();
                            String bookingStatus = response.body().getData().get(i).getBookingStatus();

                            availableTableLists.add(new AvailableTableList(tableid,
                                    tablename,
                                    floor,
                                    seat,
                                    availableSeat,
                                    bookingStatus));
                        }
                        availableTableAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AvailableTable> call, Throwable t) {

            }
        });
    }

    private void getWaiterApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<GetWaiter> call = api.getWaiter();

        call.enqueue(new Callback<GetWaiter>() {
            @Override
            public void onResponse(Call<GetWaiter> call, Response<GetWaiter> response) {
                try {
                    if (response.body().getStatus().equals("true")) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            String employeeId = response.body().getData().get(i).getEmployeeId();
                            String employeeName = response.body().getData().get(i).getEmployeeName();

                            waiterListArrayList.add(new WaiterList(employeeId,
                                    employeeName));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetWaiter> call, Throwable t) {

            }
        });
    }

    private void getSelfKisok() {
//        ProgressDialog dialog = new ProgressDialog(this);
//        dialog.setCancelable(false);
//        dialog.setMessage("Loading....");
//        dialog.show();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<GetSelfkiosk> call = api.getSelfkiosk();

        call.enqueue(new Callback<GetSelfkiosk>() {
            @Override
            public void onResponse(Call<GetSelfkiosk> call, Response<GetSelfkiosk> response) {
            //    dialog.dismiss();
                selfKisokArrayList.removeAll(selfKisokArrayList);
                try {
                    if (response.body().getStatus().equals("true")) {

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            String order_id = response.body().getData().get(i).getOrder_id();
                            String saleinvoice = response.body().getData().get(i).getSaleinvoice();
                            String tokenno = response.body().getData().get(i).getTokenno();
                            String tableid = response.body().getData().get(i).getTableid();
                            String tablename = response.body().getData().get(i).getTablename();
                            String orderdate = response.body().getData().get(i).getOrderdate();
                            String ordertime = response.body().getData().get(i).getOrdertime();
                            String totalamount = response.body().getData().get(i).getTotalamount();
                            String customertypeid = response.body().getData().get(i).getCustomertypeid();
                            String customertype = response.body().getData().get(i).getCustomertype();
                            String customer_id = response.body().getData().get(i).getCustomer_id();
                            String customername = response.body().getData().get(i).getCustomername();
                            String customeremail = response.body().getData().get(i).getCustomeremail();
                            String customerphone = response.body().getData().get(i).getCustomerphone();

                            selfKisokArrayList.add(new SelfKisok(order_id,
                                    saleinvoice,
                                    tokenno,
                                    tableid,
                                    tablename,
                                    orderdate,
                                    ordertime,
                                    totalamount,
                                    customertypeid,
                                    customertype,
                                    customer_id,
                                    customername,
                                    customeremail,
                                    customerphone));
                        }
                        selfkioskAdapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetSelfkiosk> call, Throwable t) {
             //   dialog.dismiss();
                Toast.makeText(SelfKiosk_Activity.this, t.toString() + "", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @SuppressLint("ResourceType")
    @Override
    public void accept(String order_id, String customer_type, String customer_id, String table_id, String table_name)
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.today_order_layout);
        dialog.getWindow().setLayout(1200, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        if(presentation != null){
            presentation.cancel();
        }
//                showSecondByMediaRouter(MainActivity.this);
        showSecondByDisplayManager2(SelfKiosk_Activity.this);

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

                            DisplayAdapter displayAdapter = new DisplayAdapter(SelfKiosk_Activity.this, foodlistArrayList);
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

        today_recycler = (RecyclerView) dialog.findViewById(R.id.today_recycler);
        subtotal = (TextView) dialog.findViewById(R.id.subtotal);
        service_change = (TextView) dialog.findViewById(R.id.service_change);
        service_tax = (TextView) dialog.findViewById(R.id.service_tax);
        grandtotal = (TextView) dialog.findViewById(R.id.grandtotal);
        paid_amt = (TextView) dialog.findViewById(R.id.paid_amt);
        change_due = (TextView) dialog.findViewById(R.id.change_due);
        accept = (AppCompatButton) dialog.findViewById(R.id.accept);
        cancel = (AppCompatButton) dialog.findViewById(R.id.cancel);
        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        TextInputLayout order_view = (TextInputLayout) dialog.findViewById(R.id.order_view);
        TextInputLayout waiter_view = (TextInputLayout) dialog.findViewById(R.id.waiter_view);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) dialog.findViewById(R.id.autoCompleteTextView);
        AutoCompleteTextView autoCompleteTextView1 = (AutoCompleteTextView) dialog.findViewById(R.id.autoCompleteTextView1);

        if (customer_type.equals("1")) {
            availableTableAdapter = new AvailableTableAdapter(SelfKiosk_Activity.this, availableTableLists);
            autoCompleteTextView.setAdapter(availableTableAdapter);

            autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    AvailableTableList availableTableList = (AvailableTableList) adapterView.getItemAtPosition(i);
                    table_id_str = availableTableList.getTableid();
                    table_name_str = availableTableList.getTablename();

                    autoCompleteTextView.setText(table_name_str);
                }
            });

            autoCompleteTextView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    availableTableAdapter = new AvailableTableAdapter(SelfKiosk_Activity.this, availableTableLists);
                    autoCompleteTextView.setAdapter(availableTableAdapter);
                }
            });

            availableWaiterAdapter = new AvailableWaiterAdapter(SelfKiosk_Activity.this, waiterListArrayList);
            autoCompleteTextView1.setAdapter(availableWaiterAdapter);


            autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    WaiterList waiterList = (WaiterList) adapterView.getItemAtPosition(i);
                    waiter_id_str = waiterList.getEmployeeId();
                    waiter_name_str = waiterList.getEmployeeName();

                    autoCompleteTextView1.setText(waiter_name_str);
                }
            });

            autoCompleteTextView1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    availableWaiterAdapter = new AvailableWaiterAdapter(SelfKiosk_Activity.this, waiterListArrayList);
                    autoCompleteTextView1.setAdapter(availableWaiterAdapter);
                }
            });

        } else if (customer_type.equals("99")) {
            autoCompleteTextView.setText(table_name);
            table_id_str = table_id;
            waiter_view.setVisibility(GONE);
        } else {
            order_view.setVisibility(GONE);
            waiter_view.setVisibility(GONE);
        }

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(presentation != null){
                    presentation.cancel();
                }
//                showSecondByMediaRouter(MainActivity.this);
                showSecondByDisplayManager(SelfKiosk_Activity.this);

                if(presentation != null){
                    //presentation.cancel();
                    //presentation..showTextView("");
                    ImageView imageView = presentation.findViewById(R.id.image);
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageResource(R.mipmap.dhivyalogo);

                }

                dialog.dismiss();
            }
        });

        getSelfKisokOrder(order_id);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (customer_type.equals("4")) {
                    getPlaceDialog(order_id, grandtotal.getText().toString(), change_due.getText().toString());
                    dialog.dismiss();
                } else if (customer_type.equals("99")) {
                    if (table_id_str.equals("")) {
                        getSelectTable();
                    } else {
                        getIPAddress();
                        getAccept(order_id, customer_id);
                        dialog.dismiss();
                    }

                } else {
                    if (table_id_str.equals("")) {
                        getSelectTable();
                    } else if (waiter_id_str.equals("")) {
                        getSelectWaiter();
                    } else {
                        getIPAddress();
                        getAccept(order_id, customer_id);
                        dialog.dismiss();
                    }
                }


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(presentation != null){
                    presentation.cancel();
                }
//                showSecondByMediaRouter(MainActivity.this);
                showSecondByDisplayManager(SelfKiosk_Activity.this);

                if(presentation != null){
                    //presentation.cancel();
                    //presentation..showTextView("");
                    ImageView imageView = presentation.findViewById(R.id.image);
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageResource(R.mipmap.dhivyalogo);

                }

                getCancelDialog(order_id, dialog);
            }
        });
    }

    public void getSelectWaiter() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.update_dialog);
        Window window = dialog.getWindow();
        window.setLayout(600, AbsListView.LayoutParams.WRAP_CONTENT);

        TextView text = (TextView) dialog.findViewById(R.id.text);
        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        AppCompatButton confirm = (AppCompatButton) dialog.findViewById(R.id.confirm);

        text.setText("Select the Waiter");

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

    public void getSelectTable() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.update_dialog);
        Window window = dialog.getWindow();
        window.setLayout(600, AbsListView.LayoutParams.WRAP_CONTENT);

        TextView text = (TextView) dialog.findViewById(R.id.text);
        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        AppCompatButton confirm = (AppCompatButton) dialog.findViewById(R.id.confirm);

        text.setText("Select the Table");

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

    private void getSelfKisokOrder(String order_id) {
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
             //   dialog.dismiss();
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

                            todayFoodAdapter = new TodayFoodAdapter(SelfKiosk_Activity.this, todayFoodViewArrayList);
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

    private void getCancelDialog(String order_id, Dialog dialog) {
        final Dialog dialog1 = new Dialog(this);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.cancel_layout);
        Window window = dialog1.getWindow();
        window.setLayout(900, AbsListView.LayoutParams.WRAP_CONTENT);

        ImageView back = (ImageView) dialog1.findViewById(R.id.back);
        EditText reason = (EditText) dialog1.findViewById(R.id.reason);
        AppCompatButton cancel = (AppCompatButton) dialog1.findViewById(R.id.cancel);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reason.getText().toString().equals("")) {
                    Toast.makeText(SelfKiosk_Activity.this, "Enter the Reason", Toast.LENGTH_SHORT).show();
                } else {
                    getCancel(order_id, reason.getText().toString(), dialog, dialog1);
                }
            }
        });

        dialog1.show();
    }

    private void getAccept(String order_id, String customer_id) {
//        ProgressDialog dialog = new ProgressDialog(this);
//        dialog.setCancelable(false);
//        dialog.setMessage("Loading....");
//        dialog.show();

        progressbar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<OrderAccept> call = api.getSelfAccept(order_id, table_id_str, customer_id, waiter_id_str);

        call.enqueue(new Callback<OrderAccept>() {
            @Override
            public void onResponse(Call<OrderAccept> call, Response<OrderAccept> response) {
            //    dialog.dismiss();
                try {
                    if (response.body().getStatus().equals("true")) {

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getOrderCompleteDialog(order_id);
                                progressbar.setVisibility(GONE);
                            }
                        }, 5000);
                        getSelfKisok();
                        getAvailableTable();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<OrderAccept> call, Throwable t) {
               // dialog.dismiss();
                progressbar.setVisibility(GONE);
            }
        });
    }

    private void getOrderCompleteDialog(String order_id) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.empty_dialog);
        Window window = dialog.getWindow();
        window.setLayout(800, AbsListView.LayoutParams.WRAP_CONTENT);

        TextView text = (TextView) dialog.findViewById(R.id.text);
        ImageView image = (ImageView) dialog.findViewById(R.id.tic);
        AppCompatButton confirm = (AppCompatButton) dialog.findViewById(R.id.confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPrint(order_id);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void cancel(String order_id) {
    }

    @Override
    public void addPrinter() {

    }

    private void getPlaceDialog(String Place_order_id, String total_amt, String total_due_str) {
        Dialog dialog = new Dialog(SelfKiosk_Activity.this);
        dialog.setContentView(R.layout.placeorder);
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT);

//        mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.USB);

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
        AppCompatButton pay_btn = (AppCompatButton) dialog.findViewById(R.id.pay_btn);
        AppCompatButton unpaid_btn = (AppCompatButton) dialog.findViewById(R.id.unpaid_btn);
        AppCompatButton cancel = (AppCompatButton) dialog.findViewById(R.id.cancel);
        RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
        LinearLayout paid_layout = (LinearLayout) dialog.findViewById(R.id.paid_layout);
        LinearLayout change_layout = (LinearLayout) dialog.findViewById(R.id.change_layout);
        EditText paid_txt = (EditText) dialog.findViewById(R.id.paid_txt);
        TextView change_txt = (TextView) dialog.findViewById(R.id.change_txt);
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

        card_layout.setVisibility(GONE);
        pin_layout.setVisibility(GONE);
        unpaid_btn.setVisibility(GONE);
        cancel.setVisibility(GONE);

//        paymentId_str = "";

        payment_method.setText(paymentType_str);

        paid_layout.setVisibility(View.VISIBLE);
        change_layout.setVisibility(View.VISIBLE);


        grand_total.setText("RM " + total_amt);
        total_due.setText("RM " + total_due_str);

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

                showSecondByDisplayManager2(SelfKiosk_Activity.this);
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

        paymentMethodAdapter = new PaymentMethodAdapter(SelfKiosk_Activity.this, paymenttypesArrayList);
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
//                            getIPAddress();
//                            getPaymentApi(dialog, Place_order_id, total_amt,paid_txt.getText().toString(), change_txt.getText().toString());
//                        }
//                    }
//                    else {
//                        getIPAddress();
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
                                 getIPAddress();
                                getPaymentApi(dialog, Place_order_id, String.valueOf(tot_bal) , grand_total.getText().toString(), paid_txt.getText().toString(), change_txt.getText().toString());
                            }
                        }
                    }
                    else{
                        getIPAddress();
                        getPaymentApi(dialog, Place_order_id, String.valueOf(tot_bal) , grand_total.getText().toString(), paid_txt.getText().toString(), change_txt.getText().toString());
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

        dialog.show();
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

    private void getBankDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);
        Window window = dialog.getWindow();
        window.setLayout(800, AbsListView.LayoutParams.WRAP_CONTENT);

        RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
        ImageButton close = dialog.findViewById(R.id.close);
        TextView text = (TextView) dialog.findViewById(R.id.text);

        text.setText("Bank Details");

        LinearLayoutManager layoutManager = new LinearLayoutManager(dialog.getContext());
        recyclerView.setLayoutManager(layoutManager);

        bankAdapter = new BankAdapter(SelfKiosk_Activity.this, bankListArrayList);
        recyclerView.setAdapter(bankAdapter);
        bankAdapter.setOnItemClickListener(new BankAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ArrayList<BankList> bankListArrayList, int position) {
                bankid_str = bankListArrayList.get(position).getBankid();
                bankname_str = bankListArrayList.get(position).getBankname();

                select_bank.setText(bankname_str);
                select_bank.setError(null);
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

    private void getCardDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);
        Window window = dialog.getWindow();
        window.setLayout(800, AbsListView.LayoutParams.WRAP_CONTENT);

        RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
        ImageButton close = dialog.findViewById(R.id.close);
        TextView text = (TextView) dialog.findViewById(R.id.text);

        text.setText("Card Details");

        LinearLayoutManager layoutManager = new LinearLayoutManager(dialog.getContext());
        recyclerView.setLayoutManager(layoutManager);

        cardAdapter = new CardAdapter(SelfKiosk_Activity.this, cardListArrayList);
        recyclerView.setAdapter(cardAdapter);
        cardAdapter.setOnItemClickListener(new CardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ArrayList<CardList> cardListArrayList, int position) {
                card_terminalid_str = cardListArrayList.get(position).getCard_terminalid();
                terminal_name_str = cardListArrayList.get(position).getTerminal_name();

                card_terminal.setText(terminal_name_str);
                card_terminal.setError(null);
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

        paymentMethodAdapter = new PaymentMethodAdapter(SelfKiosk_Activity.this, paymenttypesArrayList);
        recyclerView.setAdapter(paymentMethodAdapter);
        paymentMethodAdapter.setOnItemClickListener(new PaymentMethodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ArrayList<Paymenttypes> paymenttypesArrayList, int position) {
                paymentId_str = paymenttypesArrayList.get(position).getPaymentId();
                paymentType_str = paymenttypesArrayList.get(position).getPaymentType();

                payment_method.setText(paymentType_str);
                payment_method.setError(null);

                if (paymentId_str.equals("1")) {
                    card_layout.setVisibility(View.VISIBLE);
                    pin_layout.setVisibility(View.VISIBLE);

                } else {
                    card_layout.setVisibility(GONE);
                    pin_layout.setVisibility(GONE);
                }

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

    private void getBankApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<GetBank> call = api.getBank();

        call.enqueue(new Callback<GetBank>() {
            @Override
            public void onResponse(Call<GetBank> call, Response<GetBank> response) {
                try {
                    bankListArrayList.removeAll(bankListArrayList);
                    if (response.body().getStatus().equals("true")) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            String bankid = response.body().getData().get(i).getBankid();
                            String bankname = response.body().getData().get(i).getBankname();

                            bankListArrayList.add(new BankList(bankid,
                                    bankname));
                        }
                        bankAdapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetBank> call, Throwable t) {

            }
        });
    }

    private void getCardApi() {
//        ProgressDialog dialog = new ProgressDialog(this);
//        dialog.setCancelable(false);
//        dialog.setMessage("Loading...");
//        dialog.show();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<GetCard> call = api.getCard();

        call.enqueue(new Callback<GetCard>() {
            @Override
            public void onResponse(Call<GetCard> call, Response<GetCard> response) {
              //  dialog.dismiss();
                try {
                    cardListArrayList.removeAll(cardListArrayList);
                    if (response.body().getStatus().equals("true")) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            String card_terminalid = response.body().getData().get(i).getCard_terminalid();
                            String terminal_name = response.body().getData().get(i).getTerminal_name();

                            cardListArrayList.add(new CardList(card_terminalid,
                                    terminal_name));
                        }
                        cardAdapter.notifyDataSetChanged();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetCard> call, Throwable t) {
                //dialog.dismiss();
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
               // dialog.dismiss();
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
                        paymentMethodAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(SelfKiosk_Activity.this, e.toString()+"", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetPayment> call, Throwable t) {
               // dialog.dismiss();
                //Toast.makeText(SelfKiosk_Activity.this, t.toString()+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPaymentApi(Dialog dialog, String place_order_id,String discount, String total_amt, String paid, String change) {
//        ProgressDialog dialog1 = new ProgressDialog(this);
//        dialog1.setCancelable(false);
//        dialog1.setMessage("Loading...");
//        dialog1.show();

        progressbar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<PaymentResult> call = api.getPayment(place_order_id, pref.getCounterId(),discount, grandtotal.getText().toString(), paymentId_str, "", "", "", paid, change);

        call.enqueue(new Callback<PaymentResult>() {
            @Override
            public void onResponse(Call<PaymentResult> call, Response<PaymentResult> response) {
                //dialog1.dismiss();
                try {
                    if (response.body().getStatus().equals("true")) {
                        getSelfKisok();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                getCompleteDialog(place_order_id);
                                progressbar.setVisibility(GONE);
                            }
                        }, 5000);
                        getAvailableTable();
                        getNextPage(place_order_id);
//                        getOngoingOrder();
//                        getPrinter(place_order_id);
                        //getBillingGenarate(place_order_id);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<PaymentResult> call, Throwable t) {
                progressbar.setVisibility(GONE);
            }
        });
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

                        Intent i = new Intent(SelfKiosk_Activity.this, TestService.class);
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
//                showSecondByMediaRouter(MainActivity.this);
                        showSecondByDisplayManager(SelfKiosk_Activity.this);

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
                Toast.makeText(SelfKiosk_Activity.this, t.toString() + "", Toast.LENGTH_SHORT).show();
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

        text.setText("Payment Successfully");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPrint(place_order_id);
                dialog.dismiss();

            }
        });

        dialog.show();
    }

    private void getCancel(String order_id, String reason, Dialog dialog, Dialog dialog1) {
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
                        dialog1.dismiss();
                        for (int j = 0; j < myBinder.GetPrinterInfoList().size(); j++) {
                            myBinder.RemovePrinter(myBinder.GetPrinterInfoList().get(j).printerName, new TaskCallback() {
                                @Override
                                public void OnSucceed() {
                                    //Toast.makeText(SelfKiosk_Activity.this, "Removed", Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void OnFailed() {

                                }
                            });
                        }
                        getCancel();
                        getSelfKisok();
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

    private void getCancel() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.empty_dialog);
        Window window = dialog.getWindow();
        window.setLayout(800, AbsListView.LayoutParams.WRAP_CONTENT);

        TextView text = (TextView) dialog.findViewById(R.id.text);
        ImageView image = (ImageView) dialog.findViewById(R.id.tic);
        AppCompatButton confirm = (AppCompatButton) dialog.findViewById(R.id.confirm);

        text.setText("Order Cancelled Successfully");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void getIPAddress() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<Kitchen_List> call = api.getlist();

        call.enqueue(new Callback<Kitchen_List>() {
            @Override
            public void onResponse(Call<Kitchen_List> call, Response<Kitchen_List> response) {

                try {
                    ipAddressArrayList.removeAll(ipAddressArrayList);
                    if (response.body().getStatus().equals("true")) {
                        myBinder.GetPrinterInfoList().removeAll(myBinder.GetPrinterInfoList());
                        PosPrinterDev.PrinterInfo printer;
                        for (int i = 0; i < response.body().getData().size(); i++) {

                            String kitchenid = response.body().getData().get(i).getKitchenid();
                            String kitchenname = response.body().getData().get(i).getKitchenname();
                            String ip = response.body().getData().get(i).getIp();
                            String port = response.body().getData().get(i).getPort();
                            String status = response.body().getData().get(i).getStatus();


                            ipAddressArrayList.add(new IPAddress(kitchenid,
                                    kitchenname,
                                    ip,
                                    port,
                                    status));

                            printer = new PosPrinterDev.PrinterInfo(kitchenname, PosPrinterDev.PortType.Ethernet, ip);
                            AddPrinter(printer);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Kitchen_List> call, Throwable t) {
                Toast.makeText(SelfKiosk_Activity.this, t.toString() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AddPrinter(PosPrinterDev.PrinterInfo printer) {
        myBinder.AddPrinter(printer, new TaskCallback() {
            @Override
            public void OnSucceed() {
                // Toast.makeText(SelfKiosk_Activity.this, "Success", Toast.LENGTH_SHORT).show();
                //  ip_status = true;
            }

            @Override
            public void OnFailed() {
                 //Toast.makeText(getApplicationContext(), "Connection failed", Toast.LENGTH_SHORT).show();
                // ip_status = false;
            }
        });
    }

    private void getPrint(String order_id) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<GetPrint> call = api.getPrint(order_id);

        call.enqueue(new Callback<GetPrint>() {
            @Override
            public void onResponse(Call<GetPrint> call, Response<GetPrint> response) {

                try {
                    if (response.body().getStatus().equals("true")) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            int finalN = i;
                            print_kitchenName = response.body().getData().get(i).getKitchenName();

//                            Toast.makeText(Menu_Activity.this, print_kitchenName + " for", Toast.LENGTH_SHORT).show();
                            for (int j = 0; j < myBinder.GetPrinterInfoList().size(); j++) {

                                if (myBinder.GetPrinterInfoList().get(j).printerName.equals(print_kitchenName)) {
                                   // Toast.makeText(SelfKiosk_Activity.this, myBinder.GetPrinterInfoList().get(j).printerName + " Print", Toast.LENGTH_SHORT).show();
                                    int finalJ = j;
                                    myBinder.SendDataToPrinter(myBinder.GetPrinterInfoList().get(j).printerName, new TaskCallback() {
                                        @Override
                                        public void OnSucceed() {

                                        }

                                        @Override
                                        public void OnFailed() {

                                        }
                                    }, new ProcessData() {
                                        @Override
                                        public List<byte[]> processDataBeforeSend() {
                                            List<byte[]> list = new ArrayList<byte[]>();

                                            if (finalI == 0) {
                                                String waiter_name = "Waiter Name : " + response.body().getData().get(finalN).getWaiter() + "\r\n";
                                                String token_no = "Token No  : " + response.body().getData().get(finalN).getTokenno() + "\r\n";
                                                String date = "Date/Time : " + response.body().getData().get(finalN).getOrderDate() + "/" + response.body().getData().get(finalN).getOrderTime() + "\r\n";
                                                String kitchen_name = "KOT " + response.body().getData().get(finalN).getKitchenName() + "\r\n";
                                                String customer_type = "** " + response.body().getData().get(finalN).getCutomertypeName() + " **\r\n";
                                                String order_no =  "Order No :" + response.body().getData().get(finalN).getSaleinvoice() + "\r\n";

                                                String table_name;
                                                if (response.body().getData().get(finalN).getCutomertypeName().equals("Takeaway")) {
                                                    table_name = "Table No :" + "\r\n\n";
                                                } else {
                                                    table_name = "Table No :" + response.body().getData().get(finalN).getTablename() + "\r\n\n";
                                                }

                                                list.add(DataForSendToPrinterPos80.selectAlignment(1));
                                                list.add(DataForSendToPrinterPos80.selectOrCancelBoldModel(1));
                                                list.add(DataForSendToPrinterPos80.selectCharacterSize(1));
                                                list.add(table_name.getBytes());
                                                list.add(DataForSendToPrinterPos80.selectCharacterSize(0));
                                                list.add(kitchen_name.getBytes());
                                                list.add(token_no.getBytes());
                                                list.add(customer_type.getBytes());
                                                list.add(order_no.getBytes());
                                                list.add(waiter_name.getBytes());
                                                list.add(date.getBytes());
                                                list.add("----------------------------------------------\r\n\n".getBytes());
                                                list.add(DataForSendToPrinterPos80.selectAlignment(0));
                                                list.add(DataForSendToPrinterPos80.selectCharacterSize(1));
                                                list.add("      Qty       Item\r\n\n".getBytes());
                                                for (int k = 0; k < response.body().getData().get(finalN).getOrderItems().size(); k++)
                                                {

                                                    String print_productName = response.body().getData().get(finalN).getOrderItems().get(k).getProductName();

                                                    String print_menuqty = response.body().getData().get(finalN).getOrderItems().get(k).getMenuqty();

                                                    String product = "      "+ print_menuqty + "        " + print_productName +"\r\n";
                                                    list.add(product.getBytes());

                                                    String notes =response.body().getData().get(finalN).getOrderItems().get(k).getNotes() + "\r\n";
                                                    list.add(notes.getBytes());

                                                    if (response.body().getData().get(finalN).getOrderItems().get(k).getAddons().size() > 0) {
                                                        for (int m = 0; m < response.body().getData().get(finalN).getOrderItems().get(k).getAddons().size(); m++) {
                                                            String a_name = "         " + response.body().getData().get(finalN).getOrderItems().get(k).getAddons().get(m).getAddOnName() + "\r\n";
                                                            list.add(a_name.getBytes());
                                                        }
                                                    }
                                                }
//                                                list.add(DataForSendToPrinterPos80.printRasterBmp(0, bitmap, BitmapToByteData.BmpType.Dithering, BitmapToByteData.AlignType.Center, 576));
//
//                                                list.add(DataForSendToPrinterPos80.printAndFeedLine())
                                                list.add(DataForSendToPrinterPos80.selectCutPagerModerAndCutPager(0x42, 0x66));

                                            } else {
                                                //设置标签纸大小
                                                list.add(DataForSendToPrinterTSC.sizeBymm(50, 30));
                                                //设置间隙
                                                list.add(DataForSendToPrinterTSC.gapBymm(2, 0));
                                                //清除缓存
                                                list.add(DataForSendToPrinterTSC.cls());
                                                //设置方向
                                                list.add(DataForSendToPrinterTSC.direction(0));
                                                //线条
                                                // list.add(DataForSendToPrinterTSC.bar(10,10,200,3));
                                                //条码
                                                list.add(DataForSendToPrinterTSC.barCode(10, 15, "128", 100, 1, 0, 2, 2, "abcdef12345"));
                                                //文本
                                                //list.add(DataForSendToPrinterTSC.text(10,30,"1",0,1,1,"abcasdjknf"));
                                                //打印
                                                list.add(DataForSendToPrinterTSC.print(1));
                                            }
                                            return list;
                                        }
                                    });
                                }
                            }

                            String Order_id = response.body().getData().get(i).getOrderId();


                        }

                        getChangeStatus(order_id);


                        if(presentation != null){
                            presentation.cancel();
                        }
//                showSecondByMediaRouter(MainActivity.this);
                        showSecondByDisplayManager(SelfKiosk_Activity.this);

                        if(presentation != null){
                            //presentation.cancel();
                            //presentation..showTextView("");
                            ImageView imageView = presentation.findViewById(R.id.image);
                            imageView.setVisibility(View.VISIBLE);
                            imageView.setImageResource(R.mipmap.dhivyalogo);

                        }

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                for (int j = 0; j < myBinder.GetPrinterInfoList().size(); j++) {
                                    myBinder.RemovePrinter(myBinder.GetPrinterInfoList().get(j).printerName, new TaskCallback() {
                                        @Override
                                        public void OnSucceed() {
                                            //Toast.makeText(SelfKiosk_Activity.this, "Removed", Toast.LENGTH_SHORT).show();

                                        }

                                        @Override
                                        public void OnFailed() {

                                        }
                                    });
                                }
                                // ip_status = true;
//                                getIPAddress();
                            }
                        }, 2000);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetPrint> call, Throwable t) {
                Log.d("print", "Print" + t.toString());
                Toast.makeText(SelfKiosk_Activity.this, t.toString() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getChangeStatus(String order_id) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<ChangeOrderStatus> call = api.getOrderChange(order_id);

        call.enqueue(new Callback<ChangeOrderStatus>() {
            @Override
            public void onResponse(Call<ChangeOrderStatus> call, Response<ChangeOrderStatus> response) {

            }

            @Override
            public void onFailure(Call<ChangeOrderStatus> call, Throwable t) {
                Toast.makeText(SelfKiosk_Activity.this, t.toString() + "", Toast.LENGTH_SHORT).show();
            }
        });


    }



    @Override
    protected void onResume() {
        super.onResume();

        boolean isConnected = ConnectivityReceiver.isConnected();

        if (!isConnected) {
            startActivity(new Intent(this, NoNetworkActivity.class));
        }

        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);
                getAvailableTable();
            }
        }, delay);


    }

}