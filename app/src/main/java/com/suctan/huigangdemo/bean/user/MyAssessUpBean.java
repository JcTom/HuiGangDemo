package com.suctan.huigangdemo.bean.user;

/**
 * Created by Tom on 2017/4/16.
 */

public class MyAssessUpBean {

    private String image_up_evaluate_User;//我的评价头像

    private String image_up_imageUrl;//我的评价头像

    public MyAssessUpBean(String image_up_imageUrl,String image_up_evaluate_User){

        this.image_up_evaluate_User = image_up_evaluate_User;
        this.image_up_imageUrl = image_up_imageUrl;

    }

    public String getImage_up_evaluate_User() {
        return image_up_evaluate_User;
    }

    public void setImage_up_evaluate_User(String image_up_evaluate_User) {
        this.image_up_evaluate_User = image_up_evaluate_User;
    }

    public String getImage_up_imageUrl() {
        return image_up_imageUrl;
    }

    public void setImage_up_imageUrl(String image_up_imageUrl) {
        this.image_up_imageUrl = image_up_imageUrl;
    }
}
