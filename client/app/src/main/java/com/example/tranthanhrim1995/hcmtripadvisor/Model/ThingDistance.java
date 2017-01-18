package com.example.tranthanhrim1995.hcmtripadvisor.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dangv on 1/18/2017.
 */

public class ThingDistance implements Parcelable {
    private String _idThing;
    private Double _distance;

    protected ThingDistance(Parcel in) {
        _idThing = in.readString();
    }

    public static final Creator<ThingDistance> CREATOR = new Creator<ThingDistance>() {
        @Override
        public ThingDistance createFromParcel(Parcel in) {
            return new ThingDistance(in);
        }

        @Override
        public ThingDistance[] newArray(int size) {
            return new ThingDistance[size];
        }
    };

    public String get_idThing() {
        return _idThing;
    }

    public void set_idThing(String _idThing) {
        this._idThing = _idThing;
    }

    public Double get_distance() {
        return _distance;
    }

    public void set_distance(Double _distance) {
        this._distance = _distance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_idThing);
    }
}
