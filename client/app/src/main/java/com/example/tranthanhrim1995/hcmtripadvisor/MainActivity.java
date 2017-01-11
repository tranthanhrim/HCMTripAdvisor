package com.example.tranthanhrim1995.hcmtripadvisor;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        View hView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
//        ImageView avatar = (ImageView)hView.findViewById(R.id.imageView);
//        TextView email = (TextView)hView.findViewById(R.id.);
////        imgvw .setImageResource();
//        email.setText("new text");

        //Change avatar and name
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPref.contains(getString(R.string.google_id))
                && !sharedPref.getString(getString(R.string.google_id), "").isEmpty()) {
            View header = navigationView.getHeaderView(0);
            ImageView avatar = (ImageView)header.findViewById(R.id.avatarInfo);
            TextView username = (TextView)header.findViewById(R.id.usernameInfo);
            TextView email = (TextView)header.findViewById(R.id.emailInfo);

            username.setText(sharedPref.getString(getString(R.string.display_name), ""));
            email.setText(sharedPref.getString(getString(R.string.email), ""));
//            ImageLoader imageLoader = ImageLoader.getInstance();
//            Bitmap bmp = imageLoader.loadImageSync(sharedPref.getString(getString(R.string.photo_url), ""));
//            Bitmap bmp = imageLoader.loadImageSync("http://lh3.googleusercontent.com/-BQovJQPw6Nw/AAAAAAAAAAI/AAAAAAAAAHk/paY4_MeEJc4/photo.jpg");
//            avatar.setImageBitmap(bmp);
            Picasso.with(this).load(sharedPref.getString(getString(R.string.photo_url), ""))
                        .transform(new CircleTransform()).into(avatar);
//            imageLoader.displayImage(uri, avatar);
//            URL url = null;
//            try {
//                url = new URL("http://image10.bizrate-images.com/resize?sq=60&uid=2216744464");
//                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                avatar.setImageBitmap(bmp);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }





        fragmentManager.beginTransaction().replace(R.id.container,
            FragmentFactory.getInstance().getSigninFragment()).commit();

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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_logout) {
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
}
