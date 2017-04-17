package com.suctan.huigangdemo.bean.user;

/**
 * Created by Tom on 2017/4/16.
 */

public class ProceedingBean {

    private String ProceedImageUrl;
    private String ProceedText;

    public  ProceedingBean(String ProceedImageUrl){
        this.ProceedImageUrl = ProceedImageUrl;
    }

    public String getProceedImageUrl() {
        return ProceedImageUrl;
    }

    public void setProceedImageUrl(String proceedImageUrl) {
        ProceedImageUrl = proceedImageUrl;
    }

    public String getProceedText() {
        return ProceedText;
    }

    public void setProceedText(String proceedText) {
        ProceedText = proceedText;
    }
}
