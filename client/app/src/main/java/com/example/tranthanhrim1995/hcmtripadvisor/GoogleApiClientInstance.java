package com.example.tranthanhrim1995.hcmtripadvisor;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by tranthanhrim1995 on 1/11/2017.
 */

public class GoogleApiClientInstance implements GoogleApiClient.OnConnectionFailedListener{
    private static GoogleApiClientInstance instance = null;

    GoogleSignInOptions gso;
    GoogleApiClient mGoogleApiClient;

    static FragmentActivity fragmentActivity = null;

    private GoogleApiClientInstance() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(fragmentActivity)
                .enableAutoManage(fragmentActivity /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    public static GoogleApiClientInstance getInstance(FragmentActivity fa) {
//        if (fragmentActivity == null) {
            fragmentActivity = fa;
//        }
        if (instance == null) {
            instance = new GoogleApiClientInstance();
        }
        return instance;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public GoogleApiClient getGoogleApiClient() {
        return mGoogleApiClient;
    }
}
