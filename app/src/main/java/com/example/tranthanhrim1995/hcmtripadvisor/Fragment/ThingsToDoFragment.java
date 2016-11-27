package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.ThingsToDoAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.BackToMainScreen;
import com.example.tranthanhrim1995.hcmtripadvisor.FragmentFactory;
import com.example.tranthanhrim1995.hcmtripadvisor.ItemAdapter.ItemMainScreenMenu;
import com.example.tranthanhrim1995.hcmtripadvisor.ItemAdapter.ItemThingsToDo;
import com.example.tranthanhrim1995.hcmtripadvisor.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThingsToDoFragment extends Fragment {

//    @BindView(R.id.rvThingsToDo)
    RecyclerView rvThingsToDo;

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    FragmentManager fragmentManager;
    String[] iconTitles;
    TypedArray icons;
    List<ItemThingsToDo> listItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ButterKnife.bind(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout thingsToDoFragment = (LinearLayout)inflater.inflate(R.layout.fragment_things_to_do, null);
        rvThingsToDo = (RecyclerView)thingsToDoFragment.findViewById(R.id.rvThingsToDo);

        rvThingsToDo.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        rvThingsToDo.setLayoutManager(mLayoutManager);

        fragmentManager = getActivity().getSupportFragmentManager();
        iconTitles = getActivity().getResources().getStringArray(R.array.list_label_things_to_do);
        icons = getActivity().getResources().obtainTypedArray(R.array.list_icon_things_to_do);
        listItem = new ArrayList<>();
        for(int i = 0; i < iconTitles.length; i++){
            listItem.add(new ItemThingsToDo(iconTitles[i], icons.getResourceId(i, -1)));
        }
        mAdapter = new ThingsToDoAdapter(getActivity(), listItem);
        rvThingsToDo.setAdapter(mAdapter);

        // Inflate the layout for this fragment
        return thingsToDoFragment;
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
                            FragmentFactory.getInstance().getMainFragment()).commit();
                    return true;
                }
                return true;
            }
        });
    }
}
