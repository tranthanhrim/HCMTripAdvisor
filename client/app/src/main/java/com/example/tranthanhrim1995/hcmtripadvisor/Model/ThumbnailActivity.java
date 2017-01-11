package com.example.tranthanhrim1995.hcmtripadvisor.Model;

/**
 * Created by tranthanhrim1995 on 1/10/2017.
 */

public class ThumbnailActivity {
    private String label;
    private int image;

    public ThumbnailActivity() {}

    public ThumbnailActivity(String label, int image) {
        this.label = label;
        this.image = image;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
