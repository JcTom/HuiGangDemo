package com.suctan.huigangdemo.activity.share;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.suctan.huigangdemo.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bigkoo.pickerview.TimePickerView.Type.HOURS_MINS;

/**
 * Created by B-305 on 2017/4/13.
 */

public class ReleaseService extends AppCompatActivity{

    @BindView(R.id.login_back)
    ImageView loginBack;
    @BindView(R.id.login_title)
    TextView loginTitle;
    @BindView(R.id.search)
    ImageView search;

    @BindView(R.id.service_time_add)
    Button addTime;
    @BindView(R.id.service_time_minus)
    Button minusTime;
    @BindView(R.id.service_time)
    TextView serviceTime;

    @BindView(R.id.eat_select_time)
    Button eatSelectTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_release);
        ButterKnife.bind(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initView();
        initServiceTime();

    }

    //初始化标题
    private void initView() {
        final Intent it=ReleaseService.this.getIntent();
        final String serviceType=it.getStringExtra("serviceType");
        loginTitle.setText("发布"+serviceType+"服务");
        search.setVisibility(View.GONE);
        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //响应时间
    private void initServiceTime(){
        serviceTime.setText("1.0");
        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float sT =(float) (Float.parseFloat(serviceTime.getText().toString())+0.5);
                serviceTime.setText(sT+"");
            }
        });
        minusTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float sT =(float) (Float.parseFloat(serviceTime.getText().toString())-0.5);
                if (sT<0) sT=0;
                serviceTime.setText(sT+"");
            }
        });

        eatSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowCustomTimePicker();
            }
        });
    }

    //期望时间选择
    private TimePickerView pvCustomTime;
    private void ShowCustomTimePicker() {// 弹出选择器
        Calendar startDate = Calendar.getInstance();//系统当前时间
        startDate.set(2014,1,23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027,2,28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView(this,HOURS_MINS){

        };
        pvCustomTime.show();
        pvCustomTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                eatSelectTime.setText(getTime(date));
                eatSelectTime.setGravity(Gravity.CENTER);
                eatSelectTime.setTextSize(18);
            }
        });
    }
    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }


    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super .onTouchEvent(event);
    }


}
