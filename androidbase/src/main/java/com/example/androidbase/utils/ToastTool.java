package com.example.androidbase.utils;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.toast.TastyToast;

/**
 * Created by chenxiaozhou on 16/6/23.
 */
public class ToastTool {

    /**
     *
     * @param msg
     * @param type 1：成功时的提示，0：错误时的提示，2：普通信息提示
     */
    public static void showToast(String msg,int type) {
        if(msg!=null&&msg.length()!=0) {
            if(type==1) {
                TastyToast.makeText(BaseApplication.getContext(), msg , TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
            } else if(type==0) {
                TastyToast.makeText(BaseApplication.getContext(), msg , TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            } else if(type==2) {
                TastyToast.makeText(BaseApplication.getContext(), msg , TastyToast.LENGTH_SHORT, TastyToast.INFO);
            }
        }
    }

}
