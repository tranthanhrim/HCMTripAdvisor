package com.example.tranthanhrim1995.hcmtripadvisor.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.BaseFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.FragmentFactory;
import com.example.tranthanhrim1995.hcmtripadvisor.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by tranthanhrim1995 on 1/12/2017.
 */

public class GridImageAdapter extends RecyclerView.Adapter<GridImageAdapter.ViewHolder> {

    private ArrayList<String> images;
    private ArrayList<Bitmap> bitmapImages;
    FragmentManager fragmentManager;
    Context context;
    ImageLoader imageLoader;
    int imageLoaded;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivItemGridImage;
        public ViewHolder(View view) {
            super(view);
            ivItemGridImage = (ImageView) view.findViewById(R.id.ivItemGridImage);
        }
    }

    public GridImageAdapter() {}

    public GridImageAdapter(ArrayList<String> images, FragmentManager fm, Context context) {
        this.images = images;
        this.fragmentManager = fm;
        this.context = context;
        imageLoader = ImageLoader.getInstance();
        imageLoaded = 0;
        bitmapImages = new ArrayList<>();
    }

    @Override
    public GridImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grid_image, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GridImageAdapter.ViewHolder holder, final int position) {
//        Picasso.with(context).load(images.get(position)).into(holder.ivItemGridImage);
        if (bitmapImages.size() > position) {
            holder.ivItemGridImage.setImageBitmap(bitmapImages.get(position));
        } else {
            imageLoader.displayImage(images.get(position), holder.ivItemGridImage, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    imageLoaded++;
                    bitmapImages.add(loadedImage);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bitmapImages.size() > position) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("bitmapImage", bitmapImages.get(position));
//                    bundle.putString("image", images.get(position));
                    Fragment specificImageFragment = FragmentFactory.getInstance().getSpecificImageFragment();
                    specificImageFragment.setArguments(bundle);
                    fragmentManager.beginTransaction().replace(R.id.container, specificImageFragment)
                            .addToBackStack(null).commit();
                } else {
                    Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public int getNumOfImageLoaded() {
        return imageLoaded;
    }
}
