package com.example.tranthanhrim1995.hcmtripadvisor.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.media.Rating;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.tranthanhrim1995.hcmtripadvisor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RateDialogFragment extends DialogFragment {
    RatingBar rbRateValue;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialog_rate = inflater.inflate(R.layout.dialog_rate, null);

        //Change color rating bar
        rbRateValue = (RatingBar)dialog_rate.findViewById(R.id.rbRateValue);
        LayerDrawable stars = (LayerDrawable) rbRateValue.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#569441"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_ATOP);

        //Button Submit rate
        TextView btnRateSubmit = (TextView) dialog_rate.findViewById(R.id.btnRateSubmit);
        btnRateSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float rateValue = rbRateValue.getRating();
                //call api review
            }
        });

        //Button dismiss dialog
        TextView btnCancelDialogMessage = (TextView) dialog_rate.findViewById(R.id.btnCancelDialogMessage);
        btnCancelDialogMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialog_rate);
        return builder.create();
    }
}
