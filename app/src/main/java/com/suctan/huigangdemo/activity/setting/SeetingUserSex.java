package com.suctan.huigangdemo.activity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.BaseActivity;
import com.suctan.huigangdemo.activity.myself.SettingActivity;

/**
 * Created by 黄淑翰 on 2017/4/13.
 */
public class SeetingUserSex extends BaseActivity implements View.OnClickListener {

    private static final int resultUserSex = 1005;//获取性别
    private String tempSex;
    private Button btn_sex_comfirm;
    private RadioGroup ragp_change_sex;
    private RadioButton rab_sex_boy;
    private RadioButton rag_sex_girl;
    private ImageView imv_sex_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_sex);
        initView();
        getInentData();

    }

    private void initView() {
        btn_sex_comfirm = (Button) findViewById(R.id.btn_sex_comfirm);
        ragp_change_sex = (RadioGroup) findViewById(R.id.ragp_change_sex);
        rab_sex_boy = (RadioButton) findViewById(R.id.rab_sex_boy);
        rag_sex_girl = (RadioButton) findViewById(R.id.rag_sex_girl);
        imv_sex_back = (ImageView) findViewById(R.id.imv_sex_back);

        //监听
        btn_sex_comfirm.setOnClickListener(this);
        imv_sex_back.setOnClickListener(this);

        getChoseData();
    }

    //设置单选按钮的选中状态
    private void setChoseRadioButton() {
        if (tempSex == null | tempSex.isEmpty()) {
            return;
        }
        if (tempSex.equals(rab_sex_boy.getText().toString())) {
            rab_sex_boy.setChecked(true);
        } else if (tempSex.equals(rag_sex_girl.getText().toString())) {
            rag_sex_girl.setChecked(true);
        }
    }


    private void getInentData() {
        tempSex = getIntent().getStringExtra("oldData");
        setChoseRadioButton();
    }


    private void getChoseData() {
        ragp_change_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rab_sex_boy:
                        tempSex = rab_sex_boy.getText().toString();
                        break;
                    case R.id.rag_sex_girl:
                        tempSex = rag_sex_girl.getText().toString();
                        break;
                }
            }
        });
    }

    //设置回调数据
    private void setResultData(String temAgeString) {
        Intent intent = new Intent(this, SettingActivity.class);
        intent.putExtra("newData", tempSex);
        setResult(resultUserSex, intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sex_comfirm:
                setResultData(tempSex);
                break;
            case R.id.imv_sex_back:
                finish();
                break;
        }
    }
}
