package com.suctan.huigangdemo.activity.address;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.PhoneInfomation;
import com.example.androidbase.utils.VerifyTool;
import com.jaeger.library.StatusBarUtil;
import com.roger.catloadinglibrary.CatLoadingView;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.bean.address.AddressBean;
import com.suctan.huigangdemo.bean.user.CourseBean;
import com.suctan.huigangdemo.mvp.login.Addaddress.addressPresenter;
import com.suctan.huigangdemo.mvp.login.Addaddress.addressView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B-305 on 2017/4/14.
 */

//这里的activity 用来加载数据进入数据库,并且传递给addressActivity,从而在这里显示出来
public class address_listitemActivity extends MvpActivity<addressPresenter> implements View.OnClickListener, addressView {

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
    private Button Save;       //保存按钮
    private ImageView add_address_back;
    private AddressBean mAddress;
    private static final int addressResult = 1011;
    private static final int changeAddressResult = 1012;
    private String comeKey;
    private AddressBean mgetAddress;
    private int addressIndex;
    private CatLoadingView catLoadingView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_address_list_item);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        ButterKnife.bind(this);

        initView();
        getIntentData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        comeKey = intent.getStringExtra("goKey");
        if (comeKey.equals("change")) {
            mgetAddress = (AddressBean) intent.getSerializableExtra("nowAddress");
            addressIndex = intent.getIntExtra("clickIndex", 0);
            initViewData(mgetAddress);
        } else {
            Save.setText("添加");
        }
    }

    private void initViewData(AddressBean mAddress) {
        Save.setText("修改");
        people.setText(mAddress.getName());
        phone.setText(mAddress.getPhone());
        Area.setText(mAddress.getArea());
        Community.setText(mAddress.getCommunity());
        AllAera.setText(mAddress.getAddress());
    }

    private void initView() {
        catLoadingView = new CatLoadingView();
        //返回按钮点击事件
        add_address_back = (ImageView) findViewById(R.id.add_address_back);
        Save = (Button) findViewById(R.id.btnadressSave);
        add_address_back.setOnClickListener(this);
        //保存按钮点击事件
        Save.setOnClickListener(this);
    }

    private void toogleShowCataloading(boolean isshow) {
        if (isshow) {
            catLoadingView.show(getSupportFragmentManager(), "");
        } else {
            catLoadingView.dismiss();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_address_back:
                finish();
            case R.id.btnadressSave:
                addressVarity();
        }
    }

    //封装数据,传递给presenter
    private void addressVarity() {
        toogleShowCataloading(true);
        String people1 = people.getText().toString().trim();
        String phone1 = phone.getText().toString().trim();
        String Area1 = Area.getText().toString().trim();
        String AllArea1 = AllAera.getText().toString().trim();
        String community1 = Community.getText().toString().trim();
        if (TextUtils.isEmpty(people1)) {
            Toast.makeText(this, "收货人填写不能为空", Toast.LENGTH_SHORT).show();
            toogleShowCataloading(false);
            return;
        }
        if (TextUtils.isEmpty(phone1)) {
            Toast.makeText(this, "电话号码填写不能为空", Toast.LENGTH_SHORT).show();
            toogleShowCataloading(false);
            return;
        }
        if (TextUtils.isEmpty(Area1)) {
            Toast.makeText(this, "地区填写不能为空", Toast.LENGTH_SHORT).show();
            toogleShowCataloading(false);
            return;
        }
        if (TextUtils.isEmpty(AllArea1)) {
            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
            toogleShowCataloading(false);
            return;
        }
        if (TextUtils.isEmpty(community1)) {
            Toast.makeText(this, "校区填写不能为空", Toast.LENGTH_SHORT).show();
            toogleShowCataloading(false);
            return;
        }
        mAddress = new AddressBean();
        mAddress.setName(people1);
        mAddress.setPhone(phone1);
        mAddress.setArea(Area1);
        mAddress.setCommunity(community1);
        mAddress.setAddress(AllArea1);

        //api接口还没有写,待定
        Map<String, Object> map = new HashMap<>();
        if (comeKey.equals("add")) {
            map.put("user_token", TokenManager.getToken());
        } else {
            map.put("id", mgetAddress.getId());
        }

        map.put("name", people1);
        map.put("phone", phone1);
        map.put("area", Area1);
        map.put("community", community1);
        map.put("address", AllArea1);
        if (comeKey.equals("add")) {
            AddAddress(map);
        } else {
            ChangeAddress(map);
        }
    }

    private void ChangeAddress(Map<String, Object> map) {
        mvpPresenter.changeAddressActoin(map);

    }

    private void AddAddress(Map map) {
        mvpPresenter.addAddressActoin(map);
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
    protected addressPresenter createPresenter() {
        return new addressPresenter(this);
    }

    @Override
    public void addAdressSuc(int id) {
        toogleShowCataloading(false);
        mAddress.setId(id);
        Intent intent = new Intent(address_listitemActivity.this, addressActivity.class);
        intent.putExtra("newAddress", mAddress);
        setResult(addressResult, intent);
        finish();
    }

    @Override
    public void addAdressFail() {
        catLoadingView.dismiss();
    }

    @Override
    public void getAddressListSuc(ArrayList<AddressBean> addressBeenList) {


    }

    @Override
    public void getAddressListFail() {

    }

    @Override
    public void changeAddressSuc() {
        toogleShowCataloading(false);
        mAddress.setId(mgetAddress.getId());
        Intent intent = new Intent(address_listitemActivity.this, addressActivity.class);
        intent.putExtra("changeAddress", mAddress);
        intent.putExtra("addressIndex", addressIndex);
        setResult(changeAddressResult, intent);
        finish();
    }

    @Override
    public void changeAddressFail() {
        catLoadingView.dismiss();
    }
}
