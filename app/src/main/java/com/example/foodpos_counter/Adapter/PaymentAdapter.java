package com.example.foodpos_counter.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodpos_counter.Model.PaymentWiseList;
import com.example.foodpos_counter.R;
import com.example.foodpos_counter.Reports.PaymentReportActivity;

import java.util.ArrayList;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder> {

    Activity activity;
    ArrayList<PaymentWiseList> paymentWiseLists;
    public PaymentAdapter(Activity activity, ArrayList<PaymentWiseList> paymentWiseLists) {
        this.activity = activity;
        this.paymentWiseLists = paymentWiseLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.report_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.s_no.setText(String.valueOf(position + 1));
        holder.sale_date.setText(paymentWiseLists.get(position).getSalesdate());
        holder.sale_invoice.setText(paymentWiseLists.get(position).getSaleinvoice());
        holder.counter_name.setText(paymentWiseLists.get(position).getPaymentmethod());
        holder.food_total.setText(paymentWiseLists.get(position).getFoodtotalamount());
        holder.service_tax.setText(paymentWiseLists.get(position).getVatamount());
        holder.service_charge.setText(paymentWiseLists.get(position).getServicecharge());
        holder.discount.setText(paymentWiseLists.get(position).getDiscount());
        holder.amount.setText(paymentWiseLists.get(position).getTotalamount());
    }

    @Override
    public int getItemCount() {
        return paymentWiseLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView s_no;
        TextView sale_date;
        TextView sale_invoice;
        TextView counter_name;
        TextView food_total;
        TextView service_tax;
        TextView service_charge;
        TextView discount;
        TextView amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            s_no = (TextView) itemView.findViewById(R.id.s_no);
            sale_date = (TextView) itemView.findViewById(R.id.sale_date);
            sale_invoice = (TextView) itemView.findViewById(R.id.sale_invoice);
            counter_name = (TextView) itemView.findViewById(R.id.counter_name);
            food_total = (TextView) itemView.findViewById(R.id.food_total);
            service_tax = (TextView) itemView.findViewById(R.id.service_tax);
            service_charge = (TextView) itemView.findViewById(R.id.service_charge);
            discount = (TextView) itemView.findViewById(R.id.discount);
            amount = (TextView) itemView.findViewById(R.id.amount);
        }
    }
}
