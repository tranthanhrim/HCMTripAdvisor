package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.tranthanhrim1995.hcmtripadvisor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailThingFragment extends Fragment {


    public DetailThingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ScrollView detailThingFragment = (ScrollView) inflater.inflate(R.layout.fragment_detail_thing, null);
        return detailThingFragment;
    }

}
