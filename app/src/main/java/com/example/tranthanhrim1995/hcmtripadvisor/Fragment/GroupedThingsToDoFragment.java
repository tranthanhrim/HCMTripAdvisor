package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.GroupedThingsToDoAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Thing;
import com.example.tranthanhrim1995.hcmtripadvisor.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupedThingsToDoFragment extends Fragment {

    ArrayList<Thing> listThing = new ArrayList<>();
    RecyclerView recyclerView;
    GroupedThingsToDoAdapter mAdapter;

    public GroupedThingsToDoFragment() {
        for(int i = 0; i < 7; i++) {
            String name = "Destination" + i;
            listThing.add(new Thing("Museums", name, "This is Detail"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout groupedThingFragment = (LinearLayout)inflater.inflate(R.layout.fragment_grouped_things_to_do, null);
        recyclerView = (RecyclerView) groupedThingFragment.findViewById(R.id.rvGroupedThings);
        mAdapter = new GroupedThingsToDoAdapter(listThing);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        //Horizontal RecyclerView
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        return groupedThingFragment;
    }

}
