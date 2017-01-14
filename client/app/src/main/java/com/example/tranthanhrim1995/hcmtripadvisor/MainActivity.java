package com.example.tranthanhrim1995.hcmtripadvisor;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.MainFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager = getSupportFragmentManager();
    static Context context;
    static NavigationView navigationView;
    LoadingDataGlobal taskLoadingData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ConnectionChecking.getInstance().init(this);
        context = getBaseContext();
        taskLoadingData = new LoadingDataGlobal();
        taskLoadingData.execute();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BackToMainScreen.setFragmentManager(getSupportFragmentManager());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (!ConnectionChecking.getInstance().isInternetEnabled()) {
            FragmentFactory.getInstance().getInternetNotFoundDialog().show(getFragmentManager(), "no-internet");
        }
        fragmentManager.beginTransaction().replace(R.id.container,
                FragmentFactory.getInstance().getSigninFragment()).commit();

        //Change avatar and name
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPref.contains(getString(R.string.google_id))
                && !sharedPref.getString(getString(R.string.google_id), "").isEmpty()) {
//            View header = navigationView.getHeaderView(0);
//            ImageView avatar = (ImageView)header.findViewById(R.id.avatarInfo);
//            TextView username = (TextView)header.findViewById(R.id.usernameInfo);
//            TextView email = (TextView)header.findViewById(R.id.emailInfo);
//
//            username.setText(sharedPref.getString(getString(R.string.display_name), ""));
//            email.setText(sharedPref.getString(getString(R.string.email), ""));
//            Picasso.with(this).load(sharedPref.getString(getString(R.string.photo_url), ""))
//                        .transform(new CircleTransform()).into(avatar);
            updateInfoUser(navigationView, sharedPref.getString(getString(R.string.display_name), ""),
                    sharedPref.getString(getString(R.string.email), ""),
                    sharedPref.getString(getString(R.string.photo_url), ""));
        }


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        //Change menu icon to back icon
        final View.OnClickListener originalToolbarListener = toggle.getToolbarNavigationClickListener();
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    toggle.setDrawerIndicatorEnabled(false);
                    toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getSupportFragmentManager().popBackStack();
                        }
                    });
                } else {
                    toggle.setDrawerIndicatorEnabled(true);
                    toggle.setToolbarNavigationClickListener(originalToolbarListener);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
//        navigationView.getMenu().getItem(item.getItemId()).setChecked(false);
        navigationView.getMenu().findItem(item.getItemId()).setChecked(false);

       if (id == R.id.nav_logout) {
            GoogleApiClient mGoogleApiClient = GoogleApiClientInstance.getInstance(this).getGoogleApiClient();
//            return false;
            Auth.GoogleSignInApi.signOut(GoogleApiClientInstance.getInstance(this).getGoogleApiClient()).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString(getString(R.string.google_id), "").apply();
                        editor.putString(getString(R.string.display_name), "").apply();
                        editor.putString(getString(R.string.email), "").apply();
                        editor.putString(getString(R.string.google_id_token), "").apply();
                        editor.putString(getString(R.string.photo_url), "").apply();
                        fragmentManager.beginTransaction().replace(R.id.container,
                                FragmentFactory.getInstance().getSigninFragment()).commit();
                    }
                }
            );
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private static void updateInfoUser(NavigationView navigationView, String username, String email, String photoUrl) {
        View header = navigationView.getHeaderView(0);
        ImageView ivAvatar = (ImageView)header.findViewById(R.id.avatarInfo);
        TextView tvUsername = (TextView)header.findViewById(R.id.usernameInfo);
        TextView tvEmail = (TextView)header.findViewById(R.id.emailInfo);

        tvUsername.setText(username);
        tvEmail.setText(email);
        Picasso.with(context).load(photoUrl).transform(new CircleTransform()).into(ivAvatar);
    }

    public static void updateInfoUserFromOutside(String username, String email, String photoUrl) {
        updateInfoUser(navigationView, username, email, photoUrl);
    }

    private static class LoadingDataGlobal extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            DataGlobal.getInstance().init(context);
            //Inject fragment to DataGlobal
//            DataGlobal.getInstance().setGroupedThingsToDoFragment(
//                    FragmentFactory.getInstance().getGroupedThingsToDoFragment()
//            );
            return null;
        }
    }

}
