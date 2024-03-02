package com.example.foodpos_counter.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.foodpos_counter.Interface.Self_inter;
import com.example.foodpos_counter.Menu_Activity;
import com.example.foodpos_counter.Model.SelfKisok;
import com.example.foodpos_counter.R;
import com.example.foodpos_counter.SelfKiosk_Activity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SelfKioskAdapter extends RecyclerView.Adapter<SelfKioskAdapter.ViewHolder> {
    SelfKiosk_Activity activity;
    ArrayList<SelfKisok> selfKisokArrayList;
    Self_inter inter;


    public SelfKioskAdapter(SelfKiosk_Activity activity, ArrayList<SelfKisok> selfKisokArrayList) {
        this.activity = activity;
        this.selfKisokArrayList = selfKisokArrayList;
        this.inter = activity;

    }

    @NonNull
    @Override
    public SelfKioskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.kiosk_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelfKioskAdapter.ViewHolder holder, int position) {


        holder.date.setText(selfKisokArrayList.get(position).getOrderdate());
        holder.time.setText(selfKisokArrayList.get(position).getOrdertime());
        holder.customer_type.setText(selfKisokArrayList.get(position).getCustomertype());
        holder.customer_name.setText(selfKisokArrayList.get(position).getCustomername());
        holder.token_no.setText(selfKisokArrayList.get(position).getTokenno());
        holder.mobile_number.setText(selfKisokArrayList.get(position).getCustomerphone());
        holder.total.setText("RM "+selfKisokArrayList.get(position).getTotalamount());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inter.accept(selfKisokArrayList.get(position).getOrder_id(), selfKisokArrayList.get(position).getCustomertypeid(),
                        selfKisokArrayList.get(position).getCustomer_id(), selfKisokArrayList.get(position).getTableid(),
                        selfKisokArrayList.get(position).getTablename());

                inter.addPrinter();
            }
        });

    }

    @Override
    public int getItemCount() {
        return selfKisokArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date, time, token_no, customer_type, customer_name, mobile_number, total, view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.date);
            time = (TextView) itemView.findViewById(R.id.time);
            token_no = (TextView) itemView.findViewById(R.id.token_no);
            customer_type = (TextView) itemView.findViewById(R.id.customer_type);
            customer_name = (TextView) itemView.findViewById(R.id.customer_name);
            mobile_number = (TextView) itemView.findViewById(R.id.mobile_number);
            total = (TextView) itemView.findViewById(R.id.total);
            view = (TextView) itemView.findViewById(R.id.view_details);

        }
    }
}
