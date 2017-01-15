package com.example.tranthanhrim1995.hcmtripadvisor.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by tranthanhrim1995 on 1/15/2017.
 */

public class DetailThing implements Parcelable {
    private String _id;
    private String _ma;
    private float _ratingSummary;
    private String _location;
    private String _description;
    private Location _map;
    private ArrayList<String> _review;
    private ArrayList<String> _openHour;
    private ArrayList<String> _goodFor;
    private ArrayList<String> _feature;

    protected DetailThing(Parcel in) {
        _id = in.readString();
        _ma = in.readString();
        _ratingSummary = in.readFloat();
        _location = in.readString();
        _description = in.readString();
        _map = in.readParcelable(Location.class.getClassLoader());
        _review = in.createStringArrayList();
        _openHour = in.createStringArrayList();
        _goodFor = in.createStringArrayList();
        _feature = in.createStringArrayList();
    }

    public static final Creator<DetailThing> CREATOR = new Creator<DetailThing>() {
        @Override
        public DetailThing createFromParcel(Parcel in) {
            return new DetailThing(in);
        }

        @Override
        public DetailThing[] newArray(int size) {
            return new DetailThing[size];
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

    public float get_ratingSummary() {
        return _ratingSummary;
    }

    public void set_ratingSummary(float _ratingSummary) {
        this._ratingSummary = _ratingSummary;
    }

    public String get_location() {
        return _location;
    }

    public void set_location(String _location) {
        this._location = _location;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public Location get_map() {
        return _map;
    }

    public void set_map(Location _map) {
        this._map = _map;
    }

    public ArrayList<String> get_review() {
        return _review;
    }

    public void set_review(ArrayList<String> _review) {
        this._review = _review;
    }

    public ArrayList<String> get_openHour() {
        return _openHour;
    }

    public void set_openHour(ArrayList<String> _openHour) {
        this._openHour = _openHour;
    }

    public ArrayList<String> get_goodFor() {
        return _goodFor;
    }

    public void set_goodFor(ArrayList<String> _goodFor) {
        this._goodFor = _goodFor;
    }

    public ArrayList<String> get_feature() {
        return _feature;
    }

    public void set_feature(ArrayList<String> _feature) {
        this._feature = _feature;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(_ma);
        parcel.writeFloat(_ratingSummary);
        parcel.writeString(_location);
        parcel.writeString(_description);
        parcel.writeParcelable(_map, i);
        parcel.writeStringList(_review);
        parcel.writeStringList(_openHour);
        parcel.writeStringList(_goodFor);
        parcel.writeStringList(_feature);
    }
}
