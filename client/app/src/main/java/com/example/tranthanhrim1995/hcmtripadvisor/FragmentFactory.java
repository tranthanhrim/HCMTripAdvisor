package com.example.tranthanhrim1995.hcmtripadvisor;

import android.app.DialogFragment;
import android.os.Bundle;

import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.BaseListThingFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.DetailThingFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.GridImageFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.GroupedThingsToDoFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.ListFoodFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.ListThingsToDoFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.MainFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.NearMeNowFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.MapThingFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.NearMeFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.SigninFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.SpecificImageFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.Fragment.ThingsToDoFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.dialog.ListCommentDialogFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.dialog.MessageDialogFragment;
import com.example.tranthanhrim1995.hcmtripadvisor.dialog.RateDialogFragment;

/**
 * Created by tranthanhrim1995 on 11/27/2016.
 */

public class FragmentFactory {

    private MainFragment mainFragment;
    private ThingsToDoFragment thingsToDoFragment;
    private GroupedThingsToDoFragment groupedThingsToDoFragment;
    private DetailThingFragment detailThingFragment;
    private ListThingsToDoFragment listThingsToDoFragment;
    private NearMeFragment nearMeFragment;
    private NearMeNowFragment nearMeNowFragment;
    private MapThingFragment mapThingFragment;
    private SigninFragment signinFragment;
    private GridImageFragment gridImageFragment;
    private SpecificImageFragment specificImageFragment;
    private BaseListThingFragment destinationFragment;
    private BaseListThingFragment hotelFragment;
    private BaseListThingFragment foodFragment;

    private ListFoodFragment listFoodFragment;

    /*Dialog Fragment*/
    private MessageDialogFragment gpsNotFoundDialog;
    private MessageDialogFragment internetNotFoundDialog;
    private RateDialogFragment rateDialogFragment;
    private ListCommentDialogFragment listCommentDialogFragment;

    private static FragmentFactory instance = null;

    private FragmentFactory() {
        setMainFragment(new MainFragment());
        setThingsToDoFragment(new ThingsToDoFragment());
        setGroupedThingsToDoFragment(new GroupedThingsToDoFragment());
        setDetailThingFragment(new DetailThingFragment());
        setListThingsToDoFragment(new ListThingsToDoFragment());
        setNearMeFragment(new NearMeFragment());
        setMapThingFragment(new MapThingFragment());
        setNearMeNowFragment(new NearMeNowFragment());
        setSigninFragment(new SigninFragment());
        setGridImageFragment(new GridImageFragment());
        setSpecificImageFragment(new SpecificImageFragment());
        setDestinationFragment(new BaseListThingFragment());
        setHotelFragment(new BaseListThingFragment());
        setFoodFragment(new BaseListThingFragment());

        setListFoodFragment(new ListFoodFragment());

        /*Dialog Fragment*/
        setGpsNotFoundDialog(new MessageDialogFragment());
        setRateDialogFragment(new RateDialogFragment());
        setListCommentDialogFragment(new ListCommentDialogFragment());
        setInternetNotFoundDialog(new MessageDialogFragment());
    }

    public static FragmentFactory getInstance() {
        if (instance == null) {
            instance = new FragmentFactory();
        }
        return instance;
    }

    public MainFragment getMainFragment() {
        return mainFragment;
    }

    public void setMainFragment(MainFragment mainFragment) {
        this.mainFragment = mainFragment;
    }

    public ThingsToDoFragment getThingsToDoFragment() {
        return thingsToDoFragment;
    }

    public void setThingsToDoFragment(ThingsToDoFragment thingsToDoFragment) {
        this.thingsToDoFragment = thingsToDoFragment;
    }

    public GroupedThingsToDoFragment getGroupedThingsToDoFragment() {
        return groupedThingsToDoFragment;
    }

    public void setGroupedThingsToDoFragment(GroupedThingsToDoFragment groupedThingsToDoFragment) {
        this.groupedThingsToDoFragment = groupedThingsToDoFragment;
    }

    public DetailThingFragment getDetailThingFragment() {
        return detailThingFragment;
    }

