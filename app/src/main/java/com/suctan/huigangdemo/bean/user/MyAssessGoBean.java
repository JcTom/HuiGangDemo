package com.suctan.huigangdemo.bean.user;

import android.widget.ImageView;

/**
 * Created by Tom on 2017/4/16.
 */

public class MyAssessGoBean {

    private String image_evaluate_User;//我的评价头像

    private String imageUrl;//我的评价头像

    public MyAssessGoBean(String imageUrl,String image_evaluate_User){
        this.imageUrl=imageUrl;
        this.image_evaluate_User=  image_evaluate_User;
    }
    public String getImage_evaluate_User() {
        return image_evaluate_User;
    }

    public void setImage_evaluate_User(String image_evaluate_User) {
        this.image_evaluate_User = image_evaluate_User;
    }

    public  String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
