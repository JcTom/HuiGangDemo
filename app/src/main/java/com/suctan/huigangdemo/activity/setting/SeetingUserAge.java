package com.suctan.huigangdemo.activity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.BaseActivity;
import com.suctan.huigangdemo.activity.myself.SettingActivity;

/**
 * Created by 黄淑翰 on 2017/4/13.
 */
public class SeetingUserAge extends BaseActivity implements View.OnClickListener {

    private static final int resultUserAge = 1006;//获取年龄段
    private ImageView imv_chang_back;
    private Button btn_changeAge_comfirm;
    private RadioGroup ragp_change_age;
    private RadioButton rab_aftZero;
    private RadioButton rab_aftseven;
    private RadioButton rab_aftEight;
    private RadioButton rab_aftNight;
    private RadioButton rab_aftOther;
    private String temAgeString;//当前选中的年龄对象字符

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_age);
        initView();
        getInentData();
        getChoseData();

    }

    private void getInentData() {
        temAgeString = getIntent().getStringExtra("oldData");
        setChoseRadioButton();
    }

    private void initView() {
        imv_chang_back = (ImageView) findViewById(R.id.imv_chang_back);
        btn_changeAge_comfirm = (Button) findViewById(R.id.btn_changeAge_comfirm);
        ragp_change_age = (RadioGroup) findViewById(R.id.ragp_change_age);
        rab_aftZero = (RadioButton) findViewById(R.id.rab_aftZero);
        rab_aftseven = (RadioButton) findViewById(R.id.rab_aftseven);
        rab_aftEight = (RadioButton) findViewById(R.id.rab_aftEight);
        rab_aftNight = (RadioButton) findViewById(R.id.rab_aftNight);
        rab_aftOther = (RadioButton) findViewById(R.id.rab_aftOther);

        //事件监听
        imv_chang_back.setOnClickListener(this);
        btn_changeAge_comfirm.setOnClickListener(this);
        ragp_change_age.setOnClickListener(this);
    }

    //设置单选按钮的选中状态
    private void setChoseRadioButton() {
        if (temAgeString == null | temAgeString.isEmpty()) {
            return;
        }
        if (temAgeString.equals(rab_aftZero.getText().toString())) {
            rab_aftZero.setChecked(true);
        } else if (temAgeString.equals(rab_aftseven.getText().toString())) {
            rab_aftseven.setChecked(true);
        } else if (temAgeString.equals(rab_aftEight.getText().toString())) {
            rab_aftEight.setChecked(true);
        } else if (temAgeString.equals(rab_aftNight.getText().toString())) {
            rab_aftNight.setChecked(true);
        } else {
            rab_aftOther.setChecked(true);
        }


    }


    //获得当前选中的年龄
    private void getChoseData() {
        ragp_change_age.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rab_aftZero:
                        temAgeString = rab_aftZero.getText().toString();
                        break;
                    case R.id.rab_aftseven:
                        temAgeString = rab_aftseven.getText().toString();
                        break;
                    case R.id.rab_aftEight:
                        temAgeString = rab_aftEight.getText().toString();
                        break;
                    case R.id.rab_aftNight:
                        temAgeString = rab_aftNight.getText().toString();
                        break;
                    case R.id.rab_aftOther:
                        temAgeString = rab_aftOther.getText().toString();
                        break;
                }
            }
        });
    }

    //设置回调数据
    private void setResultData(String temAgeString) {
        Intent intent = new Intent(this, SettingActivity.class);
        intent.putExtra("newData", temAgeString);
        setResult(resultUserAge, intent);
        finish();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_chang_back:
                finish();
                break;
            case R.id.btn_changeAge_comfirm:
                setResultData(temAgeString);
                break;
        }
    }
}
