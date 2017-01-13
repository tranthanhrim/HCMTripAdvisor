package com.example.tranthanhrim1995.hcmtripadvisor.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tranthanhrim1995.hcmtripadvisor.CircleTransform;
import com.example.tranthanhrim1995.hcmtripadvisor.Model.Comment;
import com.example.tranthanhrim1995.hcmtripadvisor.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by tranthanhrim1995 on 1/12/2017.
 */

public class ListCommentAdapter extends RecyclerView.Adapter<ListCommentAdapter.ViewHolder> {

    private ArrayList<Comment> listComment;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivAvatarComment;
        public TextView tvContentComment, tvDateTimeComment, tvUsernameComment;

        public ViewHolder(View view) {
            super(view);

            ivAvatarComment = (ImageView) view.findViewById(R.id.ivAvatarComment);
            tvContentComment = (TextView) view.findViewById(R.id.tvContentComment);
            tvDateTimeComment = (TextView) view.findViewById(R.id.tvDateTimeComment);
            tvUsernameComment = (TextView) view.findViewById(R.id.tvUsernameComment);
        }
    }

    public ListCommentAdapter(ArrayList<Comment> listComment, Context context) {
        this.listComment = listComment;
        this.context = context;
    }

    @Override
    public ListCommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListCommentAdapter.ViewHolder holder, int position) {
        Comment comment = listComment.get(position);
        holder.tvContentComment.setText(comment.getComment());
//        holder.tvUsernameComment.setText(comment.getUsername());
        holder.tvDateTimeComment.setText(comment.getDate() + " " + comment.getTime());
        Picasso.with(context).load(comment.getAvatar())
                .transform(new CircleTransform()).into(holder.ivAvatarComment);
    }

    @Override
    public int getItemCount() {
        return listComment.size();
    }
}
