package com.suctan.huigangdemo.bean.address;

import java.util.ArrayList;

/**
 * Created by 黄淑翰 on 2017/4/22.
 */
public class AddAdressReturn {
    private int status;
    private String msg;
    private ArrayList<AddressBean> addressList;


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

    public ArrayList<AddressBean> getAddressList() {
        return addressList;
    }

    public void setAddressList(ArrayList<AddressBean> addressList) {
        this.addressList = addressList;
    }
}
