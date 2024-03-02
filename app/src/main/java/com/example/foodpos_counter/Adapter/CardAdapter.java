package com.example.foodpos_counter.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodpos_counter.Menu_Activity;
import com.example.foodpos_counter.Model.CardList;
import com.example.foodpos_counter.Model.Paymenttypes;
import com.example.foodpos_counter.R;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{
    Activity activity;
    ArrayList<CardList> cardListArrayList;
    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(View view, ArrayList<CardList> cardListArrayList, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public CardAdapter(Activity activity, ArrayList<CardList> cardListArrayList) {
        this.activity = activity;
        this.cardListArrayList = cardListArrayList;
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
        holder.textView.setText(cardListArrayList.get(position).getTerminal_name());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(view,cardListArrayList,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardListArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }
}
