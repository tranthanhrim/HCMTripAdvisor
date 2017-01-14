package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tranthanhrim1995.hcmtripadvisor.ManageActionBar;
import com.example.tranthanhrim1995.hcmtripadvisor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFoodFragment extends Fragment {


    public DetailFoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detal_food, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        ManageActionBar.getInstance().setTitle("Destination");
        ManageActionBar.getInstance().showButtonBack();
    }
}
