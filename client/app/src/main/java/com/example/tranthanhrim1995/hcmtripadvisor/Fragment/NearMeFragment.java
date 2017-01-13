package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.ListThingsToDoAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.TabNearMeAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.FragmentFactory;
import com.example.tranthanhrim1995.hcmtripadvisor.ManageActionBar;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Thing;
import com.example.tranthanhrim1995.hcmtripadvisor.R;
import com.example.tranthanhrim1995.hcmtripadvisor.WebServiceInterface;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearMeFragment extends BaseFragment implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    ArrayList<Thing> listThing = new ArrayList<>();
    FragmentManager fragmentManager;
    GoogleApiClient mGoogleApiClient;

    Spinner spCategory;
    RecyclerView rvNearMe;
    ListThingsToDoAdapter mAdapter;

    WebServiceInterface service;
    private Call<List<Thing>> callGetThingsNearMe;
    private GetThingsNearMeDelegate getThingsNearMeDelegate;

    Location currentLocation;

    public NearMeFragment() {
//        for(int i = 0; i < 7; i++) {
//            String name = "Destination" + i;
//            listThing.add(new Thing("Museums", name, "This is Detail"));
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        fragmentManager = getActivity().getSupportFragmentManager();
        LinearLayout nearMeFragment = (LinearLayout)inflater.inflate(R.layout.fragment_near_me, null);

        spCategory = (Spinner) nearMeFragment.findViewById(R.id.spCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);
        spCategory.setSelection(0);

        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) { //All

                } else if (position == 1) { //Destination

                } else if (position == 2) { //Hotel

                } else if (position == 3) { //Food

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rvNearMe = (RecyclerView)nearMeFragment.findViewById(R.id.rvNearMe);
        mAdapter = new ListThingsToDoAdapter(listThing, getActivity().getSupportFragmentManager());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvNearMe.setLayoutManager(mLayoutManager);
        rvNearMe.setItemAnimator(new DefaultItemAnimator());
        rvNearMe.setAdapter(mAdapter);

        fragmentManager = getActivity().getSupportFragmentManager();
        LocationManager manager = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            FragmentFactory.getInstance().getGpsNotFoundDialog().show(getActivity().getFragmentManager(), "gps-not-found");
        }
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        if (listThing.size() == 0) {
            OkHttpClient client = new OkHttpClient.Builder().build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://hcmtripadvisor.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            service = retrofit.create(WebServiceInterface.class);

            callGetThingsNearMe = service.listThingsToDo();
            getThingsNearMeDelegate = new GetThingsNearMeDelegate(this);
            showProgressDialog();
            callGetThingsNearMe.enqueue(getThingsNearMeDelegate);
        }

        return nearMeFragment;
    }

    /*Google API Location*/
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (mLastLocation != null) {
                currentLocation = mLastLocation;
                return;
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        ManageActionBar.getInstance().setTitle("Near me");
        ManageActionBar.getInstance().showButtonBack();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_thing, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_nearest) {

        } else if (id == R.id.action_promotion) {

        } else if (id == R.id.action_price_decrease) {

        } else if (id == R.id.action_price_increase) {

        }
        return super.onOptionsItemSelected(item);
    }

    private static class GetThingsNearMeDelegate implements Callback<List<Thing>> {

        private final WeakReference<NearMeFragment> fragmentWeakReference;

        private GetThingsNearMeDelegate(@NonNull final NearMeFragment fragment) {
            this.fragmentWeakReference = new WeakReference<>(fragment);
        }

        @Override
        public void onResponse(Call<List<Thing>> call, Response<List<Thing>> response) {
            NearMeFragment fragment = fragmentWeakReference.get();
            if (fragment != null) {
                fragment.dismissProgressDialog();
                fragment.listThing.clear();
                fragment.listThing.addAll(response.body());
                fragment.mAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onFailure(Call<List<Thing>> call, Throwable t) {

        }
    }

}
