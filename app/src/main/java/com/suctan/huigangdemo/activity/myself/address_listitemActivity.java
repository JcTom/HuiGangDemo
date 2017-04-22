package com.suctan.huigangdemo.activity.myself;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidbase.mvp.MvpActivity;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.bean.user.CourseBean;
import com.suctan.huigangdemo.mvp.login.Addaddress.addressPresenter;
import com.suctan.huigangdemo.mvp.login.Addaddress.addressView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by B-305 on 2017/4/14.
 */

//这里的activity 用来加载数据进入数据库,并且传递给addressActivity,从而在这里显示出来
public class address_listitemActivity extends MvpActivity<addressPresenter> implements View.OnClickListener,addressView {

    @BindView(R.id.addressPeople)
    EditText people;    //收货人
    @BindView(R.id.addressPhone)
    EditText phone;    //联系电话
    @BindView(R.id.addressArea)
    EditText Area;     //所在地区
    @BindView(R.id.addressCommunity)
    EditText Community;   //小区
    @BindView(R.id.addressAllAera)
    EditText AllAera;    //详细地址
    @BindView(R.id.btnadressSave)
    Button Save;       //保存按钮
   private ImageView add_address_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_address_list_item);
        initView();
    }



    private void initView() {
        //返回按钮点击事件
        add_address_back = (ImageView) findViewById(R.id.add_address_back);
        add_address_back.setOnClickListener(this);
        //保存按钮点击事件
        Save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_address_back:
                finish();
            case R.id.btnadressSave:
                addressVarity();
        }
    }
    //封装数据,传递给presenter
    private void addressVarity(){
          String token = TokenManager.getToken();
          String people1 = people.getText().toString().trim();
          String phone1  =phone.getText().toString().trim();
          String Area1   =Area.getText().toString().trim();
          String AllArea1=AllAera.getText().toString().trim();
          String community1 = Community.getText().toString().trim();
         if (TextUtils.isEmpty(people1)){
             Toast.makeText(this,"不能为空", Toast.LENGTH_SHORT).show();
             return;
         }
        if (TextUtils.isEmpty(phone1)){
            Toast.makeText(this,"不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Area1)){
            Toast.makeText(this,"不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(AllArea1)){
            Toast.makeText(this,"不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(community1)){
            Toast.makeText(this,"不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
       //api接口还没有写,待定,还有一个addressList返回值无法接的,等待
        Map<String,Object> map = new HashMap<>();
        map.put("user_token",token);
        map.put("name",people1);
        map.put("phone",phone1);
        map.put("area",Area1);
        map.put("address",AllArea1);
        map.put("community",community1);
        mvpPresenter.addressActoin(map);
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
    public void loadCourseDone(CourseBean courseBean) {

    }
    @Override
    protected addressPresenter createPresenter() {
        return new addressPresenter(this);
    }
}
