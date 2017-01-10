package com.example.tranthanhrim1995.hcmtripadvisor.Adapter;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.tranthanhrim1995.hcmtripadvisor.FragmentFactory;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Thing;
import com.example.tranthanhrim1995.hcmtripadvisor.R;

import java.util.ArrayList;

/**
 * Created by tranthanhrim1995 on 1/10/2017.
 */

public class ListThingsToDoAdapter extends RecyclerView.Adapter<ListThingsToDoAdapter.ViewHolder>{

    private ArrayList<Thing> listThings;
    FragmentManager fragmentManager;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public RatingBar rbRate;
        public ViewHolder(View view) {
            super(view);
            rbRate = (RatingBar) view.findViewById(R.id.rbRate);
        }
    }


    public ListThingsToDoAdapter(ArrayList<Thing> listThings, FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        this.listThings = listThings;
    }

    @Override
    public ListThingsToDoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hotel, parent, false);
        return new ListThingsToDoAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListThingsToDoAdapter.ViewHolder holder, int position) {
        Thing thing = listThings.get(position);
//        holder.tvNameGroupedThing.setText(thing.getPlaceName());
//        holder.tvTypeGroupedThing.setText(thing.getType());
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fragmentManager.beginTransaction().replace(R.id.container,
//                        FragmentFactory.getInstance().getDetailThingFragment()).commit();
//            }
//        });
//        LayerDrawable stars = (LayerDrawable) holder.rbRate.getProgressDrawable();
//        stars.getDrawable(2).setColorFilter(Color.parseColor("#569441"), PorterDuff.Mode.SRC_ATOP);
        LayerDrawable stars = (LayerDrawable) holder.rbRate.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#569441"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(Color.parseColor("#569441"), PorterDuff.Mode.SRC_ATOP);

    }

    @Override
    public int getItemCount() {
        return listThings.size();
    }
}
