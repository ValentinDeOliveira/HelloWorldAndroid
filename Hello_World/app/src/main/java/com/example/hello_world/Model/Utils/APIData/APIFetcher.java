package com.example.hello_world.Model.Utils.APIData;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hello_world.Model.Structures.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class APIFetcher {

    private ArrayList<Employee> employees = new ArrayList<>();


    public void fetchData(final Context context){
        RequestQueue queue = Volley.newRequestQueue(context);

        String url ="http://dummy.restapiexample.com/api/v1/employees";

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray array = response.getJSONArray("data");
                    for (int i = 0; i != array.length(); i++){
                        addNewEmployee(array.getJSONObject(i));
                    }
                    DataInteractor s = new DataInteractor(context);
                    s.saveEmployees(employees);
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(jsonObjectRequest);



    }

    private void addNewEmployee(JSONObject employee) {
        try {
            String name = employee.getString("employee_name");
            String salary = employee.getString("employee_salary");
            String age = employee.getString("employee_age");
            String image = employee.getString("profile_image");

            employees.add(new Employee(name,salary,age,image));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
