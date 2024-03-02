package com.example.foodpos_counter.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodpos_counter.Interface.Inter;
import com.example.foodpos_counter.Menu_Activity;
import com.example.foodpos_counter.Model.foodlist_data;
import com.example.foodpos_counter.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {
    Menu_Activity activity;
    ArrayList<foodlist_data> foodlistarray;
    Inter inter;

    public FoodListAdapter(Menu_Activity activity, ArrayList<foodlist_data> foodlistarray) {
        this.activity = activity;
        this.foodlistarray = foodlistarray;
        this.inter = activity;
    }

    @NonNull
    @Override
    public FoodListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.foodlist_layout,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListAdapter.ViewHolder holder, int position) {
        Glide.with(activity).load(foodlistarray.get(position).getProductImage()).into(holder.image);

        if (foodlistarray.get(position).getProductName().length() >= 12) {
            holder.txt.setText(foodlistarray.get(position).getProductName().substring(0, 12) + "\n" + foodlistarray.get(position).getProductName().substring(12));
        }
        else{
            holder.txt.setText(foodlistarray.get(position).getProductName()+"\n");

        }

        holder.txt1.setText(foodlistarray.get(position).getVarientName());
        holder.rate.setText(foodlistarray.get(position).getPrice());

        holder.food_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (foodlistarray.get(position).getAddonsarray() != null) {
                    inter.addCart(foodlistarray.get(position).getProductsId(),
                            foodlistarray.get(position).getCategoryId(),
                            foodlistarray.get(position).getProductName(),
                            foodlistarray.get(position).getProductImage(),
                            foodlistarray.get(position).getVarientId(),
                            foodlistarray.get(position).getVatCharge(),
                            foodlistarray.get(position).getVarientName(),
                            foodlistarray.get(position).getPrice(),
                            foodlistarray.get(position).getKitchenId(),
                            foodlistarray.get(position).getKitchenName(),
                            foodlistarray.get(position).getKitchenIpAdrees(),
                            foodlistarray.get(position).getKitchenPort(),
                            foodlistarray.get(position).getAddonsarray()
                    );
                }
                else{
                    inter.addCart(foodlistarray.get(position).getProductsId(),
                            foodlistarray.get(position).getCategoryId(),
                            foodlistarray.get(position).getProductName(),
                            foodlistarray.get(position).getProductImage(),
                            foodlistarray.get(position).getVarientId(),
                            foodlistarray.get(position).getVatCharge(),
                            foodlistarray.get(position).getVarientName(),
                            foodlistarray.get(position).getPrice(),
                            foodlistarray.get(position).getKitchenId(),
                            foodlistarray.get(position).getKitchenName(),
                            foodlistarray.get(position).getKitchenIpAdrees(),
                            foodlistarray.get(position).getKitchenPort(),
                            null
                    );
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return foodlistarray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView food_layout;
        CircleImageView image;
        TextView txt,txt1, rate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            food_layout = (CardView) itemView.findViewById(R.id.food_layout);
            image = (CircleImageView) itemView.findViewById(R.id.image);
            txt = (TextView) itemView.findViewById(R.id.txt);
            txt1 = (TextView) itemView.findViewById(R.id.txt1);
            rate = (TextView) itemView.findViewById(R.id.rate);

        }
    }
}
