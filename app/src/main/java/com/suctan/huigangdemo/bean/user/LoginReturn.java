package com.suctan.huigangdemo.bean.user;

/**
 * Created by Administrator on 2016/11/11.
 */

public class LoginReturn {
    private int status;//请求结果状态
    private String msg;//返回信息
    private String token;//返回数据

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
