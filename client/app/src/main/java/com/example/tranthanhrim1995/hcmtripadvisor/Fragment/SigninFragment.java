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
import com.example.tranthanhrim1995.hcmtripadvisor.FragmentFactory;
import com.example.tranthanhrim1995.hcmtripadvisor.GoogleApiClientInstance;
import com.example.tranthanhrim1995.hcmtripadvisor.MainActivity;
import com.example.tranthanhrim1995.hcmtripadvisor.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

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
                switch (view.getId()) {
                    case R.id.sign_in_button:
                        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(
                                GoogleApiClientInstance.getInstance(getActivity()).getGoogleApiClient());
                        startActivityForResult(signInIntent, RC_SIGN_IN);
                        break;
                }
            }
        });

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        //User logged in
        if (sharedPref.contains(getString(R.string.google_id))
            && !sharedPref.getString(getString(R.string.google_id), "").isEmpty()) {
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
            // Signed in successfully, show authenticated UI.
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
//            mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
//            updateUI();
            MainActivity.updateInfoUserFromOutside(acct.getDisplayName(), acct.getEmail(), acct.getPhotoUrl().toString());
        } else {
            // Signed out, show unauthenticated UI.
//            updateUI(false);
        }
    }

    private void updateUI() {
        NavigationView navigationView = (NavigationView) nav_header_main.findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        ImageView avatar = (ImageView)header.findViewById(R.id.avatarInfo);
        TextView username = (TextView)header.findViewById(R.id.usernameInfo);
        TextView email = (TextView)header.findViewById(R.id.emailInfo);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        username.setText(sharedPref.getString(getString(R.string.display_name), ""));
        email.setText(sharedPref.getString(getString(R.string.email), ""));
        Picasso.with(getActivity()).load(sharedPref.getString(getString(R.string.photo_url), ""))
                .transform(new CircleTransform()).into(avatar);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        DrawerLayout mDrawer = (DrawerLayout) this.getActivity().findViewById(R.id.drawer_layout);
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }
}
