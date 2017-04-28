package com.suctan.huigangdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ScrollingView;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.suctan.huigangdemo.activity.BaseActivity;


/**
 * Created by Tom on 2017/4/15.
 */

public class FoodParticulars extends BaseActivity {
    private Context context = null;
    private ScrollingView scrollingView;
    private ImageView imageView;

    // 记录首次按下位置
    private float mFirstPosition = 0;
    // 是否正在放大
    private Boolean mScaling = false;

    private DisplayMetrics metric;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }
    private void initView() {
    }
}
