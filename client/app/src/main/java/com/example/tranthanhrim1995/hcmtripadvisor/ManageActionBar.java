package com.example.tranthanhrim1995.hcmtripadvisor;

import android.content.Context;

/**
 * Created by tranthanhrim1995 on 1/12/2017.
 */

public class ManageActionBar {
    private static ManageActionBar instance = null;
    private ManageActionBar() {}

    private Context context;
    public static ManageActionBar getInstance() {
        if (instance == null) {
            instance = new ManageActionBar();
        }
        return instance;
    }

    public void init(Context context) {
        this.context = context;
    }

    public void setTitle(String title) {
        ((MainActivity) context).getSupportActionBar().setTitle(title);
    }

    public void showButtonBack() {
        ((MainActivity) context).getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        ((MainActivity) context).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
