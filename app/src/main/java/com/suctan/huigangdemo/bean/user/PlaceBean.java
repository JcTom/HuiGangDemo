package com.suctan.huigangdemo.bean.user;

/**
 * Created by junweiliu on 16/11/7.
 */
public class PlaceBean implements BaseFilter {
    /**
     * 显示的name
     */
    private String showName;

    /**
     * 构造方法
     * @param showName
     */
    public PlaceBean(String showName) {
        this.showName = showName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    /**
     * 筛选的Str
     *
     * @return
     */
    @Override
    public String getFilterStr() {
        return showName;
    }
}
