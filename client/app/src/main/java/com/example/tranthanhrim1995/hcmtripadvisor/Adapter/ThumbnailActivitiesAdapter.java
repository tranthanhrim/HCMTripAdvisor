package com.example.tranthanhrim1995.hcmtripadvisor.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.tranthanhrim1995.hcmtripadvisor.Model.ThumbnailActivity;
import com.example.tranthanhrim1995.hcmtripadvisor.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by tranthanhrim1995 on 1/10/2017.
 */

public class ThumbnailActivitiesAdapter extends RecyclerView.Adapter<ThumbnailActivitiesAdapter.ViewHolder>{
    private ArrayList<ThumbnailActivity> listThumbnailActivity;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivActivity;
        public TextView tvActivity;
        public ViewHolder(View view) {
            super(view);
            ivActivity = (ImageView)view.findViewById(R.id.ivActivity);
            tvActivity = (TextView)view.findViewById(R.id.tvActivity);

        }
    }

    public ThumbnailActivitiesAdapter() {}

    public  ThumbnailActivitiesAdapter(ArrayList<ThumbnailActivity> listThumbnailActivity) {
        this.listThumbnailActivity = listThumbnailActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_thumbnail_activity, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ThumbnailActivity thumbnailActivity = listThumbnailActivity.get(position);
        String name = "http://www.osu.edu/alumni/assets/uploads/galleries/Portrait_of_Italy_Photo_4_1.jpg";
        String uri = "drawable://" + R.drawable.venice2;
//        URL url_value = null;
//        try {
//            url_value = new URL(name);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        try {
//            Bitmap mIcon1 =
//                    BitmapFactory.decodeStream(url_value.openConnection().getInputStream());
//            holder.ivActivity.setImageBitmap(mIcon1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        holder.ivActivity.setImageResource(thumbnailActivity.getImage());
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(uri, holder.ivActivity);
        holder.tvActivity.setText(thumbnailActivity.getLabel());

    }

    @Override
    public int getItemCount() {
        return listThumbnailActivity.size();
    }

}
