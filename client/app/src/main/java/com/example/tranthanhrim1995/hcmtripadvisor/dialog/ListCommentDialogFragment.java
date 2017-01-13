package com.example.tranthanhrim1995.hcmtripadvisor.dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.tranthanhrim1995.hcmtripadvisor.Adapter.ListCommentAdapter;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Comment;
import com.example.tranthanhrim1995.hcmtripadvisor.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListCommentDialogFragment extends DialogFragment {

    ArrayList<Comment> listComment = new ArrayList<>();
    RecyclerView rvListComment;
    ListCommentAdapter mAdapter;
    public ListCommentDialogFragment() {
        Comment comment = new Comment("Rim Tran", "http://aminoapps.com/static/img/user-icon-placeholder.png",
                "THE BEST JUNGLER EVER | NEW WARWICK REWORK JUNGLE SPOTLIGHT | 1-SHOT ULTIMATE 20 KILLS - https://youtu.be/v_O1TO-QBVk",
                "20/04/1995", "10:26");
        for (int i = 0; i < 5; i++) {
            listComment.add(comment);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View listCommentDialogFragment = inflater.inflate(R.layout.fragment_list_comment, null);

        mAdapter = new ListCommentAdapter(listComment, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        rvListComment = (RecyclerView) listCommentDialogFragment.findViewById(R.id.rvListComment);
        rvListComment.setLayoutManager(mLayoutManager);
        rvListComment.setItemAnimator(new DefaultItemAnimator());
        rvListComment.setAdapter(mAdapter);
        rvListComment.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(listCommentDialogFragment);
        return builder.create();
    }
}
