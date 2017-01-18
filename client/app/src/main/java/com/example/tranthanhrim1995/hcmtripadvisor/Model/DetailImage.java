package com.example.tranthanhrim1995.hcmtripadvisor.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by BaoIT on 1/18/2017.
 */

public class DetailImage implements Parcelable {
    private String _id;
    private String _ma;
    private ArrayList<String> _link;


    protected DetailImage(Parcel in) {
        _id = in.readString();
        _ma = in.readString();
        _link = in.createStringArrayList();
    }

    public static final Creator<DetailImage> CREATOR = new Creator<DetailImage>() {
        @Override
        public DetailImage createFromParcel(Parcel in) {
            return new DetailImage(in);
        }

        @Override
        public DetailImage[] newArray(int size) {
            return new DetailImage[size];
        }
    };

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_ma() {
        return _ma;
    }

    public void set_ma(String _ma) {
        this._ma = _ma;
    }

    public ArrayList<String> get_link() {
        return _link;
    }

    public void set_link(ArrayList<String> _link) {
        this._link = _link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(_ma);
        dest.writeStringList(_link);
    }
}

