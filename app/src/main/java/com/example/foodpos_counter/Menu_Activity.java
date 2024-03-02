package com.example.foodpos_counter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.foodpos_counter.Model.Bill_summary;
import com.example.foodpos_counter.Model.Change_due_summary;
import com.example.foodpos_counter.Model.Collection_summary;
import com.example.foodpos_counter.Model.Collectiondetails;
import com.example.foodpos_counter.Model.Food_price_update;
import com.example.foodpos_counter.Model.Fooddetails;
import com.example.foodpos_counter.Model.GetReprint;
import com.example.foodpos_counter.Model.InactiveFoods;
import com.example.foodpos_counter.Model.Items_summary;
import com.example.foodpos_counter.Model.Ongoing_count;
import com.example.foodpos_counter.Model.OverallReports;
import com.example.foodpos_counter.Model.Player_ids;
import com.example.foodpos_counter.Model.Print_AddonsItem;
import com.example.foodpos_counter.Model.Print_Details;
import com.example.foodpos_counter.Model.Print_Items;
import com.example.foodpos_counter.Model.ReprintItem;
import com.example.foodpos_counter.Model.Reprint_AddonsItem;
import com.example.foodpos_counter.Model.Sales_summary;
import com.example.foodpos_counter.Model.ViewOrder;
import com.example.foodpos_counter.Notification.ConnectivityReceiver;

import android.app.Dialog;
import android.app.Presentation;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.display.DisplayManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodpos_counter.Adapter.AddAdapter;
import com.example.foodpos_counter.Adapter.AddCartAdapter;
import com.example.foodpos_counter.Adapter.BankAdapter;
import com.example.foodpos_counter.Adapter.CardAdapter;
import com.example.foodpos_counter.Adapter.CategoryAdapter;
import com.example.foodpos_counter.Adapter.CustomernameAdapter;
import com.example.foodpos_counter.Adapter.CustomertypeAdapter;
import com.example.foodpos_counter.Adapter.FoodListAdapter;
import com.example.foodpos_counter.Adapter.OrderAdapter;
import com.example.foodpos_counter.Adapter.PaymentMethodAdapter;
import com.example.foodpos_counter.Adapter.TableAdapter;
import com.example.foodpos_counter.Adapter.WaiternameAdapter;
import com.example.foodpos_counter.Api.Api;
import com.example.foodpos_counter.Interface.Inter;
import com.example.foodpos_counter.Model.Addons;
import com.example.foodpos_counter.Model.BankList;
import com.example.foodpos_counter.Model.CardList;
import com.example.foodpos_counter.Model.Category;
import com.example.foodpos_counter.Model.ChangeOrderStatus;
import com.example.foodpos_counter.Model.CounterDetails;
import com.example.foodpos_counter.Model.CounterFoodlist;
import com.example.foodpos_counter.Model.Customer;
import com.example.foodpos_counter.Model.CustomerList;
import com.example.foodpos_counter.Model.CustomerTypes;
import com.example.foodpos_counter.Model.Customer_type;
import com.example.foodpos_counter.Model.FoodList;
import com.example.foodpos_counter.Model.GetCounterPrint;
import com.example.foodpos_counter.Model.GetCustomer;
import com.example.foodpos_counter.Model.GetPayment;
import com.example.foodpos_counter.Model.GetPrint;
import com.example.foodpos_counter.Model.GetTable;
import com.example.foodpos_counter.Model.GetTableInfo;
import com.example.foodpos_counter.Model.GetWaiter;
import com.example.foodpos_counter.Model.IPAddress;
import com.example.foodpos_counter.Model.Kitchen_List;
import com.example.foodpos_counter.Model.OrderSummary;
import com.example.foodpos_counter.Model.Order_Addons;
import com.example.foodpos_counter.Model.Order_FoodList;
import com.example.foodpos_counter.Model.PaymentResult;
import com.example.foodpos_counter.Model.Paymenttypes;
import com.example.foodpos_counter.Model.PlaceAddons;
import com.example.foodpos_counter.Model.PlaceOrderFood;
import com.example.foodpos_counter.Model.PlaceOrderList;
import com.example.foodpos_counter.Model.PlaceOrderResult;
import com.example.foodpos_counter.Model.Service_Tax;
import com.example.foodpos_counter.Model.UpdateAddons;
import com.example.foodpos_counter.Model.UpdateFoodlist;
import com.example.foodpos_counter.Model.UpdateOrderList;
import com.example.foodpos_counter.Model.UpdateOrderResult;
import com.example.foodpos_counter.Model.WaiterList;
import com.example.foodpos_counter.Model.category_data;
import com.example.foodpos_counter.Model.foodcart;
import com.example.foodpos_counter.Model.foodlist_data;
import com.example.foodpos_counter.Model.selectperson;
import com.example.foodpos_counter.Pref.Pref;
import com.example.foodpos_counter.Utils.AnotherScreen;
import com.example.foodpos_counter.Utils.OverallbillService;
import com.example.foodpos_counter.Utils.TestService;
import com.example.foodpos_counter.db.DAO;
import com.example.foodpos_counter.db.Entity.Add_ons_cart;
import com.example.foodpos_counter.db.Entity.CartTable;
import com.example.foodpos_counter.db.Entity.EmptyCartTable;
import com.example.foodpos_counter.db.RoomDB;
import com.example.foodpos_counter.db.ViewModel;
import com.google.gson.Gson;
import com.imin.printerlib.IminPrintUtils;
import com.onesignal.OSDeviceState;
import com.onesignal.OneSignal;

import net.posprinter.posprinterface.IMyBinder;
import net.posprinter.posprinterface.ProcessData;
import net.posprinter.posprinterface.TaskCallback;
import net.posprinter.service.PosprinterService;
import net.posprinter.utils.DataForSendToPrinterPos80;
import net.posprinter.utils.DataForSendToPrinterTSC;
import net.posprinter.utils.PosPrinterDev;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Menu_Activity extends AppCompatActivity implements Inter {


    Pref pref;
    ViewModel viewModel;
    RecyclerView category_recycler, recycler, addcart_recycler, order_recycler;
    RecyclerView addons_recycler;

    TextView order_no;
    TextView token_no;

    EditText customer_type;
    EditText customer_name;
    EditText waiter_name;
    EditText table_no;

    ArrayList<category_data> cate_array;
    ArrayList<foodlist_data> foodlistarray;
    ArrayList<foodcart> foodcartarray;
    ArrayList<CustomerTypes> customerTypesArrayList;
    ArrayList<CustomerList> customerListArrayList;
    ArrayList<WaiterList> waiterListArrayList;
    ArrayList<GetTableInfo> getTableInfoArrayList;
    ArrayList<IPAddress> ipAddressArrayList;
    ArrayList<Add_ons_cart> addOnsCartArrayList;
    ArrayList<CartTable> cartTableArrayList;
    ArrayList<EmptyCartTable> qtycartTableArrayList;
    ArrayList<PlaceOrderFood> placeOrderFoodArrayList;
    ArrayList<UpdateFoodlist> updateFoodlists;
    ArrayList<UpdateAddons> updateAddons;
    ArrayList<PlaceAddons> placeAddonsArrayList;
    ArrayList<Order_FoodList> orderFoodListArrayList;
    ArrayList<Order_Addons> orderAddonsArrayList;
    ArrayList<Paymenttypes> paymenttypesArrayList;
    ArrayList<CardList> cardListArrayList;
    ArrayList<BankList> bankListArrayList;

    CategoryAdapter categoryAdapter;
    FoodListAdapter foodlistAdapter;
    CustomertypeAdapter customertypeAdapter;
    CustomernameAdapter customernameAdapter;
    WaiternameAdapter waiternameAdapter;
    AddAdapter addAdapter;
    OrderAdapter orderAdapter;
    AddCartAdapter addCartAdapter;
    PaymentMethodAdapter paymentMethodAdapter;
    CardAdapter cardAdapter;
    BankAdapter bankAdapter;
    AppCompatButton cancel_btn;

    TextView total;
    TextView service;
    TextView tax;
    TextView customer_type_text;
    TextView waiter_name_text;
    TextView order_count;
    TextView reprint;

    AppCompatButton add_customer, report_btn;
    ImageView close, close_person;
    AppCompatButton select_personbtn, close_btn, completeorder_btn, new_order;

    TextView ongoing_btn, selfkiosk_btn;
    RecyclerView person_recycler, person_recycler1, person_recycler2;
    TableAdapter tableAdapter;
    ArrayList<selectperson> personarray;
    LinearLayout person, table;

    EditText payment_method;
    EditText card_terminal;
    EditText select_bank;
    EditText pin;

    CardView view_order;
    LinearLayout card_layout;
    LinearLayout waiter_layout;
    LinearLayout pin_layout;
    LinearLayout update_order;
    LinearLayout no_person;
    LinearLayout self_service_layout;
    AppCompatButton place_order;
    AppCompatButton logout;
    ImageView placeorder_close;

    TextView cart_badge;
    TextView cart_badge1;

    int update_count;
    String customer_type_str;
    String bankid_str;
    String bankname_str;
    String card_terminalid_str;
    String terminal_name_str;
    String categoryid_str;
    String customertype_id;
    String customertype_name;
    String customer_Id = "0";
    String customer_Name;
    String employee_Id;
    String employee_Name;
    String tableid_str;
    String tablename_str;
    String floor_str;
    String seat_str;
    String availableSeat_str;
    String bookingStatus_str;
    String orderid_str = "";
    String servicecharge_str = "0";
    String type_str;
    String typeName_str = "";
    String cart_qty;
    String cart_addons_qty;
    String cart_addons_status;

    String update_category_id;

    String order__id;
    String order_customertype_id;
    String order_table_id;
    String order_seat_id;
    String order_waiter_id;
    String order_waiter_name;
    String place_order_id;
    String print_kitchenName;
    String print_productName;
    String print_menuqty;
    String print_variantName;

    String order_customer_id;
    String total_amt_str = "0.0";
    String total_product_str = "0.0";
    String total_vat_str = "0.0";
    float tot_amt = 0;
    String paymentId_str;
    String paymentType_str;

    boolean update_qty = false;

    boolean isCheck;
    String addons_id_str;
    String addons_name_str;
    String addons_price_str;
    String addons_qty_str;

    float c = 0;
    float total_flt;
    float paid_flt;

    float tax_flt = 0;
    float product_flt = 0;
    float tot = 0;
    float addons_tot = 0;
    String c1;
    String product_str;
    String tax_str;
    String bal_str;
    String paid_str;
    String update_order_id = "";
    String productsId;
    int finalI;
    int finalI1;

    private IminPrintUtils mIminPrintUtils;
    public static IMyBinder myBinder;
    Handler handler;
    boolean ip_status;
    boolean inactivestatus;

    Handler handler1;

    Runnable runnable;
    int delay = 15000;

    LinearLayout progressBar;

    private static final String TAG = "display_demo";
    private Presentation presentation;

    List<String> products_id_lst;
    List<String> products_name_lst;
    OSDeviceState device = OneSignal.getDeviceState();
    String playerID;
    boolean txt_amt;
    float tot_bal;
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
        setContentView(R.layout.activity_menu);

        Intent intent = new Intent(this, PosprinterService.class);
        bindService(intent, mSerconnection, BIND_AUTO_CREATE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                // Toast.makeText(this,"请同意显示窗口权限",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION));
            }
        }

//        if (mIminPrintUtils == null) {
//            mIminPrintUtils = IminPrintUtils.getInstance(Menu_Activity.this);
//        }
//        mIminPrintUtils.resetDevice();

        playerID = device.getUserId();
        //Log.d("PlayerId" , "Player Id "+ playerID);
