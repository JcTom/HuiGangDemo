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
public class SeetingUserName extends BaseActivity implements View.OnClickListener {
    private static final int resultUserName = 1003;//获取用户名
    private ImageView imv_UserName_back;
    private EditText edt_changeUserName;
    private Button btn_changUserName_comfirm;
    private String tempUserName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_username);
        initView();
        getIntentData();
    }

    private void initView() {
        imv_UserName_back = (ImageView) findViewById(R.id.imv_UserName_back);
        edt_changeUserName = (EditText) findViewById(R.id.edt_changeUserName);
        btn_changUserName_comfirm = (Button) findViewById(R.id.btn_changUserName_comfirm);

        //实现监听
        imv_UserName_back.setOnClickListener(this);
        btn_changUserName_comfirm.setOnClickListener(this);
    }


    private void getIntentData() {
        tempUserName = getIntent().getStringExtra("oldData");
        setHoppyEdit();
    }

    //设置输入框的数据
    private void setHoppyEdit() {
        if (tempUserName == null) {
            return;
        }
        edt_changeUserName.setText(tempUserName);
        edt_changeUserName.setSelection(tempUserName.length());
    }

    //设置回调数据
    private void setResultData(String temAgeString) {
        Intent intent = new Intent(this, SettingActivity.class);
        intent.putExtra("newData", temAgeString);
        setResult(resultUserName, intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_UserName_back:
                finish();
                break;
            case R.id.btn_changUserName_comfirm:
                tempUserName = edt_changeUserName.getText().toString();
                setResultData(tempUserName);
                break;
        }
    }

}
