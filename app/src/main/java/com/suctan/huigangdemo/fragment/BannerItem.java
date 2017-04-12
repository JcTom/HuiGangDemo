package com.suctan.huigangdemo.fragment;

/**
 * Created by Tom on 2017/4/10.
 */

public class BannerItem {

    private String title;
    private String image;

    public BannerItem() {

    }

    public BannerItem(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
