package com.example.tranthanhrim1995.hcmtripadvisor;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.GroupedThingsToDoFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Thing;

import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    private ArrayList<Thing> listThingsTodo;

    private ArrayList<Thing> listFoodnDrink;
    private ArrayList<Thing> listHotel;
    private ArrayList<Thing> listDestination;

    private ArrayList<Thing> listTopThingsToDo;


    private DataGlobal() {
        this.setListThingsTodo(new ArrayList<Thing>());
        this.setListFoodnDrink(new ArrayList<Thing>());
        this.setListHotel(new ArrayList<Thing>());
        this.setListDestination(new ArrayList<Thing>());
        this.setListTopThingsToDo(new ArrayList<Thing>());
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

//        /*Get top things to do*/
        Call<List<Thing>> callGetListThings = service.listThingsToDo();
        callGetListThings.enqueue(new GetListThingsDelegate(this));

//        Call<List<Thing>> callGetListFood = service.getlistThingsWithType("FoodnDrink");
//        callGetListFood.enqueue(new GetListFoodDrinkDelegate(this));
//
//        Call<List<Thing>> callGetListHotel = service.getlistThingsWithType("Hotels");
//        callGetListHotel.enqueue(new GetListHotelDelegate(this));
//
//        Call<List<Thing>> callGetListDestination = service.getListDestination();
//        callGetListDestination.enqueue(new GetListDestinationDelegate(this));

        Call<List<Thing>> callGetTopThings = service.getListTopThingsToDo();
        callGetTopThings.enqueue(new Callback<List<Thing>>() {
            @Override
            public void onResponse(Call<List<Thing>> call, Response<List<Thing>> response) {
                listTopThingsToDo.clear();
                listTopThingsToDo = new ArrayList<>(response.body());
            }

            @Override
            public void onFailure(Call<List<Thing>> call, Throwable t) {

            }
        });

    }

    public WebServiceInterface getService() {
        return service;
    }


    public ArrayList<Thing> getListFoodnDrink() {
        return listFoodnDrink;
    }

    public void setListFoodnDrink(ArrayList<Thing> listFoodnDrink) {
        this.listFoodnDrink = listFoodnDrink;
    }

    public ArrayList<Thing> getListHotel() {
        return listHotel;
    }

    public void setListHotel(ArrayList<Thing> listHotel) {
        this.listHotel = listHotel;
    }

    public ArrayList<Thing> getListDestination() {
        return listDestination;
    }

    public void setListDestination(ArrayList<Thing> listDestination) {
        this.listDestination = listDestination;
    }

    public ArrayList<Thing> getListThingsTodo() {
        return listThingsTodo;
    }

    public void setListThingsTodo(ArrayList<Thing> listThingsTodo) {
        this.listThingsTodo = listThingsTodo;
    }

    public ArrayList<Thing> getListTopThingsToDo() {
        return listTopThingsToDo;
    }

    public void setListTopThingsToDo(ArrayList<Thing> listTopThingsToDo) {
        this.listTopThingsToDo = listTopThingsToDo;
    }

    private class GetListThingsDelegate implements Callback<List<Thing>> {

        private final WeakReference<DataGlobal> weakReference;

        private GetListThingsDelegate(@NonNull final DataGlobal dataGlobal) {
            this.weakReference = new WeakReference<>(dataGlobal);
        }

        @Override
        public void onResponse(Call<List<Thing>> call, Response<List<Thing>> response) {
            DataGlobal dataGlobal = weakReference.get();
            if (dataGlobal != null) {
                dataGlobal.getListThingsTodo().clear();
                dataGlobal.getListHotel().clear();
                dataGlobal.getListFoodnDrink().clear();
                dataGlobal.getListDestination().clear();
                dataGlobal.getListThingsTodo().addAll(response.body());
                ArrayList<Thing> tempList = new ArrayList<>();
                tempList.addAll(response.body());
                for(int i = 0; i < tempList.size(); i++) {
                    if (tempList.get(i).getType().equals("Hotels")) {
                        dataGlobal.getListHotel().add(tempList.get(i));
                    } else if (tempList.get(i).getType().equals("FoodnDrink")) {
                        dataGlobal.getListFoodnDrink().add(tempList.get(i));
                    } else {
                        dataGlobal.getListDestination().add(tempList.get(i));
                    }
                }
                int a = 5;
            }
        }

        @Override
        public void onFailure(Call<List<Thing>> call, Throwable t) {

        }
    }

    private class GetListFoodDrinkDelegate implements Callback<List<Thing>> {
        private final WeakReference<DataGlobal> weakReference;

        private GetListFoodDrinkDelegate(@NonNull final DataGlobal fragment) {
            this.weakReference = new WeakReference<>(fragment);
        }

        @Override
        public void onResponse(Call<List<Thing>> call, Response<List<Thing>> response) {
            DataGlobal dataGlobal = weakReference.get();
            if (dataGlobal != null) {
                dataGlobal.getListFoodnDrink().clear();
                dataGlobal.getListFoodnDrink().addAll(response.body());
            }
        }

        @Override
        public void onFailure(Call<List<Thing>> call, Throwable t) {

        }
    }

    private class GetListHotelDelegate implements Callback<List<Thing>> {

        private final WeakReference<DataGlobal> weakReference;

        private GetListHotelDelegate(@NonNull final DataGlobal fragment) {
            this.weakReference = new WeakReference<>(fragment);
        }

        @Override
        public void onResponse(Call<List<Thing>> call, Response<List<Thing>> response) {
            DataGlobal dataGlobal = weakReference.get();
            if (dataGlobal != null) {
                dataGlobal.getListHotel().clear();
                dataGlobal.getListHotel().addAll(response.body());
            }
        }

        @Override
        public void onFailure(Call<List<Thing>> call, Throwable t) {

        }
    }

    private class GetListDestinationDelegate implements Callback<List<Thing>> {

        private final WeakReference<DataGlobal> weakReference;

        private GetListDestinationDelegate(@NonNull final DataGlobal fragment) {
            this.weakReference = new WeakReference<>(fragment);
        }

        @Override
        public void onResponse(Call<List<Thing>> call, Response<List<Thing>> response) {
            DataGlobal dataGlobal = weakReference.get();
            if (dataGlobal != null) {
                dataGlobal.getListDestination().clear();
                dataGlobal.getListDestination().addAll(response.body());
            }
        }

        @Override
        public void onFailure(Call<List<Thing>> call, Throwable t) {

        }
    }




    /*This region is used if fragment was injected, do not remove it*/
