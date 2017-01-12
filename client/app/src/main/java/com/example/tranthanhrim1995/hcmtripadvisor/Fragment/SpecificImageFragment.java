package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;


import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tranthanhrim1995.hcmtripadvisor.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecificImageFragment extends Fragment {

    ImageView ivSpecificImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout specificImageFragment = (LinearLayout)inflater.inflate(R.layout.fragment_specific_image, null);
        ivSpecificImage = (ImageView) specificImageFragment.findViewById(R.id.ivSpecificImage);

        return specificImageFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = getArguments();
        Bitmap bitmap = bundle.getParcelable("bitmapImage");
        ivSpecificImage.setImageBitmap(bitmap);
//        String image = bundle.getString("image");
//        Picasso.with(getActivity()).load(image).into(ivSpecificImage);
    }
}
