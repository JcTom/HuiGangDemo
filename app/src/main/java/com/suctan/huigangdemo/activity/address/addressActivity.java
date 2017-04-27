package com.suctan.huigangdemo.activity.address;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.ACache;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.CurrentUser;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.adapter.address.AddressListAdapter;
import com.suctan.huigangdemo.bean.address.AddressBean;
import com.suctan.huigangdemo.bean.user.Users;
import com.suctan.huigangdemo.mvp.login.Addaddress.addressPresenter;
import com.suctan.huigangdemo.mvp.login.Addaddress.addressView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/13.
 */

public class addressActivity extends MvpActivity<addressPresenter> implements View.OnClickListener, addressView {

    private ImageView address_back;   //地址管理中的返回按钮
    private TextView address_add;     //地址管理中的添加按钮
    private ListView lv_address;
    private ArrayList<AddressBean> addressBeanLists = new ArrayList<>();
    private static final int requestAddress = 1011;


    private boolean isFirstCreateAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_manager);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        initView();
        getAddressList();
    }

    private void getAddressList() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        mvpPresenter.getAddressList(map);
    }

    @Override
    protected addressPresenter createPresenter() {
        return new addressPresenter(this);
    }

    private void initView() {
        //地址管理中的添加按钮 初始化
        address_add = (TextView) findViewById(R.id.address_add);

        address_back = (ImageView) findViewById(R.id.address_back);
        lv_address = (ListView) findViewById(R.id.lv_address);

        address_back.setOnClickListener(this);
        address_add.setOnClickListener(this);
        //地址管理中的返回按钮 初始化


        lv_address.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(addressActivity.this, address_listitemActivity.class);
                intent.putExtra("nowAddress", addressBeanLists.get(i));
                intent.putExtra("goKey", "change");
                intent.putExtra("clickIndex", i);
                startActivityForResult(intent, 1000);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.address_back:
                finish();
                break;
            case R.id.address_add:
                Intent add_address = new Intent(addressActivity.this, address_listitemActivity.class);
                add_address.putExtra("goKey", "add");
                startActivityForResult(add_address, requestAddress);
        }
    }


    @Override
    public void addAdressSuc(int id) {

    }

    @Override
    public void addAdressFail() {

    }

    @Override
    public void getAddressListSuc(ArrayList<AddressBean> addressBeenList) {
        addressBeanLists.addAll(addressBeenList);
        initAddressAdapter(addressBeenList);

    }


    AddressListAdapter addressAdapter;

    private void initAddressAdapter(ArrayList<AddressBean> addressBeenList) {
        if (!isFirstCreateAdapter) {
            addressAdapter = new AddressListAdapter(this, addressBeenList);
            lv_address.setAdapter(addressAdapter);
            isFirstCreateAdapter = true;
        } else {
            addressAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getAddressListFail() {

    }

    @Override
    public void changeAddressSuc() {

    }

    @Override
    public void changeAddressFail() {

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == requestAddress) {
                AddressBean mAddress = (AddressBean) data.getSerializableExtra("newAddress");
                if (mAddress != null) {
                    addressBeanLists.add(mAddress);
                    initAcache(mAddress.getAddress());
                    initAddressAdapter(addressBeanLists);
                }
            } else {
                AddressBean mAddress = (AddressBean) data.getSerializableExtra("changeAddress");
                int addressIndex = data.getIntExtra("addressIndex", 0);
                for (int i = 0; i < addressBeanLists.size(); i++) {
                    if (i == addressIndex) {
                        addressBeanLists.remove(addressBeanLists.get(addressIndex));
                        addressBeanLists.add(addressIndex, mAddress);
                        initAddressAdapter(addressBeanLists);
                        initAcache(mAddress.getAddress());
                        break;
                    }
                }
            }
        }
    }

    private void initAcache(String address) {
        Users currentUser = CurrentUser.getInstance().getUserBean();
        currentUser.setUser_address(address);
        ACache aCache = ACache.get(BaseApplication.getContext());
        String oldUserStr = aCache.getAsString("User");
        //删除缓存中的数据
        if (oldUserStr != null) {
            aCache.remove("User");
        }
        //添加缓存数据
        aCache.put("User", new Gson().toJson(currentUser));
        //获取数据缓存
        CurrentUser.getInstance().setUserBean(currentUser);
    }

}
