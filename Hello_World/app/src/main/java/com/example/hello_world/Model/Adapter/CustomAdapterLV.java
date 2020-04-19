package com.example.hello_world.Model.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hello_world.Model.Structures.Employee;
import com.example.hello_world.R;

import java.util.List;

public class CustomAdapterLV extends ArrayAdapter<Employee> {


    public CustomAdapterLV(@NonNull Context context, int resource, @NonNull List<Employee> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.custom_tv, parent, false);
        }
        LinearLayout rootCustomTV = convertView.findViewById(R.id.rootCustomTV);

        if(position % 2 == 0)
            rootCustomTV.setBackgroundColor(Color.parseColor("#78BDE3"));
        else
            rootCustomTV.setBackgroundColor(Color.parseColor("#8DC7E7"));

        ((TextView) convertView.findViewById(R.id.employeeName)).setText(getItem(position).getName());
        ((TextView) convertView.findViewById(R.id.employeeAge)).setText(getItem(position).getAge());
        ((TextView) convertView.findViewById(R.id.employeeSalary)).setText(getItem(position).getSalary());

        String profileImage = getItem(position).getProfileImage();
        if(profileImage.trim().equals(""))
            ((TextView)convertView.findViewById(R.id.employeeImage)).setText(R.string.no_picture);
        else
            ((TextView)convertView.findViewById(R.id.employeeImage)).setText(profileImage);


        return convertView;
    }
}
