package com.suctan.huigangdemo.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidbase.LoadImageManager;
import com.example.androidbase.mvp.MvpFragment;
import com.example.androidbase.widget.CircleImageView;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.CurrentUser;
import com.suctan.huigangdemo.activity.myself.MydiscountAcitity;
import com.suctan.huigangdemo.activity.myself.MykitchenActity;
import com.suctan.huigangdemo.activity.myself.MymoneyActivity;
import com.suctan.huigangdemo.activity.order.SellActivity;
import com.suctan.huigangdemo.activity.myself.SettingActivity;
import com.suctan.huigangdemo.activity.myself.WaitOrdersActivity;
import com.suctan.huigangdemo.activity.myself.WaitassessActivity;
import com.suctan.huigangdemo.activity.myself.WaitfoodActitity;
import com.suctan.huigangdemo.activity.myself.WaitrealActivity;
import com.suctan.huigangdemo.activity.address.addressActivity;
import com.suctan.huigangdemo.activity.order.BuyActivity;
import com.suctan.huigangdemo.activity.myself.my_assess;
import com.suctan.huigangdemo.mvp.login.index.myselft.MySelftPresenter;
import com.suctan.huigangdemo.mvp.login.index.myselft.MySelftView;
import com.suctan.huigangdemo.widget.BigImgDialog;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentMySelft extends MvpFragment<MySelftPresenter> implements View.OnClickListener, MySelftView {


    private View viewMyselft;
    @BindView(R.id.setting)  //绑定这个个人设置界面
            ImageView setting;
    @BindView(R.id.address_setting)     //绑定我的中地址管理
            LinearLayout address;
    @BindView(R.id.I_buy)          //绑定我的中  我买的的页面
            LinearLayout i_buy;
    @BindView(R.id.I_sell)    //绑定我的中 我卖出的页面
            LinearLayout i_sell;
    @BindView(R.id.my_money)   //绑定我的中 我的钱包页面
            LinearLayout my_money;
    @BindView(R.id.My_discount)    //绑定我的中  我的优惠卷页面
            LinearLayout my_discount;
    @BindView(R.id.Mykitchen)       //绑定我的中  我的厨房
            LinearLayout mykitchen;
    @BindView(R.id.waiting_orders)     //绑定我的中 待接单选项页面
            LinearLayout wait_orders;
    @BindView(R.id.wait_food)      //绑定我的中 待送餐选项页面
            LinearLayout wait_food;
    @BindView(R.id.wait_assess)       //绑定我的中 待评价选项页面
            LinearLayout wait_assess;
    @BindView(R.id.wait_real)      //绑定我的中 待确认选项页面
            LinearLayout wait_real;
    @BindView(R.id.my_assess_me)      //绑定我的中 我的评价页面
            LinearLayout my_assess_me;
    private TextView tv_user_name;//用户名
    private CircleImageView imv_head;//头像


    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (viewMyselft == null) {
            this.viewMyselft = inflater.inflate(R.layout.myself, container, false);
        }
        ViewGroup parent = (ViewGroup) viewMyselft.getParent();
        if (parent != null) {
            parent.removeView(viewMyselft);
        }
        ButterKnife.bind(this, viewMyselft);
        return this.viewMyselft;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initview();
    }

    private void initview() {
        tv_user_name = (TextView) viewMyselft.findViewById(R.id.tv_user_name);
        imv_head = (CircleImageView) viewMyselft.findViewById(R.id.imv_head);

        //监听
        imv_head.setOnClickListener(this);
        //设置页面数据
        initViewData();

        //设置个人资料跳转页面
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosetting = new Intent(getActivity(), SettingActivity.class);
                startActivity(gotosetting);
            }
        });

        //设置地址管理跳转页面
        address.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent gotoaddress = new Intent(getActivity(), addressActivity.class);
                startActivity(gotoaddress);
            }
        });

        //设置我买到的页面跳转
        i_buy.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent gotobuy = new Intent(getActivity(), BuyActivity.class);
                startActivity(gotobuy);
            }
        });

        //设置我卖出的页面跳转
        i_sell.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent gotosell = new Intent(getActivity(), SellActivity.class);
                startActivity(gotosell);
            }
        });

        //设置我的钱包跳转页面
        my_money.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent Mymoney = new Intent(getActivity(), MymoneyActivity.class);
                startActivity(Mymoney);
            }
        });

        //设置我的中 我的优惠卷跳转页面
        my_discount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent mydiscount = new Intent(getActivity(), MydiscountAcitity.class);
                startActivity(mydiscount);
            }
        });

        //设置我的中 我的厨房的跳转事件
        mykitchen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent mykitchen = new Intent(getActivity(), MykitchenActity.class);
                startActivity(mykitchen);
            }
        });

        //设置我中，横向栏的 待接单事件
        wait_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wait_orders = new Intent(getActivity(), WaitOrdersActivity.class);
                startActivity(wait_orders);
            }
        });

        //我的页面中的  待送餐
        wait_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wait_food = new Intent(getActivity(), WaitfoodActitity.class);
                startActivity(wait_food);
            }
        });

        //我的页面中的  待评价
        wait_assess.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent wait_assess = new Intent(getActivity(), WaitassessActivity.class);
                startActivity(wait_assess);
            }
        });

        //我的页面中的 待确认
        wait_real.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent wait_real = new Intent(getActivity(), WaitrealActivity.class);
                startActivity(wait_real);
            }
        });

        //我的页面中的 我的评价页面跳转
        my_assess_me.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent gotoassess = new Intent(getActivity(), my_assess.class);
                startActivity(gotoassess);
            }
        });
    }

    //设计view的数据
    private void initViewData() {
        System.out.println("当前user对象" + CurrentUser.getInstance().getUserBean());
        if (CurrentUser.getInstance().getUserBean().getUser_name() != null) {
            tv_user_name.setText(CurrentUser.getInstance().getUserBean().getUser_name());
        }
        if (CurrentUser.getInstance().getUserBean().getUser_icon() != null) {
            LoadImageManager.getImageLoader().displayImage(CurrentUser.getInstance().getUserBean().getUser_icon(), imv_head);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imv_head:
                Drawable drawavle = getResources().getDrawable(R.mipmap.tou);
                BigImgDialog bigDialog = new BigImgDialog(getActivity(), 0, drawavle);
                bigDialog.show();
                break;
        }

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
    protected MySelftPresenter createPresenter() {
        return new MySelftPresenter(this);
    }

    @Override
    protected void lazyLoad() {

    }
}
