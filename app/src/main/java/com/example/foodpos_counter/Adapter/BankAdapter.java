package com.example.foodpos_counter.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodpos_counter.Menu_Activity;
import com.example.foodpos_counter.Model.BankList;
import com.example.foodpos_counter.Model.CardList;
import com.example.foodpos_counter.R;

import java.util.ArrayList;

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.ViewHolder> {

    Activity activity;
    ArrayList<BankList> bankListArrayList;

    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(View view, ArrayList<BankList> bankListArrayList, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public BankAdapter(Activity activity, ArrayList<BankList> bankListArrayList) {
        this.activity = activity;
        this.bankListArrayList = bankListArrayList;
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
        holder.textView.setText(bankListArrayList.get(position).getBankname());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(view,bankListArrayList,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bankListArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }
}
