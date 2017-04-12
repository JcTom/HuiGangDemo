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
            currentUser.setNid(jsonObject.getString("nid"));//管理员ID
            currentUser.setUserName(jsonObject.getString("UserName"));// 用户名
            currentUser.setNickname(jsonObject.getString("nickname"));// 昵称
            currentUser.setName(jsonObject.getString("Name"));// 真实姓名
            currentUser.setMobile(jsonObject.getString("mobile"));//手机号码
            currentUser.setEmail(jsonObject.getString("Email"));// 邮箱
            currentUser.setAddTime(jsonObject.getString("AddTime"));// 添加时间
            currentUser.setAvatar(jsonObject.getString("avatar"));// 头像
            currentUser.setAvatar1(jsonObject.getString("avatar1"));
            currentUser.setAvatar2(jsonObject.getString("avatar2"));
            currentUser.setAvatar3(jsonObject.getString("avatar3"));
            currentUser.setAvatarSet(jsonObject.getString("avatarSet"));// 头像上传的设置
            if (!jsonObject.getString("balance").isEmpty()) {
                currentUser.setBalance(new BigDecimal(jsonObject.getString("balance")).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            currentUser.setIdNumber(jsonObject.getString("IdNumber"));
            currentUser.setSex(jsonObject.getInt("sex"));
            currentUser.setBirthday(jsonObject.getString("birthday"));
            currentUser.setIsReg(jsonObject.getInt("isReg"));
            currentUser.setSpecialtyName(jsonObject.getString("major"));
            currentUser.setEducateLevel(jsonObject.getString("degree"));
            currentUser.setProfessional(jsonObject.getString("professionalTitle"));
            currentUser.setAddress(jsonObject.getString("address"));
            currentUser.setOrganizaton(jsonObject.getString("organization"));
            currentUser.setPrivince(jsonObject.getString("province"));
            currentUser.setRegTime(jsonObject.getString("regTime"));
            currentUser.setRegIp(jsonObject.getString("regIp"));
            currentUser.setSecureId(jsonObject.getString("secureId"));//密保ID
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return currentUser;
    }
}



