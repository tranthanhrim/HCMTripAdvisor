package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tranthanhrim1995.hcmtripadvisor.CircleTransform;
import com.example.tranthanhrim1995.hcmtripadvisor.ConnectionChecking;
import com.example.tranthanhrim1995.hcmtripadvisor.DataGlobal;
import com.example.tranthanhrim1995.hcmtripadvisor.FragmentFactory;
import com.example.tranthanhrim1995.hcmtripadvisor.GoogleApiClientInstance;
import com.example.tranthanhrim1995.hcmtripadvisor.MainActivity;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.User;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.response.EndPointResponse;
import com.example.tranthanhrim1995.hcmtripadvisor.R;
import com.example.tranthanhrim1995.hcmtripadvisor.WebServiceInterface;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class SigninFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    final int RC_SIGN_IN = 2108;
    FragmentManager fragmentManager;
    public SigninFragment() {
        // Required empty public constructor
    }

    LinearLayout nav_header_main;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getActivity().getSupportFragmentManager();
        LinearLayout signinFragment = (LinearLayout)inflater.inflate(R.layout.fragment_signin, null);
        nav_header_main = (LinearLayout)inflater.inflate(R.layout.nav_header_main, null);
        
        SignInButton signInButton = (SignInButton) signinFragment.findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConnectionChecking.getInstance().isInternetEnabled()) {
                    switch (view.getId()) {
                        case R.id.sign_in_button:
                            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(
                                    GoogleApiClientInstance.getInstance(getActivity()).getGoogleApiClient());
                            startActivityForResult(signInIntent, RC_SIGN_IN);
                            break;
                    }
                }
                else {
                    FragmentFactory.getInstance().getInternetNotFoundDialog()
                            .show(getActivity().getFragmentManager(), "no-internet");
                }
            }
        });

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        //User logged in
        if (sharedPref.contains(getString(R.string.google_id))
            && !sharedPref.getString(getString(R.string.google_id), "").isEmpty()
            && ConnectionChecking.getInstance().isInternetEnabled()) {
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(
                    GoogleApiClientInstance.getInstance(getActivity()).getGoogleApiClient());
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }
//        else {
//            fragmentManager.beginTransaction().replace(R.id.container,
//                FragmentFactory.getInstance().getSigninFragment()).commit();
//        }

        return signinFragment;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            Auth.GoogleSignInApi.getS
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.google_id), acct.getId()).apply();
            editor.putString(getString(R.string.display_name), acct.getDisplayName()).apply();
            editor.putString(getString(R.string.email), acct.getEmail()).apply();
            editor.putString(getString(R.string.google_id_token), acct.getIdToken()).apply();
            editor.putString(getString(R.string.photo_url), acct.getPhotoUrl().toString()).apply();

            fragmentManager.beginTransaction().replace(R.id.container,
                    FragmentFactory.getInstance().getMainFragment()).commit();

            MainActivity.updateInfoUserFromOutside(acct.getDisplayName(), acct.getEmail(), acct.getPhotoUrl().toString());

            //Call API POST User
            User user = new User(acct.getId(), acct.getEmail(), acct.getDisplayName(), acct.getPhotoUrl().toString());
            Call<EndPointResponse> callCreateUser = DataGlobal.getInstance().getService().createUser(user);
            callCreateUser.enqueue(new Callback<EndPointResponse>() {
                @Override
                public void onResponse(Call<EndPointResponse> call, Response<EndPointResponse> response) {
                    int a = 1;
                }

                @Override
                public void onFailure(Call<EndPointResponse> call, Throwable t) {
                    int a = 1;
                }
            });
        } else {
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        DrawerLayout mDrawer = (DrawerLayout) this.getActivity().findViewById(R.id.drawer_layout);
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }
}
