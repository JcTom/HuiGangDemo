package com.suctan.huigangdemo.bean.user;

/**
 * Created by junweiliu on 16/11/22.
 */
public class TimeBean implements BaseFilter{
    /**
     * 时间str
     */
    String timeStr;
    /**
     * 时间事件
     */
/*    String timeEvent;*/

    public TimeBean(String timeStr) {
        this.timeStr = timeStr;
      /*  this.timeEvent = timeEvent;*/
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

   /* public String getTimeEvent() {
        return timeEvent;
    }

    public void setTimeEvent(String timeEvent) {
        this.timeEvent = timeEvent;
    }*/

    @Override
    public String getFilterStr() {
        return timeStr;
    }
}
