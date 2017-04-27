package com.suctan.huigangdemo.activity.eatfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.androidbase.LoadImageManager;
import com.example.androidbase.mvp.MvpActivity;
import com.jaeger.library.StatusBarUtil;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.activity.cart.ShoppingCart;
import com.suctan.huigangdemo.adapter.eatfood.EatFoodGridAdapter;
import com.suctan.huigangdemo.adapter.eatfood.EatRecycleViewAdapter;
import com.suctan.huigangdemo.bean.index.EatFoodBean;
import com.suctan.huigangdemo.bean.wanteat.WantEatOtherComment;
import com.suctan.huigangdemo.mvp.login.wanteat.WantEatPresenter;
import com.suctan.huigangdemo.mvp.login.wanteat.WantEatView;
import com.suctan.huigangdemo.widget.TipsAddCartDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 黄淑翰 on 2017/4/20.
 */
public class EatFoodDetail extends MvpActivity<WantEatPresenter> implements WantEatView, EatRecycleViewAdapter.OnAddCartButtonLisetner, View.OnClickListener {
    private LinearLayout ly_arroud_detail;
    private RecyclerView bottom_recycle_detail;
    private ListView bottom_listview_comment;
    private EatFoodBean mEatBean;
    private ImageView imv_eat_deail;//详情图
    private TextView tv_eat_name;
    private TextView tv_remain_food;
    private TextView tv_eat_momeny;
    private TextView tv_food_name;
    private Button btn_addcart_comfrim;
    private ImageView imv_goto_cart;
    private ImageView imv_eatdeatil_back;
    private ScrollView scv_fooddetail;


