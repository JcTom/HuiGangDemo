package com.suctan.huigangdemo.activity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.BaseActivity;
import com.suctan.huigangdemo.activity.myself.SettingActivity;

/**
 * Created by 黄淑翰 on 2017/4/13.
 */
public class SeetingUserKnowArea extends BaseActivity implements View.OnClickListener {
    private static final int resultUserKnowArea = 1008;//获取熟悉领域
    private ImageView imv_know_Area;
    private EditText edt_know_Area;
    private Button btn_knowArea_comfirm;
    private String tempKnowArea;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_special);
        initView();
        getIntentData();
    }

    private void initView() {
        imv_know_Area = (ImageView) findViewById(R.id.imv_know_Area);
        edt_know_Area = (EditText) findViewById(R.id.edt_know_Area);
        btn_knowArea_comfirm = (Button) findViewById(R.id.btn_knowArea_comfirm);
        //实现监听
        imv_know_Area.setOnClickListener(this);
        btn_knowArea_comfirm.setOnClickListener(this);

    }

    private void getIntentData() {
        tempKnowArea = getIntent().getStringExtra("oldData");
        setHoppyEdit();
    }

    //设置输入框的数据
    private void setHoppyEdit() {
        if (tempKnowArea == null) {
            return;
        }
        edt_know_Area.setText(tempKnowArea);
        edt_know_Area.setSelection(tempKnowArea.length());
    }

    //设置回调数据
    private void setResultData(String temAgeString) {
        Intent intent = new Intent(this, SettingActivity.class);
        intent.putExtra("newData", temAgeString);
        setResult(resultUserKnowArea, intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_know_Area:
                finish();
                break;
            case R.id.btn_knowArea_comfirm:
                tempKnowArea = edt_know_Area.getText().toString();
                setResultData(tempKnowArea);
                break;
        }
    }
}
