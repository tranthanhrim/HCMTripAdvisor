package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.GroupedThingsToDoAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.FragmentFactory;
import com.example.tranthanhrim1995.hcmtripadvisor.MainActivity;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Thing;
import com.example.tranthanhrim1995.hcmtripadvisor.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailThingFragment extends Fragment {

    ArrayList<Thing> listHotel, listFood;
    RatingBar rbRateDetail;
    RecyclerView rvHotelDetailThing, rvFoodDetailThing;
    GroupedThingsToDoAdapter mAdapterHotel, mAdapterFood;
    FragmentManager fragmentManager;
    public DetailThingFragment() {
        listHotel = new ArrayList<>();
        listFood = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            String name = "Destination" + i;
            listHotel.add(new Thing("Museums", name, "This is Detail"));
            listFood.add(new Thing("Museums", name, "This is Detail"));
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getActivity().getSupportFragmentManager();

        ScrollView detailThingFragment = (ScrollView) inflater.inflate(R.layout.fragment_detail_thing, null);
        rbRateDetail = (RatingBar)detailThingFragment.findViewById(R.id.rbRateDetail);

        LayerDrawable stars = (LayerDrawable) rbRateDetail.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#569441"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(Color.parseColor("#569441"), PorterDuff.Mode.SRC_ATOP);

        mAdapterHotel = new GroupedThingsToDoAdapter(listHotel, getActivity().getSupportFragmentManager());
        LinearLayoutManager hlayoutManager
                = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvHotelDetailThing = (RecyclerView)detailThingFragment.findViewById(R.id.rvHotelDetailThing);
        rvHotelDetailThing.setLayoutManager(hlayoutManager);
        rvHotelDetailThing.setItemAnimator(new DefaultItemAnimator());
        rvHotelDetailThing.setAdapter(mAdapterHotel);

        mAdapterFood = new GroupedThingsToDoAdapter(listFood, getActivity().getSupportFragmentManager());
        LinearLayoutManager h2layoutManager
                = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvFoodDetailThing = (RecyclerView)detailThingFragment.findViewById(R.id.rvFoodDetailThing);
        rvFoodDetailThing.setLayoutManager(h2layoutManager);
        rvFoodDetailThing.setItemAnimator(new DefaultItemAnimator());
        rvFoodDetailThing.setAdapter(mAdapterFood);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Detail Thing");
//        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return detailThingFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
//        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
