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
public class SeetingUserDegree extends BaseActivity implements View.OnClickListener {
    private static final int resultUserDegree = 1007;//获取学历
    private ImageView imv_degree_back;
    private RadioGroup ragp_degree;
    private RadioButton rab_degree_junior;
    private RadioButton rab_degree_senior;
    private RadioButton rab_degree_specialist;
    private RadioButton rab_degree_underGradte;
    private RadioButton rab_degree_master;
    private RadioButton rab_degree_doctor;
    private RadioButton rab_degree_other;
    private Button btn_degree_comfirm;

    private String tempDegreeString;//代替当前选择的学历

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_eduction);
        initView();
        getIntentData();
    }

    private void getIntentData() {
        tempDegreeString = getIntent().getStringExtra("oldData");
        setChoseRadioButton();
        getChoseData();
    }

    private void initView() {
        imv_degree_back = (ImageView) findViewById(R.id.imv_degree_back);
        ragp_degree = (RadioGroup) findViewById(R.id.ragp_degree);
        rab_degree_junior = (RadioButton) findViewById(R.id.rab_degree_junior);
        rab_degree_senior = (RadioButton) findViewById(R.id.rab_degree_senior);
        rab_degree_specialist = (RadioButton) findViewById(R.id.rab_degree_specialist);
        rab_degree_underGradte = (RadioButton) findViewById(R.id.rab_degree_underGradte);
        rab_degree_master = (RadioButton) findViewById(R.id.rab_degree_master);
        rab_degree_doctor = (RadioButton) findViewById(R.id.rab_degree_doctor);
        btn_degree_comfirm = (Button) findViewById(R.id.btn_degree_comfirm);

        //监听事件
        imv_degree_back.setOnClickListener(this);
        btn_degree_comfirm.setOnClickListener(this);

    }

    //设置单选按钮的选中状态
    private void setChoseRadioButton() {
        if (tempDegreeString == null | tempDegreeString.isEmpty()) {
            return;
        }
        if (tempDegreeString.equals(rab_degree_junior.getText().toString())) {
            rab_degree_junior.setChecked(true);
        } else if (tempDegreeString.equals(rab_degree_senior.getText().toString())) {
            rab_degree_senior.setChecked(true);
        } else if (tempDegreeString.equals(rab_degree_specialist.getText().toString())) {
            rab_degree_specialist.setChecked(true);
        } else if (tempDegreeString.equals(rab_degree_underGradte.getText().toString())) {
            rab_degree_underGradte.setChecked(true);
        } else if (tempDegreeString.equals(rab_degree_master.getText().toString())) {
            rab_degree_master.setChecked(true);
        } else if (tempDegreeString.equals(rab_degree_doctor.getText().toString())) {
            rab_degree_doctor.setChecked(true);
        } else {
            rab_degree_other.setChecked(true);
        }
    }


    //获得当前选中的年龄
    private void getChoseData() {
        ragp_degree.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rab_degree_junior:
                        tempDegreeString = rab_degree_junior.getText().toString();
                        break;
                    case R.id.rab_degree_senior:
                        tempDegreeString = rab_degree_senior.getText().toString();
                        break;
                    case R.id.rab_degree_specialist:
                        tempDegreeString = rab_degree_specialist.getText().toString();
                        break;
                    case R.id.rab_degree_master:
                        tempDegreeString = rab_degree_master.getText().toString();
                        break;
                    case R.id.rab_degree_doctor:
                        tempDegreeString = rab_degree_doctor.getText().toString();
                        break;
                    case R.id.rab_degree_other:
                        tempDegreeString = rab_degree_other.getText().toString();
                        break;
                }
            }
        });
    }

    //设置回调数据
    private void setResultData(String temAgeString) {
        Intent intent = new Intent(this, SettingActivity.class);
        intent.putExtra("newData", temAgeString);
        setResult(resultUserDegree, intent);
        finish();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_degree_back:
                finish();
                break;
            case R.id.btn_degree_comfirm:
                setResultData(tempDegreeString);
                break;

        }

    }
}
