package com.suctan.huigangdemo.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.suctan.huigangdemo.R;

/**
 * Create by kushanmao on 2017/2/28 0028 10:29
 */

public class BigImgDialog extends AlertDialog {

    private Context context;
    private int width;
    private ImageView img;
    private Drawable drawable;

    protected BigImgDialog(Context context) {
        this(context, 0, null);
    }

    public BigImgDialog(Context context, int themeResId, Drawable drawable) {
        super(context, themeResId);
        this.context = context;
        this.drawable = drawable;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.big_img_dialog, null);
        img = (ImageView) view.findViewById(R.id.big_img);
        //获取window的宽，设置给图片放大
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        //使dialog全屏
        Window win = getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        win.setAttributes(lp);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        if (drawable != null) {
            ViewGroup.LayoutParams params = img.getLayoutParams();
            lp.width = lp.height = width;
            img.setLayoutParams(params);
            img.setImageDrawable(drawable);
        }

        setContentView(view);
    }

    /**
     * 设置要放大的图片
     *
     * @param drawable
     */
}
