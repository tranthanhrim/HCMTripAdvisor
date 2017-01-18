package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;


import android.database.sqlite.SQLiteBindOrColumnIndexOutOfRangeException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.tranthanhrim1995.hcmtripadvisor.MainActivity;
import com.example.tranthanhrim1995.hcmtripadvisor.ManageActionBar;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Location;
import com.example.tranthanhrim1995.hcmtripadvisor.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapThingFragment extends Fragment implements OnMapReadyCallback {

    //    FragmentManager fragmentManager;
    SupportMapFragment mMapFragment;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    FragmentManager fm;
    Float lon = (float)106.4605228;
    Float lat = (float)11.1423988;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        fragmentManager = getActivity().getSupportFragmentManager();
        LinearLayout mapThingFragment = (LinearLayout)inflater.inflate(R.layout.fragment_map_thing, null);
        fm = getChildFragmentManager();
        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return mapThingFragment;

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Cu Chi Tunnel"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = getArguments();
        lon = bundle.getFloat("lon");
        lat = bundle.getFloat("lat");
        mapFragment.getMapAsync(this);
        ManageActionBar.getInstance().setTitle(bundle.getString("nameThing"));
        ManageActionBar.getInstance().showButtonBack();
    }
}