    public void setDetailThingFragment(DetailThingFragment detailThingFragment) {
        this.detailThingFragment = detailThingFragment;
    }

    public ListThingsToDoFragment getListThingsToDoFragment() {
        return listThingsToDoFragment;
    }

    public void setListThingsToDoFragment(ListThingsToDoFragment listThingsToDoFragment) {
        this.listThingsToDoFragment = listThingsToDoFragment;
    }

    public NearMeFragment getNearMeFragment() {
        return nearMeFragment;
    }

    public void setNearMeFragment(NearMeFragment nearMeFragment) {
        this.nearMeFragment = nearMeFragment;
    }

    public MapThingFragment getMapThingFragment() {
        return mapThingFragment;
    }

    public void setMapThingFragment(MapThingFragment mapThingFragment) {
        this.mapThingFragment = mapThingFragment;
    }

    public NearMeNowFragment getNearMeNowFragment() {
        return nearMeNowFragment;
    }

    public void setNearMeNowFragment(NearMeNowFragment nearMeNowFragment) {
        this.nearMeNowFragment = nearMeNowFragment;
    }

    public SigninFragment getSigninFragment() {
        return signinFragment;
    }

    public void setSigninFragment(SigninFragment signinFragment) {
        this.signinFragment = signinFragment;
    }

    public MessageDialogFragment getGpsNotFoundDialog() {
        return gpsNotFoundDialog;
    }

    public void setGpsNotFoundDialog(MessageDialogFragment gpsNotFoundDialog) {
        this.gpsNotFoundDialog = gpsNotFoundDialog;
        Bundle bundle = new Bundle();
        bundle.putString("messageDialog", "GPS Not found!");
        this.gpsNotFoundDialog.setArguments(bundle);
    }

    public RateDialogFragment getRateDialogFragment() {
        return rateDialogFragment;
    }

    public void setRateDialogFragment(RateDialogFragment rateDialogFragment) {
        this.rateDialogFragment = rateDialogFragment;
    }

    public GridImageFragment getGridImageFragment() {
        return gridImageFragment;
    }

    public void setGridImageFragment(GridImageFragment gridImageFragment) {
        this.gridImageFragment = gridImageFragment;
    }

    public SpecificImageFragment getSpecificImageFragment() {
        return specificImageFragment;
    }

    public void setSpecificImageFragment(SpecificImageFragment specificImageFragment) {
        this.specificImageFragment = specificImageFragment;
    }

    public ListCommentDialogFragment getListCommentDialogFragment() {
        return listCommentDialogFragment;
    }

    public void setListCommentDialogFragment(ListCommentDialogFragment listCommentDialogFragment) {
        this.listCommentDialogFragment = listCommentDialogFragment;
    }

    public MessageDialogFragment getInternetNotFoundDialog() {
        return internetNotFoundDialog;
    }

    public void setInternetNotFoundDialog(MessageDialogFragment internetNotFoundDialog) {
        this.internetNotFoundDialog = internetNotFoundDialog;
        Bundle bundle = new Bundle();
        bundle.putString("messageDialog", "No internet access!");
        this.internetNotFoundDialog.setArguments(bundle);
    }

    public BaseListThingFragment getDestinationFragment() {
        return destinationFragment;
    }

    public void setDestinationFragment(BaseListThingFragment destinationFragment) {
        this.destinationFragment = destinationFragment;
    }

    public BaseListThingFragment getHotelFragment() {
        return hotelFragment;
    }

    public void setHotelFragment(BaseListThingFragment hotelFragment) {
        this.hotelFragment = hotelFragment;
    }

    public BaseListThingFragment getFoodFragment() {
        return foodFragment;
    }

    public void setFoodFragment(BaseListThingFragment foodFragment) {
        this.foodFragment = foodFragment;
    }

    public ListFoodFragment getListFoodFragment() {
        return listFoodFragment;
    }

    public void setListFoodFragment(ListFoodFragment listFoodFragment) {
        this.listFoodFragment = listFoodFragment;
    }
}
