package com.example.foodpos_counter.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodpos_counter.Model.cate_food;
import com.example.foodpos_counter.Model.category_data;
import com.example.foodpos_counter.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    Activity activity;
    ArrayList<category_data> cate_array;
    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void OnItemClick(View view, String CategoryName, String categoryId, String CategoryImage, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CategoryAdapter(Activity activity, ArrayList<category_data> cate_array) {
        this.activity = activity;
        this.cate_array = cate_array;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.category_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        Glide.with(activity).load(cate_array.get(position).getCategoryImage()).into(holder.image);
        holder.txt.setText(cate_array.get(position).getCategoryName());

        holder.linear_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onItemClickListener != null) {
                    onItemClickListener.OnItemClick(v, cate_array.get(position).getCategoryName(), cate_array.get(position).getCategoryId(),
                            cate_array.get(position).getCategoryId(), position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return cate_array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView txt;
        LinearLayout linear_category;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = (CircleImageView) itemView.findViewById(R.id.image);
            txt = (TextView) itemView.findViewById(R.id.txt);
            linear_category = (LinearLayout) itemView.findViewById(R.id.linear_category);
        }
    }
}
