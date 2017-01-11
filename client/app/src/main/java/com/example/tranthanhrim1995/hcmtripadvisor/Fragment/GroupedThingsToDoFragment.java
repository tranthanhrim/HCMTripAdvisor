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

import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.GroupedThingsToDoAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.ThumbnailActivitiesAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.FragmentFactory;
import com.example.tranthanhrim1995.hcmtripadvisor.MainActivity;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Thing;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.ThumbnailActivity;
import com.example.tranthanhrim1995.hcmtripadvisor.R;
import com.example.tranthanhrim1995.hcmtripadvisor.WebServiceInterface;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

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
public class GroupedThingsToDoFragment extends Fragment {

    ArrayList<Thing> listThing = new ArrayList<>();
    ArrayList<ThumbnailActivity> listThumbnailActivity = new ArrayList<>();
    RecyclerView recyclerView, recyclerView2;
    ImageView btnListThingsToDo;
    GroupedThingsToDoAdapter mAdapter;
    ThumbnailActivitiesAdapter mAdapter2;
    FragmentManager fragmentManager;

    WebServiceInterface service;
    private Call<List<Thing>> callGetThings;
    private GetThingsDelegate getThingsDelegate;

    public GroupedThingsToDoFragment() {
        for(int i = 0; i < 7; i++) {
            String name = "Destination" + i;
            listThing.add(new Thing("Museums", name, "This is Detail"));
        }

        listThumbnailActivity.add(new ThumbnailActivity("Activities", R.drawable.venice2));
        listThumbnailActivity.add(new ThumbnailActivity("Food, Wine & Nightlife", R.drawable.venice2));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getActivity().getSupportFragmentManager();
        NestedScrollView groupedThingFragment = (NestedScrollView)inflater.inflate(R.layout.fragment_grouped_things_to_do, null);
        recyclerView = (RecyclerView) groupedThingFragment.findViewById(R.id.rvGroupedThings);
        recyclerView2 = (RecyclerView) groupedThingFragment.findViewById(R.id.rvActivities);
        mAdapter = new GroupedThingsToDoAdapter(listThing, fragmentManager);
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

        recyclerView2.setLayoutManager(glayoutManager);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(mAdapter2);

        btnListThingsToDo = (ImageView)groupedThingFragment.findViewById(R.id.btnListThingsToDo);
        btnListThingsToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.container,
                        FragmentFactory.getInstance().getListThingsToDoFragment()).addToBackStack(null).commit();
            }
        });

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(newDefaultLogging())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hcmtripadvisor.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        service = retrofit.create(WebServiceInterface.class);

        callGetThings = service.listThingsToDo();
        getThingsDelegate = new GetThingsDelegate(this);
        callGetThings.enqueue(getThingsDelegate);

        ((MainActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return groupedThingFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                fragment.listThing.addAll(response.body());
                fragment.mAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onFailure(Call<List<Thing>> call, Throwable t) {

        }

    }
}
