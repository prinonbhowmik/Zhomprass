package com.my.zhomprass_java.Models;

import android.graphics.Bitmap;

public class SliderImage {
    private int id;
    private String title;
    private String image;
    private String url;

    public SliderImage(int id, String title, String image, String url) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }
}
