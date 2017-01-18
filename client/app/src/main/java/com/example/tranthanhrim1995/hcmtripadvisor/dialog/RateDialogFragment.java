package com.example.tranthanhrim1995.hcmtripadvisor.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.media.Rating;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.tranthanhrim1995.hcmtripadvisor.DataGlobal;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.response.EndPointResponse;
import com.example.tranthanhrim1995.hcmtripadvisor.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RateDialogFragment extends DialogFragment {

    String idThing;
    @BindView(R.id.rbRateValue)
    RatingBar rbRateValue;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialog_rate = inflater.inflate(R.layout.dialog_rate, null);
        ButterKnife.bind(this, dialog_rate);

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
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String idUser = sharedPref.getString(getActivity().getString(R.string.google_id), "");
                HashMap map = new HashMap();
                map.put("_idUser", idUser);
                map.put("_idThing", idThing);
                map.put("_rate", rbRateValue.getRating());
                Call<EndPointResponse> callSendRate = DataGlobal.getInstance().getService()
                        .postRate(map);
                callSendRate.enqueue(new Callback<EndPointResponse>() {
                    @Override
                    public void onResponse(Call<EndPointResponse> call, Response<EndPointResponse> response) {
                        dismiss();
                    }

                    @Override
                    public void onFailure(Call<EndPointResponse> call, Throwable t) {

                    }
                });
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

//    @OnClick(R.id.btnRateSubmit) void rateSubmit(){
//        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        String idUser = sharedPref.getString(getActivity().getString(R.string.google_id), "");
//        HashMap map = new HashMap();
//        map.put("_idUser", idUser);
//        map.put("_idThing", idThing);
//        map.put("_rate", rbRateValue.getRating());
//        Call<EndPointResponse> callSendRate = DataGlobal.getInstance().getService()
//                .postRate(map);
//        callSendRate.enqueue(new Callback<EndPointResponse>() {
//            @Override
//            public void onResponse(Call<EndPointResponse> call, Response<EndPointResponse> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<EndPointResponse> call, Throwable t) {
//
//            }
//        });
//    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = getArguments();
        idThing = bundle.getString("idThing");
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String idUser = sharedPref.getString(getActivity().getString(R.string.google_id), "");

        Call<Map> callPersonalRating = DataGlobal.getInstance().getService()
                .getPersonalRating(idUser, idThing);
        callPersonalRating.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                Double rateValue = (Double) response.body().get("rate");
                if (rateValue == -1) {
                    rbRateValue.setRating(0);
                } else {
                    rbRateValue.setRating(Float.parseFloat(rateValue.toString()));
                }
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {

            }
        });
    }
}