//    private GroupedThingsToDoFragment groupedThingsToDoFragment;
//
//    public GroupedThingsToDoFragment getGroupedThingsToDoFragment() {
//        return groupedThingsToDoFragment;
//    }
//    public void setGroupedThingsToDoFragment(GroupedThingsToDoFragment groupedThingsToDoFragment) {
//        this.groupedThingsToDoFragment = groupedThingsToDoFragment;
//    }
//    private static class GetThingsDelegate implements Callback<List<Thing>> {
//
//        private final WeakReference<GroupedThingsToDoFragment> fragmentWeakReference;
//
//        private GetThingsDelegate(@NonNull final GroupedThingsToDoFragment fragment) {
//            this.fragmentWeakReference = new WeakReference<>(fragment);
//        }
//
//        @Override
//        public void onResponse(Call<List<Thing>> call, Response<List<Thing>> response) {
//            GroupedThingsToDoFragment fragment = fragmentWeakReference.get();
//            if (fragment != null) {
//                fragment.dismissProgressDialog();
//                fragment.listThing.clear();
//                fragment.listThing.addAll(response.body());
//                fragment.mAdapter.notifyDataSetChanged();
//            }
//        }
//
//        @Override
//        public void onFailure(Call<List<Thing>> call, Throwable t) {
//
//        }
//
//    }
    /*End region*/
}
