package com.example.foodpos_counter.Adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodpos_counter.Interface.Inter;
import com.example.foodpos_counter.Menu_Activity;
import com.example.foodpos_counter.Model.Order_FoodList;
import com.example.foodpos_counter.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    Menu_Activity activity;
    ArrayList<Order_FoodList> orderFoodListArrayList;
    Inter inter;

    public OrderAdapter(Menu_Activity activity, ArrayList<Order_FoodList> orderFoodListArrayList) {
        this.activity = activity;
        this.orderFoodListArrayList = orderFoodListArrayList;
        this.inter = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.addcart_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.product_delete.setVisibility(View.GONE);
        holder.product_add.setVisibility(View.GONE);
        holder.product_reduce.setVisibility(View.GONE);

        Glide.with(activity).load(orderFoodListArrayList.get(position).getProductImage()).into(holder.pic);

        if (orderFoodListArrayList.get(position).getProductName().length() > 18) {
            holder.item_name.setText(orderFoodListArrayList.get(position).getProductName().substring(0, 18) + "\n"+ orderFoodListArrayList.get(position).getProductName().substring(18));
        }
        else{
            holder.item_name.setText(orderFoodListArrayList.get(position).getProductName());

        }
        holder.rate.setText(orderFoodListArrayList.get(position).getPrice());

        holder.product_qty.setText(orderFoodListArrayList.get(position).getMenuqty());

        float a = Float.parseFloat(orderFoodListArrayList.get(position).getPrice());
        float b = Float.parseFloat(orderFoodListArrayList.get(position).getMenuqty());

        float c = a * b;

        String tot = String.format("%.2f",c);

        holder.rate1.setText("RM "+tot);

        if (orderFoodListArrayList.get(position).getNotes().equals("")){
            holder.notes.setVisibility(View.GONE);
        }
        else {

            holder.notes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    inter.UpdateOrderNotes(orderFoodListArrayList.get(position).getNotes());
                }
            });
        }

        if (orderFoodListArrayList.get(position).getAddons().size() > 0){
            holder.addons.setVisibility(View.VISIBLE);
            float c2 = 0 ;

            for (int i = 0; i < orderFoodListArrayList.get(position).getAddons().size(); i++) {

                holder.addons_name.setText(orderFoodListArrayList.get(position).getAddons().get(i).getAddOnName());
                holder.addons_qty.setText("Qty : " + orderFoodListArrayList.get(position).getAddons().get(i).getAddOnQty());

                float a1 = Float.parseFloat(orderFoodListArrayList.get(position).getAddons().get(i).getAddOnPrice());
                float b1 = Float.parseFloat(orderFoodListArrayList.get(position).getAddons().get(i).getAddOnQty());

                c2 = a1 * b1;

            }

            String c3 = String.format("%.2f", c2);
            holder.addons_total.setText("RM "+ c3);

        }else {
            holder.addons.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return orderFoodListArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView pic;
        TextView item_name, rate, rate1;
        TextView product_qty;
        ImageView notes;
        TextView addons_name;
        TextView addons_qty;
        TextView addons_total;
        LinearLayout addons;
        ImageButton product_add;
        ImageButton product_reduce;
        ImageView product_delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.pic);
            item_name = (TextView) itemView.findViewById(R.id.item_name);
            rate = (TextView) itemView.findViewById(R.id.rate);
            rate1 = (TextView) itemView.findViewById(R.id.rate1);
            product_qty = (TextView) itemView.findViewById(R.id.product_qty);
            addons_name = (TextView) itemView.findViewById(R.id.addons_name);
            addons_qty = (TextView) itemView.findViewById(R.id.addons_qty);
            addons_total = (TextView) itemView.findViewById(R.id.addons_total);
            notes = (ImageView) itemView.findViewById(R.id.notes);
            product_add = (ImageButton) itemView.findViewById(R.id.product_add);
            product_reduce = (ImageButton) itemView.findViewById(R.id.product_reduce);
            product_delete = (ImageView) itemView.findViewById(R.id.product_delete);
            addons = (LinearLayout) itemView.findViewById(R.id.addons);
        }
    }
}

