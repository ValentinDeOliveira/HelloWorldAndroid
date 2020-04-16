package com.example.helloworld;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.helloworld.Model.CustomAdapter;
import com.example.helloworld.Model.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InnerPage extends AppCompatActivity {

    final ArrayList<Employee> employeesList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inner_page);


        final ListView listView = findViewById(R.id.listView);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://dummy.restapiexample.com/api/v1/employees";

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray array = response.getJSONArray("data");

                    for (int i = 0; i != array.length(); i++){
                        JSONObject employee = array.getJSONObject(i);

                        String name = employee.getString("employee_name");
                        String salary = employee.getString("employee_salary");
                        String age = employee.getString("employee_age");
                        String image = employee.getString("profile_image");

                        employeesList.add(new Employee(name,salary,age,image));
                    }
                    listView.setAdapter(new CustomAdapter(InnerPage.this, R.layout.custom_tv, employeesList));

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


        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);

    }
}
