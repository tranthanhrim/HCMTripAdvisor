package com.example.tranthanhrim1995.hcmtripadvisor.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.BaseListThingFragment;

/**
 * Created by tranthanhrim1995 on 1/10/2017.
 */

public class TabNearMeAdapter extends FragmentPagerAdapter {

    FragmentManager fragmentManager;
    private BaseListThingFragment listDestinationFragment;
    private BaseListThingFragment listHotelFragment;
    private BaseListThingFragment listFoodFragment;
    int tabCount;
    public TabNearMeAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
        this.fragmentManager = fm;
        setListDestinationFragment(new BaseListThingFragment());
        setListHotelFragment(new BaseListThingFragment());
        setListFoodFragment(new BaseListThingFragment());
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return listDestinationFragment;
            case 1: return listHotelFragment;
            case 2: return listFoodFragment;
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    public BaseListThingFragment getListDestinationFragment() {
        return listDestinationFragment;
    }

    public void setListDestinationFragment(BaseListThingFragment listDestinationFragment) {
        this.listDestinationFragment = listDestinationFragment;
    }

    public BaseListThingFragment getListHotelFragment() {
        return listHotelFragment;
    }

    public void setListHotelFragment(BaseListThingFragment listHotelFragment) {
        this.listHotelFragment = listHotelFragment;
    }

    public BaseListThingFragment getListFoodFragment() {
        return listFoodFragment;
    }

    public void setListFoodFragment(BaseListThingFragment listFoodFragment) {
        this.listFoodFragment = listFoodFragment;
    }
}
