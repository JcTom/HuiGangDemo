package com.suctan.huigangdemo.acache;

import android.content.Context;

import com.example.androidbase.BaseApplication;

import org.xutils.x;

/**
 * Created by Administrator on 2017/3/20.
 */

public class HuiGangApplication extends BaseApplication {
    @Override
    protected void otherInit(Context context) {
        x.Ext.init(this);
    }
}
