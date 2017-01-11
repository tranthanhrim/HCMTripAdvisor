package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.ListThingsToDoAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.FragmentFactory;
import com.example.tranthanhrim1995.hcmtripadvisor.MainActivity;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Thing;
import com.example.tranthanhrim1995.hcmtripadvisor.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListThingsToDoFragment extends Fragment {

    ArrayList<Thing> listThing = new ArrayList<>();
    RecyclerView rvListThingsToDo;
    ListThingsToDoAdapter mAdapter;
    FragmentManager fragmentManager;

    public ListThingsToDoFragment() {
//        fragmentManager = getActivity().getSupportFragmentManager();
        for(int i = 0; i < 7; i++) {
            String name = "Destination" + i;
            listThing.add(new Thing("Museums", name, "This is Detail"));
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getActivity().getSupportFragmentManager();
        LinearLayout listThingsToDoFragment = (LinearLayout)inflater.inflate(R.layout.fragment_list_things_to_do, null);

        rvListThingsToDo = (RecyclerView)listThingsToDoFragment.findViewById(R.id.rvListThingsToDo);
        mAdapter = new ListThingsToDoAdapter(listThing, getActivity().getSupportFragmentManager());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvListThingsToDo.setLayoutManager(mLayoutManager);
        rvListThingsToDo.setItemAnimator(new DefaultItemAnimator());
        rvListThingsToDo.setAdapter(mAdapter);

        return listThingsToDoFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    fragmentManager.beginTransaction().replace(R.id.container,
                            FragmentFactory.getInstance().getGroupedThingsToDoFragment()).commit();
                    return true;
                }
                return false;
            }
        });
    }
}
