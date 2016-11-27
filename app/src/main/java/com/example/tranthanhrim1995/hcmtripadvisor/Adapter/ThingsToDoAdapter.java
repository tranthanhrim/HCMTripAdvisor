package com.example.tranthanhrim1995.hcmtripadvisor.Adapter;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tranthanhrim1995.hcmtripadvisor.ItemAdapter.ItemThingsToDo;
import com.example.tranthanhrim1995.hcmtripadvisor.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThingsToDoAdapter extends RecyclerView.Adapter<ThingsToDoAdapter.ViewHolder> {

    Context context;
    private List<ItemThingsToDo> listThingsToDo;
    FragmentManager fragmentManager;
    ImageView ivThingsToDo;
    TextView txtTitleThingsToDo;

    public class ViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.ivThingsToDo)
//        ImageView ivThingsToDo;
//
//        @BindView(R.id.txtTitleThingsToDo)
//        TextView txtTitleThingsToDo;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void load(@NonNull final ItemThingsToDo item) {
            txtTitleThingsToDo.setText(item.getLabel());
            ivThingsToDo.setImageResource(item.getIcon());
        }
    }

    public ThingsToDoAdapter(Context context, @NonNull final List<ItemThingsToDo> myDataset) {
        this.context = context;
        listThingsToDo = myDataset;
        fragmentManager = ((Activity)context).getFragmentManager();
    }


    @Override
    public ThingsToDoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_things_to_do, parent, false);
        ivThingsToDo = (ImageView)v.findViewById(R.id.ivThingsToDo);
        txtTitleThingsToDo = (TextView)v.findViewById(R.id.txtTitleThingsToDo);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemThingsToDo item = listThingsToDo.get(position);
        holder.load(item);
    }

    @Override
    public int getItemCount() {
        return listThingsToDo.size();
    }
}
