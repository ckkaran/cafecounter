package com.example.foodpos_counter.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodpos_counter.Menu_Activity;
import com.example.foodpos_counter.Model.CustomerList;
import com.example.foodpos_counter.Model.CustomerTypes;
import com.example.foodpos_counter.R;

import java.util.ArrayList;
import java.util.List;

public class CustomernameAdapter extends RecyclerView.Adapter<CustomernameAdapter.ViewHolder> {

    Menu_Activity activity;
    ArrayList<CustomerList> customerListArrayList;

    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(View view, ArrayList<CustomerList> customerListList, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public CustomernameAdapter(Menu_Activity activity, ArrayList<CustomerList> customerListArrayList) {
        this.activity = activity;
        this.customerListArrayList = customerListArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.text_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(customerListArrayList.get(position).getCustomerName());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(view,customerListArrayList,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return customerListArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }
}
