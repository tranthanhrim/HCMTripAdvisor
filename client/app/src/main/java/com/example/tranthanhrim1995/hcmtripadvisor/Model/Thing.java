package com.example.tranthanhrim1995.hcmtripadvisor.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by tranthanhrim1995 on 1/6/2017.
 */

public class Thing implements Parcelable{

    private String _id;
    private String _ma;
    private String _type;
    private String _placeName;
    private String _detail;
    private String _image;
    private int _grade;
    private String _thumnailLink;
    private float  _ratingSummary;
    private Location _map;
    private ArrayList<String> _meal;
    private boolean _isPromotion;

    public Thing() {}

    public Thing(String type, String _placeName, String _detail) {
        this._type= type;
        this._placeName = _placeName;
        this._detail = _detail;
    }


    protected Thing(Parcel in) {
        _id = in.readString();
        _ma = in.readString();
        _type = in.readString();
        _placeName = in.readString();
        _detail = in.readString();
        _image = in.readString();
        _grade = in.readInt();
        _thumnailLink = in.readString();
        _ratingSummary = in.readFloat();
        _map = in.readParcelable(Location.class.getClassLoader());
        _meal = in.createStringArrayList();
        _isPromotion = in.readByte() != 0;
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


    public Location getMap() {
        return _map;
    }

    public void setMap(Location _map) {
        this._map = _map;
    }

    public String get_ma() {
        return _ma;
    }

    public void set_ma(String _ma) {
        this._ma = _ma;
    }

    public String get_thumnailLink() {
        return _thumnailLink;
    }

    public void set_thumnailLink(String _thumnailLink) {
        this._thumnailLink = _thumnailLink;
    }

    public float get_ratingSummary() {
        return _ratingSummary;
    }

    public void set_ratingSummary(float _ratingSummary) {
        this._ratingSummary = _ratingSummary;
    }

    public ArrayList<String> get_meal() {
        return _meal;
    }

    public void set_meal(ArrayList<String> _meal) {
        this._meal = _meal;
    }


    public boolean get_isPromotion() {
        return _isPromotion;
    }

    public void set_isPromotion(boolean _isPromotion) {
        this._isPromotion = _isPromotion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(_ma);
        parcel.writeString(_type);
        parcel.writeString(_placeName);
        parcel.writeString(_detail);
        parcel.writeString(_image);
        parcel.writeInt(_grade);
        parcel.writeString(_thumnailLink);
        parcel.writeFloat(_ratingSummary);
        parcel.writeParcelable(_map, i);
        parcel.writeStringList(_meal);
        parcel.writeByte((byte) (_isPromotion ? 1 : 0));
    }
}
