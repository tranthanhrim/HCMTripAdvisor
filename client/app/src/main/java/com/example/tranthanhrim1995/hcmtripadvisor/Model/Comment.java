package com.example.tranthanhrim1995.hcmtripadvisor.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tranthanhrim1995 on 1/12/2017.
 */

public class Comment implements Parcelable{
    private String _id;
    private String _thingsToDoID;
    private String _content;
    private String _time;
    private String _avatar;
    private String _userName;

    protected Comment(Parcel in) {
        _id = in.readString();
        _thingsToDoID = in.readString();
        _content = in.readString();
        _time = in.readString();
    }

    public Comment(String _userName, String _avatar, String _content, String _time) {
        this._userName = _userName;
        this._avatar = _avatar;
        this._content = _content;
        this._time = _time;
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_thingsToDoID() {
        return _thingsToDoID;
    }

    public void set_thingsToDoID(String _thingsToDoID) {
        this._thingsToDoID = _thingsToDoID;
    }

    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }

    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }

    public String get_avatar() {
        return _avatar;
    }

    public void set_avatar(String _avatar) {
        this._avatar = _avatar;
    }

    public String get_userName() {
        return _userName;
    }

    public void set_userName(String _userName) {
        this._userName = _userName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(_thingsToDoID);
        parcel.writeString(_content);
        parcel.writeString(_time);
    }
}
