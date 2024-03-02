package com.example.foodpos_counter.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodpos_counter.Model.FoodWiseList;
import com.example.foodpos_counter.R;
import com.example.foodpos_counter.Reports.FoodReportActivity;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    Activity activity;
    ArrayList<FoodWiseList> foodWiseLists;

    public FoodAdapter(Activity activity, ArrayList<FoodWiseList> foodWiseLists) {
        this.activity = activity;
        this.foodWiseLists = foodWiseLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.food_report_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.s_no.setText(String.valueOf(position + 1));
        holder.order_date.setText(foodWiseLists.get(position).getOrderdate());
        holder.product_name.setText(foodWiseLists.get(position).getProductName());
        holder.variant_name.setText(foodWiseLists.get(position).getVariantName());
        holder.qty.setText(foodWiseLists.get(position).getQty());
        holder.price.setText(foodWiseLists.get(position).getPrice());
        holder.total.setText(foodWiseLists.get(position).getTotalprice());
    }

    @Override
    public int getItemCount() {
        return foodWiseLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView s_no;
        TextView order_date;
        TextView product_name;
        TextView variant_name;
        TextView qty;
        TextView price;
        TextView total;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            s_no = (TextView) itemView.findViewById(R.id.s_no);
            order_date = (TextView) itemView.findViewById(R.id.order_date);
            product_name = (TextView) itemView.findViewById(R.id.product_name);
            variant_name = (TextView) itemView.findViewById(R.id.variant_name);
            qty = (TextView) itemView.findViewById(R.id.qty);
            price = (TextView) itemView.findViewById(R.id.price);
            total = (TextView) itemView.findViewById(R.id.total);
        }
    }
}
