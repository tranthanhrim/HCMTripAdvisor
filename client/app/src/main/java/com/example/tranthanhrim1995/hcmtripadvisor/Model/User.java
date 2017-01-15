package com.example.tranthanhrim1995.hcmtripadvisor.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tranthanhrim1995 on 1/15/2017.
 */

public class User {
    private String ma;
    private String email;
    private String userName;
    private String avatar;


    public User(String ma, String email, String userName, String avatar) {
        this.ma = ma;
        this.email = email;
        this.userName = userName;
        this.avatar = avatar;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
