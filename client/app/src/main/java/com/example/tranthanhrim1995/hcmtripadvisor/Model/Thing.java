package com.example.tranthanhrim1995.hcmtripadvisor.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tranthanhrim1995 on 1/6/2017.
 */

public class Thing implements Parcelable{
    private String _type;
    private String _placeName;
    private String _detail;
    private String _image;
    private String _id;
    private int _grade;

    public Thing() {}

    public Thing(String type, String _placeName, String _detail) {
        this._type= type;
        this._placeName = _placeName;
        this._detail = _detail;
    }

    protected Thing(Parcel in) {
        _type = in.readString();
        _placeName = in.readString();
        _detail = in.readString();
        _image = in.readString();
        _id = in.readString();
        _grade = in.readInt();
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
        return _placeName;
    }

    public void setPlaceName(String placeName) {
        this._placeName = placeName;
    }

    public String get_detail() {
        return _detail;
    }

    public void set_detail(String _detail) {
        this._detail = _detail;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public int get_grade() {
        return _grade;
    }

    public void set_grade(int _grade) {
        this._grade = _grade;
    }

    public String getImage() {
        return _image;
    }

    public void setImages(String _image) {
        this._image = _image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this._type);
        parcel.writeString(this._placeName);
        parcel.writeString(this._detail);
        parcel.writeString(this._image);
        parcel.writeString(this._id);
        parcel.writeInt(this._grade);
    }
}
