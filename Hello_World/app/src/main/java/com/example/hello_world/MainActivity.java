package com.example.hello_world;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hello_world.CodeBehind.APIData.APIDataMainPage;
import com.example.hello_world.CodeBehind.CameraApplication.CameraApplicationMainPage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToAPIData(View view) {
        Intent intent = new Intent(this, APIDataMainPage.class);
        startActivity(intent);
    }


    public void goToCameraActions(View view) {
        Intent intent = new Intent(this, CameraApplicationMainPage.class);
        startActivity(intent);
    }
}
