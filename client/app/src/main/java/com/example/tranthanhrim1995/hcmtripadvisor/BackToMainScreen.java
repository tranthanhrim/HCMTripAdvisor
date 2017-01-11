package com.example.tranthanhrim1995.hcmtripadvisor;

import android.support.v4.app.FragmentManager;
import android.view.View;

/**
 * Created by tranthanhrim1995 on 11/27/2016.
 */

public class BackToMainScreen {
    static FragmentManager fragmentManager = null;
    public static void setFragmentManager(FragmentManager obj){
        fragmentManager = obj;
    }

    public static View.OnClickListener execute = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fragmentManager.beginTransaction().replace(R.id.container,
                    FragmentFactory.getInstance().getMainFragment()).commit();
        }
    };
}
