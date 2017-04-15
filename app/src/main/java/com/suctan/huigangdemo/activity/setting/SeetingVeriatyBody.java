package com.suctan.huigangdemo.activity.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.BaseActivity;

/**
 * Created by 黄淑翰 on 2017/4/13.
 */
public class SeetingVeriatyBody extends BaseActivity {
    private static final int resultUserVeriatyBody = 1004;//验证身份
    private ImageView imv_varityBody_back;
    private EditText edt_varity_Name;
    private Button btn_varity_comfirm;
    private EditText edt_varity_IDCard;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.know_username);
        initView();


    }

    private void initView() {
        imv_varityBody_back = (ImageView) findViewById(R.id.imv_varityBody_back);
        edt_varity_Name = (EditText) findViewById(R.id.edt_varity_Name);
        btn_varity_comfirm = (Button) findViewById(R.id.btn_varity_comfirm);
        edt_varity_IDCard = (EditText) findViewById(R.id.edt_varity_IDCard);

    }
}
