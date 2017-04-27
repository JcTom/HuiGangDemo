package com.suctan.huigangdemo.activity.want;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.ScreenTools;
import com.example.androidbase.utils.ToastTool;
import com.jaeger.library.StatusBarUtil;
import com.roger.catloadinglibrary.CatLoadingView;
import com.sevenheaven.segmentcontrol.SegmentControl;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.CurrentUser;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.activity.Home_Page.HomeActivity;
import com.suctan.huigangdemo.activity.address.addressActivity;
import com.suctan.huigangdemo.adapter.wanteat.AddFoodItemAdapter;
import com.suctan.huigangdemo.adapter.wanteat.AddFoodSpinneItemAdapter;
import com.suctan.huigangdemo.bean.index.EatFoodBean;
import com.suctan.huigangdemo.bean.wanteat.WantEatFoodBean;
import com.suctan.huigangdemo.bean.wanteat.WantEatFoodItem;
import com.suctan.huigangdemo.bean.wanteat.WantEatOtherBean;
import com.suctan.huigangdemo.bean.wanteat.WantEatOtherComment;
import com.suctan.huigangdemo.mvp.login.wanteat.WantEatPresenter;
import com.suctan.huigangdemo.mvp.login.wanteat.WantEatView;
import com.suctan.huigangdemo.widget.TimePickDialog;
import com.suctan.huigangdemo.widget.TipsAddAddressDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tom on 2017/4/12.
 */

public class Want extends MvpActivity<WantEatPresenter> implements View.OnClickListener, SegmentControl.OnSegmentControlClickListener, TimePickDialog.OnHourPickListener, WantEatView, AddFoodItemAdapter.OnWantEatItemOnclick {
    private double reponseTime = 0.5;
    private TimePickDialog timePickDialog;
    private WantEatFoodBean wantEatFoodBean = new WantEatFoodBean();
    private ArrayList<WantEatFoodItem> wantEatFoodItemsList = new ArrayList<>();
    final String[] foodType = {"小份", "中份", "大份"};
    private int tempfoodType;
    private int tempServiceType;
    private int selectHour, selectMinute = 1;
    private String hopetime;

