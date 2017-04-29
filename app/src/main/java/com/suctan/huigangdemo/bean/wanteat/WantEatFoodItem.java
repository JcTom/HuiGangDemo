package com.suctan.huigangdemo.bean.wanteat;

/**
 * Created by 黄淑翰 on 2017/4/23.
 */
public class WantEatFoodItem {
    private String foodName;
    private int foodType;//大中小
    private int foodCount;//总数


    public WantEatFoodItem(String foodName, int foodType, int foodCount) {
        this.foodName = foodName;
        this.foodType = foodType;
        this.foodCount = foodCount;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodType() {
        return foodType;
    }

    public void setFoodType(int foodType) {
        this.foodType = foodType;
    }
}
