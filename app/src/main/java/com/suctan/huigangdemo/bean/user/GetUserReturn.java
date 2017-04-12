package com.suctan.huigangdemo.bean.user;

/**
 * Created by Administrator on 2016/11/11.
 */

public class GetUserReturn {
    private String status;//请求结果状态
    private String msg;//返回信息
    private String datas;//返回用户数据
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }
}
