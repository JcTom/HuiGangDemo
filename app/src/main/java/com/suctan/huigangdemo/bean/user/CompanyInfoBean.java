package com.suctan.huigangdemo.bean.user;

/**
 * Created by Tom on 2017/4/12.
 */

public class CompanyInfoBean {
    private String imageUrl;
    public CompanyInfoBean(String imageUrl){
        this.imageUrl=imageUrl;
    }



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
