package com.example.tranthanhrim1995.hcmtripadvisor;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by tranthanhrim1995 on 1/14/2017.
 */

public class ConnectionChecking {
    private static ConnectionChecking instance = new ConnectionChecking();
    private ConnectionChecking() {}

    private Context context;
    public static ConnectionChecking getInstance() {
        return instance;
    }

    public void init(Context context) {
        this.context = context;
    }

    public boolean isGpsEnabled() {
        LocationManager manager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public boolean isInternetEnabled() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
