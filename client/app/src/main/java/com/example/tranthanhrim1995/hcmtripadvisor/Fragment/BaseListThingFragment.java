package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.ListThingsToDoAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.DataGlobal;
import com.example.tranthanhrim1995.hcmtripadvisor.ManageActionBar;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Thing;
import com.example.tranthanhrim1995.hcmtripadvisor.R;

import java.util.ArrayList;

/**
 * Created by tranthanhrim1995 on 1/13/2017.
 */

public class BaseListThingFragment extends Fragment {

    ArrayList<Thing> listThingDestination = new ArrayList<>();
    ArrayList<Thing> listThingHotel = new ArrayList<>();
    RecyclerView rvListThingsToDo;
    ListThingsToDoAdapter mAdapterDestination = null;
    ListThingsToDoAdapter mAdapterHotel = null;
    FragmentManager fragmentManager;

    ArrayList<Thing> listThingToShow = new ArrayList<>();

    String category = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public BaseListThingFragment() {
//        for(int i = 0; i < 7; i++) {
//            String name = "Destination" + i;
//            listThing.add(new Thing("Museums", name, "This is Detail"));
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getActivity().getSupportFragmentManager();
        LinearLayout listThingsToDoFragment = (LinearLayout)inflater.inflate(R.layout.fragment_list_things_to_do, null);

        rvListThingsToDo = (RecyclerView)listThingsToDoFragment.findViewById(R.id.rvListThingsToDo);
        if (mAdapterHotel == null) {
            mAdapterHotel = new ListThingsToDoAdapter(listThingToShow, getActivity().getSupportFragmentManager());
        }
        if (mAdapterDestination == null) {
            mAdapterDestination = new ListThingsToDoAdapter(listThingToShow, getActivity().getSupportFragmentManager());
        }

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvListThingsToDo.setLayoutManager(mLayoutManager);
        rvListThingsToDo.setItemAnimator(new DefaultItemAnimator());
        if (category.equals("Destination")) {
            rvListThingsToDo.setAdapter(mAdapterDestination);
        } else {
            rvListThingsToDo.setAdapter(mAdapterHotel);
        }

        return listThingsToDoFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
//        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        category = bundle.getString("category");
        listThingToShow.clear();
        if (category.equals("Destination")) {
            listThingDestination.clear();
            listThingDestination.addAll(DataGlobal.getInstance().getListDestination());
            rvListThingsToDo.setAdapter(mAdapterDestination);
            mAdapterDestination.notifyDataSetChanged();
            ManageActionBar.getInstance().setTitle("Destination");
            listThingToShow.addAll(DataGlobal.getInstance().getListDestination());
        } else if (category.equals("Hotel")) {
            listThingHotel.clear();
            listThingHotel.addAll(DataGlobal.getInstance().getListHotel());
            rvListThingsToDo.setAdapter(mAdapterHotel);
            mAdapterHotel.notifyDataSetChanged();
            ManageActionBar.getInstance().setTitle("Hotel");
            listThingToShow.addAll(DataGlobal.getInstance().getListHotel());
        }
        ManageActionBar.getInstance().showButtonBack();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_without_nearest, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_all) {
            listThingToShow.clear();
            if (category.equals("Destination")) {
                listThingToShow.addAll(DataGlobal.getInstance().getListDestination());
                mAdapterDestination.notifyDataSetChanged();
            } else if (category.equals("Hotel")) {
                listThingToShow.addAll(DataGlobal.getInstance().getListHotel());
                mAdapterHotel.notifyDataSetChanged();
            }
        }
        else if (id == R.id.action_promotion) {
            for(int i = listThingToShow.size() - 1; i >= 0; i--) {
                if (!listThingToShow.get(i).get_isPromotion())
                    listThingToShow.remove(i);
            }
            mAdapterDestination.notifyDataSetChanged();
            mAdapterHotel.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }
}
