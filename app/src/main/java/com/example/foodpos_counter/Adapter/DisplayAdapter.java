package com.example.foodpos_counter.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodpos_counter.Model.CounterFoodlist;
import com.example.foodpos_counter.OnGoingOrder_Activity;
import com.example.foodpos_counter.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.ViewHolder> {
    Activity activity;
    ArrayList<CounterFoodlist> foodlistArrayList;

    public DisplayAdapter(Activity activity, ArrayList<CounterFoodlist> foodlistArrayList) {
        this.activity = activity;
        this.foodlistArrayList = foodlistArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.display_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.item.setText(foodlistArrayList.get(position).getFoodname());
        holder.qty.setText(foodlistArrayList.get(position).getFoodqty());
        holder.price.setText(foodlistArrayList.get(position).getFoodprice());
    }

    @Override
    public int getItemCount() {
        return foodlistArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView item;
        TextView qty;
        TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item = (TextView) itemView.findViewById(R.id.item);
            qty = (TextView) itemView.findViewById(R.id.qty);
            price = (TextView) itemView.findViewById(R.id.price);
        }
    }
}
