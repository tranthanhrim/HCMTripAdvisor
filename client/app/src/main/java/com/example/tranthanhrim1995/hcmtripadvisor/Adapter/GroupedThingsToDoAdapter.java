package com.example.tranthanhrim1995.hcmtripadvisor.Adapter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.media.Image;
import android.media.Rating;
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
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

/**
 * Created by tranthanhrim1995 on 1/6/2017.
 */

public class GroupedThingsToDoAdapter extends RecyclerView.Adapter<GroupedThingsToDoAdapter.ViewHolder>{

    private ArrayList<Thing> listThings;
    FragmentManager fragmentManager;
    ArrayList<Bitmap> imagesLoaded;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivImageGroupedThing;
        public TextView tvNameGroupedThing, tvTypeGroupedThing;
        public RatingBar rbRateGroupedThing;
        public ViewHolder(View view) {
            super(view);
            ivImageGroupedThing = (ImageView) view.findViewById(R.id.ivImageGroupedThing);
            tvNameGroupedThing = (TextView) view.findViewById(R.id.tvNameGroupedThing);
            tvTypeGroupedThing = (TextView) view.findViewById(R.id.tvTypeGroupedThing);
            rbRateGroupedThing = (RatingBar) view.findViewById(R.id.rbRateGroupedThing);
        }
    }


    public GroupedThingsToDoAdapter(ArrayList<Thing> listThings, FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        this.listThings = listThings;
        imagesLoaded = new ArrayList<>();
    }

    @Override
    public GroupedThingsToDoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grouped_things_to_do, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GroupedThingsToDoAdapter.ViewHolder holder, int position) {
        Thing thing = listThings.get(position);
        holder.tvNameGroupedThing.setText(thing.getPlaceName());
        holder.tvTypeGroupedThing.setText(thing.getType());

        holder.rbRateGroupedThing.setRating(thing.get_ratingSummary());
        LayerDrawable stars = (LayerDrawable) holder.rbRateGroupedThing.getProgressDrawable();
        stars.getDrawable(0).setColorFilter(Color.parseColor("#CFCFCF"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(Color.parseColor("#CFCFCF"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(2).setColorFilter(Color.parseColor("#569441"), PorterDuff.Mode.SRC_ATOP);

        if (imagesLoaded.size() > position){
            holder.ivImageGroupedThing.setImageBitmap(imagesLoaded.get(position));
        } else {
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(thing.get_thumnailLink(), holder.ivImageGroupedThing, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    imagesLoaded.add(loadedImage);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.container,
                        FragmentFactory.getInstance().getDetailThingFragment()).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listThings.size();
    }
}
