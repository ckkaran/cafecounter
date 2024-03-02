package com.example.foodpos_counter.Adapter;

import android.app.Activity;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodpos_counter.CompletedOrder_Activity;
import com.example.foodpos_counter.Interface.complete_inter;
import com.example.foodpos_counter.Model.TodayOrderList;
import com.example.foodpos_counter.R;

import java.util.ArrayList;

public class CompleteOrderAdapter extends RecyclerView.Adapter<CompleteOrderAdapter.ViewHolder> {
    CompletedOrder_Activity activity;
    ArrayList<TodayOrderList> todayOrderLists;

    complete_inter inter;

    public CompleteOrderAdapter(CompletedOrder_Activity activity, ArrayList<TodayOrderList> todayOrderLists) {
        this.activity = activity;
        this.todayOrderLists = todayOrderLists;
        this.inter = activity;
    }

    @NonNull
    @Override
    public CompleteOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.completeorder_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompleteOrderAdapter.ViewHolder holder, int position) {

        holder.s_no.setText(String.valueOf(position + 1));
        holder.order_date.setText(todayOrderLists.get(position).getOrderdate() +"/\n"+todayOrderLists.get(position).getOrdertime());
        holder.order_no.setText(todayOrderLists.get(position).getSaleinvoice());
        holder.table_no.setText(todayOrderLists.get(position).getTablename());
        holder.customer_type.setText(todayOrderLists.get(position).getCustomertype());
        holder.token_no.setText(todayOrderLists.get(position).getTokenno());
        holder.total.setText(todayOrderLists.get(position).getTotalamount());
        holder.waiter_name.setText(todayOrderLists.get(position).getWaiter());
        holder.payment_mode.setText(todayOrderLists.get(position).getPaymentmethod());
        holder.counter_name.setText(todayOrderLists.get(position).getCountername());


        holder.view_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inter.OrderView(todayOrderLists.get(position).getOrder_id());
            }
        });

        holder.print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inter.OrderPrint(todayOrderLists.get(position).getOrder_id());
            }
        });

        holder.token_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inter.OrderToken(todayOrderLists.get(position).getOrder_id());
            }
        });

    }

    @Override
    public int getItemCount() {
        return todayOrderLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView s_no, order_no, token_no, customer_type, waiter_name, table_no, order_date, total, payment_mode, counter_name;
        ImageView token_print, view_details, print;

        LinearLayout confirm_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            s_no = (TextView) itemView.findViewById(R.id.s_no);
            order_no = (TextView) itemView.findViewById(R.id.order_no);
            token_no = (TextView) itemView.findViewById(R.id.token_no);
            customer_type = (TextView) itemView.findViewById(R.id.customer_type);
            waiter_name = (TextView) itemView.findViewById(R.id.waiter_name);
            table_no = (TextView) itemView.findViewById(R.id.table_no);
            order_date = (TextView) itemView.findViewById(R.id.date);
            total = (TextView) itemView.findViewById(R.id.total);
            payment_mode = (TextView) itemView.findViewById(R.id.payment_mode);
            counter_name = (TextView) itemView.findViewById(R.id.counter_name);
            token_print = (ImageView) itemView.findViewById(R.id.token_print);
            view_details = (ImageView) itemView.findViewById(R.id.view_details);
            print = (ImageView) itemView.findViewById(R.id.print);

        }
    }
}
