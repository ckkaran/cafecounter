package com.example.foodpos_counter.Api;

import com.example.foodpos_counter.Model.AvailableTable;
import com.example.foodpos_counter.Model.CancelOrder;
import com.example.foodpos_counter.Model.Category;
import com.example.foodpos_counter.Model.ChangeOrderStatus;
import com.example.foodpos_counter.Model.CounterWise;
import com.example.foodpos_counter.Model.Customer;
import com.example.foodpos_counter.Model.Customer_type;
import com.example.foodpos_counter.Model.Delete_items;
import com.example.foodpos_counter.Model.FoodList;
import com.example.foodpos_counter.Model.FoodWise;
import com.example.foodpos_counter.Model.Food_price_update;
import com.example.foodpos_counter.Model.GetBank;
import com.example.foodpos_counter.Model.GetCard;
import com.example.foodpos_counter.Model.GetCounterPrint;
import com.example.foodpos_counter.Model.GetCustomer;
import com.example.foodpos_counter.Model.GetOngoingList;
import com.example.foodpos_counter.Model.GetPayment;
import com.example.foodpos_counter.Model.GetPrint;
import com.example.foodpos_counter.Model.GetReprint;
import com.example.foodpos_counter.Model.GetSelfkiosk;
import com.example.foodpos_counter.Model.GetTable;
import com.example.foodpos_counter.Model.GetUnpaid_bill;
import com.example.foodpos_counter.Model.GetWaiter;
import com.example.foodpos_counter.Model.InactiveFoods;
import com.example.foodpos_counter.Model.Kitchen_List;
import com.example.foodpos_counter.Model.LogEntry;
import com.example.foodpos_counter.Model.Ongoing_count;
import com.example.foodpos_counter.Model.OrderAccept;
import com.example.foodpos_counter.Model.OrderSummary;
import com.example.foodpos_counter.Model.OverallReports;
import com.example.foodpos_counter.Model.PaymentResult;
import com.example.foodpos_counter.Model.PaymentWise;
import com.example.foodpos_counter.Model.PlaceOrderList;
import com.example.foodpos_counter.Model.PlaceOrderResult;
import com.example.foodpos_counter.Model.Player_ids;
import com.example.foodpos_counter.Model.SelfKisok;
import com.example.foodpos_counter.Model.SelfKisok_count;
import com.example.foodpos_counter.Model.Service_Tax;
import com.example.foodpos_counter.Model.TodayOrder;
import com.example.foodpos_counter.Model.TodayOrderView;
import com.example.foodpos_counter.Model.UpdateOrderList;
import com.example.foodpos_counter.Model.UpdateOrderResult;
import com.example.foodpos_counter.Model.ViewOrder;
import com.example.foodpos_counter.Model.login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    //String BASE_URL = "https://restaurant.dgsailor.com/v1/Counter/";
    String BASE_URL = "https://dhivyascafe.com.my/v1/Counter/";


    @POST("login")
    @FormUrlEncoded
    Call<login> getlogin(@Field("email") String email,
                         @Field("password") String password);

    @GET("get_kitchen_list")
    Call<Kitchen_List> getlist();

    @GET("get_category")
    Call<Category> getcategory();

    @GET("get_service_charge")
    Call<Service_Tax> getService();

    @POST("get_food_list")
    @FormUrlEncoded
    Call<FoodList> getfood(@Field("categoryId") String categoryId);

    @GET("get_table_list")
    Call<GetTable> getTableInfo();

    @GET("get_customer_type")
    Call<Customer_type> getCustomerType();

    @GET("get_customer")
    Call<GetCustomer> getCustomer();

    @GET("get_waiter")
    Call<GetWaiter> getWaiter();

    @POST("place_order")
    Call<PlaceOrderResult> getPlaceOrder(@Body PlaceOrderList placeOrderList);

    @POST("get_order_summary")
    @FormUrlEncoded
    Call<OrderSummary> getOrderSummary(@Field("orderid") String orderid);

    @POST("update_order")
    Call<UpdateOrderResult> getUpdateOrder(@Body UpdateOrderList updateOrderList);

    @POST("store_customer")
    @FormUrlEncoded
    Call<Customer> getAddcustomer(@Field("customername") String customername,
                                  @Field("email") String email,
                                  @Field("mobilenumber") String mobilenumber);


    @GET("get_payment_type")
    Call<GetPayment> getPaymentType();

    @GET("get_card_terminal")
    Call<GetCard> getCard();

    @GET("get_bank_list")
    Call<GetBank> getBank();

    @POST("payment")
    @FormUrlEncoded
    Call<PaymentResult> getPayment(@Field("orderid") String orderid,
                                   @Field("counterid") String counterid,
                                   @Field("discount") String discount,
                                   @Field("grandtotal") String grandtotal,
                                   @Field("paymenttype") String paymenttype,
                                   @Field("card_terminal") String card_terminal,
                                   @Field("bank") String bank,
                                   @Field("last4digit") String last4digit,
                                   @Field("customer_paid_amount") String customer_paid_amount,
                                   @Field("change_due") String change_due);

    @POST("get_print")
    @FormUrlEncoded
    Call<GetPrint> getPrint(@Field("orderid") String orderid);

    @POST("get_counter_print")
    @FormUrlEncoded
    Call<GetCounterPrint> getCounterPrint(@Field("orderid") String orderid);

    @POST("change_order_items_status")
    @FormUrlEncoded
    Call<ChangeOrderStatus> getOrderChange(@Field("orderid") String orderid);

    @GET("get_ongoing_list")
    Call<GetOngoingList> getOngoingList();

    @POST("cancel_order")
    @FormUrlEncoded
    Call<CancelOrder> getCancel(@Field("orderid") String orderid,
                                @Field("reason") String reason);

    @GET("get_self_kiosk_list")
    Call<GetSelfkiosk> getSelfkiosk();

    @POST("order_accept")
    @FormUrlEncoded
    Call<OrderAccept> getSelfAccept(@Field("orderid") String orderid,
                                    @Field("tableid") String tableid,
                                    @Field("cutomerid") String cutomerid,
                                    @Field("waiterid") String waiterid);

    @GET("get_completed_order")
    Call<TodayOrder> getTodayOrder();

    @POST("get_order_details")
    @FormUrlEncoded
    Call<TodayOrderView> getTodayOrderView(@Field("orderid") String orderid);

    @GET("avabile_table_list")
    Call<AvailableTable> getAvailableTable();

    @POST("get_reprint")
    @FormUrlEncoded
    Call<GetReprint> getReprint(@Field("orderid") String orderid);

    @POST("get_inactive_foods")
    @FormUrlEncoded
    Call<InactiveFoods> getInactiveFood(@Field("categoryId") String categoryId);

    @POST("get_payment_type_wise_reports")
    @FormUrlEncoded
    Call<PaymentWise> getPaymentWiseReport(@Field("from_date") String from_date,
                                           @Field("to_date") String to_date,
                                           @Field("counterid") String counterid,
                                           @Field("paytype") String paytype);

    @POST("get_counter_wise_reports")
    @FormUrlEncoded
    Call<CounterWise> getCounterWiseReport(@Field("from_date") String from_date,
                                           @Field("to_date") String to_date,
                                           @Field("counterid") String counterid);

    @POST("get_food_wise_reports")
    @FormUrlEncoded
    Call<FoodWise> getFoodWiseReport(@Field("from_date") String from_date,
                                     @Field("to_date") String to_date,
                                     @Field("counterid") String counterid);


    @POST("unpaid_bill_print")
    @FormUrlEncoded
    Call<GetUnpaid_bill> getUnpaidBill(@Field("orderid") String orderid);

    @POST("delete_items")
    @FormUrlEncoded
    Call<Delete_items> getDeleteItem(@Field("rowid") String rowid);


    @GET("login_entry")
    Call<LogEntry> getEntry();

    @GET("get_overall_sales_reports")
    Call<OverallReports> getOverallReports();

    @GET("get_ongoing_count")
    Call<Ongoing_count> getOngoing_count();

    @GET("get_selfkiosk_and_qr_count")
    Call<SelfKisok_count> getSelf_count();

    @POST("store_player_ids")
    @FormUrlEncoded
    Call<Player_ids> getPlayerId(@Field("player_id") String player_id);

    @POST("get_exiting_order_items")
    @FormUrlEncoded
    Call<ViewOrder> getViewOrder(@Field("orderid") String orderid);

    @POST("food_price_update")
    @FormUrlEncoded
    Call<Food_price_update> getUpdateFoodPrice(@Field("foodid") String foodid,
                                               @Field("foodamount") String foodamount);


}