//        getPlayer_id();

        pref = new Pref(getApplicationContext());
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        getCartTable();
        category_recycler = (RecyclerView) findViewById(R.id.category_recycler);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        addcart_recycler = (RecyclerView) findViewById(R.id.addcart_recycler);
        order_recycler = (RecyclerView) findViewById(R.id.order_recycler);
        //cancel_btn = (AppCompatButton) findViewById(R.id.cancel_btn);
        add_customer = (AppCompatButton) findViewById(R.id.add_customer);
        select_personbtn = (AppCompatButton) findViewById(R.id.select_personbtn);
        ongoing_btn = (TextView) findViewById(R.id.ongoing_btn);
        completeorder_btn = (AppCompatButton) findViewById(R.id.completeorder_btn);
        selfkiosk_btn = (TextView) findViewById(R.id.selfkiosk_btn);
        place_order = (AppCompatButton) findViewById(R.id.place_order);
        person = (LinearLayout) findViewById(R.id.person);
        table = (LinearLayout) findViewById(R.id.table);
        new_order = (AppCompatButton) findViewById(R.id.new_order);
        cancel_btn = (AppCompatButton) findViewById(R.id.cancel_btn);
        report_btn = (AppCompatButton) findViewById(R.id.report_btn);
        logout = (AppCompatButton) findViewById(R.id.logout);
        self_service_layout = (LinearLayout) findViewById(R.id.self_service_layout);
        no_person = (LinearLayout) findViewById(R.id.no_person);
        update_order = (LinearLayout) findViewById(R.id.update_order);
        customer_type = (EditText) findViewById(R.id.customer_type);
        customer_name = (EditText) findViewById(R.id.customer_name);
        waiter_name = (EditText) findViewById(R.id.waiter_name);
        table_no = (EditText) findViewById(R.id.table_no);
        total = (TextView) findViewById(R.id.total);
        reprint = (TextView) findViewById(R.id.reprint);
        service = (TextView) findViewById(R.id.service);
        order_count = (TextView) findViewById(R.id.order_count);
        waiter_name_text = (TextView) findViewById(R.id.waiter_name_text);
        waiter_layout = (LinearLayout) findViewById(R.id.waiter_layout);
        customer_type_text = (TextView) findViewById(R.id.customer_type_text);
        tax = (TextView) findViewById(R.id.tax);
        order_no = (TextView) findViewById(R.id.order_no);
        token_no = (TextView) findViewById(R.id.token_no);
        cart_badge = (TextView) findViewById(R.id.cart_badge);
        cart_badge1 = (TextView) findViewById(R.id.cart_badge1);
        progressBar = (LinearLayout) findViewById(R.id.progressBar);
        view_order = (CardView) findViewById(R.id.view_order);

        cate_array = new ArrayList<>();
        foodlistarray = new ArrayList<>();
        foodcartarray = new ArrayList<>();
        customerTypesArrayList = new ArrayList<>();
        customerListArrayList = new ArrayList<>();
        waiterListArrayList = new ArrayList<>();
        getTableInfoArrayList = new ArrayList<>();
        ipAddressArrayList = new ArrayList<>();
        addOnsCartArrayList = new ArrayList<>();
        cartTableArrayList = new ArrayList<>();
        qtycartTableArrayList = new ArrayList<>();
        placeOrderFoodArrayList = new ArrayList<>();
        updateFoodlists = new ArrayList<>();
        updateAddons = new ArrayList<>();
        placeAddonsArrayList = new ArrayList<>();
        orderFoodListArrayList = new ArrayList<>();
        orderAddonsArrayList = new ArrayList<>();
        paymenttypesArrayList = new ArrayList<>();
        cardListArrayList = new ArrayList<>();
        bankListArrayList = new ArrayList<>();

        products_id_lst = new ArrayList<>();
        products_name_lst = new ArrayList<>();

        if (presentation != null) {
            presentation.cancel();
        }
//                showSecondByMediaRouter(MainActivity.this);
        showSecondByDisplayManager(Menu_Activity.this);

        if (presentation != null) {
            //presentation.cancel();
            //presentation..showTextView("");
            ImageView imageView = presentation.findViewById(R.id.image);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(R.mipmap.dhivyalogo);

        }
        loadPrinters();
        getCategory();
        getCustomertypeApi();
        getCustomernameApi();
//        getWaiterApi();
//        getPaymenttype();
        getCategoryList("0");
//        getServiceTax();
//        getOngoingCount();

        //background service
        MyAsyncTasks1 myAsyncTasks1 = new MyAsyncTasks1();
        myAsyncTasks1.execute();

        finalI = 0;
        finalI1 = 0;
        inactivestatus = false;

        handler = new Handler();

        update_order.setVisibility(View.GONE);
        reprint.setVisibility(View.GONE);

        //self-service method
        self_service_layout.setVisibility(View.GONE);
        table_no.setText("");
        employee_Id = "0";
        orderid_str = "0";
        //

        Intent i = getIntent();
        update_order_id = i.getStringExtra("order_id");


        if (update_order_id.equals("")) {
            order_recycler.setVisibility(View.GONE);
            view_order.setVisibility(View.GONE);
            update_order.setVisibility(View.GONE);
            reprint.setVisibility(View.GONE);

        } else {
            update_order.setVisibility(View.VISIBLE);
            order_recycler.setVisibility(View.VISIBLE);
            view_order.setVisibility(View.VISIBLE);
            person.setVisibility(View.GONE);
            reprint.setVisibility(View.VISIBLE);

            self_service_layout.setVisibility(View.VISIBLE);
            customertype_id = i.getStringExtra("customer_type");

            // create object of MyAsyncTasks class and execute it
            MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
            myAsyncTasks.execute(update_order_id);

            orderid_str = update_order_id;
        }

        viewModel.deleteall(1);

        ongoing_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Activity.this, OnGoingOrder_Activity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLogout();
            }
        });

        customer_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCustomerTypeDialog();
                if (ip_status == true) {
//                    getIPAddress();
                }
            }
        });

        customer_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCustomerDialog();
            }
        });

        waiter_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWaiter();
            }
        });

        table_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTableDialog();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAll();
            }
        });

        completeorder_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Activity.this, CompletedOrder_Activity.class);
                startActivity(intent);
            }
        });

        report_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(Menu_Activity.this, report_btn);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
