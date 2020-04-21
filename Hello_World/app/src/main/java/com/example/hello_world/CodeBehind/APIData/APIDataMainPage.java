package com.example.hello_world.CodeBehind.APIData;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hello_world.Model.Utils.APIData.APIFetcher;
import com.example.hello_world.Model.Utils.APIData.DataInteractor;
import com.example.hello_world.Model.Utils.APIData.NetworkInformations;
import com.example.hello_world.R;

public class APIDataMainPage extends AppCompatActivity {
    private DataInteractor di;
    private boolean isDataLoaded = false;
    private RelativeLayout rootLayoutPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_data_main_layout);

        rootLayoutPopup = findViewById(R.id.layoutPopup);
        checkIfConnectedToInternet();

        di = new DataInteractor(getApplicationContext());

        if(di.loadEmployees().size() == 0){
            APIFetcher apiFetcher = new APIFetcher();
            apiFetcher.fetchData(getApplicationContext());
        }
        isDataLoaded = true;
    }

    private boolean checkIfConnectedToInternet() {
        NetworkInformations ni = new NetworkInformations(getApplicationContext());


        if(!ni.isConnectedToInternet()){
            rootLayoutPopup.setVisibility(View.VISIBLE);
            return false;
        }
        rootLayoutPopup.setVisibility(View.GONE);
        return true;
    }

    public void goToInnerPageLV(View view) {
        Intent i = new Intent(this, InnerPageListView.class);
        i.putExtra("employeesList", di.loadEmployees());
        startActivity(i);
    }

    public void goToInnerPageRV(View view) {
        Intent i = new Intent(this, InnerPageRecyclerView.class);
        i.putExtra("employeesList", di.loadEmployees());
        startActivity(i);
    }

    public void deleteData(View view) {
        di.deleteData();
        isDataLoaded = false;
        showToast(getResources().getString(R.string.toast_data_deleted_msg));
    }

    private void showToast(String s){
        Toast t = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG);
        t.show();
    }

    public void reloadData(View view) {

        if(!isDataLoaded){
            if(checkIfConnectedToInternet())
            {
                APIFetcher apiFetcher = new APIFetcher();
                apiFetcher.fetchData(getApplicationContext());
                isDataLoaded = true;
            }
            else{
                showToast(getResources().getString(R.string.toast_no_internet_msg));
            }
        }
        else{
            showToast(getResources().getString(R.string.data_loaded));
        }
    }
}
