package com.example.hello_world.CodeBehind;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hello_world.Model.Adapter.CustomAdapterRV;
import com.example.hello_world.Model.Structures.Employee;
import com.example.hello_world.R;

import java.util.ArrayList;

public class InnerPageRecyclerView extends AppCompatActivity implements CustomAdapterRV.Listener{

    ArrayList<Employee> employeesList;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inner_page_rv);

        employeesList = (ArrayList<Employee>) getIntent().getSerializableExtra("employeesList");
        initRecyclerView();

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(InnerPageRecyclerView.this);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView .setAdapter(new CustomAdapterRV(InnerPageRecyclerView.this, employeesList, this));
    }

    @Override
    public void onEmployeeClick(int pos) {
        Intent i = new Intent(this, DisplaySingleEmployee.class);
        i.putExtra("position", pos);
        i.putExtra("currentEmployee", employeesList.get(pos));
        startActivity(i);
    }
}
