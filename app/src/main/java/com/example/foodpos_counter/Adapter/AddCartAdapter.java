package com.example.foodpos_counter.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.foodpos_counter.Model.foodcart;
import com.example.foodpos_counter.R;
import com.example.foodpos_counter.db.Entity.CartTable;

import java.util.ArrayList;

public class AddCartAdapter extends RecyclerView.Adapter<AddCartAdapter.ViewHolder> {
    Menu_Activity activity;
    ArrayList<CartTable> cartTableArrayList;
    Inter inter;

    public AddCartAdapter(Menu_Activity activity, ArrayList<CartTable> cartTableArrayList) {
        this.activity = activity;
        this.cartTableArrayList = cartTableArrayList;
        this.inter = activity;
    }

    @NonNull
    @Override
    public AddCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.addcart_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddCartAdapter.ViewHolder holder, int position) {
        Glide.with(activity).load(cartTableArrayList.get(position).getProductImage()).into(holder.pic);
        if (cartTableArrayList.get(position).getProductName().length() > 20) {
            holder.item_name.setText(cartTableArrayList.get(position).getProductName().substring(0, 20) + "\n"+ cartTableArrayList.get(position).getProductName().substring(20));
        }
        else{
            holder.item_name.setText(cartTableArrayList.get(position).getProductName());

        }
        holder.rate.setText("RM "+cartTableArrayList.get(position).getPrice());


        float a = Float.parseFloat(cartTableArrayList.get(position).getPrice());
        float b = Float.parseFloat(cartTableArrayList.get(position).getQty());

        float c = a * b;

        String tot = String.format("%.2f",c);

        holder.rate1.setText("RM  "+tot);

        holder.product_qty.setText(cartTableArrayList.get(position).getQty());

        holder.notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inter.UpdateNotes(cartTableArrayList.get(position).getProductsId(),
                        cartTableArrayList.get(position).getNotes());
            }
        });

        if (cartTableArrayList.get(position).getAddons().size() > 0){
            holder.addons.setVisibility(View.VISIBLE);
            float c1 = 0 ;

            for (int i = 0; i < cartTableArrayList.get(position).getAddons().size(); i++) {

                holder.addons_name.setText(cartTableArrayList.get(position).getAddons().get(i).getAddons_name());
                holder.addons_qty.setText("Qty : " + cartTableArrayList.get(position).getAddons().get(i).getAddons_qty());

                float a1 = Float.parseFloat(cartTableArrayList.get(position).getAddons().get(i).getAddons_price());
                float b1 = Float.parseFloat(cartTableArrayList.get(position).getAddons().get(i).getAddons_qty());

                c1 = a1 * b1;

            }

            String c2 = String.format("%.2f", c1);
            holder.addons_total.setText("RM "+ c2);

        }else {
            holder.addons.setVisibility(View.GONE);
        }

        holder.product_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inter.addOneQuantity(cartTableArrayList.get(position).getProductsId());
            }
        });

        holder.product_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(holder.product_qty.getText().toString()) > 1){
                    inter.reduceOneQuantity(cartTableArrayList.get(position).getProductsId());
                }
            }
        });

        holder.product_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
                dialog.setMessage("Are you sure you want to Remove the item?");
                dialog.setTitle("Delete");

                dialog.setPositiveButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.setNegativeButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inter.deleteSingleItem(cartTableArrayList.get(position).getProductsId());
                    }
                });
                dialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return cartTableArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pic;
        TextView item_name, rate, rate1;

        TextView product_qty;
        TextView sno;
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
