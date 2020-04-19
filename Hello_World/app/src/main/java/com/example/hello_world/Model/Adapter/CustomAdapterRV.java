package com.example.hello_world.Model.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hello_world.Model.Structures.Employee;
import com.example.hello_world.R;

import java.util.ArrayList;

public class CustomAdapterRV extends RecyclerView.Adapter<CustomAdapterRV.ViewHolder>{

    private Context context;
    private ArrayList<Employee> employees;
    private Listener listener;

    public interface Listener{
        void onEmployeeClick(int pos);
    }



    public CustomAdapterRV(Context context, ArrayList<Employee> employees, Listener listener){
        this.context = context;
        this.employees = employees;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_tv, parent, false);
        return new ViewHolder(v, listener);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(position % 2 == 0)
            holder.rootCustomTV.setBackgroundColor(Color.parseColor("#78BDE3"));
        else
            holder.rootCustomTV.setBackgroundColor(Color.parseColor("#8DC7E7"));

        holder.tvName.setText(employees.get(position).getName());
        holder.tvSalary.setText(employees.get(position).getSalary());
        holder.tvAge.setText(employees.get(position).getAge());

        String profileImage = employees.get(position).getProfileImage();

        if(profileImage.trim().equals(""))
            holder.tvImage.setText(R.string.no_picture);
        else
            holder.tvImage.setText(profileImage);
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        LinearLayout rootCustomTV;
        TextView tvAge, tvSalary, tvName, tvImage;
        Listener listener;

        ViewHolder(@NonNull View itemView, Listener listener) {
            super(itemView);

            rootCustomTV = itemView.findViewById(R.id.rootCustomTV);

            tvAge = itemView.findViewById(R.id.employeeAge);
            tvSalary = itemView.findViewById(R.id.employeeSalary);
            tvName = itemView.findViewById(R.id.employeeName);
            tvImage = itemView.findViewById(R.id.employeeImage);

            this.listener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onEmployeeClick(getAdapterPosition());
        }
    }
}
