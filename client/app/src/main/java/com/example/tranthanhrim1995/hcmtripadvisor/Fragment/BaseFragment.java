package com.example.tranthanhrim1995.hcmtripadvisor.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.example.tranthanhrim1995.hcmtripadvisor.dialog.ProgressDialogFragment;

/**
 * Created by tranthanhrim1995 on 1/12/2017.
 */

public class BaseFragment extends Fragment {
    protected static final String TAG_PROGRESS_DIALOG = "progressDialog";

    public void showProgressDialog() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment prevFrag = getActivity().getSupportFragmentManager().findFragmentByTag(TAG_PROGRESS_DIALOG);
        if (prevFrag == null) {
            ProgressDialogFragment newFrag = ProgressDialogFragment.newInstance();
            ft.add(newFrag, TAG_PROGRESS_DIALOG);
        } else {
            ft.remove(prevFrag);
        }
        ft.commitAllowingStateLoss();
    }

    public void dismissProgressDialog() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment prevFrag = getActivity().getSupportFragmentManager().findFragmentByTag(TAG_PROGRESS_DIALOG);
        if (prevFrag != null) {
            ft.remove(prevFrag);
        }
        ft.commitAllowingStateLoss();
    }
}
