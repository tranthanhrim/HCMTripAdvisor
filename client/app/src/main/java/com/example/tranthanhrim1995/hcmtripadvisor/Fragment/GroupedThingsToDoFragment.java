package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.GroupedThingsToDoAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.ListThingsToDoAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.ThumbnailActivitiesAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.DataGlobal;
import com.example.tranthanhrim1995.hcmtripadvisor.FragmentFactory;
import com.example.tranthanhrim1995.hcmtripadvisor.MainActivity;
import com.example.tranthanhrim1995.hcmtripadvisor.ManageActionBar;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Thing;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.ThumbnailActivity;
import com.example.tranthanhrim1995.hcmtripadvisor.R;
import com.example.tranthanhrim1995.hcmtripadvisor.WebServiceInterface;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupedThingsToDoFragment extends BaseFragment {

    ArrayList<Thing> listThing = new ArrayList<>();
    ArrayList<ThumbnailActivity> listThumbnailActivity = new ArrayList<>();
    RecyclerView recyclerView, rvListDestination;
//    RecyclerView recyclerView2;
    GroupedThingsToDoAdapter mAdapter = null;
    ListThingsToDoAdapter mAdapterDestination = null;
    ThumbnailActivitiesAdapter mAdapter2 = null;
    FragmentManager fragmentManager;
    NestedScrollView groupedThingFragment;

    WebServiceInterface service;
    private Call<List<Thing>> callGetThings;
    private GetThingsDelegate getThingsDelegate;


    public GroupedThingsToDoFragment() {
//        for(int i = 0; i < 7; i++) {
//            String name = "Destination" + i;
//            listThing.add(new Thing("Museums", name, "This is Detail"));
//        }
        listThumbnailActivity.add(new ThumbnailActivity("Activities", R.drawable.venice2));
        listThumbnailActivity.add(new ThumbnailActivity("Food, Wine & Nightlife", R.drawable.venice2));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getActivity().getSupportFragmentManager();
        groupedThingFragment = (NestedScrollView)inflater.inflate(R.layout.fragment_grouped_things_to_do, null);
        ButterKnife.bind(this, groupedThingFragment);
        recyclerView = (RecyclerView) groupedThingFragment.findViewById(R.id.rvGroupedThings);
//        recyclerView2 = (RecyclerView) groupedThingFragment.findViewById(R.id.rvActivities);
        if (mAdapter == null) {
            if (DataGlobal.getInstance().getListTopThingsToDo().size() == 0) {
                Toast.makeText(getActivity(), "Data is loading...", Toast.LENGTH_SHORT).show();
                fragmentManager.popBackStack();
            } else {
                ArrayList<Thing> temp = new ArrayList<>(DataGlobal.getInstance().getListTopThingsToDo());
                Collections.sort(temp, new Comparator<Thing>() {
                    @Override
                    public int compare(Thing a, Thing b) {
                        if (a.get_grade() > b.get_grade())
                            return 1;
                        else if (a.get_grade() < b.get_grade())
                            return - 1;
                        else return 0;
                    }
                });
                mAdapter = new GroupedThingsToDoAdapter(temp, fragmentManager);
            }
        }
        mAdapter2 = new ThumbnailActivitiesAdapter(listThumbnailActivity);

        //Vertical RecyclerView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        //Horizontal RecyclerView
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        //Grid RecyclerView
        RecyclerView.LayoutManager glayoutManager = new GridLayoutManager(getActivity().getApplicationContext(),2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        rvListDestination = (RecyclerView) groupedThingFragment.findViewById(R.id.rvListDestination);
        if (mAdapterDestination == null) {
            mAdapterDestination = new ListThingsToDoAdapter(DataGlobal.getInstance().getListDestination(), getActivity().getSupportFragmentManager());
        }
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity().getApplicationContext());
        rvListDestination.setLayoutManager(mLayoutManager2);
        rvListDestination.setItemAnimator(new DefaultItemAnimator());
        rvListDestination.setAdapter(mAdapterDestination);


        return groupedThingFragment;
    }

    @OnClick(R.id.btnListDestination) void showListDestination() {
        Bundle bundle = new Bundle();
        bundle.putString("category", "Destination");
        Fragment fragment = FragmentFactory.getInstance().getDestinationFragment();
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        ManageActionBar.getInstance().setTitle("Things to do");
        ManageActionBar.getInstance().showButtonBack();
    }

    private HttpLoggingInterceptor newDefaultLogging() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    private static class GetThingsDelegate implements Callback<List<Thing>> {

        private final WeakReference<GroupedThingsToDoFragment> fragmentWeakReference;

        private GetThingsDelegate(@NonNull final GroupedThingsToDoFragment fragment) {
            this.fragmentWeakReference = new WeakReference<>(fragment);
        }

        @Override
        public void onResponse(Call<List<Thing>> call, Response<List<Thing>> response) {
            GroupedThingsToDoFragment fragment = fragmentWeakReference.get();
            if (fragment != null) {
                fragment.dismissProgressDialog();
                fragment.listThing.clear();
                fragment.listThing.addAll(response.body());
                fragment.mAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onFailure(Call<List<Thing>> call, Throwable t) {
        }
    }
}
