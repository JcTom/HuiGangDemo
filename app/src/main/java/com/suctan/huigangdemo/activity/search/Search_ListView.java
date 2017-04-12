package com.suctan.huigangdemo.activity.search;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Tom on 2017/4/6.
 */

public class Search_ListView extends ListView{
    //解决ListView和ScrollView的冲突
    public Search_ListView(Context context) {
        super(context);
    }

    public Search_ListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Search_ListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //重写onMeasure
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
