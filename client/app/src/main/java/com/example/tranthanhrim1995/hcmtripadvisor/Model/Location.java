package com.example.tranthanhrim1995.hcmtripadvisor.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tranthanhrim1995 on 1/13/2017.
 */

public class Location implements Parcelable {

    private float longtitude;
    private float latitude;

    public Location(float longtitude, float latitude) {
        this.longtitude = longtitude;
        this.latitude = latitude;
    }


    protected Location(Parcel in) {
        longtitude = in.readFloat();
        latitude = in.readFloat();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(float longtitude) {
        this.longtitude = longtitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(longtitude);
        parcel.writeFloat(latitude);
    }
}
