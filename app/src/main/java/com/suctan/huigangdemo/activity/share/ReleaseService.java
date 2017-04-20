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
import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.mvp.login.share.PubNeedPresener;
import com.suctan.huigangdemo.mvp.login.share.PubNeedView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bigkoo.pickerview.TimePickerView.Type.HOURS_MINS;

/**
 * Created by B-305 on 2017/4/13.
 */

public class ReleaseService extends MvpActivity<PubNeedPresener> implements PubNeedView, View.OnClickListener {
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
    TextView service_time;
    @BindView(R.id.service_money)
    TextView service_money;
    @BindView(R.id.service_title)
    TextView service_title;
    @BindView(R.id.service_content)
    TextView service_content;
    @BindView(R.id.eat_select_time)
    Button eat_select_time;

    @BindView(R.id.service_fb)
    Button service_fb;
    //此处定义共享需求类型常量
//    private  int need_type = 8  ;
//    private static final int HOME_SERVICES=1; //家政
//    private static final int HELP_BUY= 2;     //代购
//    private static final int FREE_CAR = 3;    //顺风车

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_release);
        ButterKnife.bind(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initView();
        initServiceTime();

    }

    @Override
    protected PubNeedPresener createPresenter() {
        return new PubNeedPresener(this);
    }

    //初始化标题
    private void initView() {
        Intent it = getIntent();
         String serviceType = it.getStringExtra("serviceType");
         int needType = it.getIntExtra("needType",0);
        System.out.println("serviceType是"+serviceType+"需求类型是"+needType);
        loginTitle.setText("发布" + serviceType + "服务");
        search.setVisibility(View.GONE);
        service_fb.setOnClickListener(this);
        loginBack.setOnClickListener(this);

    }
    //响应时间
    private void initServiceTime() {
        service_time.setText("1.0");
        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float sT = (float) (Float.parseFloat(service_time.getText().toString()) + 0.5);
                service_time.setText(sT + "");
            }
        });
        minusTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float sT = (float) (Float.parseFloat(service_time.getText().toString()) - 0.5);
                if (sT < 0) sT = 0;
                service_time.setText(sT + "");
            }
        });

        eat_select_time.setOnClickListener(new View.OnClickListener() {
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
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView(this, HOURS_MINS) {

        };
        pvCustomTime.show();

        pvCustomTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                eat_select_time.setText(getTime(date));
                eat_select_time.setGravity(Gravity.CENTER);
                eat_select_time.setTextSize(18);
            }
        });
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }


    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }


    public void pubNeedVariety() {
        String token = TokenManager.getToken();
        String response_time =  service_time.getText().toString().trim();
        String expect_time =  eat_select_time.getText().toString().trim();
        System.out.println("响应时间是:"+ "期望时间是：");

        String title = service_title.getText().toString().trim();
        String content = service_content.getText().toString().trim();
        String money = service_money.getText().toString().trim();

        Map<String, Object> map = new HashMap();
        map.put("user_token", token);
        map.put("need_title", title);
        map.put("need_content", content);
        map.put("need_price", money);
        map.put("response_time", response_time);
        map.put("expect_time", expect_time);
//        map.put("need_type", need_type);
        System.out.println(map);
        mvpPresenter.pub_needAction(map);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.service_fb:
                pubNeedVariety();
                break;
        }
    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void pubNeedGoBack() {

        ToastTool.showToast("发布成功",1);
    }
}
