package com.example.helloworld.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.helloworld.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Employee> {

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Employee> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.custom_tv, parent, false);
        }
        ((TextView)convertView.findViewById(R.id.employeeName)).setText(getItem(position).getName());
        ((TextView)convertView.findViewById(R.id.employeeAge)).setText(getItem(position).getAge());
        ((TextView)convertView.findViewById(R.id.employeeSalary)).setText(getItem(position).getSalary());

        String profileImage = getItem(position).getProfileImage();
        if(profileImage.trim().equals(""))
            ((TextView)convertView.findViewById(R.id.employeeImage)).setText("None");
        else
            ((TextView)convertView.findViewById(R.id.employeeImage)).setText(profileImage);


        return convertView;
    }
}
