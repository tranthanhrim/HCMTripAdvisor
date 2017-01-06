package com.example.tranthanhrim1995.hcmtripadvisor;

import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.DetailThingFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.GroupedThingsToDoFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.MainFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.ThingsToDoFragment;

/**
 * Created by tranthanhrim1995 on 11/27/2016.
 */

public class FragmentFactory {

    private MainFragment mainFragment;
    private ThingsToDoFragment thingsToDoFragment;
    private GroupedThingsToDoFragment groupedThingsToDoFragment;
    private DetailThingFragment detailThingFragment;

    private static FragmentFactory instance = null;

    private FragmentFactory() {
        setMainFragment(new MainFragment());
        setThingsToDoFragment(new ThingsToDoFragment());
        setGroupedThingsToDoFragment(new GroupedThingsToDoFragment());
        setDetailThingFragment(new DetailThingFragment());
    }

    public static FragmentFactory getInstance() {
        if (instance == null) {
            instance = new FragmentFactory();
        }
        return instance;
    }

    public MainFragment getMainFragment() {
        return mainFragment;
    }

    public void setMainFragment(MainFragment mainFragment) {
        this.mainFragment = mainFragment;
    }

    public ThingsToDoFragment getThingsToDoFragment() {
        return thingsToDoFragment;
    }

    public void setThingsToDoFragment(ThingsToDoFragment thingsToDoFragment) {
        this.thingsToDoFragment = thingsToDoFragment;
    }

    public GroupedThingsToDoFragment getGroupedThingsToDoFragment() {
        return groupedThingsToDoFragment;
    }

    public void setGroupedThingsToDoFragment(GroupedThingsToDoFragment groupedThingsToDoFragment) {
        this.groupedThingsToDoFragment = groupedThingsToDoFragment;
    }

    public DetailThingFragment getDetailThingFragment() {
        return detailThingFragment;
    }

    public void setDetailThingFragment(DetailThingFragment detailThingFragment) {
        this.detailThingFragment = detailThingFragment;
    }
}
