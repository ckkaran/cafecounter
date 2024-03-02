package com.example.foodpos_counter.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodpos_counter.Interface.Ongoing_inter;
import com.example.foodpos_counter.Menu_Activity;
import com.example.foodpos_counter.Model.OngoingList;
import com.example.foodpos_counter.OnGoingOrder_Activity;
import com.example.foodpos_counter.R;

import java.util.ArrayList;

public class OnGoingAdapter extends RecyclerView.Adapter<OnGoingAdapter.ViewHolder> {
    OnGoingOrder_Activity activity;
    ArrayList<OngoingList> ongoingListArrayList;
    Ongoing_inter inter;

    public OnGoingAdapter(OnGoingOrder_Activity activity, ArrayList<OngoingList> ongoingListArrayList) {
        this.activity = activity;
        this.ongoingListArrayList = ongoingListArrayList;
        this.inter = activity;
    }

    @NonNull
    @Override
    public OnGoingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ongoingorder_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnGoingAdapter.ViewHolder holder, int position) {

        holder.type.setText(ongoingListArrayList.get(position).getCustomertype());

        holder.order_no.setText(ongoingListArrayList.get(position).getSaleinvoice());
        holder.token_no.setText(ongoingListArrayList.get(position).getTokenno());
        holder.order_date.setText(ongoingListArrayList.get(position).getOrderdate());
        holder.order_time.setText(ongoingListArrayList.get(position).getOrdertime());
//        if (ongoingListArrayList.get(position).getCustomertype().equals("QR Customer")){
//            holder.waiter_layout.setVisibility(View.GONE);
//        }else {
            holder.waiter.setText(ongoingListArrayList.get(position).getWaitername());
//        }

//        if (ongoingListArrayList.get(position).getCountername().equals("")){
//            holder.counter_layout.setVisibility(View.GONE);
//        }
//        else{
            holder.counter_layout.setVisibility(View.GONE);
            holder.counter.setText(ongoingListArrayList.get(position).getCountername());
      //  }

        if (ongoingListArrayList.get(position).getCustomertypeid().equals("4")){
            holder.table_layout.setVisibility(View.GONE);
            holder.update.setVisibility(View.GONE);
        }
        else{
            holder.table_layout.setVisibility(View.VISIBLE);
            holder.update.setVisibility(View.VISIBLE);
            holder.table_no.setText(ongoingListArrayList.get(position).getTablename());
            holder.update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // inter.reomveprinter();
                    Intent intent = new Intent(activity, Menu_Activity.class);
                    intent.putExtra("order_id", ongoingListArrayList.get(position).getOrderid());
                    intent.putExtra("customer_type", ongoingListArrayList.get(position).getCustomertypeid());
                    activity.startActivity(intent);

                }
            });
        }



        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(activity, Menu_Activity.class);
//                activity.startActivity(intent);

                inter.cancel(ongoingListArrayList.get(position).getOrderid());
            }
        });

        holder.complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inter.complete(ongoingListArrayList.get(position).getOrderid(),
                        ongoingListArrayList.get(position).getTotalamount(),
                        ongoingListArrayList.get(position).getTotaldueamount(),
                        ongoingListArrayList.get(position).getTablename(),
                        ongoingListArrayList.get(position).getSaleinvoice());
            }
        });

    }

    @Override
    public int getItemCount() {
        return ongoingListArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView type, table_no, order_no, token_no, waiter, order_date, order_time, counter;
        ImageButton update, complete;

        ImageView cancel;
        LinearLayout table_layout, waiter_layout, counter_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            type = (TextView) itemView.findViewById(R.id.type);
            table_no = (TextView) itemView.findViewById(R.id.table_no);
            order_no = (TextView) itemView.findViewById(R.id.order_no);
            token_no = (TextView) itemView.findViewById(R.id.token_no);
            order_date = (TextView) itemView.findViewById(R.id.order_date);
            order_time = (TextView) itemView.findViewById(R.id.order_time);
            table_layout = (LinearLayout) itemView.findViewById(R.id.table_layout);
            waiter_layout = (LinearLayout) itemView.findViewById(R.id.waiter_layout);
            counter_layout = (LinearLayout) itemView.findViewById(R.id.counter_layout);
            waiter = (TextView) itemView.findViewById(R.id.waiter);
            counter = (TextView) itemView.findViewById(R.id.counter);
            update = (ImageButton) itemView.findViewById(R.id.update);
            cancel = (ImageView) itemView.findViewById(R.id.cancel);
            complete = (ImageButton) itemView.findViewById(R.id.complete);


        }
    }
}
