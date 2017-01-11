package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.TabNearMeAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearMeFragment extends Fragment implements TabLayout.OnTabSelectedListener{

    TabLayout tabLayout;
    ViewPager viewPager;
    TabNearMeAdapter adapter;
    FragmentManager fragmentManager;
    public NearMeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getActivity().getSupportFragmentManager();
        LinearLayout nearMeFragment = (LinearLayout)inflater.inflate(R.layout.fragment_near_me, null);

        //Initializing the tablayout
        TabLayout tabLayout = (TabLayout) nearMeFragment.findViewById(R.id.tabLayoutNearMe);
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        tab.select();

        tabLayout.getTabAt(0).setIcon(R.drawable.icon_search_things_to_do_white);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_type_ahead_hotel_white);
        tabLayout.getTabAt(2).setIcon(R.drawable.inverted_food_drink_white);

        //Initializing viewPager
        viewPager = (ViewPager) nearMeFragment.findViewById(R.id.pagerNearMe);

        //Creating our pager adapter
        adapter = new TabNearMeAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        tabLayout.setOnTabSelectedListener(this);

        return nearMeFragment;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