//                            case R.id.counter:
//                                Intent intent1 = new Intent(Menu_Activity.this, CounterReportsActivity.class);
//                                startActivity(intent1);
//                                break;
//                            case R.id.payment:
//                                Intent intent2 = new Intent(Menu_Activity.this, PaymentReportActivity.class);
//                                startActivity(intent2);
//                                break;
//                            case R.id.food:
//                                Intent intent3 = new Intent(Menu_Activity.this, FoodReportActivity.class);
//                                startActivity(intent3);
//                                break;

                            case R.id.daily:
                                getOverAllReport();
                                break;
                        }
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });

        selfkiosk_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Activity.this, SelfKiosk_Activity.class);
                startActivity(intent);
            }
        });

        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartTableArrayList.size() == 0) {
                    getUpdateDialog();
                } else {
                    if (update_order_id.equals("")) {
                        if (customer_type.getText().toString().equals("")) {
                            getSelectCustomer();
                        } else {
                            if (customertype_id.equals("4")) {
                                if (waiter_name.getText().toString().equals("")) {
                                    getSelectWaiter();
                                } else {
                                    getPlaceDialog(place_order_id, customertype_id, "", "");
                                    //getPlaceOrderApi(customertype_id, "", "");
                                }
                            } else if (customertype_id.equals("101")) {
                                getPlaceDialog(place_order_id, customertype_id, "", "");
                            } else {
                                if (waiter_name.getText().toString().equals("")) {
                                    getSelectWaiter();
                                } else if (table_no.getText().toString().equals("")) {
                                    getSelectTable();
                                } else {
                                    PosPrinterDev.PrinterInfo printer = getIPAddress();
                                    if (orderid_str.equals("0")) {
                                        getPlaceOrderApi(customertype_id, tableid_str, seat_str, paid_str, bal_str, printer);
                                    } else {
                                        getUpdateOrderApi(printer);
                                    }
                                }
                            }
                        }
                    } else {
                        PosPrinterDev.PrinterInfo printer = getIPAddress();
                        getUpdateOrderApi(printer);
                    }
                }
            }
        });

        add_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCustomerDialog();
            }
        });

        view_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getViewOrder();
            }
        });

        select_personbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTableInfoApi();
                getSelectPerson();
            }
        });

        new_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAll();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        category_recycler.setLayoutManager(linearLayoutManager);

        reprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                PosPrinterDev.PrinterInfo printer = getIPAddress();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getRetokenDialog(update_order_id);
                        progressBar.setVisibility(View.GONE);
                    }
                }, 10000);
            }
        });

        GridLayoutManager linearLayoutManager1 = new GridLayoutManager(getApplicationContext(), 4);
        recycler.setLayoutManager(linearLayoutManager1);


        GridLayoutManager linearLayoutManager2 = new GridLayoutManager(getApplicationContext(), 1);
        addcart_recycler.setLayoutManager(linearLayoutManager2);

        addCartAdapter = new AddCartAdapter(Menu_Activity.this, cartTableArrayList);
        addcart_recycler.setAdapter(addCartAdapter);

        viewModel.getTb_cart().observe(this, new Observer<List<CartTable>>() {
            @Override
            public void onChanged(List<CartTable> cartTables) {
                cardListArrayList.removeAll(cardListArrayList);
                placeOrderFoodArrayList.removeAll(placeOrderFoodArrayList);
                updateFoodlists.removeAll(updateFoodlists);
                for (int i = 0; i < cartTables.size(); i++) {
                    String productsId = cartTables.get(i).getProductsId();
                    String categoryId = cartTables.get(i).getCategoryId();
                    String productName = cartTables.get(i).getProductName();
                    String productImage = cartTables.get(i).getProductImage();
                    String varientId = cartTables.get(i).getVarientId();
                    String vatCharge = cartTables.get(i).getVatCharge();
                    String varientName = cartTables.get(i).getVarientName();
                    String price = cartTables.get(i).getPrice();
                    String kitchenId = cartTables.get(i).getKitchenId();
                    String kitchenName = cartTables.get(i).getKitchenName();
                    String kitchenIpAdrees = cartTables.get(i).getKitchenIpAdrees();
                    String kitchenPort = cartTables.get(i).getKitchenPort();
                    ArrayList<Add_ons_cart> addons = cartTables.get(i).getAddons();
                    String qty = cartTables.get(i).getQty();
                    String notes = cartTables.get(i).getNotes();
                    String status = cartTables.get(i).getStatus();
                    String addons_status = cartTables.get(i).getAddons_status();
                    String uid = cartTables.get(i).getUid();


                    if (update_order_id.equals("")) {
                        if (addons_status.equals("1")) {
                            placeAddonsArrayList = new ArrayList<>();
                            for (int j = 0; j < addons.size(); j++) {
                                String a_id = addons.get(j).getAddons_id();
                                String a_qty = addons.get(j).getAddons_qty();

                                placeAddonsArrayList.add(new PlaceAddons(a_id, a_qty));
                            }
                        } else {
                            placeAddonsArrayList = new ArrayList<>();
                        }

                        placeOrderFoodArrayList.add(new PlaceOrderFood(
                                productsId,
                                qty,
                                placeAddonsArrayList,
                                varientId,
                                price,
                                notes));
                    } else {

                        if (addons_status.equals("1")) {
                            updateAddons = new ArrayList<>();
                            for (int j = 0; j < addons.size(); j++) {
                                String a_id = addons.get(j).getAddons_id();
                                String a_qty = addons.get(j).getAddons_qty();

                                updateAddons.add(new UpdateAddons(a_id, a_qty));
                            }
                        } else {
                            updateAddons = new ArrayList<>();
                        }

                        updateFoodlists.add(new UpdateFoodlist(
                                productsId,
                                qty,
                                updateAddons,
                                varientId,
                                price,
                                notes
                        ));
                    }
                }
            }
        });
    }


    private void getPlayer_id() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<Player_ids> call = api.getPlayerId(playerID);

        call.enqueue(new Callback<Player_ids>() {
            @Override
            public void onResponse(Call<Player_ids> call, Response<Player_ids> response) {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Player_ids> call, Throwable t) {

            }
        });
    }

    private void showSecondByDisplayManager(Context context) {
        DisplayManager mDisplayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = mDisplayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION);
        if (displays != null && getPresentationDisplays() != null) {
            presentation = new AnotherScreen(context, getPresentationDisplays());
            presentation.show();
        } else {
            // Toast.makeText(Menu_Activity.this, getString(R.string.no_second_screen), Toast.LENGTH_SHORT);
        }
        /*副屏的Window*/
    }

    private Display getPresentationDisplays() {
        DisplayManager mDisplayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = mDisplayManager.getDisplays();
        if (displays != null) {
            for (int i = 0; i < displays.length; i++) {
                Log.e(TAG, "屏幕==>" + displays[i] + " Flag:==> " + displays[i].getFlags());
                if ((displays[i].getFlags() & Display.FLAG_SECURE) != 0
                        && (displays[i].getFlags() & Display.FLAG_SUPPORTS_PROTECTED_BUFFERS) != 0
                        && (displays[i].getFlags() & Display.FLAG_PRESENTATION) != 0) {
                    Log.e(TAG, "第一个真实存在的副屏屏幕==> " + displays[i]);
                    return displays[i];
                }
            }
        }

        return null;
    }

    private void getSelectCustomer() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.update_dialog);
        Window window = dialog.getWindow();
        window.setLayout(600, AbsListView.LayoutParams.WRAP_CONTENT);

        TextView text = (TextView) dialog.findViewById(R.id.text);
        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        AppCompatButton confirm = (AppCompatButton) dialog.findViewById(R.id.confirm);

        text.setText("Select the Customer Type");

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

    public void getSelectPaymentMethod() {
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

    private void getUpdateDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.update_dialog);
        Window window = dialog.getWindow();
        window.setLayout(800, AbsListView.LayoutParams.WRAP_CONTENT);

        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        AppCompatButton confirm = (AppCompatButton) dialog.findViewById(R.id.confirm);

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

    private void getServiceTax() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<Service_Tax> call = api.getService();

        call.enqueue(new Callback<Service_Tax>() {
            @Override
            public void onResponse(Call<Service_Tax> call, Response<Service_Tax> response) {
                try {
                    servicecharge_str = response.body().getData().getServicecharge();
                    type_str = response.body().getData().getType();
                    typeName_str = response.body().getData().getTypeName();
                    // Toast.makeText(Menu_Activity.this, servicecharge_str+"", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Service_Tax> call, Throwable t) {

            }
        });
    }

    private void loadPrinters() {

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
//                        myBinder.GetPrinterInfoList().removeAll(myBinder.GetPrinterInfoList());
//                        PosPrinterDev.PrinterInfo printer;
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


                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Kitchen_List> call, Throwable t) {
                //Toast.makeText(Menu_Activity.this, t.toString() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private PosPrinterDev.PrinterInfo getIPAddress() {
        PosPrinterDev.PrinterInfo printer = null;
        boolean exist = false;
        for (int i = 0; i < ipAddressArrayList.size(); i++) {
            exist = false;
            // myBinder.GetPrinterInfoList().removeAll(myBinder.GetPrinterInfoList());
//            for (int j = 0; j < myBinder.GetPrinterInfoList().size(); j++) {
//                if (myBinder.GetPrinterInfoList().get(j).printerName.equals(ipAddressArrayList.get(i).getKitchenname())) {
//                    exist = true;
//                    break;
//                }
//
//            }
//            if (!exist) {
            printer = new PosPrinterDev.PrinterInfo(ipAddressArrayList.get(i).getKitchenname(), PosPrinterDev.PortType.Ethernet, ipAddressArrayList.get(i).getIp());
            AddPrinter(printer);
//            }
        }

        return printer;

    }

    private void getIPAddress_old() {

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
                //  Toast.makeText(Menu_Activity.this, t.toString() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AddPrinter(PosPrinterDev.PrinterInfo printer) {

        myBinder.AddPrinter(printer, new TaskCallback() {
            @Override
            public void OnSucceed() {
                // Toast.makeText(Menu_Activity.this, "Success", Toast.LENGTH_SHORT).show();
                //  ip_status = true;
            }

            @Override
            public void OnFailed() {
                // Toast.makeText(getApplicationContext(), "Connection failed", Toast.LENGTH_SHORT).show();
                // ip_status = false;
            }
        });
    }

    private void getTableInfoApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<GetTable> call = api.getTableInfo();

        call.enqueue(new Callback<GetTable>() {
            @Override
            public void onResponse(Call<GetTable> call, Response<GetTable> response) {

                try {
                    getTableInfoArrayList.removeAll(getTableInfoArrayList);
                    if (response.body().getStatus().equals("true")) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            String tableid = response.body().getData().get(i).getTableid();
                            String tablename = response.body().getData().get(i).getTablename();
                            String floor = response.body().getData().get(i).getFloor();
                            String seat = response.body().getData().get(i).getSeat();
                            String availableSeat = response.body().getData().get(i).getAvailableSeat();
                            String bookingStatus = response.body().getData().get(i).getBookingStatus();
                            String orderid = response.body().getData().get(i).getOrderid();

                            getTableInfoArrayList.add(new GetTableInfo(tableid,
                                    tablename,
                                    floor,
                                    seat,
                                    availableSeat,
                                    bookingStatus,
                                    orderid));

                        }
                        tableAdapter.notifyDataSetChanged();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetTable> call, Throwable t) {

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
                        waiternameAdapter.notifyDataSetChanged();
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

    private void getCustomernameApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<GetCustomer> call = api.getCustomer();

        call.enqueue(new Callback<GetCustomer>() {
            @Override
            public void onResponse(Call<GetCustomer> call, Response<GetCustomer> response) {
                try {
                    if (response.body().getStatus().equals("true")) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            String customerId = response.body().getData().get(i).getCustomerId();
                            String customerName = response.body().getData().get(i).getCustomerName();

                            customerListArrayList.add(new CustomerList(customerId,
                                    customerName));
                        }
                        customernameAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GetCustomer> call, Throwable t) {

            }
        });
    }

    private void getCustomertypeApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<Customer_type> call = api.getCustomerType();

        call.enqueue(new Callback<Customer_type>() {
            @Override
            public void onResponse(Call<Customer_type> call, Response<Customer_type> response) {
                customerTypesArrayList.removeAll(customerTypesArrayList);
                try {
                    if (response.body().getStatus().equals("true")) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            String customertypeid = response.body().getData().get(i).getCustomertypeid();
                            String customertypename = response.body().getData().get(i).getCustomertypename();

                            customer_type_str = response.body().getData().get(4).getCustomertypename();
                            customer_type.setText(customer_type_str);
                            customertype_id = response.body().getData().get(4).getCustomertypeid();

                            customerTypesArrayList.add(new CustomerTypes(customertypeid, customertypename));

                        }
                        //customertypeAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Customer_type> call, Throwable t) {

            }
        });
    }

    private void getTableDialog() {
    }

    private void getWaiter() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);
        Window window = dialog.getWindow();
        window.setLayout(600, AbsListView.LayoutParams.WRAP_CONTENT);

        RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
        ImageButton close = dialog.findViewById(R.id.close);
        TextView text = (TextView) dialog.findViewById(R.id.text);

        text.setText("Waiter name");

        LinearLayoutManager layoutManager = new LinearLayoutManager(dialog.getContext());
        recyclerView.setLayoutManager(layoutManager);

        waiternameAdapter = new WaiternameAdapter(Menu_Activity.this, waiterListArrayList);
        recyclerView.setAdapter(waiternameAdapter);

        waiternameAdapter.setOnItemClickListener(new WaiternameAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ArrayList<WaiterList> waiterListArrayList, int position) {
                employee_Id = waiterListArrayList.get(position).getEmployeeId();
                employee_Name = waiterListArrayList.get(position).getEmployeeName();

                waiter_name.setText(employee_Name);
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

    private void getCustomerDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);
        Window window = dialog.getWindow();
        window.setLayout(800, AbsListView.LayoutParams.WRAP_CONTENT);

        RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
        ImageButton close = dialog.findViewById(R.id.close);
        TextView text = (TextView) dialog.findViewById(R.id.text);

        text.setText("Customer name");

        LinearLayoutManager layoutManager = new LinearLayoutManager(dialog.getContext());
        recyclerView.setLayoutManager(layoutManager);

        customernameAdapter = new CustomernameAdapter(Menu_Activity.this, customerListArrayList);
        recyclerView.setAdapter(customernameAdapter);
        customernameAdapter.setOnItemClickListener(new CustomernameAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ArrayList<CustomerList> customerListList, int position) {
                customer_Id = customerListList.get(position).getCustomerId();
                customer_Name = customerListList.get(position).getCustomerName();

                customer_name.setText(customer_Name);
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

    private void getCustomerTypeDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);
        Window window = dialog.getWindow();
        window.setLayout(800, AbsListView.LayoutParams.WRAP_CONTENT);

        RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
        ImageButton close = dialog.findViewById(R.id.close);
        TextView text = (TextView) dialog.findViewById(R.id.text);

        text.setText("Customer type");

        LinearLayoutManager layoutManager = new LinearLayoutManager(dialog.getContext());
        recyclerView.setLayoutManager(layoutManager);

        customertypeAdapter = new CustomertypeAdapter(Menu_Activity.this, customerTypesArrayList);
        recyclerView.setAdapter(customertypeAdapter);

        customertypeAdapter.setOnItemClickListener(new CustomertypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ArrayList<CustomerTypes> customerTypesList, int position) {
                customertype_id = customerTypesList.get(position).getCustomertypeid();
                customertype_name = customerTypesList.get(position).getCustomertypename();

                customer_type.setText(customertype_name);

                if (customertype_id.equals("4")) {
                    self_service_layout.setVisibility(View.VISIBLE);
                    waiter_layout.setVisibility(View.VISIBLE);
                    waiter_name.setVisibility(View.VISIBLE);
                    person.setVisibility(View.GONE);
                    table.setVisibility(View.GONE);
                    table_no.setText("");
                    orderid_str = "0";
                } else if (customertype_id.equals("99")) {
                    self_service_layout.setVisibility(View.VISIBLE);
                    waiter_layout.setVisibility(View.GONE);
                    person.setVisibility(View.VISIBLE);
                    table.setVisibility(View.VISIBLE);

                } else if (customertype_id.equals("101")) {
                    self_service_layout.setVisibility(View.GONE);
                    table_no.setText("");
                    employee_Id = "0";
                    orderid_str = "0";
                } else {
                    self_service_layout.setVisibility(View.VISIBLE);
                    waiter_layout.setVisibility(View.VISIBLE);
                    waiter_name.setVisibility(View.VISIBLE);
                    person.setVisibility(View.VISIBLE);
                    table.setVisibility(View.VISIBLE);
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

    private void getLogout() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Do you want to logout this application ?");
        dialog.setTitle("Logout");

        dialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pref.clearData();
                viewModel.deleteall(1);
                Intent intent1 = new Intent(Menu_Activity.this, MainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }
        });
        dialog.show();
    }

    private void addCustomerDialog() {
        Dialog dialog = new Dialog(Menu_Activity.this);
        dialog.setContentView(R.layout.addcustomer_layout);
        dialog.getWindow().setLayout(1000, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        close = (ImageView) dialog.findViewById(R.id.close);
        EditText name = (EditText) dialog.findViewById(R.id.name);
        EditText email = (EditText) dialog.findViewById(R.id.email);
        EditText mobile_no = (EditText) dialog.findViewById(R.id.mobile_no);
        AppCompatButton cancel = (AppCompatButton) dialog.findViewById(R.id.cancel);
        AppCompatButton submit = (AppCompatButton) dialog.findViewById(R.id.submit);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equals("")) {
                    Toast.makeText(Menu_Activity.this, "Enter the name", Toast.LENGTH_SHORT).show();
                } else if (mobile_no.getText().toString().equals("")) {
                    Toast.makeText(Menu_Activity.this, "Enter the Mobile Number", Toast.LENGTH_SHORT).show();
                } else {
                    addCustomer(name, email, mobile_no);
                    dialog.dismiss();
                }
            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void addCustomer(EditText name, EditText email, EditText mobile_no) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
        dialog.show();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<Customer> call = api.getAddcustomer(name.getText().toString(), email.getText().toString(), mobile_no.getText().toString());

        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                dialog.dismiss();
                getCustomernameApi();
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }

    private void getSelectPerson() {
        Dialog dialog = new Dialog(Menu_Activity.this);
        dialog.setContentView(R.layout.selectperson_layout);
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        close_person = (ImageView) dialog.findViewById(R.id.close_person);
        close_btn = (AppCompatButton) dialog.findViewById(R.id.close_btn);
        person_recycler = (RecyclerView) dialog.findViewById(R.id.person_recycler);
        personarray = new ArrayList<>();


        close_btn.setVisibility(View.GONE);

        close_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(), 8);
        person_recycler.setLayoutManager(linearLayoutManager);

        tableAdapter = new TableAdapter(Menu_Activity.this, getTableInfoArrayList);
        person_recycler.setAdapter(tableAdapter);

        tableAdapter.setOnItemClickListener(new TableAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ArrayList<GetTableInfo> getTableInfoArrayList, int position) {
                tableid_str = getTableInfoArrayList.get(position).getTableid();
                tablename_str = getTableInfoArrayList.get(position).getTablename();
                floor_str = getTableInfoArrayList.get(position).getFloor();
                seat_str = getTableInfoArrayList.get(position).getSeat();
                availableSeat_str = getTableInfoArrayList.get(position).getAvailableSeat();
                bookingStatus_str = getTableInfoArrayList.get(position).getBookingStatus();
                orderid_str = getTableInfoArrayList.get(position).getOrderid();

                // if (orderid_str.equals("0")) {
                order_recycler.setVisibility(View.GONE);
                update_order.setVisibility(View.GONE);
                table_no.setText(tablename_str);
//                } else {
//                    update_order.setVisibility(View.VISIBLE);
//                    order_recycler.setVisibility(View.VISIBLE);
//                    OrderSummary(orderid_str);
//                }
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    private void getPlaceDialog(String Place_order_id, String customertype_id, String s, String s1) {
        Dialog dialog = new Dialog(Menu_Activity.this);
        dialog.setContentView(R.layout.placeorder);
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT);

        //  mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.USB);

        placeorder_close = (ImageView) dialog.findViewById(R.id.placeorder_close);
        TextView grand_total = (TextView) dialog.findViewById(R.id.grand_total);
        TextView total_due = (TextView) dialog.findViewById(R.id.total_due);
        payment_method = (EditText) dialog.findViewById(R.id.payment_method);
        card_terminal = (EditText) dialog.findViewById(R.id.card_terminal);
        select_bank = (EditText) dialog.findViewById(R.id.select_bank);
        pin = (EditText) dialog.findViewById(R.id.pin);
        RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
        card_layout = (LinearLayout) dialog.findViewById(R.id.card_layout);
        pin_layout = (LinearLayout) dialog.findViewById(R.id.pin_layout);
        LinearLayout discount_layout = (LinearLayout) dialog.findViewById(R.id.discount_layout);
        LinearLayout layout = (LinearLayout) dialog.findViewById(R.id.layout);
        LinearLayout paid_layout = (LinearLayout) dialog.findViewById(R.id.paid_layout);
        LinearLayout change_layout = (LinearLayout) dialog.findViewById(R.id.change_layout);
        EditText paid_txt = (EditText) dialog.findViewById(R.id.paid_txt);
        TextView change_txt = (TextView) dialog.findViewById(R.id.change_txt);
        AppCompatButton pay_btn = (AppCompatButton) dialog.findViewById(R.id.pay_btn);
        AppCompatButton unpaid_btn = (AppCompatButton) dialog.findViewById(R.id.unpaid_btn);
        AppCompatButton cancel = (AppCompatButton) dialog.findViewById(R.id.cancel);
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

        discount_layout.setVisibility(View.GONE);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paid_txt.setText(paid_txt.getText().toString() + "1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paid_txt.setText(paid_txt.getText().toString() + "2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paid_txt.setText(paid_txt.getText().toString() + "3");

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paid_txt.setText(paid_txt.getText().toString() + "4");

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paid_txt.setText(paid_txt.getText().toString() + "5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paid_txt.setText(paid_txt.getText().toString() + "6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paid_txt.setText(paid_txt.getText().toString() + "7");

            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paid_txt.setText(paid_txt.getText().toString() + "8");

            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paid_txt.setText(paid_txt.getText().toString() + "9");

            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paid_txt.setText(paid_txt.getText().toString() + "0");

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paid_txt.setText(paid_txt.getText().toString() + ".");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paid_txt.getText().length() > 0) {
                    StringBuilder stringBuilder = new StringBuilder(paid_txt.getText());
                    stringBuilder.deleteCharAt(paid_txt.getText().length() - 1);
                    String newString = stringBuilder.toString();
                    paid_txt.setText(newString);
                } else {
                    paid_txt.setText("0");
                }
            }
        });

        layout.setVisibility(View.GONE);
        placeorder_close.setVisibility(View.VISIBLE);
        card_layout.setVisibility(View.GONE);
        pin_layout.setVisibility(View.GONE);
        unpaid_btn.setVisibility(View.GONE);
        this.customertype_id = "101";
        // paymentId_str = "";

        payment_method.setText(paymentType_str);

        paid_layout.setVisibility(View.VISIBLE);
        change_layout.setVisibility(View.VISIBLE);

        placeorder_close.setOnClickListener(new View.OnClickListener() {
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


        grand_total.setText("RM      " + c1);
        total_due.setText("RM      " + c1);


        float Tot_flt = Float.parseFloat(c1);
        total_flt = Float.parseFloat(c1);

        GridLayoutManager layoutManager = new GridLayoutManager(dialog.getContext(), 4);
        recyclerView.setLayoutManager(layoutManager);

        paymentMethodAdapter = new PaymentMethodAdapter(Menu_Activity.this, paymenttypesArrayList);
        recyclerView.setAdapter(paymentMethodAdapter);
        paymentMethodAdapter.setOnItemClickListener(new PaymentMethodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ArrayList<Paymenttypes> paymenttypesArrayList, int position) {
                //getIPAddress();
                paymentId_str = paymenttypesArrayList.get(position).getPaymentId();
                paymentType_str = paymenttypesArrayList.get(position).getPaymentType();
                payment_method.setText(paymentType_str);
                if (paymentId_str.equals("4")) {
                    paid_layout.setVisibility(View.VISIBLE);
                    change_layout.setVisibility(View.VISIBLE);
                } else {
                    paid_layout.setVisibility(View.GONE);
                    change_layout.setVisibility(View.GONE);
                    paid_str = "0";
                    bal_str = "0";
                }
            }
        });


        paid_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                float amt_flt;
                if (paid_txt.getText().toString().equals("")) {
                    amt_flt = 0;
                    change_txt.setText("RM     0.00");
                } else {
                    amt_flt = Float.parseFloat(paid_txt.getText().toString());
                    paid_flt = Float.parseFloat(paid_txt.getText().toString());
                    if (amt_flt >= Tot_flt) {
                        if (amt_flt == 0) {
                            paid_txt.setText("0");
                            change_txt.setText("RM     0.00");
                        } else {
                            float bal = amt_flt - Tot_flt;
                            bal_str = String.format("%.2f", bal);
                            paid_str = String.format("%2f", paid_flt);
                            change_txt.setText("RM     " + bal_str);
                        }
                    } else {
                        change_txt.setText("RM     0.00");
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

//                if (payment_method.getText().toString().equals("")) {
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
//                            getPaymentApi(dialog, Place_order_id, paid_str, bal_str);
//
//                        }
//                    } else {
//                      //  Toast.makeText(Menu_Activity.this, paid_str+"", Toast.LENGTH_SHORT).show();
//                       // Toast.makeText(Menu_Activity.this, bal_str+"", Toast.LENGTH_SHORT).show();
//                        getPaymentApi(dialog, Place_order_id, paid_str, bal_str);
//
//                    }
//                }
                PosPrinterDev.PrinterInfo printer = getIPAddress();
                if (paymentId_str.equals("")) {
                    getSelectPaymentMethod();
                } else {
                    if (paymentId_str.equals("4")) {
                        if (paid_txt.getText().toString().equals("")) {
                            paid_txt.setError("Enter the Customer Paid Amount");
                        } else {
                            if (paid_flt < total_flt) {
                                getCustomerPaid();
                            } else {
                                // getPaymentApi(dialog, Place_order_id, paid_str, bal_str);
                                getPlaceOrderApi(customertype_id, "", "", paid_str, bal_str, printer);
                                dialog.dismiss();
                            }
                        }
                    } else {
                        getPlaceOrderApi(customertype_id, "", "", paid_str, bal_str, printer);
                        dialog.dismiss();
                        // getPaymentApi(dialog, Place_order_id, paid_str, bal_str);
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
//
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

    private void getPaymentApi(Dialog dialog, String place_order_id, String paid, String change) {
        ProgressDialog dialog1 = new ProgressDialog(this);
        dialog1.setCancelable(false);
        dialog1.setMessage("Loading...");
        dialog1.show();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<PaymentResult> call = api.getPayment(place_order_id, pref.getCounterId(),"", c1, paymentId_str, "", "", "", paid, change);

        call.enqueue(new Callback<PaymentResult>() {
            @Override
            public void onResponse(Call<PaymentResult> call, Response<PaymentResult> response) {
                dialog1.dismiss();
                try {
                    if (response.body().getStatus().equals("true")) {
                        dialog.dismiss();
                        // getCounterPrint(place_order_id);
                        getCategoryList("0");
                        getCompleteDialog();
                        //  getNextPage(place_order_id);
                        //getBillingGenarate(place_order_id);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //  Toast.makeText(Menu_Activity.this, e.toString() + " exeception", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PaymentResult> call, Throwable t) {
                //   Toast.makeText(Menu_Activity.this, t.toString() + "", Toast.LENGTH_SHORT).show();
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
                    //   Toast.makeText(Menu_Activity.this, response+"", Toast.LENGTH_SHORT).show();
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

                        Intent i = new Intent(Menu_Activity.this, TestService.class);
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
                // Toast.makeText(Menu_Activity.this, t.toString() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCompleteDialog() {
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
                viewModel.deleteall(1);
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
                //  dialog.dismiss();
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

    private void getCategoryList(String number) {
//        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<FoodList> call = api.getfood(number);

        call.enqueue(new Callback<FoodList>() {
            @Override
            public void onResponse(Call<FoodList> call, Response<FoodList> response) {
                // progressDialog.dismiss();
                foodlistarray.removeAll(foodlistarray);
                try {
                    if (response.body().getStatus().equals("true")) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            String ProductsId = response.body().getData().get(i).getProductsId();
                            String CategoryId = response.body().getData().get(i).getCategoryId();
                            String ProductName = response.body().getData().get(i).getProductName();
                            String tamilname = response.body().getData().get(i).getTamilname();
                            String ProductImage = response.body().getData().get(i).getProductImage();
                            String varientId = response.body().getData().get(i).getVarientId();
                            String vatCharge = response.body().getData().get(i).getVatCharge();
                            String varientName = response.body().getData().get(i).getVarientName();
                            String price = response.body().getData().get(i).getPrice();
                            String kitchenId = response.body().getData().get(i).getKitchenId();
                            String kitchenName = response.body().getData().get(i).getKitchenName();
                            String kitchenIpAdrees = response.body().getData().get(i).getKitchenIpAdrees();
                            String kitchenPort = response.body().getData().get(i).getKitchenPort();
                            ArrayList<Addons> addonsarray = response.body().getData().get(i).getAddonsarray();

                            foodlistarray.add(new foodlist_data(ProductsId, CategoryId, ProductName, tamilname, ProductImage,
                                    varientId, vatCharge, varientName, price, kitchenId, kitchenName, kitchenIpAdrees,
                                    kitchenPort, addonsarray));

                            foodlistAdapter = new FoodListAdapter(Menu_Activity.this, foodlistarray);
                            recycler.setAdapter(foodlistAdapter);

                        }

                        foodlistAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<FoodList> call, Throwable t) {

                //progressDialog.dismiss();
            }
        });


    }

    private void getCategory() {
//        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<Category> call = api.getcategory();

        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                cate_array.removeAll(cate_array);
                //  progressDialog.dismiss();

                try {

                    if (response.body().getStatus().equals("true")) {

                        for (int i = 0; i < response.body().getData().size(); i++) {

                            String categoryId = response.body().getData().get(i).getCategoryId();
                            String CategoryName = response.body().getData().get(i).getCategoryName();
                            String tamilname = response.body().getData().get(i).getTamilname();
                            String CategoryImage = response.body().getData().get(i).getCategoryImage();

                            cate_array.add(new category_data(categoryId, CategoryName, tamilname, CategoryImage));

                            categoryAdapter = new CategoryAdapter(Menu_Activity.this, cate_array);
                            category_recycler.setAdapter(categoryAdapter);

                            categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
                                @Override
                                public void OnItemClick(View view, String CategoryName, String categoryId, String CategoryImage, int position) {
                                    categoryid_str = categoryId;
                                    update_category_id = categoryId;
                                    getCategoryList(categoryId);
                                }
                            });
                        }
                        categoryAdapter.notifyDataSetChanged();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                //Toast.makeText(Menu_Activity.this, t.toString()+"", Toast.LENGTH_SHORT).show();
                //  progressDialog.dismiss();
                Log.i("throw", "Hello " + t.toString());

            }
        });
    }

    @Override
    public void addCart(String productsId, String categoryId, String productName, String productImage, String varientId, String vatCharge, String varientName, String price, String kitchenId, String kitchenName, String kitchenIpAdrees, String kitchenPort, ArrayList<Addons> addons) {
//        getAddtocart(productsId,
//                categoryId,
//                productName,
//                productImage,
//                varientId,
//                vatCharge,
//                varientName,
//                price,
//                kitchenId,
//                kitchenName,
//                kitchenIpAdrees,
//                kitchenPort,
//                addons);
        final CartTable cartTable;
        if (qtycartTableArrayList.size() > 0) {
            for (int i = 0; i < qtycartTableArrayList.size(); i++) {
                if (qtycartTableArrayList.get(i).getProductsId().equals(productsId)) {
                    cart_qty = qtycartTableArrayList.get(i).getQty();
                    cart_addons_status = qtycartTableArrayList.get(i).getAddons_status();
                    if (qtycartTableArrayList.get(i).getAddons_status().equals("1")) {
                        for (int j = 0; j < qtycartTableArrayList.get(i).getAddons().size(); j++) {
                            cart_addons_qty = qtycartTableArrayList.get(i).getAddons().get(j).getAddons_qty();
                        }
                    }
                    update_qty = true;
                }
            }
        }

        if (update_qty) {

            if (cart_addons_status.equals("0")) {
                if (isCheck) {
                    int qty = Integer.parseInt("1");

                    viewModel.updateQuantity(Integer.parseInt(productsId), qty + Integer.parseInt(cart_qty));
                    //addons_status update
                    //addons Arraylist update
                    RoomDB db = Room.databaseBuilder(getApplicationContext(), RoomDB.class, "add_cart").
                            allowMainThreadQueries().build();

                    DAO dao = db.dao();

                    ArrayList<Add_ons_cart> addOnsCarts = new ArrayList<>();


                    for (int i = 0; i < addOnsCartArrayList.size(); i++) {
                        String addons_id = addOnsCartArrayList.get(i).getAddons_id();
                        String addons_name = addOnsCartArrayList.get(i).getAddons_name();
                        String addons_price = addOnsCartArrayList.get(i).getAddons_price();
                        String addons_qty = addOnsCartArrayList.get(i).getAddons_qty();

                        addOnsCarts.add(new Add_ons_cart(addons_id, addons_name, addons_price, addons_qty));
                    }

                    dao.updateAddonsQuantity(addOnsCarts, "1", productsId);
                    //     Toast.makeText(Menu_Activity.this, "update successfully", Toast.LENGTH_SHORT).show();
                    isCheck = false;
                } else {
                    int qty = Integer.parseInt("1");

                    viewModel.updateQuantity(Integer.parseInt(productsId), qty + Integer.parseInt(cart_qty));

                    int qtys = qty + Integer.parseInt(cart_qty);

                    qtycartTableArrayList.add(new EmptyCartTable(productsId,
                            categoryId,
                            productName,
                            productImage,
                            varientId,
                            vatCharge,
                            varientName,
                            price,
                            kitchenId,
                            kitchenName,
                            kitchenIpAdrees,
                            kitchenPort,
                            addOnsCartArrayList,
                            String.valueOf(qtys),
                            "",
                            "1", "0", "1"));
                    isCheck = false;
                    //  Toast.makeText(Menu_Activity.this, "update successfully", Toast.LENGTH_SHORT).show();
                }
            } else {
                //qty update
                //addons_qty update
                int qty = Integer.parseInt("1");

                viewModel.updateQuantity(Integer.parseInt(productsId), qty + Integer.parseInt(cart_qty));

                RoomDB db = Room.databaseBuilder(getApplicationContext(), RoomDB.class, "add_cart").
                        allowMainThreadQueries().build();

                DAO dao = db.dao();

                ArrayList<Add_ons_cart> addOnsCarts = new ArrayList<>();


                for (int i = 0; i < addOnsCartArrayList.size(); i++) {
                    String addons_id = addOnsCartArrayList.get(i).getAddons_id();
                    String addons_name = addOnsCartArrayList.get(i).getAddons_name();
                    String addons_price = addOnsCartArrayList.get(i).getAddons_price();
                    String addons_qty = addOnsCartArrayList.get(i).getAddons_qty();

                    int addons_int = (Integer.parseInt(cart_addons_qty) + Integer.parseInt(addons_qty));

                    String update_addons_qty = String.valueOf(addons_int);

                    addOnsCarts.add(new Add_ons_cart(addons_id, addons_name, addons_price, update_addons_qty));
                }

                dao.updateAddonsQuantitys(addOnsCarts, productsId);
                // Toast.makeText(Menu_Activity.this, "update successfully", Toast.LENGTH_SHORT).show();
                isCheck = false;
            }
            update_qty = false;
        } else {
            if (isCheck) {
                cartTable = new CartTable(productsId,
                        categoryId,
                        productName,
                        productImage,
                        varientId,
                        vatCharge,
                        varientName,
                        price,
                        kitchenId,
                        kitchenName,
                        kitchenIpAdrees,
                        kitchenPort,
                        addOnsCartArrayList,
                        "1",
                        "",
                        "1", "1", "1");
                isCheck = false;
                update_qty = false;
            } else {
                addOnsCartArrayList = new ArrayList<>();

                cartTable = new CartTable(productsId,
                        categoryId,
                        productName,
                        productImage,
                        varientId,
                        vatCharge,
                        varientName,
                        price,
                        kitchenId,
                        kitchenName,
                        kitchenIpAdrees,
                        kitchenPort,
                        addOnsCartArrayList,
                        "1",
                        "",
                        "1", "0", "1");
                isCheck = false;
                update_qty = false;

                qtycartTableArrayList.add(new EmptyCartTable(productsId,
                        categoryId,
                        productName,
                        productImage,
                        varientId,
                        vatCharge,
                        varientName,
                        price,
                        kitchenId,
                        kitchenName,
                        kitchenIpAdrees,
                        kitchenPort,
                        addOnsCartArrayList,
                        "1",
                        "",
                        "1", "0", "1"));

                // Toast.makeText(Menu_Activity.this, "Add product successfully", Toast.LENGTH_SHORT).show();
            }
            viewModel.insert(cartTable);
        }


    }

    @Override
    public void addOneQuantity(String product_id) {
        viewModel.addOnesItem(product_id);

        viewModel.getOneCartItem(product_id).observe(Menu_Activity.this, new Observer<List<CartTable>>() {
            @Override
            public void onChanged(List<CartTable> cartTables) {
                if (cartTables.size() > 0) {
                    for (int i = 0; i < cartTables.size(); i++) {
                        if (cartTables.get(i).getProductsId().toString().equals(product_id)) {
                            String productsId = cartTables.get(i).getProductsId();
                            String categoryId = cartTables.get(i).getCategoryId();
                            String productName = cartTables.get(i).getProductName();
                            String productImage = cartTables.get(i).getProductImage();
                            String varientId = cartTables.get(i).getVarientId();
                            String vatCharge = cartTables.get(i).getVatCharge();
                            String varientName = cartTables.get(i).getVarientName();
                            String price = cartTables.get(i).getPrice();
                            String kitchenId = cartTables.get(i).getKitchenId();
                            String kitchenName = cartTables.get(i).getKitchenName();
                            String kitchenIpAdrees = cartTables.get(i).getKitchenIpAdrees();
                            String kitchenPort = cartTables.get(i).getKitchenPort();
                            ArrayList<Add_ons_cart> addons = cartTables.get(i).getAddons();
                            String qty = cartTables.get(i).getQty();
                            String notes = cartTables.get(i).getNotes();
                            String status = cartTables.get(i).getStatus();
                            String addons_status = cartTables.get(i).getAddons_status();
                            String uid = cartTables.get(i).getUid();

                            qtycartTableArrayList.add(new EmptyCartTable(productsId,
                                    categoryId,
                                    productName,
                                    productImage,
                                    varientId,
                                    vatCharge,
                                    varientName,
                                    price,
                                    kitchenId,
                                    kitchenName,
                                    kitchenIpAdrees,
                                    kitchenPort,
                                    addons,
                                    qty,
                                    notes,
                                    status, addons_status, uid));

                            if (cartTables.get(i).getAddons_status().equals("1")) {
                                for (int j = 0; j < cartTables.get(i).getAddons().size(); j++) {
                                    cart_addons_qty = cartTables.get(i).getAddons().get(j).getAddons_qty();
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void reduceOneQuantity(String product_id) {
        viewModel.reducesItem(product_id);
    }

    @Override
    public void deleteSingleItem(String product_id) {
        viewModel.deletesingaldata(product_id);

        viewModel.getTb_cart().observe(this, new Observer<List<CartTable>>() {
            @Override
            public void onChanged(List<CartTable> cartTables) {

                qtycartTableArrayList.removeAll(qtycartTableArrayList);

                for (int i = 0; i < cartTables.size(); i++) {
                    String productsId = cartTables.get(i).getProductsId();
                    String categoryId = cartTables.get(i).getCategoryId();
                    String productName = cartTables.get(i).getProductName();
                    String productImage = cartTables.get(i).getProductImage();
                    String varientId = cartTables.get(i).getVarientId();
                    String vatCharge = cartTables.get(i).getVatCharge();
                    String varientName = cartTables.get(i).getVarientName();
                    String price = cartTables.get(i).getPrice();
                    String kitchenId = cartTables.get(i).getKitchenId();
                    String kitchenName = cartTables.get(i).getKitchenName();
                    String kitchenIpAdrees = cartTables.get(i).getKitchenIpAdrees();
                    String kitchenPort = cartTables.get(i).getKitchenPort();
                    ArrayList<Add_ons_cart> addons = cartTables.get(i).getAddons();
                    String qty = cartTables.get(i).getQty();
                    String notes = cartTables.get(i).getNotes();
                    String status = cartTables.get(i).getStatus();
                    String addons_status = cartTables.get(i).getAddons_status();
                    String uid = cartTables.get(i).getUid();

                    qtycartTableArrayList.add(new EmptyCartTable(productsId,
                            categoryId,
                            productName,
                            productImage,
                            varientId,
                            vatCharge,
                            varientName,
                            price,
                            kitchenId,
                            kitchenName,
                            kitchenIpAdrees,
                            kitchenPort,
                            addons,
                            qty,
                            notes,
                            status, addons_status, uid));
                }
            }
        });
    }

    @Override
    public void UpdateNotes(String product_id, String notes) {
        getUpdateNotes(product_id, notes);
    }

    @Override
    public void UpdateOrderNotes(String notes) {
        Dialog dialog = new Dialog(Menu_Activity.this);
        dialog.setContentView(R.layout.notes_layout);
        dialog.getWindow().setLayout(700, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        EditText edit_notes = (EditText) dialog.findViewById(R.id.notes);
        AppCompatButton update_notes = (AppCompatButton) dialog.findViewById(R.id.update_note);
        ImageView back = (ImageView) dialog.findViewById(R.id.back);

        edit_notes.setText(notes);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        update_notes.setVisibility(View.GONE);
    }

    private void getUpdateNotes(String product_id, String notes) {

        Dialog dialog = new Dialog(Menu_Activity.this);
        dialog.setContentView(R.layout.notes_layout);
        dialog.getWindow().setLayout(700, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        EditText edit_notes = (EditText) dialog.findViewById(R.id.notes);
        AppCompatButton update_notes = (AppCompatButton) dialog.findViewById(R.id.update_note);
        ImageView back = (ImageView) dialog.findViewById(R.id.back);

        edit_notes.setText(notes);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        update_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.UpdateNotes(edit_notes.getText().toString(), product_id);
                dialog.dismiss();
            }
        });

    }

    private void getAddtocart(String productsId, String categoryId, String productName, String productImage, String varientId, String vatCharge, String varientName, String price, String kitchenId, String kitchenName, String kitchenIpAdrees, String kitchenPort, ArrayList<Addons> addons) {
        Dialog dialog = new Dialog(Menu_Activity.this);
        dialog.setContentView(R.layout.addons_layout);
        dialog.getWindow().setLayout(800, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        TextView product_name = (TextView) dialog.findViewById(R.id.product_name);
        TextView variant_name = (TextView) dialog.findViewById(R.id.variant_name);
        TextView add_ons_text = (TextView) dialog.findViewById(R.id.add_ons_text);
        TextView add_qty = (TextView) dialog.findViewById(R.id.add_qty);
        ImageView back = (ImageView) dialog.findViewById(R.id.back);
        ImageButton add_variant = (ImageButton) dialog.findViewById(R.id.add_variant);
        ImageButton reduce_variant = (ImageButton) dialog.findViewById(R.id.reduce_variant);
        AppCompatButton add_cart = (AppCompatButton) dialog.findViewById(R.id.add_cart);
        addons_recycler = (RecyclerView) dialog.findViewById(R.id.addons_recycler);
        EditText notes = (EditText) dialog.findViewById(R.id.notes);


        product_name.setText(productName);
        variant_name.setText(varientName);
        add_qty.setText("1");

        refreshCartItems(productsId);

        add_variant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_qty.setText(String.valueOf(Integer.parseInt(add_qty.getText().toString()) + 1));
            }
        });

        reduce_variant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(add_qty.getText().toString()) > 1) {
                    add_qty.setText(String.valueOf(Integer.parseInt(add_qty.getText().toString()) - 1));
                }
            }
        });

        if (addons.size() == 0) {
            addons_recycler.setVisibility(View.GONE);
            add_ons_text.setVisibility(View.GONE);
            addOnsCartArrayList = new ArrayList<>();

        } else {
            addons_recycler.setVisibility(View.VISIBLE);
            add_ons_text.setVisibility(View.VISIBLE);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            addons_recycler.setLayoutManager(layoutManager);

            addAdapter = new AddAdapter(Menu_Activity.this, addons);
            addons_recycler.setAdapter(addAdapter);
            addAdapter.setOnItemClickListener(new AddAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(View view, String addons_name, String addons_price, String addons_id, String addons_qty, boolean isChecked, int position) {

                    isCheck = isChecked;

                    addons_id_str = addons_id;
                    addons_name_str = addons_name;
                    addons_price_str = addons_price;
                    addons_qty_str = addons_qty;

                    addOnsCartArrayList.add(new Add_ons_cart(addons_id, addons_name, addons_price, addons_qty));

                }
            });
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CartTable cartTable;

                if (update_qty) {
                    if (cart_addons_status.equals("0")) {
                        if (isCheck) {
                            int qty = Integer.parseInt(add_qty.getText().toString());

                            viewModel.updateQuantity(Integer.parseInt(productsId), qty + Integer.parseInt(cart_qty));
                            //addons_status update
                            //addons Arraylist update
                            RoomDB db = Room.databaseBuilder(getApplicationContext(), RoomDB.class, "add_cart").
                                    allowMainThreadQueries().build();

                            DAO dao = db.dao();

                            ArrayList<Add_ons_cart> addOnsCarts = new ArrayList<>();


                            for (int i = 0; i < addOnsCartArrayList.size(); i++) {
                                String addons_id = addOnsCartArrayList.get(i).getAddons_id();
                                String addons_name = addOnsCartArrayList.get(i).getAddons_name();
                                String addons_price = addOnsCartArrayList.get(i).getAddons_price();
                                String addons_qty = addOnsCartArrayList.get(i).getAddons_qty();

                                addOnsCarts.add(new Add_ons_cart(addons_id, addons_name, addons_price, addons_qty));
                            }

                            dao.updateAddonsQuantity(addOnsCarts, "1", productsId);
                            //Toast.makeText(Menu_Activity.this, "update successfully", Toast.LENGTH_SHORT).show();
                            isCheck = false;
                        } else {
                            int qty = Integer.parseInt(add_qty.getText().toString());

                            viewModel.updateQuantity(Integer.parseInt(productsId), qty + Integer.parseInt(cart_qty));
                            isCheck = false;
                            // Toast.makeText(Menu_Activity.this, "update successfully", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //qty update
                        //addons_qty update
                        int qty = Integer.parseInt(add_qty.getText().toString());

                        viewModel.updateQuantity(Integer.parseInt(productsId), qty + Integer.parseInt(cart_qty));

                        RoomDB db = Room.databaseBuilder(getApplicationContext(), RoomDB.class, "add_cart").
                                allowMainThreadQueries().build();

                        DAO dao = db.dao();

                        ArrayList<Add_ons_cart> addOnsCarts = new ArrayList<>();


                        for (int i = 0; i < addOnsCartArrayList.size(); i++) {
                            String addons_id = addOnsCartArrayList.get(i).getAddons_id();
                            String addons_name = addOnsCartArrayList.get(i).getAddons_name();
                            String addons_price = addOnsCartArrayList.get(i).getAddons_price();
                            String addons_qty = addOnsCartArrayList.get(i).getAddons_qty();

                            int addons_int = (Integer.parseInt(cart_addons_qty) + Integer.parseInt(addons_qty));

                            String update_addons_qty = String.valueOf(addons_int);

                            addOnsCarts.add(new Add_ons_cart(addons_id, addons_name, addons_price, update_addons_qty));
                        }

                        dao.updateAddonsQuantitys(addOnsCarts, productsId);
                        //  Toast.makeText(Menu_Activity.this, "update successfully", Toast.LENGTH_SHORT).show();
                        isCheck = false;
                    }

                    update_qty = false;
                } else {
                    if (isCheck) {
                        cartTable = new CartTable(productsId,
                                categoryId,
                                productName,
                                productImage,
                                varientId,
                                vatCharge,
                                varientName,
                                price,
                                kitchenId,
                                kitchenName,
                                kitchenIpAdrees,
                                kitchenPort,
                                addOnsCartArrayList,
                                add_qty.getText().toString(),
                                notes.getText().toString(),
                                "1", "1", "1");
                        isCheck = false;
                        update_qty = false;
                    } else {
                        addOnsCartArrayList = new ArrayList<>();

                        cartTable = new CartTable(productsId,
                                categoryId,
                                productName,
                                productImage,
                                varientId,
                                vatCharge,
                                varientName,
                                price,
                                kitchenId,
                                kitchenName,
                                kitchenIpAdrees,
                                kitchenPort,
                                addOnsCartArrayList,
                                add_qty.getText().toString(),
                                notes.getText().toString(),
                                "1", "0", "1");
                        isCheck = false;
                        update_qty = false;
                    }
                    //   Toast.makeText(Menu_Activity.this, "Add product successfully", Toast.LENGTH_SHORT).show();
                    viewModel.insert(cartTable);
                }

                //   getCartTable();
                dialog.dismiss();
            }
        });

    }

    private void refreshCartItems(String productsid) {
        update_qty = false;
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);


        viewModel.getOneCartItem(productsid).observe(Menu_Activity.this, new Observer<List<CartTable>>() {
            @Override
            public void onChanged(List<CartTable> cartTables) {
                if (cartTables.size() > 0) {
                    for (int i = 0; i < cartTables.size(); i++) {
                        if (cartTables.get(i).getProductsId().toString().equals(productsid)) {
                            cart_qty = cartTables.get(i).getQty();
                            cart_addons_status = cartTables.get(i).getAddons_status();
                            if (cartTables.get(i).getAddons_status().equals("1")) {
                                for (int j = 0; j < cartTables.get(i).getAddons().size(); j++) {
                                    cart_addons_qty = cartTables.get(i).getAddons().get(j).getAddons_qty();
                                }
                            }
                            update_qty = true;
                        }
                    }
                }
            }
        });
    }

    private void getCartTable() {
        viewModel.getTb_cart().observe(this, new Observer<List<CartTable>>() {
            @Override
            public void onChanged(List<CartTable> cartTables) {
                cartTableArrayList.removeAll(cartTableArrayList);

                tot = 0;
                tax_flt = 0;
                product_flt = 0;
                tot_amt = 0;
                if (cartTables.size() > 0) {
                    addcart_recycler.setVisibility(View.VISIBLE);
                    for (int i = 0; i < cartTables.size(); i++) {

                        String productsId = cartTables.get(i).getProductsId();
                        String categoryId = cartTables.get(i).getCategoryId();
                        String productName = cartTables.get(i).getProductName();
                        String productImage = cartTables.get(i).getProductImage();
                        String varientId = cartTables.get(i).getVarientId();
                        String vatCharge = cartTables.get(i).getVatCharge();
                        String varientName = cartTables.get(i).getVarientName();
                        String price = cartTables.get(i).getPrice();
                        String kitchenId = cartTables.get(i).getKitchenId();
                        String kitchenName = cartTables.get(i).getKitchenName();
                        String kitchenIpAdrees = cartTables.get(i).getKitchenIpAdrees();
                        String kitchenPort = cartTables.get(i).getKitchenPort();
                        ArrayList<Add_ons_cart> addons = cartTables.get(i).getAddons();
                        String qty = cartTables.get(i).getQty();
                        String notes = cartTables.get(i).getNotes();
                        String status = cartTables.get(i).getStatus();
                        String addons_status = cartTables.get(i).getAddons_status();
                        String uid = cartTables.get(i).getUid();


                        cartTableArrayList.add(new CartTable(productsId,
                                categoryId,
                                productName,
                                productImage,
                                varientId,
                                vatCharge,
                                varientName,
                                price,
                                kitchenId,
                                kitchenName,
                                kitchenIpAdrees,
                                kitchenPort,
                                addons,
                                qty,
                                notes,
                                status,
                                addons_status,
                                uid));

                        addons_tot = 0;

                        if (addons.size() > 0) {
                            for (int j = 0; j < addons.size(); j++) {
                                String a_price = addons.get(j).getAddons_price();
                                String a_qty = addons.get(j).getAddons_qty();

                                float c = Float.parseFloat(a_price);
                                float d = Float.parseFloat(a_qty);
                                addons_tot = c * d;
                            }
                        }

                        float a = Float.parseFloat(qty);
                        float b = Float.parseFloat(price);
                        float e = Float.parseFloat(vatCharge);
                        float f = b * a;
                        float g = e * a;
                        c = f + g;
                        tax_flt = tax_flt + g;
                        product_flt = product_flt + f;

                        tot = tot + c + addons_tot;
                    }

                    if (update_order_id.equals("")) {
                        String size = String.valueOf(cartTables.size());
                        order_count.setText("Order Details - " + size);
                    } else {
                        String update_size = String.valueOf(cartTables.size() + update_count);
                        order_count.setText("Update Order Details - " + update_size);
                    }

                    addCartAdapter.notifyDataSetChanged();
                    float tot_tax = Float.parseFloat(total_vat_str);
                    float grand_tax = tot_tax + tax_flt;
                    tax_str = String.format("%.2f", grand_tax);
                    product_str = String.format("%.2f", product_flt);

                    tax.setText(tax_str);
                    if (typeName_str.equals("Amount")) {
                        float service_change = Float.parseFloat(servicecharge_str);
                        float amt = tot + service_change;

                        tot_amt = Float.parseFloat(total_amt_str);
                        float grand_amt = tot_amt + amt;
                        c1 = String.format("%.2f", grand_amt);
                        // String price = NumberFormat.getIntegerInstance().format(tot);
                        total.setText("RM : " + c1);
                        service.setText(servicecharge_str);
                    } else {
                        String percent = "100.00";
                        float service_change = Float.parseFloat(servicecharge_str);
                        float service1 = service_change / Float.parseFloat(percent);
                        float service2 = service1 * tot;

                        float service3 = service2 + tot;

                        tot_amt = Float.parseFloat(total_amt_str);
                        float grand_amt = tot_amt + service3;

                        String service_str = String.format("%.2f", service2);
                        c1 = String.format("%.2f", grand_amt);
                        service.setText(service_str);
                        // String price = NumberFormat.getIntegerInstance().format(tot);
                        total.setText("RM : " + c1);

                    }

                } else {

                    addcart_recycler.setVisibility(View.GONE);
                    total.setText("RM 0.00");
                    tax.setText("RM 0.00");
                    service.setText("0");
                    order_count.setText("Order Details");
                }
            }
        });
    }

    private void getPlaceOrderApi(String customertype_id, String tableid_str, String seat_str, String paid_str, String bal_str, PosPrinterDev.PrinterInfo printer) {

        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        PlaceOrderList placeOrderList = new PlaceOrderList(customer_Id, customertype_id, employee_Id, pref.getCounterId(), c1, paymentId_str, "", "", "", paid_str, bal_str, tableid_str, c1, product_str, tax_str,
                seat_str , placeOrderFoodArrayList);

        Call<PlaceOrderResult> call = api.getPlaceOrder(placeOrderList);

        call.enqueue(new Callback<PlaceOrderResult>() {
            @Override
            public void onResponse(Call<PlaceOrderResult> call, Response<PlaceOrderResult> response) {
                Log.d("Place_order", "Response : " + response.body().getPrinterlist());

                try {

                    if (response.body().getStatus().equals("true")) {
                        if (response.body().getOrderid().equals("")) {
                            for (int j = 0; j < myBinder.GetPrinterInfoList().size(); j++) {
                                myBinder.RemovePrinter(myBinder.GetPrinterInfoList().get(j).printerName, new TaskCallback() {
                                    @Override
                                    public void OnSucceed() {
                                        // Toast.makeText(Menu_Activity.this, "Removed", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void OnFailed() {

                                    }
                                });

                            }
                            ErrorDialog();
                        } else {
                            viewModel.deleteall(1);
                            qtycartTableArrayList.removeAll(qtycartTableArrayList);
                            cardListArrayList.removeAll(cartTableArrayList);
                            place_order_id = response.body().getOrderid();
                            pref.setCustomer_email("");
                            pref.setCustomer_name("");
                            pref.setCustomer_mobile("");
                            view_order.setVisibility(View.GONE);
                            customer_type.setText(customer_type_str);
                            customer_name.setText("");
                            waiter_name.setText("");
                            table_no.setText("");
                            total.setText("RM 0");
                            tax.setText("RM 0");
                            service.setText("0");
                            employee_Id = "0";
                            paymentId_str = "4";
                            paymentType_str = "Cash Payment";
                            person.setVisibility(View.GONE);
                            table.setVisibility(View.GONE);
                            self_service_layout.setVisibility(View.GONE);

                            CounterDetails counterDetails = new CounterDetails("", "", "", "", "", "", "", "", "", "",
                                    "", "", "", "", "", "", null, "", "",
                                    "", "", "", "", "", "", "", "", "", "");
                            if (customertype_id.equals("101")) {
                                progressBar.setVisibility(View.GONE);
                                getCategoryList("0");
                                getCompleteDialog();
                                getNextPage(place_order_id);

                            } else if (customertype_id.equals("4")) {

                                getCategoryList("0");
                                getCompleteDialog();
                                getNextPage(place_order_id);
                                ArrayList<Print_Details> printer_list = response.body().getPrinterlist();
                                Log.e("PRINT_DATA", printer_list.toString());
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        getPLacedDialog(place_order_id, counterDetails, printer, printer_list);
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }, 8000);

                            } else {
                                //  Toast.makeText(Menu_Activity.this, "yes1", Toast.LENGTH_SHORT).show();
                                getCategoryList("0");
                                total_amt_str = "0.0";
                                total_vat_str = "0.0";
                                c1 = "";
                                ArrayList<Print_Details> printer_list = response.body().getPrinterlist();
                               // Log.e("PRINT_DATA", String.valueOf(printer_list.length()));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        getPLacedDialog(place_order_id, counterDetails, printer, printer_list);
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }, 8000);
                            }
                        }
                    }
                } catch (Exception e) {
                    Log.d("Place_order", "Place_order Error : " + e.toString());
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(Call<PlaceOrderResult> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.d("Place_order", "Place_order Errrrr : " + t.toString());
            }
        });

    }

    private void getPLacedDialog(String place_order_id, CounterDetails counterDetails, PosPrinterDev.PrinterInfo printer,  ArrayList<Print_Details> print_data) {
        Log.e("PRINT_DATA", print_data.toString());
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.empty_dialog);
        Window window = dialog.getWindow();
        window.setLayout(800, AbsListView.LayoutParams.WRAP_CONTENT);

        TextView text = (TextView) dialog.findViewById(R.id.text);
        ImageView image = (ImageView) dialog.findViewById(R.id.tic);
        AppCompatButton confirm = (AppCompatButton) dialog.findViewById(R.id.confirm);

        customertype_id = "101";

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                try {
                    getPrinter(place_order_id, counterDetails, print_data);
                } catch (JSONException e) {
                    //Toast.makeText(Menu_Activity.this, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                    // throw new RuntimeException(e);
                }
            }
        });

        dialog.show();
    }

    private void getUpdateOrderApi(PosPrinterDev.PrinterInfo printer) {
//        ProgressDialog dialog = new ProgressDialog(this);
//        dialog.setCancelable(false);
//        dialog.setMessage("Connecting Printer...");
//        dialog.show();
        progressBar.setVisibility(View.VISIBLE);

//        viewModel.getTb_cart().observe(this, new Observer<List<CartTable>>() {
//            @Override
//            public void onChanged(List<CartTable> cartTables) {
//
//                for (int i = 0; i < cartTables.size(); i++) {
//                    String productsId = cartTables.get(i).getProductsId();
//                    String categoryId = cartTables.get(i).getCategoryId();
//                    String productName = cartTables.get(i).getProductName();
//                    String productImage = cartTables.get(i).getProductImage();
//                    String varientId = cartTables.get(i).getVarientId();
//                    String vatCharge = cartTables.get(i).getVatCharge();
//                    String varientName = cartTables.get(i).getVarientName();
//                    String price = cartTables.get(i).getPrice();
//                    String kitchenId = cartTables.get(i).getKitchenId();
//                    String kitchenName = cartTables.get(i).getKitchenName();
//                    String kitchenIpAdrees = cartTables.get(i).getKitchenIpAdrees();
//                    String kitchenPort = cartTables.get(i).getKitchenPort();
//                    ArrayList<Add_ons_cart> addons = cartTables.get(i).getAddons();
//                    String qty = cartTables.get(i).getQty();
//                    String notes = cartTables.get(i).getNotes();
//                    String status = cartTables.get(i).getStatus();
//                    String addons_status = cartTables.get(i).getAddons_status();
//                    String uid = cartTables.get(i).getUid();
//
//
//                    if (addons_status.equals("1")) {
//                        updateAddons = new ArrayList<>();
//                        for (int j = 0; j < addons.size(); j++) {
//                            String a_id = addons.get(j).getAddons_id();
//                            String a_qty = addons.get(j).getAddons_qty();
//
//                            updateAddons.add(new UpdateAddons(a_id, a_qty));
//                        }
//                    } else {
//                        updateAddons = new ArrayList<>();
//                    }
//
//                    updateFoodlists.add(new UpdateFoodlist(
//                            productsId,
//                            qty,
//                            updateAddons,
//                            varientId,
//                            price,
//                            notes
//                    ));
//                }
//            }
//        });


//        float tot_a = Float.parseFloat(total_amt_str);
//        float tot_b = Float.parseFloat(c1);
//        float tot_c = tot_a + tot_b;
//        String grand_total = String.format("%.2f", tot_c);
//
//        float tot_product1 = Float.parseFloat(total_product_str);
//        float tot_product2 = Float.parseFloat(product_str);
//        float tot_product3 = tot_product1 + tot_product2;
//        String grand_product = String.format("%.2f", tot_product3);
//
//        float tot_vat1 = Float.parseFloat(total_vat_str);
//        float tot_vat2 = Float.parseFloat(tax_str);
//        float tot_vat3 = tot_vat1 + tot_vat2;
//        String grand_vat = String.format("%.2f", tot_vat3);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        UpdateOrderList updateOrderList = new UpdateOrderList(order_customer_id, order__id,
                order_customertype_id,
                order_waiter_id,
                pref.getCounterId(),
                order_table_id, c1, product_str, tax_str,
                order_seat_id, updateFoodlists);

        Call<UpdateOrderResult> call = api.getUpdateOrder(updateOrderList);

        call.enqueue(new Callback<UpdateOrderResult>() {
            @Override
            public void onResponse(Call<UpdateOrderResult> call, Response<UpdateOrderResult> response) {
                try {
                    if (response.body().getStatus().equals("true")) {


                        String order_id = response.body().getOrderid();
                        CounterDetails counterDetails = new CounterDetails("", "", "", "", "", "", "", "", "", "",
                                "", "", "", "", "", "", null, "", "",
                                "", "", "", "", "", "", "", "", "", "");
//                        getPrinter(order_id, counterDetails);

                        viewModel.deleteall(1);
                        qtycartTableArrayList.removeAll(qtycartTableArrayList);
                        cardListArrayList.removeAll(cartTableArrayList);
                        addcart_recycler.setVisibility(View.GONE);
                        pref.setCustomer_email("");
                        pref.setCustomer_name("");
                        pref.setCustomer_mobile("");
                        token_no.setText("");
                        order_no.setText("");
                        customer_type.setText(customer_type_str);
                        customertype_id = "101";
                        customer_name.setText("");
                        waiter_name.setText("");
                        table_no.setText("");
                        view_order.setVisibility(View.GONE);
                        order_recycler.setVisibility(View.GONE);
                        update_order.setVisibility(View.GONE);
                        reprint.setVisibility(View.GONE);
                        total.setText("RM 0.00");
                        tax.setText("RM 0.00");
                        service.setText("0");
                        employee_Id = "0";
                        c1 = "";
                        total_amt_str = "0.0";
                        total_vat_str = "0.0";
                        update_order_id = "";
                        orderid_str = "0";
                        self_service_layout.setVisibility(View.GONE);
                        waiter_layout.setVisibility(View.GONE);
                        customer_type.setVisibility(View.VISIBLE);
                        waiter_name.setVisibility(View.GONE);
                        person.setVisibility(View.GONE);
                        order_count.setText("Order Details");
                        customer_type_text.setVisibility(View.GONE);
                        waiter_name_text.setVisibility(View.GONE);
                        getCategoryList("0");
                        ArrayList<Print_Details> printer_list = response.body().getPrinterlist();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getPLacedDialog(order_id, counterDetails, printer, printer_list);
                                progressBar.setVisibility(View.GONE);
                            }
                        }, 8000);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UpdateOrderResult> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void getPrinter(String order_id, CounterDetails counterDetails,  ArrayList<Print_Details> print_array) throws JSONException {
//        JSONArray print_array = print_data;

        for (int i = 0; i < print_array.size(); i++) {
            int finalN = i;
            Print_Details printerObj = print_array.get(i);
            String print_kitchenName = printerObj.getKitchenName();
            String printer_name = printerObj.getIp();
            String printer_port = printerObj.getPort();
            String kitchen_id = printerObj.getKitchenId();
            String waiter = printerObj.getWaiter();
            String order_date = printerObj.getOrderDate();
            String tokenNo = printerObj.getTokenno();
            String customer_type_name = printerObj.getCutomertypeName();
            String sales_invoice_no = printerObj.getSaleinvoice();
            String tableName = printerObj.getTablename();
            ArrayList<Print_Items> order_items = printerObj.getOrderItems();


            myBinder.SendDataToPrinter(print_kitchenName, new TaskCallback() {
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

                    String waiter_name = "Waiter Name : " + waiter + "\r\n";
                    String token_no = "Token No  : " + tokenNo + "\r\n";
                    String date = "Date/Time : " + order_date + "\r\n\n";
                    String kitchen_name = "KOT " + print_kitchenName + "\r\n\n";
                    String customer_type = "** " + customer_type_name + " **\r\n";
                    String order_no =  "Order No :" + sales_invoice_no + "\r\n";

                    String table_name;
                    if (customer_type_name.equals("Takeaway")) {
                        table_name =  "Table No :" + "\r\n";
                    } else {
                        table_name = "Table No :" + tableName + "\r\n";
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
                    for (int k = 0; k < order_items.size(); k++) {

                        Print_Items items = order_items.get(k);
                        String productName = items.getProductName();
                        String varient_name = items.getVariantName();
                        String menu_qty = items.getMenuqty();
                        String item_notes = items.getNotes();
                        ArrayList<Print_AddonsItem> addons = items.getAddons();

                        print_productName = productName;
                        print_variantName = varient_name;
                        print_menuqty = menu_qty;

                        String product = "         " + print_menuqty + "        " + print_productName  + "\r\n";
                        list.add(product.getBytes());

                        String notes = "         " + item_notes + "\r\n";
                        list.add(notes.getBytes());

                        for (int m = 0; m < addons.size(); m++) {
                            Print_AddonsItem addonsObj = addons.get(m);
                            String a_name = "         " + addonsObj.getAddOnName() + "\r\n";
                            list.add(a_name.getBytes());
                        }
                    }
                    list.add(DataForSendToPrinterPos80.selectCutPagerModerAndCutPager(0x42, 0x66));


                    return list;
                }
            });
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < myBinder.GetPrinterInfoList().size(); j++) {
                    myBinder.RemovePrinter(myBinder.GetPrinterInfoList().get(j).printerName, new TaskCallback() {
                        @Override
                        public void OnSucceed() {
                            // Toast.makeText(Menu_Activity.this, "Removed", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void OnFailed() {

                        }
                    });
                }
                ip_status = true;
            }
        }, 2000);

        getChangeStatus(order_id);


    }

    private void getPrinter_old(String order_id, CounterDetails counterDetails, String print_data) {
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
                            String counterPrint = counterDetails.getPrintname();

//                            Toast.makeText(Menu_Activity.this, print_kitchenName + " for", Toast.LENGTH_SHORT).show();
                            for (int j = 0; j < myBinder.GetPrinterInfoList().size(); j++) {

                                if (myBinder.GetPrinterInfoList().get(j).printerName.equals(print_kitchenName)) {
                                    //    Toast.makeText(Menu_Activity.this, myBinder.GetPrinterInfoList().get(j).printerName+" Print", Toast.LENGTH_SHORT).show();
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
                                                String waiter_name = "              Waiter Name : " + response.body().getData().get(finalN).getWaiter() + "           \r\n";
                                                String token_no = "                  Token No  : " + response.body().getData().get(finalN).getTokenno() + "           \r\n";
                                                String date = "        Date/Time : " + response.body().getData().get(finalN).getOrderDate() + "/" + response.body().getData().get(finalN).getOrderTime() + "    \r\n\n";
                                                String kitchen_name = "                KOT " + response.body().getData().get(finalN).getKitchenName() + "         \r\n\n";
                                                String customer_type = "                  ** " + response.body().getData().get(finalN).getCutomertypeName() + " **            \r\n";
                                                String order_no = "                " + "Order No :" + response.body().getData().get(finalN).getSaleinvoice() + "          \r\n";

                                                String table_name;
                                                if (response.body().getData().get(finalN).getCutomertypeName().equals("Takeaway")) {
                                                    table_name = "                " + "Table No :" + "          \r\n";
                                                } else {
                                                    table_name = "                " + "Table No :" + response.body().getData().get(finalN).getTablename() + "          \r\n";
                                                }

                                                list.add(table_name.getBytes());
                                                list.add(kitchen_name.getBytes());
                                                list.add(token_no.getBytes());
                                                list.add(customer_type.getBytes());
                                                list.add(order_no.getBytes());
                                                list.add(waiter_name.getBytes());
                                                list.add(date.getBytes());
                                                list.add("    ---------------------------------------\r\n\n".getBytes());
                                                list.add("        Qty       Item           Size      \r\n\n".getBytes());
                                                for (int k = 0; k < response.body().getData().get(finalN).getOrderItems().size(); k++) {

                                                    print_productName = response.body().getData().get(finalN).getOrderItems().get(k).getProductName();
                                                    print_variantName = response.body().getData().get(finalN).getOrderItems().get(k).getVariantName();
                                                    print_menuqty = response.body().getData().get(finalN).getOrderItems().get(k).getMenuqty();

                                                    String product = "         " + print_menuqty + "        " + print_productName + "          " + print_variantName + "    \r\n";
                                                    list.add(product.getBytes());

                                                    String notes = "         " + response.body().getData().get(finalN).getOrderItems().get(k).getNotes() + "                       \r\n";
                                                    list.add(notes.getBytes());

                                                    if (response.body().getData().get(finalN).getOrderItems().get(k).getAddons().size() > 0) {
                                                        for (int m = 0; m < response.body().getData().get(finalN).getOrderItems().get(k).getAddons().size(); m++) {
                                                            String a_name = "         " + response.body().getData().get(finalN).getOrderItems().get(k).getAddons().get(m).getAddOnName() + "                       \r\n";
                                                            list.add(a_name.getBytes());
                                                        }
                                                    }
                                                }
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

                        }

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                for (int j = 0; j < myBinder.GetPrinterInfoList().size(); j++) {
                                    myBinder.RemovePrinter(myBinder.GetPrinterInfoList().get(j).printerName, new TaskCallback() {
                                        @Override
                                        public void OnSucceed() {
                                            // Toast.makeText(Menu_Activity.this, "Removed", Toast.LENGTH_SHORT).show();

                                        }

                                        @Override
                                        public void OnFailed() {

                                        }
                                    });
                                }
                                ip_status = true;
//                                getIPAddress();
                            }
                        }, 2000);

                        getChangeStatus(order_id);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetPrint> call, Throwable t) {
                Log.d("print", "Print" + t.toString());
                // Toast.makeText(Menu_Activity.this, t.toString() + "", Toast.LENGTH_SHORT).show();
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
                //  Toast.makeText(Menu_Activity.this, t.toString() + " OrderChange", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
//        Pref pref1 = new Pref(getApplicationContext());
//

        boolean isConnected = ConnectivityReceiver.isConnected();

        if (!isConnected) {
            startActivity(new Intent(this, NoNetworkActivity.class));
        }

        if (pref.getPrint_refresh().equals("yes")) {
            clearAll();
            pref.setPrint_refresh("no");
        }

//        handler.postDelayed(runnable = new Runnable() {
//            public void run() {
//                handler.postDelayed(runnable, 1000);
//               getOngoingCount();
//              // getSelfCount();
//            }
//        }, 1000);


    }

    private void getOngoingCount() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<Ongoing_count> call = api.getOngoing_count();

        call.enqueue(new Callback<Ongoing_count>() {
            @Override
            public void onResponse(Call<Ongoing_count> call, Response<Ongoing_count> response) {
                try {
                    if (response.body().getStatus().equals("true")) {
                        //  Toast.makeText(Menu_Activity.this, response.body().getData().getOngoingcount()+"", Toast.LENGTH_SHORT).show();
                        cart_badge.setText("(" + response.body().getData().getOngoingcount() + ")");
                        cart_badge1.setText("(" + response.body().getData().getSelfandqrcount() + ")");
                        getOngoingCount();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Ongoing_count> call, Throwable t) {

            }
        });
    }

    private void clearAll(){
        qtycartTableArrayList.removeAll(qtycartTableArrayList);
        pref.setCustomer_email("");
        pref.setCustomer_name("");
        pref.setCustomer_mobile("");
        viewModel.deleteall(1);
        token_no.setText("");
        order_no.setText("");
        customer_type.setText(customer_type_str);
        customertype_id = "101";
        customer_name.setText("");
        waiter_name.setText("");
        table_no.setText("");
        order_recycler.setVisibility(View.GONE);
        update_order.setVisibility(View.GONE);
        reprint.setVisibility(View.GONE);
        total.setText("RM 0.00");
        tax.setText("RM 0.00");
        service.setText("0");
        c1 = "";
        total_amt_str = "0.0";
        total_vat_str = "0.0";
        update_order_id = "";
        employee_Id = "0";
        orderid_str = "0";
        view_order.setVisibility(View.GONE);
        waiter_layout.setVisibility(View.GONE);
        self_service_layout.setVisibility(View.GONE);
        customer_type.setVisibility(View.VISIBLE);
        waiter_name.setVisibility(View.GONE);
        person.setVisibility(View.GONE);
        order_count.setText("Order Details");
        customer_type_text.setVisibility(View.GONE);
        waiter_name_text.setVisibility(View.GONE);
        getCategoryList("0");
    }

    public void ErrorDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.update_dialog);
        Window window = dialog.getWindow();
        window.setLayout(600, AbsListView.LayoutParams.WRAP_CONTENT);

        TextView text = (TextView) dialog.findViewById(R.id.text);
        ImageView close = (ImageView) dialog.findViewById(R.id.close);
        AppCompatButton confirm = (AppCompatButton) dialog.findViewById(R.id.confirm);

        text.setText("Table Already Booked");

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
                clearAll();
            }
        });

        dialog.show();
    }

    private void getRetokenDialog(String order_id) {
        Dialog dialog = new Dialog(Menu_Activity.this);
        dialog.setContentView(R.layout.success_dialog);
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(600, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        AppCompatButton yes = (AppCompatButton) dialog.findViewById(R.id.yes);
        ImageView back = (ImageView) dialog.findViewById(R.id.back);
        TextView text = (TextView) dialog.findViewById(R.id.text);
        back.setVisibility(View.GONE);

        text.setText("KOT Reprint");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getReprinter(order_id);
                dialog.dismiss();
            }
        });
    }

    private void getReprinter(String order_id) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<GetReprint> call = api.getReprint(order_id);

        call.enqueue(new Callback<GetReprint>() {
            @Override
            public void onResponse(Call<GetReprint> call, Response<GetReprint> response) {

                try {
                    if (response.body().getStatus().equals("true")) {
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            int finalN = i;
                            print_kitchenName = response.body().getData().get(i).getKitchenName();

//                            Toast.makeText(Menu_Activity.this, print_kitchenName + " for", Toast.LENGTH_SHORT).show();
                            for (int j = 0; j < myBinder.GetPrinterInfoList().size(); j++) {

                                if (myBinder.GetPrinterInfoList().get(j).printerName.equals(print_kitchenName)) {
                                    //     Toast.makeText(Menu_Activity.this, myBinder.GetPrinterInfoList().get(j).printerName+" Print", Toast.LENGTH_SHORT).show();
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
                                                String waiter_name = "Waiter Name : " + response.body().getData().get(finalN).getWaiter()+ "\r\n";
                                                String token_no = "Token No  : " + response.body().getData().get(finalN).getTokenno() + "\r\n";
                                                String date = "Date/Time : " + response.body().getData().get(finalN).getOrderDate()+"/"+response.body().getData().get(finalN).getOrderTime() + "\r\n\n";
                                                String kitchen_name = "KOT " + print_kitchenName + "\r\n\n";
                                                String customer_type = "** " + response.body().getData().get(finalN).getCutomertypeName() + " **\r\n";
                                                String order_no =  "Order No :" + response.body().getData().get(finalN).getSaleinvoice()+ "\r\n";

                                                String table_name;
                                                if (response.body().getData().get(finalN).getCutomertypeName().equals("Takeaway")) {
                                                    table_name =  "Table No :" + "\r\n";
                                                } else {
                                                    table_name = "Table No :" + response.body().getData().get(finalN).getTablename() + "\r\n";
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
                                                for (int k = 0; k < response.body().getData().get(finalN).getOrderItems().size(); k++) {

                                                    ReprintItem items = response.body().getData().get(finalN).getOrderItems().get(k);
                                                    String productName = items.getProductName();
                                                    String varient_name = items.getVariantName();
                                                    String menu_qty = items.getMenuqty();
                                                    String item_notes = items.getNotes();
                                                    ArrayList<Reprint_AddonsItem> addons = items.getAddons();

                                                    print_productName = productName;
                                                    print_variantName = varient_name;
                                                    print_menuqty = menu_qty;

                                                    String product = "         " + print_menuqty + "        " + print_productName  + "\r\n";
                                                    list.add(product.getBytes());

                                                    String notes = "         " + item_notes + "\r\n";
                                                    list.add(notes.getBytes());

                                                    for (int m = 0; m < addons.size(); m++) {
                                                        Reprint_AddonsItem addonsObj = addons.get(m);
                                                        String a_name = "         " + addonsObj.getAddOnName() + "\r\n";
                                                        list.add(a_name.getBytes());
                                                    }
                                                }
                                                list.add(DataForSendToPrinterPos80.selectCutPagerModerAndCutPager(0x42, 0x66));
                                            }
                                            return list;
                                        }
                                    });
                                }
                            }

                        }

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                for (int j = 0; j < myBinder.GetPrinterInfoList().size(); j++) {
                                    myBinder.RemovePrinter(myBinder.GetPrinterInfoList().get(j).printerName, new TaskCallback() {
                                        @Override
                                        public void OnSucceed() {
                                            // Toast.makeText(Menu_Activity.this, "Removed", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void OnFailed() {

                                        }
                                    });

                                }
                            }
                        }, 2000);

                        clearAll();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetReprint> call, Throwable t) {
                Log.d("print", "Print" + t.toString());
                // Toast.makeText(Menu_Activity.this, t.toString() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getOverAllReport() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<OverallReports> call = api.getOverallReports();

        call.enqueue(new Callback<OverallReports>() {
            @Override
            public void onResponse(Call<OverallReports> call, Response<OverallReports> response) {
                try {
                    if (response.body().getStatus().equals("true")) {
                        for (int i = 0; i < response.body().getData().size(); i++) {

                            String storename = response.body().getData().get(i).getStorename();
                            String companylogo = response.body().getData().get(i).getCompanylogo();
                            String companyaddress1 = response.body().getData().get(i).getCompanyaddress1();
                            String companyaddress2 = response.body().getData().get(i).getCompanyaddress2();
                            String companyaddress3 = response.body().getData().get(i).getCompanyaddress3();
                            String printname = response.body().getData().get(i).getPrintname();
                            String printdate = response.body().getData().get(i).getPrintdate();
                            String timein = response.body().getData().get(i).getTimein();
                            String timeout = response.body().getData().get(i).getTimeout();
                            String bill_no = response.body().getData().get(i).getBill_no();
                            String token_no = response.body().getData().get(i).getToken_no();
                            Sales_summary sales_summary = response.body().getData().get(i).getSales_summary();
                            Collection_summary collection_summary = response.body().getData().get(i).getCollection_summary();
                            Bill_summary bill_summary = response.body().getData().get(i).getBill_summary();
                            Items_summary items_summary = response.body().getData().get(i).getItems_summary();
                            Change_due_summary change_due_summary = response.body().getData().get(i).getChange_due_summary();
                            String printeddate = response.body().getData().get(i).getPrinteddate();

                            ArrayList<Collectiondetails> collectiondetails = collection_summary.getCollectiondetails();
                            ArrayList<Fooddetails> fooddetails = items_summary.getFooddetails();

                            Gson gson = new Gson();
                            String arrayListJson = gson.toJson(collectiondetails);
                            String arrayListJson1 = gson.toJson(fooddetails);


                            Intent intent = new Intent(Menu_Activity.this, OverallbillService.class);
                            intent.putExtra("connectType", 1);
                            intent.putExtra("storename", storename);
                            intent.putExtra("companyaddress1", companyaddress1);
                            intent.putExtra("companyaddress2", companyaddress2);
                            intent.putExtra("companyaddress3", companyaddress3);
                            intent.putExtra("printname", printname);
                            intent.putExtra("printdate", printdate);
                            intent.putExtra("timein", timein);
                            intent.putExtra("timeout", timeout);
                            intent.putExtra("bill_no", bill_no);
                            intent.putExtra("token_no", token_no);
                            intent.putExtra("grossfoodamount", sales_summary.getGrossfoodamount());
                            intent.putExtra("grossfoodcount", sales_summary.getGrossfoodcount());
                            intent.putExtra("itemsdiscount", sales_summary.getItemsdiscount());
                            intent.putExtra("billdiscount", sales_summary.getBilldiscount());
                            intent.putExtra("salesreturn", sales_summary.getSalesreturn());
                            intent.putExtra("netsalesbefore", sales_summary.getNetsalesbefore());
                            intent.putExtra("servicecharge", sales_summary.getServicecharge());
                            intent.putExtra("vat", sales_summary.getVat());
                            intent.putExtra("roundind", sales_summary.getRoundind());
                            intent.putExtra("netsalesafter", sales_summary.getNetsalesafter());
                            intent.putExtra("jsonArray", arrayListJson);
                            intent.putExtra("totalcollectionamount", collection_summary.getTotalcollectionamount());
                            intent.putExtra("totalcreditcardamount", collection_summary.getTotalcreditcardamount());
                            intent.putExtra("billpaidcount", bill_summary.getBillpaidcount());
                            intent.putExtra("billpaidamount", bill_summary.getBillpaidamount());
                            intent.putExtra("billcancelcount", bill_summary.getBillcancelcount());
                            intent.putExtra("billcancelamount", bill_summary.getBillcancelamount());
                            intent.putExtra("billonholdcount", bill_summary.getBillonholdcount());
                            intent.putExtra("billonholdamount", bill_summary.getBillonholdamount());
                            intent.putExtra("billonordercount", bill_summary.getBillonordercount());
                            intent.putExtra("billonorderamount", bill_summary.getBillonorderamount());
                            intent.putExtra("totalbill", bill_summary.getTotalbill());
                            intent.putExtra("jsonArray1", arrayListJson1);
                            intent.putExtra("totalitemscount", items_summary.getTotalitemscount());
                            intent.putExtra("totalitemsamount", items_summary.getTotalitemsamount());
                            intent.putExtra("totalcustomerpaidamount", change_due_summary.getTotalcustomerpaidamount());
                            intent.putExtra("totalcustomerchangeamount", change_due_summary.getTotalcustomerchangeamount());
                            intent.putExtra("totalbillamount", change_due_summary.getTotalbillamount());
                            intent.putExtra("printeddate", printeddate);
                            startService(intent);

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<OverallReports> call, Throwable t) {

            }
        });
    }

    public class MyAsyncTasks extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();

            Api api = retrofit.create(Api.class);

            Call<OrderSummary> call = api.getOrderSummary(params[0]);

            call.enqueue(new Callback<OrderSummary>() {
                @Override
                public void onResponse(Call<OrderSummary> call, Response<OrderSummary> response) {
                    try {
                        orderFoodListArrayList.removeAll(orderFoodListArrayList);
                        if (response.body().getStatus().equals("true")) {
                            order_customer_id = response.body().getData().getCuntomerNo();
                            order__id = response.body().getData().getOrderid();
                            order_customertype_id = response.body().getData().getCutomertype();
                            order_table_id = response.body().getData().getTableId();
                            order_seat_id = response.body().getData().getPersonCapicity();
                            order_waiter_id = response.body().getData().getWaiterid();
                            order_waiter_name = response.body().getData().getWaitername();
                            total_amt_str = response.body().getData().getTotalamount();
                            total_product_str = response.body().getData().getTotalfoodamount();
                            total_vat_str = response.body().getData().getTotalvatamount();

                            customer_type.setVisibility(View.GONE);
                            waiter_name.setVisibility(View.GONE);
                            customer_type_text.setVisibility(View.VISIBLE);
                            waiter_name_text.setVisibility(View.VISIBLE);

                            if (response.body().getData().getCutomertype().equals("99")) {
                                waiter_layout.setVisibility(View.GONE);
                            } else {
                                waiter_layout.setVisibility(View.VISIBLE);
                            }

                            customer_type_text.setText(response.body().getData().getCutomertypeName());
                            customer_name.setText(response.body().getData().getCustomerName());
                            waiter_name_text.setText(response.body().getData().getWaitername());
                            table_no.setText(response.body().getData().getTableName());

                            tax.setText(total_vat_str);
                            total.setText("RM " + total_amt_str);
                            order_no.setText(response.body().getData().getSaleinvoice());
                            table.setVisibility(View.VISIBLE);
                            person.setVisibility(View.GONE);

                            token_no.setText(response.body().getData().getTokenno());

                            update_count = Integer.parseInt(response.body().getData().getFoodcount());
                            order_count.setText("Update Order Details - " + response.body().getData().getFoodcount());

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<OrderSummary> call, Throwable t) {
                    Log.d("Order_summary", t.toString());
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

        }

    }

    private void getViewOrder() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<ViewOrder> call = api.getViewOrder(orderid_str);

        call.enqueue(new Callback<ViewOrder>() {
            @Override
            public void onResponse(Call<ViewOrder> call, Response<ViewOrder> response) {
                try{
                    if (response.body().getStatus().equals("true")){
                        ArrayList<Order_FoodList> order_foodLists = response.body().getData();

                        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplicationContext());
                        order_recycler.setLayoutManager(linearLayoutManager1);

                        orderAdapter = new OrderAdapter(Menu_Activity.this, order_foodLists);
                        order_recycler.setAdapter(orderAdapter);
                    }
                    orderAdapter.notifyDataSetChanged();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ViewOrder> call, Throwable t) {

            }
        });
    }

    private void getUpdateFoodPrice(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<Food_price_update> call = api.getUpdateFoodPrice("128", "2.50");

        call.enqueue(new Callback<Food_price_update>() {
            @Override
            public void onResponse(Call<Food_price_update> call, Response<Food_price_update> response) {
                try{
                    Log.d("Success", "Food Success " + response);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Food_price_update> call, Throwable t) {
                Log.d("Failed", "Food Failed " + t.toString());
            }
        });
    }

    public class MyAsyncTasks1 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            backgroundApi();
            return null;
        }
    }

    private void backgroundApi() {
        getWaiterApi();
        getPlayer_id();
        getServiceTax();
        getOngoingCount();
        getPaymenttype();
    }
}