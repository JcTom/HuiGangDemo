package com.suctan.huigangdemo.bean.wanteat;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/23.
 */
public class WantEatFoodBean {
    private String foodtitle;
    private ArrayList<WantEatFoodItem> wantEatFoodItems;
    private double foodMomeny;
    private String foodResponeTime;
    private String foodHopeTime;
    private String foodDetail;//备注
    private int serviceType;


    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    public String getFoodtitle() {
        return foodtitle;
    }

    public void setFoodtitle(String foodtitle) {
        this.foodtitle = foodtitle;
    }

    public ArrayList<WantEatFoodItem> getWantEatFoodItems() {
        return wantEatFoodItems;
    }

    public void setWantEatFoodItems(ArrayList<WantEatFoodItem> wantEatFoodItems) {
        this.wantEatFoodItems = wantEatFoodItems;
    }

    public double getFoodMomeny() {
        return foodMomeny;
    }

    public void setFoodMomeny(double foodMomeny) {
        this.foodMomeny = foodMomeny;
    }

    public String getFoodResponeTime() {
        return foodResponeTime;
    }

    public void setFoodResponeTime(String foodResponeTime) {
        this.foodResponeTime = foodResponeTime;
    }

    public String getFoodHopeTime() {
        return foodHopeTime;
    }

    public void setFoodHopeTime(String foodHopeTime) {
        this.foodHopeTime = foodHopeTime;
    }

    public String getFoodDetail() {
        return foodDetail;
    }

    public void setFoodDetail(String foodDetail) {
        this.foodDetail = foodDetail;
    }
}
