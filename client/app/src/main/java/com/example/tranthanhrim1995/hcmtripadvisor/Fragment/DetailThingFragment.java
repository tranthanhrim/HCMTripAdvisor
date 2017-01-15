package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.GroupedThingsToDoAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.DataGlobal;
import com.example.tranthanhrim1995.hcmtripadvisor.FragmentFactory;
import com.example.tranthanhrim1995.hcmtripadvisor.MainActivity;
import com.example.tranthanhrim1995.hcmtripadvisor.ManageActionBar;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Location;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Thing;
import com.example.tranthanhrim1995.hcmtripadvisor.R;
import com.example.tranthanhrim1995.hcmtripadvisor.WebServiceInterface;
import com.kobakei.ratethisapp.RateThisApp;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailThingFragment extends Fragment {

    ArrayList<Thing> listHotel, listFood;
    RecyclerView rvHotelDetailThing, rvFoodDetailThing, rvListComment;
    ImageView btnCheckinDetail, btnLikeDetail;
    ImageView btnClickRate;

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

    @BindView(R.id.rbSummaryRateDetail)
    RatingBar rbSummaryRateDetail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getActivity().getSupportFragmentManager();

        ScrollView detailThingFragment = (ScrollView) inflater.inflate(R.layout.fragment_detail_thing, null);
        ButterKnife.bind(this, detailThingFragment);

        LayerDrawable stars = (LayerDrawable) rbSummaryRateDetail.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#569441"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(Color.parseColor("#CFCFCF"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(Color.parseColor("#CFCFCF"), PorterDuff.Mode.SRC_ATOP);

        //RecyclerView Hotel
        mAdapterHotel = new GroupedThingsToDoAdapter(listHotel, getActivity().getSupportFragmentManager());
        LinearLayoutManager hlayoutManager
                = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvHotelDetailThing = (RecyclerView)detailThingFragment.findViewById(R.id.rvHotelDetailThing);
        rvHotelDetailThing.setLayoutManager(hlayoutManager);
        rvHotelDetailThing.setItemAnimator(new DefaultItemAnimator());
        rvHotelDetailThing.setAdapter(mAdapterHotel);

        //RecyclerView Food
        mAdapterFood = new GroupedThingsToDoAdapter(listFood, getActivity().getSupportFragmentManager());
        LinearLayoutManager h2layoutManager
                = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvFoodDetailThing = (RecyclerView)detailThingFragment.findViewById(R.id.rvFoodDetailThing);
        rvFoodDetailThing.setLayoutManager(h2layoutManager);
        rvFoodDetailThing.setItemAnimator(new DefaultItemAnimator());
        rvFoodDetailThing.setAdapter(mAdapterFood);

        WebServiceInterface service = DataGlobal.getInstance().getService();


        //RecyclerView List Comment
//        RecyclerView.LayoutManager mLayoutManagerListComment = new LinearLayoutManager(getActivity().getApplicationContext());
//        rvListComment = (RecyclerView)detailThingFragment.findViewById(R.id.rvListComment);
//        rvListComment.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
//        rvListComment.setHasFixedSize(true);

        //Button map

        //Button rate
        btnClickRate = (ImageView)detailThingFragment.findViewById(R.id.btnClickRate);
        btnClickRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentFactory.getInstance().getRateDialogFragment().show(getActivity().getFragmentManager(), "rate-dialog");
            }
        });

        return detailThingFragment;
    }

    @OnClick(R.id.btnMapDetail) void showMap() {
//        Bundle bundle = new Bundle();
//        Location location = new Location;
//        bundle.putParcelable();
        Fragment fragment = FragmentFactory.getInstance().getMapThingFragment();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
    }

    @OnClick(R.id.layoutImageDetail) void showGridImage() {
        Bundle bundle = new Bundle();
        bundle.putString("image", "123456");
        Fragment fragment = FragmentFactory.getInstance().getGridImageFragment();
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.container,fragment).addToBackStack(null).commit();
    }

    @OnClick(R.id.btnSeeAllComments) void showAllComments() {
        FragmentFactory.getInstance().getListCommentDialogFragment().show(getActivity().getFragmentManager(), "all-comments");
    }

    @Override
    public void onResume() {
        super.onResume();
        ManageActionBar.getInstance().setTitle("Detail thing");
        ManageActionBar.getInstance().showButtonBack();
    }


}
