package com.example.foodpos_counter.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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
import com.example.foodpos_counter.Model.GetTableInfo;
import com.example.foodpos_counter.Model.selectperson;
import com.example.foodpos_counter.R;

import java.util.ArrayList;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {
    Activity activity;
    ArrayList<GetTableInfo> getTableInfoArrayList;
    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(View view, ArrayList<GetTableInfo> getTableInfoArrayList, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public TableAdapter(Activity activity, ArrayList<GetTableInfo> getTableInfoArrayList) {
        this.activity = activity;
        this.getTableInfoArrayList = getTableInfoArrayList;
    }

    @NonNull
    @Override
    public TableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.table_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableAdapter.ViewHolder holder, int position) {
        holder.text.setText("Table " + getTableInfoArrayList.get(position).getTablename());
        holder.text1.setText("Available " + getTableInfoArrayList.get(position).getAvailableSeat());
        Glide.with(activity).load(R.drawable.table).into(holder.image);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(v ,getTableInfoArrayList ,position);
                }
            }
        });



        if (getTableInfoArrayList.get(position).getBookingStatus().equals("1")){
            holder.card.setBackground(activity.getResources().getDrawable(R.color.Red_Color));
        }
        else{
            holder.card.setBackground(activity.getResources().getDrawable(R.color.green_500));
        }

    }

    @Override
    public int getItemCount() {
        return getTableInfoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout card;
        TextView text;
        TextView text1;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            card = (LinearLayout) itemView.findViewById(R.id.card);
            text = (TextView) itemView.findViewById(R.id.text);
            text1 = (TextView) itemView.findViewById(R.id.text1);
            image = (ImageView) itemView.findViewById(R.id.image);


        }
    }
}
