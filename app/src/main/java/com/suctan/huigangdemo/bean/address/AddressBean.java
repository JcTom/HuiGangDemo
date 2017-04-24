package com.suctan.huigangdemo.bean.address;

/**
 * Created by 黄淑翰 on 2017/4/22.
 */
public class AddressBean {
    private String name;//收货人
    private String phone;//电话
    private String area;//所在地区
    private String community;//所在小区
    private String address;//详细地址


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
