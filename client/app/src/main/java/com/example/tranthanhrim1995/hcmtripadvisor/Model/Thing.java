package com.example.tranthanhrim1995.hcmtripadvisor.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by tranthanhrim1995 on 1/6/2017.
 */

public class Thing implements Parcelable{
    private String _type;
    private String placeName;
    private String detail;
    private String image;
    private String id;
    private int grade;

    public Thing() {}

    public Thing(String type, String placeName, String detail) {
        this._type= type;
        this.placeName = placeName;
        this.detail = detail;
    }

    protected Thing(Parcel in) {
        _type = in.readString();
        placeName = in.readString();
        detail = in.readString();
        image = in.readString();
        id = in.readString();
        grade = in.readInt();
    }

    public static final Creator<Thing> CREATOR = new Creator<Thing>() {
        @Override
        public Thing createFromParcel(Parcel in) {
            return new Thing(in);
        }

        @Override
        public Thing[] newArray(int size) {
            return new Thing[size];
        }
    };

    public String getType() {
        return _type;
    }

    public void setType(String type) {
        this._type = type;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getImage() {
        return image;
    }

    public void setImages(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this._type);
        parcel.writeString(this.placeName);
        parcel.writeString(this.detail);
        parcel.writeString(this.image);
        parcel.writeString(this.id);
        parcel.writeInt(this.grade);
    }
}