    private CatLoadingView catloingview;
    private ImageButton imvb_wanteat_back;
    private EditText et_wanteat_name;//菜名
    private EditText et_wanteat_title;//标题
    private Spinner spiner_wanteat_item;//spinner下拉列表
    private ListView lv_wanteat_list;//添加后listview的显示
    private ImageButton imvb_wanteat_add;//添加菜的按钮
    private EditText et_wanteat_money;//金额
    private SegmentControl segment_wanteat_control;//选择方式
    private Button btn_wanteattime_minus;//降低时间
    private TextView tv_wanteat_time;//先要吃的时间
    private Button btn_wanteattime_add;//增加时间
    private Button et_wanteat_hopetime;//期望时间
    private EditText et_wanteat_bei;//备注
    private Button btn_wanteat_send;//发布按钮
    private boolean isFirstCreateFoodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.want_eat);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        initViews();
    }

    @Override
    protected WantEatPresenter createPresenter() {
        return new WantEatPresenter(this);
    }

    private void showCatLoadingDialog(boolean isToogleShow) {
        if (isToogleShow) {
            catloingview.show(getSupportFragmentManager(), "");
        } else {
            catloingview.dismiss();
        }
    }


    private void initViews() {
        catloingview = new CatLoadingView();
        et_wanteat_name = (EditText) findViewById(R.id.et_wanteat_name);
        imvb_wanteat_back = (ImageButton) findViewById(R.id.imvb_wanteat_back);
        et_wanteat_title = (EditText) findViewById(R.id.et_wanteat_title);
        spiner_wanteat_item = (Spinner) findViewById(R.id.spiner_wanteat_item);
        lv_wanteat_list = (ListView) findViewById(R.id.lv_wanteat_list);
        imvb_wanteat_add = (ImageButton) findViewById(R.id.imvb_wanteat_add);
        et_wanteat_money = (EditText) findViewById(R.id.et_wanteat_money);
        segment_wanteat_control = (SegmentControl) findViewById(R.id.segment_wanteat_control);
        btn_wanteattime_minus = (Button) findViewById(R.id.btn_wanteattime_minus);
        tv_wanteat_time = (TextView) findViewById(R.id.tv_wanteat_time);
        btn_wanteattime_add = (Button) findViewById(R.id.btn_wanteattime_add);
        et_wanteat_hopetime = (Button) findViewById(R.id.et_wanteat_hopetime);
        et_wanteat_bei = (EditText) findViewById(R.id.et_wanteat_bei);
        btn_wanteat_send = (Button) findViewById(R.id.btn_wanteat_send);


        //监听
        et_wanteat_hopetime.setOnClickListener(this);
        imvb_wanteat_back.setOnClickListener(this);
        imvb_wanteat_add.setOnClickListener(this);
        btn_wanteattime_minus.setOnClickListener(this);
        btn_wanteattime_add.setOnClickListener(this);
        btn_wanteat_send.setOnClickListener(this);
        tv_wanteat_time.setText(reponseTime + "");

        segment_wanteat_control.setOnSegmentControlClickListener(this);
        segment_wanteat_control.setSelectedIndex(0);
        initSpinnerAdapter();
    }

    private void initSpinnerAdapter() {
        final AddFoodSpinneItemAdapter adapter = new AddFoodSpinneItemAdapter(this, foodType);
        spiner_wanteat_item.setAdapter(adapter);
        spiner_wanteat_item.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tempfoodType = i;
                adapter.setDataChange(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    //验证其它类型
    private void VeriatyOrther() {
        if (TextUtils.isEmpty(et_wanteat_money.getText().toString())) {
            Toast.makeText(BaseApplication.getContext(), "请填写有效的金额", Toast.LENGTH_LONG).show();
            showCatLoadingDialog(false);
            return;
        } else {
            if (Double.parseDouble(et_wanteat_money.getText().toString()) < 0 | Double.parseDouble(et_wanteat_money.getText().toString()) == 0) {
                Toast.makeText(BaseApplication.getContext(), "请填写有效的金额", Toast.LENGTH_LONG).show();
                showCatLoadingDialog(false);
                return;
            } else {
                wantEatFoodBean.setFoodMomeny(Double.parseDouble(et_wanteat_money.getText().toString()));
            }
        }

        if (TextUtils.isEmpty(et_wanteat_title.getText().toString())) {
            Toast.makeText(this, "请输入标题！", Toast.LENGTH_SHORT).show();
            showCatLoadingDialog(false);
            return;
        } else {
            wantEatFoodBean.setFoodtitle(et_wanteat_title.getText().toString());
        }
        if (wantEatFoodItemsList.size() == 0) {
            Toast.makeText(this, "请添加你要吃的菜！", Toast.LENGTH_SHORT).show();
            showCatLoadingDialog(false);
            return;
        }
        if (hopetime == null) {
            Toast.makeText(BaseApplication.getContext(), "请选择期望时间！", Toast.LENGTH_LONG).show();
            return;
        }
        if (CurrentUser.getInstance().getUserBean().getUser_address() != null) {
            final TipsAddAddressDialog tipsAddAddressDialog = new TipsAddAddressDialog(this);
            tipsAddAddressDialog.setTipClickLisener(new TipsAddAddressDialog.OnTipLisetner() {
                @Override
                public void comfirm() {
                    Intent intent = new Intent(Want.this, addressActivity.class);
                    startActivity(intent);
                    tipsAddAddressDialog.dismiss();
                }

                @Override
                public void cancel() {
                    tipsAddAddressDialog.dismiss();
                }
            });
            tipsAddAddressDialog.show();
        }


//转换相应时间
        int intRp = (int) reponseTime;
        String hour = null;
        String minute = null;
        if (intRp < 10) {
            hour = "0" + intRp;
        } else {
            hour = intRp + "";
        }
        String strRp = reponseTime + "";
        int dorRp = Integer.parseInt(strRp.substring(strRp.indexOf(".") + 1, strRp.length()));
        if (dorRp > 0) {
            minute = 30 + "";
        } else {
            minute = "00";
        }
        wantEatFoodBean.setFoodResponeTime(hour + ":" + minute + ":" + "00");
        wantEatFoodBean.setServiceType(tempServiceType);
        wantEatFoodBean.setFoodDetail(et_wanteat_bei.getText().toString());
        postServiceWantEat();
    }

    private void postServiceWantEat() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        map.put("order_title", wantEatFoodBean.getFoodtitle());
        map.put("order_price", wantEatFoodBean.getFoodMomeny());
        map.put("order_type", wantEatFoodBean.getServiceType());
        map.put("order_res_time", wantEatFoodBean.getFoodResponeTime());
        map.put("order_expect_time", wantEatFoodBean.getFoodHopeTime());
        map.put("order_note", wantEatFoodBean.getFoodDetail());
        map.put("eatStrArr", getFoodSortList(wantEatFoodItemsList));

        mvpPresenter.PostWantEat(map);
    }


    private String getFoodSortList(ArrayList<WantEatFoodItem> wantEatFoodItemsList) {
        StringBuffer foodSort = new StringBuffer();
        for (int i = 0; i < wantEatFoodItemsList.size(); i++) {
            WantEatFoodItem mwantEatItem = wantEatFoodItemsList.get(i);
            if (wantEatFoodItemsList.size() == 1) {
                foodSort.append(mwantEatItem.getFoodName() + "comma" + mwantEatItem.getFoodType() + "comma" + mwantEatItem.getFoodCount());
            } else {
                if (i == wantEatFoodItemsList.size() - 1) {
                    foodSort.append(mwantEatItem.getFoodName() + "comma" + mwantEatItem.getFoodType() + "comma" + mwantEatItem.getFoodCount());
                } else {
                    foodSort.append(mwantEatItem.getFoodName() + "comma" + mwantEatItem.getFoodType() + "comma" + mwantEatItem.getFoodCount() + "period");
                }
            }

        }

        return foodSort.toString();
    }


    //验证菜名
    private void VerityEatName() {
        if (TextUtils.isEmpty(et_wanteat_name.getText().toString())) {
            Toast.makeText(this, "请添加菜名！", Toast.LENGTH_SHORT).show();
            return;
        } else {
            boolean isExist = false;
            if (wantEatFoodItemsList.size() > 0) {
                for (int i = 0; i < wantEatFoodItemsList.size(); i++) {
                    if (et_wanteat_name.getText().toString().equals(wantEatFoodItemsList.get(i).getFoodName()) && tempfoodType == wantEatFoodItemsList.get(i).getFoodType()) {
                        int count = wantEatFoodItemsList.get(i).getFoodCount();
                        count++;
                        wantEatFoodItemsList.get(i).setFoodCount(count);
                        isExist = true;
                        break;
                    }
                }
            }
            if (!isExist) {
                WantEatFoodItem eatFoodItem1 = new WantEatFoodItem(et_wanteat_name.getText().toString(), tempfoodType, 1);
                wantEatFoodItemsList.add(eatFoodItem1);
            }
        }
        initFoodItemAdapter(wantEatFoodItemsList);
    }

    //验证相应时间
    private void VerityResponeTime() {
        if (String.valueOf(reponseTime).equals("0.0")) {
            Toast.makeText(BaseApplication.getContext(), "请选择有有效的响应时间", Toast.LENGTH_LONG);
            return;
        } else {
            reponseTime = reponseTime - 0.5;
            tv_wanteat_time.setText(reponseTime + "");
        }
    }

    AddFoodItemAdapter addFoodAdatper;

    private void initFoodItemAdapter(ArrayList<WantEatFoodItem> wantEatFoodItemsList) {
        if (!isFirstCreateFoodAdapter) {
            addFoodAdatper = new AddFoodItemAdapter(this, wantEatFoodItemsList);
            lv_wanteat_list.setAdapter(addFoodAdatper);
            addFoodAdatper.setOnClickWantEatDeleteItem(this);
            isFirstCreateFoodAdapter = true;
        } else {
            addFoodAdatper.notifyDataSetChanged();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imvb_wanteat_back:
                finish();
                break;
            case R.id.imvb_wanteat_add:
                VerityEatName();
                break;
            case R.id.btn_wanteattime_minus:
                VerityResponeTime();
                break;
            case R.id.btn_wanteattime_add:
                reponseTime = reponseTime + 0.5;
                tv_wanteat_time.setText(reponseTime + "");
                break;
            case R.id.btn_wanteat_send:
                showCatLoadingDialog(true);
                VeriatyOrther();
                break;
            case R.id.et_wanteat_hopetime:
                showTimePickDialog();
                break;
        }
    }

    private void showTimePickDialog() {
        selectHour = 1;
        selectMinute = 1;
        timePickDialog = new TimePickDialog(this, ScreenTools.getWindowsWidth(this));
        timePickDialog.setOnTimePickOnclick(this);
        timePickDialog.show();
    }

    private void dimssTimePickDialog() {
        timePickDialog.dismiss();
    }


    @Override
    public void onSegmentControlClick(int index) {
        tempServiceType = index;
        segment_wanteat_control.setSelectedIndex(index);
    }

    @Override
    public void onHourSeletTime(int selectHour) {
        this.selectHour = selectHour;
    }

    @Override
    public void onMinuteSelectTime(int selectMinute) {
        this.selectMinute = selectMinute;
    }

    @Override
    public void comfirm() {
        String hour, mimute = null;
        if (selectHour < 10) {
            hour = "0" + selectHour;
        } else {
            hour = "" + selectHour;
        }
        if (selectMinute < 10) {
            mimute = "0" + selectMinute;
        } else {
            mimute = "" + selectMinute;
        }
        hopetime = selectHour + "小时" + selectMinute + "分";
        et_wanteat_hopetime.setText(hopetime);
        wantEatFoodBean.setFoodHopeTime(hour + ":" + mimute + ":" + "00");
        dimssTimePickDialog();
    }

    @Override
    public void cancel() {
        dimssTimePickDialog();
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
        ToastTool.showToast("发布成功,请留意察看!", 1);
        finish();
        showCatLoadingDialog(false);
    }

    @Override
    public void postWantEatFail() {
        showCatLoadingDialog(false);
    }

    @Override
    public void getOtherWantEatListSuc(ArrayList<EatFoodBean> eatFoodBeanList) {

    }

    @Override
    public void addCartSuc() {

    }


    @Override
    public void getOtherWantEatListFail() {

    }

    @Override
    public void getOtherWantCommentListSuc(ArrayList<WantEatOtherComment> wantEatOtherCommentList) {

    }

    @Override
    public void getOtherWantCommentListFail() {

    }

    @Override
    public void deleteWantEatItem(int position) {
        if (wantEatFoodItemsList.size() > 0) {
            wantEatFoodItemsList.remove(wantEatFoodItemsList.get(position));
            addFoodAdatper.notifyDataSetChanged();
        }
    }
}
