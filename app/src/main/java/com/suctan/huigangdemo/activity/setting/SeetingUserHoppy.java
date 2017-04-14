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
public class SeetingUserHoppy extends BaseActivity implements View.OnClickListener {

    private static final int resultUserHoppy = 1009;//获取业余爱好
    private ImageView imv_hoppy_back;
    private Button btn_hoppy_comfirm;
    private EditText edt_hobby;
    private String tempHoppy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_hobby);
        initView();
        getIntentData();


    }

    private void initView() {
        imv_hoppy_back = (ImageView) findViewById(R.id.imv_hoppy_back);
        btn_hoppy_comfirm = (Button) findViewById(R.id.btn_hoppy_comfirm);
        edt_hobby = (EditText) findViewById(R.id.edt_hobby);

        //监听
        imv_hoppy_back.setOnClickListener(this);
        btn_hoppy_comfirm.setOnClickListener(this);
    }

    private void getIntentData() {
        tempHoppy = getIntent().getStringExtra("oldData");
        setHoppyEdit();
    }

    //设置输入框的数据
    private void setHoppyEdit() {
        if (tempHoppy == null) {
            return;
        }
        edt_hobby.setText(tempHoppy);
        edt_hobby.setSelection(tempHoppy.length());
    }

    //设置回调数据
    private void setResultData(String temAgeString) {
        Intent intent = new Intent(this, SettingActivity.class);
        intent.putExtra("newData", temAgeString);
        setResult(resultUserHoppy, intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_hoppy_back:
                finish();
                break;
            case R.id.btn_hoppy_comfirm:
                tempHoppy = edt_hobby.getText().toString();
                setResultData(tempHoppy);
                break;
        }
    }
}
