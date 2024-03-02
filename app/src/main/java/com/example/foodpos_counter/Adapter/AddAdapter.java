package com.example.foodpos_counter.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodpos_counter.Menu_Activity;
import com.example.foodpos_counter.Model.Addons;
import com.example.foodpos_counter.R;

import java.util.ArrayList;

public class AddAdapter extends RecyclerView.Adapter<AddAdapter.ViewHolder>{
    Activity activity;
    ArrayList<Addons> addons;

    OnItemClickListener onItemClickListener;
    public AddAdapter(Menu_Activity activity, ArrayList<Addons> addons) {
        this.activity = activity;
        this.addons = addons;
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, String addons_name, String addons_price, String addons_id, String addons_qty, boolean isChecked, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AddAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.addonslist_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddAdapter.ViewHolder holder, int position) {

        holder.addons_name.setText(addons.get(position).getAddonname());
        holder.addons_price.setText(addons.get(position).getAddonprice());

        holder.addons_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.addons_qty.setText(String.valueOf(Integer.parseInt(holder.addons_qty.getText().toString()) + 1));
            }
        });

        holder.addons_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(holder.addons_qty.getText().toString()) > 1){
                    holder.addons_qty.setText(String.valueOf(Integer.parseInt(holder.addons_qty.getText().toString()) - 1));
                }
            }
        });

        holder.addons_name.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (onItemClickListener != null) {
                    onItemClickListener.OnItemClick(buttonView, addons.get(position).getAddonname(),
                            addons.get(position).getAddonprice(),
                            addons.get(position).getAddonid(),
                            holder.addons_qty.getText().toString(),
                            isChecked,
                            position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return addons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox addons_name;
        TextView addons_price;
        TextView addons_qty;
        ImageButton addons_add;
        ImageButton addons_reduce;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            addons_name = (CheckBox) itemView.findViewById(R.id.addons_name);
            addons_price = (TextView) itemView.findViewById(R.id.addons_price);
            addons_qty = (TextView) itemView.findViewById(R.id.addons_qty);
            addons_add = (ImageButton) itemView.findViewById(R.id.addons_add);
            addons_reduce = (ImageButton) itemView.findViewById(R.id.addons_reduce);
        }
    }
}
