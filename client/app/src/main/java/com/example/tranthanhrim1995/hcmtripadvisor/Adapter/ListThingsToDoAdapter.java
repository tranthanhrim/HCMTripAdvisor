package com.example.tranthanhrim1995.hcmtripadvisor.Adapter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tranthanhrim1995 on 1/10/2017.
 */

public class ListThingsToDoAdapter extends RecyclerView.Adapter<ListThingsToDoAdapter.ViewHolder>{

    private ArrayList<Thing> listThings;
    private ArrayList<Bitmap> imagesLoaded;
    private FragmentManager fragmentManager;
    private ImageLoader imageLoader;
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivThumbnail)
        ImageView ivThumbnail;

        @BindView(R.id.tvThingName)
        TextView tvThingName;

        @BindView(R.id.tvQuantityRate)
        TextView tvQuantityRate;

        @BindView(R.id.tvRank)
        TextView tvRank;

        @BindView(R.id.rbRate)
        RatingBar rbRate;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }
    }


    public ListThingsToDoAdapter(ArrayList<Thing> listThings, FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        this.listThings = listThings;
        imageLoader = ImageLoader.getInstance();
        imagesLoaded = new ArrayList<>();
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
        holder.tvThingName.setText(thing.getPlaceName());
        holder.rbRate.setRating(thing.get_ratingSummary());
        holder.tvRank.setText(thing.getType());
        if (imagesLoaded.size() > position){
            holder.ivThumbnail.setImageBitmap(imagesLoaded.get(position));
        } else {
            imageLoader.displayImage(thing.get_thumnailLink(), holder.ivThumbnail, new ImageLoadingListener() {
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

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fragmentManager.beginTransaction().replace(R.id.container,
//                        FragmentFactory.getInstance().getDetailThingFragment()).commit();
//            }
//        });
        LayerDrawable stars = (LayerDrawable) holder.rbRate.getProgressDrawable();
        stars.getDrawable(0).setColorFilter(Color.parseColor("#CFCFCF"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(Color.parseColor("#CFCFCF"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(2).setColorFilter(Color.parseColor("#569441"), PorterDuff.Mode.SRC_ATOP);

    }

    @Override
    public int getItemCount() {
        return listThings.size();
    }
}
