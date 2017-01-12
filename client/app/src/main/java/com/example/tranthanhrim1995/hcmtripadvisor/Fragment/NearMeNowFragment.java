package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;


import android.app.DialogFragment;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.tranthanhrim1995.hcmtripadvisor.FragmentFactory;
import com.example.tranthanhrim1995.hcmtripadvisor.R;
import com.example.tranthanhrim1995.hcmtripadvisor.dialog.MessageDialogFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearMeNowFragment extends Fragment implements OnMapReadyCallback {

    FragmentManager fragmentManager;
    GoogleMap mMap;
    public NearMeNowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getActivity().getSupportFragmentManager();
        LocationManager manager = (LocationManager) getActivity().getSystemService( getActivity().LOCATION_SERVICE );

        if (!manager.isProviderEnabled( LocationManager.GPS_PROVIDER)) {
//            Bundle bundle = new Bundle();
//            bundle.putString("messageDialog", "GPS Not found!");
//            DialogFragment a = new MessageDialogFragment();
//            a.setArguments(bundle);
//            a.show(getActivity().getFragmentManager(), null);
            FragmentFactory.getInstance().getGpsNotFoundDialog().show(getActivity().getFragmentManager(), "gps-not-found");

        }
        LinearLayout nearMeNowFragment = (LinearLayout)inflater.inflate(R.layout.fragment_near_me_now, null);
        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return nearMeNowFragment;


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
