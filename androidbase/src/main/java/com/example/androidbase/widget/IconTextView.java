package com.example.androidbase.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.androidbase.R;

/**
 * 显示字体库图片
 */
public class IconTextView extends TextView {

    public IconTextView(Context context) {
        super(context);
    }

    public IconTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initStyle(attributeSet);
    }

    public IconTextView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        initStyle(attributeSet);
    }

    public void initStyle(AttributeSet attributeSet) {
        TypedArray _ta = getContext().obtainStyledAttributes(attributeSet, R.styleable.IconTextView);
        this.setFacePath(_ta.getString(R.styleable.IconTextView_facePath));
        _ta.recycle();
        setTypeface(null);
    }

    private String facePath = "fonts/icons.ttf";

    public String getFacePath() {
        return facePath;
    }

    public void setFacePath(String facePath) {
        if (facePath!=null&&facePath.length()!=0) {
            this.facePath = facePath;
        }
    }

    public boolean isFaceExists() {
        boolean _rst = true;
        try {
            getContext().getAssets().open(getFacePath());
        } catch (Exception e) {
            e.printStackTrace();
            _rst = false;
        }
        return _rst;
    }

    @Override
    public void setTypeface(Typeface tf) {
        if (isFaceExists()) {
            super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), getFacePath()));
            return;
        }
        super.setTypeface(tf);
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        this.setTypeface(tf);
    }

}
