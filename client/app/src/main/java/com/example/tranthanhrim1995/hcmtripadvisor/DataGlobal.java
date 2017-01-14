package com.example.tranthanhrim1995.hcmtripadvisor;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.GroupedThingsToDoFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Thing;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tranthanhrim1995 on 1/14/2017.
 */

public class DataGlobal {
    private static DataGlobal instance = new DataGlobal();

    private Context context;
    WebServiceInterface service;

    private ArrayList<Thing> listTopThingsTodo;
    private Call<List<Thing>> callGetTopThings;
    private GetTopThingsDelegate getTopThingsDelegate;

    private DataGlobal() {
        this.setListTopThingsTodo(new ArrayList<Thing>());
    }

    public static DataGlobal getInstance() {
        return instance;
    }

    public void init(Context context) {
        this.context = context;
        OkHttpClient client = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.server_name))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        service = retrofit.create(WebServiceInterface.class);

        /*Get top things to do*/
        callGetTopThings = service.listThingsToDo();
        getTopThingsDelegate = new GetTopThingsDelegate(this);
        callGetTopThings.enqueue(getTopThingsDelegate);

    }

    public ArrayList<Thing> getListTopThingsTodo() {
        return listTopThingsTodo;
    }

    public void setListTopThingsTodo(ArrayList<Thing> listTopThingsTodo) {
        this.listTopThingsTodo = listTopThingsTodo;
    }

    private class GetTopThingsDelegate implements Callback<List<Thing>> {

        private final WeakReference<DataGlobal> weakReference;

        private GetTopThingsDelegate(@NonNull final DataGlobal fragment) {
            this.weakReference = new WeakReference<>(fragment);
        }

        @Override
        public void onResponse(Call<List<Thing>> call, Response<List<Thing>> response) {
            DataGlobal dataGlobal = weakReference.get();
            if (dataGlobal != null) {
                dataGlobal.getListTopThingsTodo().clear();
                dataGlobal.getListTopThingsTodo().addAll(response.body());
            }
        }

        @Override
        public void onFailure(Call<List<Thing>> call, Throwable t) {

        }

    }




    /*This region is used if fragment was injected, do not remove it*/
    private GroupedThingsToDoFragment groupedThingsToDoFragment;

    public GroupedThingsToDoFragment getGroupedThingsToDoFragment() {
        return groupedThingsToDoFragment;
    }
    public void setGroupedThingsToDoFragment(GroupedThingsToDoFragment groupedThingsToDoFragment) {
        this.groupedThingsToDoFragment = groupedThingsToDoFragment;
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
    /*End region*/
}
