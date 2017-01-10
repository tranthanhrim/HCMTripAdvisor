package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.MainScreenMenuAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.FragmentFactory;
import com.example.tranthanhrim1995.hcmtripadvisor.ItemAdapter.ItemMainScreenMenu;
import com.example.tranthanhrim1995.hcmtripadvisor.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    FragmentManager fragmentManager;
    ListView lvMainScreenMenu;
    String[] iconTitles;
    TypedArray icons;
    ArrayList<ItemMainScreenMenu> listItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
                if (position == 0){
                    fragmentManager.beginTransaction().replace(R.id.container,
                            FragmentFactory.getInstance().getNearMeNowFragment()).commit();
                }
                if (position == 3) {
                    fragmentManager.beginTransaction().replace(R.id.container,
                            FragmentFactory.getInstance().getGroupedThingsToDoFragment()).commit();
                }
                else if (position == 4) {
                    fragmentManager.beginTransaction().replace(R.id.container,
                            FragmentFactory.getInstance().getThingsToDoFragment()).commit();
                }
            }
        });

        return mainFragment;
    }

}
