package com.suctan.huigangdemo.bean.user;

/**
 * Created by Tom on 2017/4/14.
 */

public class MykitchenBean {

    public MykitchenBean(String ImageUrl){
        this.ImageUrl=ImageUrl;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    private String ImageUrl;

}
