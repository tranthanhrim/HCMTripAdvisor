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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
        holder.tvContentComment.setText(comment.get_content());
//        holder.tvUsernameComment.setText(comment.getUsername());
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.CHINA);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = format.parse(comment.get_time());
            int year = date.getYear() + 1900;
            int month = date.getMonth() + 1;
            String dmy = date.getDate() + "/" + month + "/" + year;
            holder.tvDateTimeComment.setText(dmy + " " + date.getHours() + ":" + date.getMinutes());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvUsernameComment.setText("");
//        Picasso.with(context).load(comment.getAvatar())
//                .transform(new CircleTransform()).into(holder.ivAvatarComment);
    }

    @Override
    public int getItemCount() {
        return listComment.size();
    }
}
