package com.example.hello_world.Model.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hello_world.Model.Structures.Employee;
import com.example.hello_world.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class DataInteractor {
    private Context context;


    public DataInteractor(Context context){
        this.context = context;
    }

    void saveEmployees(ArrayList<Employee> employees){
        SharedPreferences prefs = context.getSharedPreferences(
                "prefs", Context.MODE_PRIVATE
        );
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();

        String json = gson.toJson(employees);

        editor.putString(String.valueOf(R.string.employeeSaveTag), json);
        editor.apply();
    }

    public ArrayList<Employee> loadEmployees(){
        SharedPreferences prefs = context.getSharedPreferences(
                "prefs", Context.MODE_PRIVATE
        );

        Gson gson = new Gson();
        String json = prefs.getString(String.valueOf(R.string.employeeSaveTag), String.valueOf(new ArrayList<Employee>()));
        Type type = new TypeToken<ArrayList<Employee>>() {}.getType();

        ArrayList<Employee> employees = gson.fromJson(json, type);

        if(employees.size() == 0)
            return new ArrayList<>();
        return employees;
    }

    public void deleteData(){
        saveEmployees(new ArrayList<Employee>());
    }
}
