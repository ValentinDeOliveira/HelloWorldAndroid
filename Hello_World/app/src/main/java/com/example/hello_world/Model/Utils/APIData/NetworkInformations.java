package com.example.hello_world.Model.Utils.APIData;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkInformations {
    private Context context;

    public NetworkInformations(Context context){
        this.context = context;
    }

    private boolean isConnectedToWifi() {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        return ni.isConnected();
    }

    private boolean isConnectedToNetwork(){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return ni.getState() == NetworkInfo.State.CONNECTED;
    }

    public boolean isConnectedToInternet(){
        return isConnectedToWifi() || isConnectedToNetwork();
    }
}
