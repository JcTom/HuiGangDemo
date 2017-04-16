package com.suctan.huigangdemo.util;


import com.suctan.huigangdemo.bean.user.Users;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;


/**
 * Create by LZH  on 16/11/16
 */
public class JSONParstObject {

    /**
     * 获取用户的信息
     */
    public static Users GetUserJSonObject(String userString) {
        Users currentUser = null;
        try {
            JSONObject jsonObject = new JSONObject(userString);
            currentUser = new Users();
            if (jsonObject.getString("user_phone") != null) {
                currentUser.setUser_phone(jsonObject.getString("user_phone"));
            }
            if (jsonObject.getString("user_name") != null) {
                currentUser.setUser_name(jsonObject.getString("user_name"));
            }
            if (jsonObject.getString("user_icon") != null) {
                currentUser.setUser_icon(jsonObject.getString("user_icon"));
            }
            if (jsonObject.getString("user_alias") != null) {
                currentUser.setUser_alias(jsonObject.getString("user_alias"));
            }
            currentUser.setUser_sex(jsonObject.getInt("user_sex"));
            if (jsonObject.getString("user_age") != null) {
                currentUser.setUser_age(jsonObject.getString("user_age"));
            }
            if (jsonObject.getString("user_education") != null) {
                currentUser.setUser_education(jsonObject.getString("user_education"));
            }
            if (jsonObject.getString("user_skill") != null) {
                currentUser.setUser_skill(jsonObject.getString("user_skill"));
            }
            if (jsonObject.getString("user_hobby") != null) {
                currentUser.setUser_hobby(jsonObject.getString("user_hobby"));
            }
            if (jsonObject.getString("user_address") != null) {
                currentUser.setUser_address(jsonObject.getString("user_address"));
            }
            currentUser.setUser_money(jsonObject.getDouble("user_money"));

            if (jsonObject.getString("user_card") != null) {
                currentUser.setUser_card(jsonObject.getString("user_card"));
            }
            if (jsonObject.getString("user_coupon") != null) {
                currentUser.setUser_coupon(jsonObject.getString("user_coupon"));
            }

            currentUser.setIs_cooking(jsonObject.getInt("is_cooking"));

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return currentUser;
    }
}