    private boolean isFirstCreateGridAdaptr;
    private boolean isFirstCreateRecycleAdapter;
    private ArrayList<EatFoodBean> eatFoodBeanList = new ArrayList<>();
    private ArrayList<WantEatOtherComment> wantEatOtherCommentLists = new ArrayList<>();

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_one_food_details);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        initView();
        getIntentData();
        getOrderFoodDetail();
    }


    private void getOrderFoodDetail() {
        Map<String, Object> map = new HashMap<>();
        map.put("order_id", mEatBean.getOrder_id());
        mvpPresenter.getEatOrtherList(map);
    }

    @Override
    protected WantEatPresenter createPresenter() {
        return new WantEatPresenter(this);
    }

    private void getIntentData() {
        mEatBean = (EatFoodBean) getIntent().getSerializableExtra("nowEatFood");
        initViewData();
    }

    private void initViewData() {
        if (mEatBean != null) {
            if (mEatBean.getOrder_pic() != null) {
                LoadImageManager.getImageLoader().displayImage(mEatBean.getOrder_pic(), imv_eat_deail);
            }
            tv_eat_name.setText(mEatBean.getUser_name());
            tv_remain_food.setText(mEatBean.getNum() + "");
            tv_eat_momeny.setText("￥" + mEatBean.getOrder_price());
            tv_food_name.setText(mEatBean.getOrder_title());
        }
    }

    private void initView() {
        ly_arroud_detail = (LinearLayout) findViewById(R.id.ly_arroud_detail);
        imv_eat_deail = (ImageView) findViewById(R.id.imv_eat_deail);
        tv_eat_name = (TextView) findViewById(R.id.tv_eat_name);
        tv_eat_momeny = (TextView) findViewById(R.id.tv_eat_momeny);
        tv_food_name = (TextView) findViewById(R.id.tv_food_name);
        tv_remain_food = (TextView) findViewById(R.id.tv_remain_food);
        btn_addcart_comfrim = (Button) findViewById(R.id.btn_addcart_comfrim);
        imv_goto_cart = (ImageView) findViewById(R.id.imv_goto_cart);
        imv_eatdeatil_back = (ImageView) findViewById(R.id.imv_eatdeatil_back);
        scv_fooddetail = (ScrollView) findViewById(R.id.scv_fooddetail);
        scv_fooddetail.smoothScrollTo(0,0);
        btn_addcart_comfrim.setOnClickListener(this);
        imv_eatdeatil_back.setOnClickListener(this);
        imv_goto_cart.setOnClickListener(this);
        /*底部*/
        /**********************************************************************************/
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_one_food_details, null, false);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM;
        ly_arroud_detail.addView(view, params);
        bottom_recycle_detail = (RecyclerView) view.findViewById(R.id.bottom_recycle_detail);
        bottom_listview_comment = (ListView) view.findViewById(R.id.bottom_listview_comment);


        initListener();
    }

    private void addCartRequest() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        map.put("order_id", mEatBean.getOrder_id());
        map.put("order_title", mEatBean.getOrder_title());
        map.put("order_pic", mEatBean.getOrder_pic());
        map.put("order_price", mEatBean.getOrder_price());
        map.put("order_type", mEatBean.getOrder_type());
        map.put("order_num", 1);
        mvpPresenter.addCart(map);
    }


    private void initListener() {
        bottom_listview_comment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    EatRecycleViewAdapter recycleAdapter;

    //初始化recycleviewdata
    private void initRecycleViewAdapter(ArrayList<EatFoodBean> eatFoodBeanList) {
        if (!isFirstCreateRecycleAdapter) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            bottom_recycle_detail.setLayoutManager(layoutManager);
            recycleAdapter = new EatRecycleViewAdapter(this, eatFoodBeanList);
            bottom_recycle_detail.setAdapter(recycleAdapter);
            recycleAdapter.setOnAddCartClick(this);
            isFirstCreateRecycleAdapter = true;
        } else {
            recycleAdapter.notifyDataSetChanged();
        }
    }

    EatFoodGridAdapter commentAdapter;

    private void initGridViewAdater(ArrayList<WantEatOtherComment> wantEatOtherCommentList) {
        if (!isFirstCreateGridAdaptr) {
            commentAdapter = new EatFoodGridAdapter(this, wantEatOtherCommentList);
            bottom_listview_comment.setAdapter(commentAdapter);
            isFirstCreateGridAdaptr = true;
        } else {
            commentAdapter.notifyDataSetChanged();
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
    public void postWantEatSuc() {

    }

    @Override
    public void postWantEatFail() {

    }

    @Override
    public void getOtherWantEatListSuc(ArrayList<EatFoodBean> eatFoodBeanLists) {
        this.eatFoodBeanList.addAll(eatFoodBeanLists);
        initRecycleViewAdapter(eatFoodBeanList);
    }

    @Override
    public void addCartSuc() {
        toogleShowCartSucTip(true);
    }

    @Override
    public void getOtherWantEatListFail() {

    }

    @Override
    public void getOtherWantCommentListSuc(ArrayList<WantEatOtherComment> wantEatOtherCommentList) {
        wantEatOtherCommentLists.addAll(wantEatOtherCommentList);
        initGridViewAdater(wantEatOtherCommentLists);
    }

    @Override
    public void getOtherWantCommentListFail() {

    }

    //交换数据显示
    private void toogleChangeDataShow(int showIndex) {
        if (eatFoodBeanList != null) {
            for (int i = 0; i < eatFoodBeanList.size(); i++) {
                if (i == showIndex) {
                    mEatBean = eatFoodBeanList.get(showIndex);
                    eatFoodBeanList.remove(eatFoodBeanList.get(showIndex));
                    eatFoodBeanList.add(i, mEatBean);
                    initViewData();
                    recycleAdapter.notifyDataSetChanged();
                    return;
                }
            }
        }
    }

    TipsAddCartDialog addCartDialog;

    private void toogleShowCartSucTip(boolean isShow) {
        if (isShow) {
            addCartDialog = new TipsAddCartDialog(this);
            addCartDialog.setTipClickLisener(new TipsAddCartDialog.OnTipLisetner() {
                @Override
                public void comfirm() {
                    Intent intent = new Intent(EatFoodDetail.this, ShoppingCart.class);

                    startActivity(intent);
                    addCartDialog.dismiss();
                }

                @Override
                public void cancel() {
                    addCartDialog.dismiss();
                }
            });
            addCartDialog.show();
        }
    }

    @Override
    public void addCartIndex(int addIndex) {
        toogleChangeDataShow(addIndex);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_addcart_comfrim:
                if (mEatBean != null) {
                    addCartRequest();
                }
                break;
            case R.id.imv_goto_cart:
                Intent intent = new Intent(this, ShoppingCart.class);
                startActivity(intent);
                break;
            case R.id.imv_eatdeatil_back:
                finish();
                break;

        }
    }
}
