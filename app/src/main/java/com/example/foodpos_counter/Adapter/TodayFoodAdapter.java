package com.example.foodpos_counter.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodpos_counter.CompletedOrder_Activity;
import com.example.foodpos_counter.Model.TodayFoodView;
import com.example.foodpos_counter.R;

import java.util.ArrayList;

public class TodayFoodAdapter extends RecyclerView.Adapter<TodayFoodAdapter.ViewHolder> {

    Activity activity;
    ArrayList<TodayFoodView> todayFoodViewArrayList;

    public TodayFoodAdapter(Activity activity, ArrayList<TodayFoodView> todayFoodViewArrayList) {
        this.activity = activity;
        this.todayFoodViewArrayList = todayFoodViewArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.food_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item.setText(todayFoodViewArrayList.get(position).getFoodname());
        holder.size.setText(todayFoodViewArrayList.get(position).getVariantname());
        holder.unit_price.setText(todayFoodViewArrayList.get(position).getPrice());
        holder.qty.setText(todayFoodViewArrayList.get(position).getQty());
        holder.total_price.setText(todayFoodViewArrayList.get(position).getTotalamount());
    }

    @Override
    public int getItemCount() {
        return todayFoodViewArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item;
        TextView size;
        TextView unit_price;
        TextView qty;
        TextView total_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item = (TextView) itemView.findViewById(R.id.item);
            size = (TextView) itemView.findViewById(R.id.size);
            unit_price = (TextView) itemView.findViewById(R.id.unit_price);
            qty = (TextView) itemView.findViewById(R.id.qty);
            total_price = (TextView) itemView.findViewById(R.id.total_price);
        }
    }
}
