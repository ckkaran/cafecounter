package com.example.foodpos_counter.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foodpos_counter.Model.AvailableTableList;
import com.example.foodpos_counter.R;
import com.example.foodpos_counter.SelfKiosk_Activity;

import java.util.ArrayList;

public class AvailableTableAdapter extends ArrayAdapter<AvailableTableList> {

    SelfKiosk_Activity activity;
    ArrayList<AvailableTableList> availableTableLists;
    public AvailableTableAdapter(SelfKiosk_Activity activity, ArrayList<AvailableTableList> availableTableLists) {
        super(activity, 0, availableTableLists);

        this.activity = activity;
        this.availableTableLists = availableTableLists;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable
    View convertView, @NonNull ViewGroup parent)
    {
        return initView(position, convertView, parent);
    }

//    @Override
//    public View getDropDownView(int position, @Nullable
//    View convertView, @NonNull ViewGroup parent)
//    {
//        return initView(position, convertView, parent);
//    }

    private View initView(int position, View convertView,
                          ViewGroup parent)
    {
        // It is used to set our custom view.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.text_layout, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.text);
        AvailableTableList currentItem = getItem(position);

        // It is used the name to the TextView when the
        // current item is not null.
        if (currentItem != null) {
            textViewName.setText(currentItem.getTablename());
        }
        return convertView;
    }
}
