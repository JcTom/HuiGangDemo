package com.suctan.huigangdemo.acache;


import com.suctan.huigangdemo.bean.user.Users;

/**
 * Created by yao23 on 16/11/6.
 */
public class CurrentUser {
    private static CurrentUser ourInstance = new CurrentUser();

    public static CurrentUser getInstance() {

        return ourInstance;
    }

    private CurrentUser() {
    }

    private Users userBean;

    public Users getUserBean() {
        return userBean;
    }

    public void setUserBean(Users userBean) {

        this.userBean = userBean;
    }
}
