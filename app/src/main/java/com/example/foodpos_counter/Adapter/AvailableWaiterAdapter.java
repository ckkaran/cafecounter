package com.example.foodpos_counter.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.foodpos_counter.Model.AvailableTableList;
import com.example.foodpos_counter.Model.WaiterList;
import com.example.foodpos_counter.R;
import com.example.foodpos_counter.SelfKiosk_Activity;

import java.util.ArrayList;

public class AvailableWaiterAdapter extends ArrayAdapter<WaiterList> {
    Activity activity;
    ArrayList<WaiterList> waiterListArrayList;
    public AvailableWaiterAdapter(Activity activity, ArrayList<WaiterList> waiterListArrayList) {
        super(activity, 0, waiterListArrayList);
        this.activity = activity;
        this.waiterListArrayList = waiterListArrayList;
    }

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
        WaiterList currentItem = getItem(position);

        // It is used the name to the TextView when the
        // current item is not null.
        if (currentItem != null) {
            textViewName.setText(currentItem.getEmployeeName());
        }
        return convertView;
    }
}
