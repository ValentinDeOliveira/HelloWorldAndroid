package com.example.hello_world.CodeBehind;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hello_world.Model.Structures.Employee;
import com.example.hello_world.R;

public class DisplaySingleEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_tv);


        centerLayout(getIntent().getIntExtra("position",0));
        initTextViews((Employee) getIntent().getSerializableExtra("currentEmployee"));
    }

    private void centerLayout(int pos) {
        LinearLayout ll = findViewById(R.id.rootCustomTV);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) ll.getLayoutParams();
        params.gravity = Gravity.CENTER;

        if(pos % 2 == 0)
            ll.setBackgroundColor(getResources().getColor(R.color.darkShade));
        else
            ll.setBackgroundColor(getResources().getColor(R.color.lightShade));
    }

    private void initTextViews(Employee e) {
        TextView tv_age = findViewById(R.id.employeeAge);
        TextView tv_name = findViewById(R.id.employeeName);
        TextView tv_salary = findViewById(R.id.employeeSalary);
        TextView tv_image = findViewById(R.id.employeeImage);


        tv_age.setText(e.getAge());
        tv_name.setText(e.getName());
        tv_salary.setText(e.getSalary());

        String image = e.getProfileImage();
        if(image.trim().equals(""))
            tv_image.setText(getResources().getString(R.string.no_picture));
        else
            tv_image.setText(image);
    }
}
