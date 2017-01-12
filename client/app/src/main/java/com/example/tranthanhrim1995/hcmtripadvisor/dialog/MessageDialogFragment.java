package com.example.tranthanhrim1995.hcmtripadvisor.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tranthanhrim1995.hcmtripadvisor.R;

/**
 * Created by tranthanhrim1995 on 1/12/2017.
 */

public class MessageDialogFragment extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        Bundle bundle = getArguments();
        String messageDialog = bundle.getString("messageDialog");
        View dialog_message = inflater.inflate(R.layout.dialog_message, null);
        TextView tvTextMessageDialog = (TextView)dialog_message.findViewById(R.id.tvTextDialogMessage);
        tvTextMessageDialog.setText(messageDialog);

        TextView btnCancelDialogMessage = (TextView) dialog_message.findViewById(R.id.btnCancelDialogMessage);
        btnCancelDialogMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setView(inflater.inflate(R.layout.dialog_message, null));
        builder.setView(dialog_message);
        return builder.create();
    }
}
