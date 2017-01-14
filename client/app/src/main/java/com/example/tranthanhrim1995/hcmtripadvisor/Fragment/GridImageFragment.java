package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.GridImageAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.FragmentFactory;
import com.example.tranthanhrim1995.hcmtripadvisor.MainActivity;
import com.example.tranthanhrim1995.hcmtripadvisor.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GridImageFragment extends BaseFragment {

    ArrayList<String> images = new ArrayList<>();
    FragmentManager fragmentManager;
    RecyclerView rvGridImage;
    GridImageAdapter mAdapter;

    String oldImage = "";

    public GridImageFragment() {
        String image = "https://firebasestorage.googleapis.com/v0/b/testuploadimage-8f69f.appspot.com/o/cuchi300.jpg?alt=media&token=782ccfd4-6bfd-4307-bf16-a1c70276f952";
        images.add(image);
        images.add(image);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getActivity().getSupportFragmentManager();
        LinearLayout gridImageFragment = (LinearLayout) inflater.inflate(R.layout.fragment_grid_image, null);

        Bundle bundle = getArguments();
        if (!oldImage.equals(bundle.getString("image"))) {
            mAdapter = new GridImageAdapter(images, fragmentManager, getActivity());
            oldImage = bundle.getString("image");
        }

        //Calculate num of column
        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) ((dpWidth - 20) / 104);
        RecyclerView.LayoutManager glayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), noOfColumns);

        rvGridImage = (RecyclerView)gridImageFragment.findViewById(R.id.rvGridImage);
        rvGridImage.setLayoutManager(glayoutManager);
        rvGridImage.setItemAnimator(new DefaultItemAnimator());
        rvGridImage.setAdapter(mAdapter);
        rvGridImage.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mAdapter.getNumOfImageLoaded() == 0) {
//                    showProgressDialog();
                }
                if (mAdapter.getItemCount() == mAdapter.getNumOfImageLoaded()) {
//                    dismissProgressDialog();
                }
            }
        });

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Images");
        ((MainActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return gridImageFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Images");
        ((MainActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
