package com.example.hello_world.CodeBehind.APIData;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hello_world.Model.Adapter.CustomAdapterLV;
import com.example.hello_world.Model.Structures.Employee;
import com.example.hello_world.R;

import java.util.ArrayList;

public class InnerPageListView extends AppCompatActivity {
    ArrayList<Employee> employeesList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inner_page_lv);

        employeesList = (ArrayList<Employee>) getIntent().getSerializableExtra("employeesList");


        initListView();

    }

    private void initListView(){
        ListView listView = findViewById(R.id.listView);

        listView.setAdapter(new CustomAdapterLV(this, R.layout.custom_tv, employeesList));

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                        Intent intent = new Intent(InnerPageListView.this, DisplaySingleEmployee.class);
                        Employee e = employeesList.get(pos);

                        intent.putExtra("currentEmployee", e);
                        startActivity(intent);
                    }
                }
        );
    }
}
