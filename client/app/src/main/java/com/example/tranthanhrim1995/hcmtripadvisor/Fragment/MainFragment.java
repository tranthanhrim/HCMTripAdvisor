package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;


import android.content.res.TypedArray;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.MainScreenMenuAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.ConnectionChecking;
import com.example.tranthanhrim1995.hcmtripadvisor.FragmentFactory;
import com.example.tranthanhrim1995.hcmtripadvisor.ItemAdapter.ItemMainScreenMenu;
import com.example.tranthanhrim1995.hcmtripadvisor.MainActivity;
import com.example.tranthanhrim1995.hcmtripadvisor.ManageActionBar;
import com.example.tranthanhrim1995.hcmtripadvisor.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment{

    FragmentManager fragmentManager;
    ListView lvMainScreenMenu;
    String[] iconTitles;
    TypedArray icons;
    ArrayList<ItemMainScreenMenu> listItem;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ManageActionBar.getInstance().init(getActivity());
//        ConnectionChecking.getInstance().init(getActivity());

        LinearLayout mainFragment = (LinearLayout)inflater.inflate(R.layout.fragment_main, null);

        fragmentManager = getActivity().getSupportFragmentManager();
        iconTitles = getActivity().getResources().getStringArray(R.array.list_label_main_screen_menu);
        icons = getActivity().getResources().obtainTypedArray(R.array.list_icon_main_screen_menu);

        listItem = new ArrayList<ItemMainScreenMenu>();
        for(int i = 0; i < iconTitles.length; i++){
            listItem.add(new ItemMainScreenMenu(iconTitles[i], icons.getResourceId(i, -1)));
        }
        MainScreenMenuAdapter adapter = new MainScreenMenuAdapter(getActivity(),
                R.layout.item_main_screen_menu, listItem);
        lvMainScreenMenu = (ListView)mainFragment.findViewById(R.id.lvMainScreenMenu);
        lvMainScreenMenu.setAdapter(adapter);

        lvMainScreenMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (!ConnectionChecking.getInstance().isInternetEnabled()) {
                    FragmentFactory.getInstance().getInternetNotFoundDialog()
                            .show(getActivity().getFragmentManager(), "no-internet");
                    return;
                }
                if (position == 0){ //Near me
                    if (ConnectionChecking.getInstance().isGpsEnabled()) {
                        fragmentManager.beginTransaction().replace(R.id.container,
                                FragmentFactory.getInstance().getNearMeFragment()).addToBackStack(null).commit();
                    } else {
                        FragmentFactory.getInstance().getGpsNotFoundDialog()
                                .show(getActivity().getFragmentManager(), "gps-not-found");
                    }
                } else if (position == 1) { //Hotel
                    Bundle bundle = new Bundle();
                    bundle.putString("category", "Hotel");
                    Fragment fragment = FragmentFactory.getInstance().getDestinationFragment();
                    fragment.setArguments(bundle);
                    fragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();

                } else if (position == 2) { //Food
                    fragmentManager.beginTransaction().replace(R.id.container,
                            FragmentFactory.getInstance().getListFoodFragment()).addToBackStack(null).commit();
                } else if (position == 3) { //Things to do
                    fragmentManager.beginTransaction().replace(R.id.container,
                            FragmentFactory.getInstance().getGroupedThingsToDoFragment()).addToBackStack(null).commit();
                } else if (position == 4) {
                    fragmentManager.beginTransaction().replace(R.id.container,
                            (Fragment)FragmentFactory.getInstance().getMapThingFragment()).addToBackStack(null).commit();
                }
            }
        });

        return mainFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().show();
        DrawerLayout mDrawer = (DrawerLayout) this.getActivity().findViewById(R.id.drawer_layout);
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        ManageActionBar.getInstance().setTitle("HCM TripAdvisor");
//        setHasOptionsMenu(false);
    }

    @Override
    public void onStop() {
        super.onStop();
        DrawerLayout mDrawer = (DrawerLayout) this.getActivity().findViewById(R.id.drawer_layout);
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
    }
}
