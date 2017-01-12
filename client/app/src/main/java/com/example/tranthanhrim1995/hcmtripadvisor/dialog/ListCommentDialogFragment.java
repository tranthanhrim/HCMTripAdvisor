package com.example.tranthanhrim1995.hcmtripadvisor.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.tranthanhrim1995.hcmtripadvisor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListCommentDialogFragment extends DialogFragment {


    public ListCommentDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View listCommentDialogFragment = inflater.inflate(R.layout.fragment_list_comment, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(listCommentDialogFragment);
        return builder.create();
    }
}
