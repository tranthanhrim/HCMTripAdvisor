package com.example.tranthanhrim1995.hcmtripadvisor.Model;

import java.util.ArrayList;

/**
 * Created by tranthanhrim1995 on 1/6/2017.
 */

public class Thing {
    private String type;
    private String placeName;
    private String detail;
    private ArrayList<String> images;
    private String grade;
    private String id;

    public Thing() {}

    public Thing(String type, String placeName, String detail) {
        this.type= type;
        this.placeName = placeName;
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
