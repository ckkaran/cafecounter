package com.example.foodpos_counter.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodpos_counter.Menu_Activity;
import com.example.foodpos_counter.Model.CustomerList;
import com.example.foodpos_counter.Model.Paymenttypes;
import com.example.foodpos_counter.R;

import java.util.ArrayList;

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.ViewHolder> {
    Activity activity;
    ArrayList<Paymenttypes> paymenttypesArrayList;
    OnItemClickListener onItemClickListener;
    int pos = 0;

    public interface OnItemClickListener{
        void onItemClick(View view, ArrayList<Paymenttypes> paymenttypesArrayList, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public PaymentMethodAdapter(Activity activity, ArrayList<Paymenttypes> paymenttypesArrayList) {
        this.activity = activity;
        this.paymenttypesArrayList = paymenttypesArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.payment_type_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(paymenttypesArrayList.get(position).getPaymentType());

        Glide.with(activity).load(paymenttypesArrayList.get(position).getPaymentTypeImage()).into(holder.imageView);

        holder.payment_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos = position;

                notifyDataSetChanged();

                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(view,paymenttypesArrayList,position);
                }
            }
        });

        if (pos == 0){
            holder.payment_layout.setBackground(activity.getResources().getDrawable(R.drawable.selected_box));
            holder.textView.setTextColor(Color.WHITE);
        }
         if (pos == position){
            holder.payment_layout.setBackground(activity.getResources().getDrawable(R.drawable.selected_box));
            holder.textView.setTextColor(Color.WHITE);
        }
        else {
            holder.payment_layout.setBackground(activity.getResources().getDrawable(R.drawable.unselected_box));
            holder.textView.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return paymenttypesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        LinearLayout payment_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            imageView = itemView.findViewById(R.id.image);
            payment_layout = itemView.findViewById(R.id.payment_layout);
        }
    }
}
