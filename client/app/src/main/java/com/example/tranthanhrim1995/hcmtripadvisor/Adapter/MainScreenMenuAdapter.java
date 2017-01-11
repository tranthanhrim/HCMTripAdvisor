package com.example.tranthanhrim1995.hcmtripadvisor.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tranthanhrim1995.hcmtripadvisor.ItemAdapter.ItemMainScreenMenu;
import com.example.tranthanhrim1995.hcmtripadvisor.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tranthanhrim1995 on 11/27/2016.
 */

public class MainScreenMenuAdapter extends ArrayAdapter<ItemMainScreenMenu> {
    Context context;
    ArrayList<ItemMainScreenMenu> listItem;
    public MainScreenMenuAdapter(Context context, int resource, ArrayList<ItemMainScreenMenu> listItem) {
        super(context, resource, listItem);
        this.context = context;
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View row = inflater.inflate(R.layout.item_main_screen_menu, null);
        ImageView icon = (ImageView)row.findViewById(R.id.ivIconMainScreenMenu);
        TextView iconTitle = (TextView)row.findViewById(R.id.txtLabelMainScreenMenu);

        ItemMainScreenMenu item = getItem(position);
        icon.setImageResource(item.getIcon());
        iconTitle.setText(item.getLabel());
        return (row);
    }
}
