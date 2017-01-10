package com.example.tranthanhrim1995.hcmtripadvisor.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by tranthanhrim1995 on 1/10/2017.
 */

public class TabNearMeAdapter extends FragmentPagerAdapter {

    FragmentManager fragmentManager;
    int tabCount;
    public TabNearMeAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
        this.fragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
