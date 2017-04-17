package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.user.MyChikenFoodBean;

/**
 * Created by B-305 on 2017/4/13.
 */

public class foobBookActivity extends Activity implements View.OnClickListener {
    private static final int getFoodMatriaResult = 1018;//获取材料，过程等信息
    private ImageView food_book_back;
    private EditText edt_makefood_matiria;
    private EditText edt_makefood_care;
    private Button btn_makefood_sure;
    private EditText edt_makefood_progress;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_book);
        initView();
    }

    private void initView() {
        food_book_back = (ImageView) findViewById(R.id.food_book_back);
        edt_makefood_matiria = (EditText) findViewById(R.id.edt_makefood_matiria);
        edt_makefood_care = (EditText) findViewById(R.id.edt_makefood_care);
        btn_makefood_sure = (Button) findViewById(R.id.btn_makefood_sure);
        edt_makefood_progress = (EditText) findViewById(R.id.edt_makefood_progress);
        food_book_back.setOnClickListener(this);
        btn_makefood_sure.setOnClickListener(this);


    }

    //设置回调数据
    private void setResultData() {
        MyChikenFoodBean myChikenFoodBean = new MyChikenFoodBean();
        VarityEdT(myChikenFoodBean);
        myChikenFoodBean.setMakeFood_note(edt_makefood_care.getText().toString());
        Intent intent = new Intent(this, release_new_todayfoodActivity.class);
        intent.putExtra("newFoodData", myChikenFoodBean);
        setResult(getFoodMatriaResult, intent);
        finish();
    }

    private void VarityEdT(MyChikenFoodBean myChikenFoodBean) {
        if (edt_makefood_matiria.getText().toString().isEmpty() | edt_makefood_matiria.getText().toString().isEmpty()) {
            Toast.makeText(BaseApplication.getContext(), "请添加菜的材料信息！", Toast.LENGTH_LONG).show();
            return;
        } else {
            myChikenFoodBean.setMakeFood_res(edt_makefood_matiria.getText().toString());
        }
        if (edt_makefood_progress.getText().toString().isEmpty() | edt_makefood_progress.getText() == null) {
            Toast.makeText(BaseApplication.getContext(), "请添加菜的详细流程！", Toast.LENGTH_LONG).show();
            return;
        } else {
            myChikenFoodBean.setMakeFood_float(edt_makefood_progress.getText().toString());
        }
        if (edt_makefood_care.getText().toString().isEmpty() | edt_makefood_care.getText() == null) {
            Toast.makeText(BaseApplication.getContext(), "请添加菜的注意事项！", Toast.LENGTH_LONG).show();
            return;
        } else {
            myChikenFoodBean.setMakeFood_float(edt_makefood_care.getText().toString());
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.food_book_back:
                finish();
            case R.id.btn_makefood_sure:
                setResultData();

        }
    }
}
