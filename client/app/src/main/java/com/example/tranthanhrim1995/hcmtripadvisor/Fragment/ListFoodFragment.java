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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.ListFoodAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.ListThingsToDoAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.DataGlobal;
import com.example.tranthanhrim1995.hcmtripadvisor.ManageActionBar;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Thing;
import com.example.tranthanhrim1995.hcmtripadvisor.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by tranthanhrim1995 on 1/14/2017.
 */

public class ListFoodFragment extends Fragment {

    ArrayList<Thing> listThing = new ArrayList<>();
    ArrayList<Thing> listThingToShow = new ArrayList<>();
    RecyclerView rvListThing;
    Spinner spCategory;
    ListFoodAdapter mAdapter = null;
    FragmentManager fragmentManager;
    ArrayList<String> meal = new ArrayList<>(Arrays.asList("Breakfast", "Lunch", "Dinner", "Drink", "Other"));
    String typeOfFood = "All";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public ListFoodFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getActivity().getSupportFragmentManager();
        LinearLayout listFoodFragment = (LinearLayout)inflater.inflate(R.layout.layout_category_list_thing, null);

        rvListThing = (RecyclerView)listFoodFragment.findViewById(R.id.rvListThing);
        if (mAdapter == null) {
            if (DataGlobal.getInstance().getListThingsTodo().size() == 0) {
                Toast.makeText(getActivity(), "Data is loading...", Toast.LENGTH_SHORT).show();
                fragmentManager.popBackStack();
            } else {
                listThing.clear();
                listThing.addAll(DataGlobal.getInstance().getListFoodnDrink());
                listThingToShow.clear();
                listThingToShow.addAll(DataGlobal.getInstance().getListFoodnDrink());
                mAdapter = new ListFoodAdapter(listThingToShow, getActivity().getSupportFragmentManager());
            }
        }
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvListThing.setLayoutManager(mLayoutManager);
        rvListThing.setItemAnimator(new DefaultItemAnimator());
        rvListThing.setAdapter(mAdapter);

        spCategory = (Spinner) listFoodFragment.findViewById(R.id.spCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.category_food, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);
        spCategory.setSelection(0);

        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                listThingToShow.clear();
                if (position == 0) { //All
                    listThingToShow.addAll(listThing);
                    typeOfFood = "All";
                } else{
                    for (int i = 0; i < listThing.size(); i++) {
                        ArrayList<String> tempMeal = new ArrayList<String>(listThing.get(i).get_meal());
                        if (tempMeal.contains(meal.get(position - 1))){
                            listThingToShow.add(listThing.get(i));
                        }
                    }
                    typeOfFood = meal.get(position - 1);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return listFoodFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        ManageActionBar.getInstance().setTitle("Food & Drink");
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
            if (typeOfFood.equals("All")) {
                listThingToShow.addAll(listThing);
            } else {
                for (int i = 0; i < listThing.size(); i++) {
                    ArrayList<String> tempMeal = new ArrayList<>(listThing.get(i).get_meal());
                    if (tempMeal.contains(typeOfFood)) {
                        listThingToShow.add(listThing.get(i));
                    }
                }
            }
            mAdapter.notifyDataSetChanged();
        } else if (id == R.id.action_promotion) {
            for(int i = listThingToShow.size() - 1; i >= 0; i--) {
                if (!listThingToShow.get(i).get_isPromotion())
                    listThingToShow.remove(i);
            }
        }
        mAdapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }
}
