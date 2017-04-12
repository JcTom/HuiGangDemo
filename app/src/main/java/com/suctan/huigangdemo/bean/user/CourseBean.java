package com.suctan.huigangdemo.bean.user;

import java.util.List;

/**
 * Created by Tom on 2017/4/11.
 */

public class CourseBean {
    private int allPages;
    private int count;

    public int getAllPages() {
        return allPages;
    }

    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CourseItemBean> getDatas() {
        return datas;
    }

    public void setDatas(List<CourseItemBean> datas) {
        this.datas = datas;
    }

    public String getStudyCenterCode() {
        return studyCenterCode;
    }

    public void setStudyCenterCode(String studyCenterCode) {
        this.studyCenterCode = studyCenterCode;
    }

    private List<CourseItemBean> datas;
    private String studyCenterCode;
}
