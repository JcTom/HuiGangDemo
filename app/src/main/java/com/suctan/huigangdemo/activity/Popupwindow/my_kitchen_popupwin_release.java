package com.suctan.huigangdemo.activity.Popupwindow;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.myself.release_new_todayfoodActivity;
import com.suctan.huigangdemo.activity.myself.release_todayfood_Activiity;

/**
 * Created by B-305 on 2017/4/10.
 */

public class my_kitchen_popupwin_release extends PopupWindow{

    private Context mContext;

    private View view;

    private Button btn_add_today_food, btn_add_new_food, btn_cancel;


    public my_kitchen_popupwin_release(Context mContext, View.OnClickListener itemsOnClick) {

        this.view = LayoutInflater.from(mContext).inflate(R.layout.my_kitchen_popupwindow, null);

        btn_add_today_food = (Button) view.findViewById(R.id.btn_add_today_food);
        btn_add_new_food   = (Button) view.findViewById(R.id.btn_add_new_food);
        btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        // 取消按钮
        btn_cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // 销毁弹出框
                dismiss();
            }
        });
        // 设置按钮监听
        //点击发布今日菜色
        btn_add_today_food.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dismiss();
                Intent gotoTodayfood =new Intent(v.getContext(), release_todayfood_Activiity.class);
                v.getContext().startActivity(gotoTodayfood);
            }
        });
        //点击发布今日新菜色
        btn_add_new_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Intent gotoNewfood = new Intent(v.getContext(), release_new_todayfoodActivity.class);
                v.getContext().startActivity(gotoNewfood);
            }
        });

        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pop_layout).getTop();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });


        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_photo_anim);

    }
}
