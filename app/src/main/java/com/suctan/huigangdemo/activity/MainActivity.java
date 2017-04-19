package com.suctan.huigangdemo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidbase.ActivityTask;
import com.example.androidbase.BaseActivity;
import com.example.androidbase.utils.ToastTool;
import com.jaeger.library.StatusBarUtil;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.activity.circle.PostRelease;
import com.suctan.huigangdemo.activity.search.SearchActivity;
import com.suctan.huigangdemo.adapter.FragmentViewPage;
import com.suctan.huigangdemo.fragment.FragmentChanel;
import com.suctan.huigangdemo.fragment.FragmentFind;
import com.suctan.huigangdemo.fragment.FragmentIndex;
import com.suctan.huigangdemo.fragment.FragmentMySelft;

import java.util.ArrayList;

/**
 * Create by Lzh on 16/11/10
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private String TAG = "FragmentMySelft";//方便调试
    private ViewPager main_fragment_viewPage;//viewPage布局


    private ImageView main_imv_index;//首页的图片组件
    private TextView main_tv_index;//首页的文本组件

    private ImageView main_imv_chanel;//频道的图片组件
    private TextView main_tv_chanel;//频道的文本组件

    private ImageView main_imv_myselft;//我的图片组件
    private TextView main_tv_myselft;//我的文本组件
    private ImageView main_imv_find;//发现图片组件
    private TextView main_tv_find;//发现文本组件
    private ListView index_listview;
    private ImageView search;
    private ImageView login_back;
    private TextView login_title;
    private Button addPost;
   /* private BGABadgeRadioButton tab_index;*/

    private LinearLayout ly_index_select;//首页点击布局
    private LinearLayout ly_chanel_select;//频道点击布局
    private LinearLayout ly_find_select;//发现的点击布局
    private LinearLayout ly_myselft_select;//我的点击布局
    private double mExitTime;//判断点击返回键的时间
    private FragmentIndex viewIndex;
    private FragmentChanel viewChanel;
    private FragmentFind viewFind;
    private FragmentMySelft viewMyselft;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        ActivityTask.getInstanse().addActivity(this);//添加进栈
         ToastTool.showToast(TokenManager.getToken(),2);
        System.out.println("token是："+TokenManager.getToken());
        initBarStatus();//初始化状态栏
        initView();//初始化组件
        initViewPage();//初始化viewPage
    }

    private void initBarStatus() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityTask.getInstanse().removeActivity(this);
    }

    private void initViewPage() {
        viewMyselft = new FragmentMySelft();
        ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();
        viewIndex = new FragmentIndex();
        viewChanel = new FragmentChanel();
        viewFind = new FragmentFind();
        viewMyselft = new FragmentMySelft();

        fragmentArrayList.add(viewIndex);
        fragmentArrayList.add(viewChanel);
        fragmentArrayList.add(viewFind);
        fragmentArrayList.add(viewMyselft);
        FragmentViewPage adapter = new FragmentViewPage(getSupportFragmentManager(), fragmentArrayList, MainActivity.this);
        main_fragment_viewPage.setAdapter(adapter);
    }

    //跳到当前fragment的第几页
    public void goFragment(int index) {
        main_fragment_viewPage.setCurrentItem(index);
    }

    public void initView() {
        main_fragment_viewPage = (ViewPager) findViewById(R.id.main_fragment_viewPage);
        ly_index_select = (LinearLayout) findViewById(R.id.ly_index_select);
        main_imv_index = (ImageView) findViewById(R.id.main_imv_index);
        main_tv_index = (TextView) findViewById(R.id.main_tv_index);
        ly_chanel_select = (LinearLayout) findViewById(R.id.ly_chanel_select);
        main_imv_chanel = (ImageView) findViewById(R.id.main_imv_chancel);
        main_tv_chanel = (TextView) findViewById(R.id.main_tv_chanel);
        ly_myselft_select = (LinearLayout) findViewById(R.id.ly_myselft_select);
        main_imv_myselft = (ImageView) findViewById(R.id.main_imv_myselft);
        main_tv_myselft = (TextView) findViewById(R.id.main_tv_myselft);
        ly_find_select = (LinearLayout) findViewById(R.id.ly_find_select);
        main_imv_find = (ImageView) findViewById(R.id.main_imv_find);
        main_tv_find = (TextView) findViewById(R.id.main_tv_find);
        search = (ImageView) findViewById(R.id.search);
        addPost = (Button) findViewById(R.id.add_post);
        login_back = (ImageView) findViewById(R.id.login_back);
        login_title = (TextView) findViewById(R.id.login_title);
        login_title.setText("享享我好吗");
       /* tab_index = (BGABadgeRadioButton) findViewById(R.id.tab_index);*/
//      txt_index_tipNumber = (TextView) findViewById(R.id.txt_index_tipNumber);
        //按钮事件监听

        /*tab_index.setOnClickListener(this);*/
        login_back.setOnClickListener(this);
        login_back.setVisibility(View.GONE);
        addPost.setOnClickListener(this);
        search.setOnClickListener(this);
        ly_index_select.setOnClickListener(this);
        main_tv_index.setTextColor(Color.parseColor("#9DB830"));//初始颜色
        main_tv_chanel.setTextColor(Color.parseColor("#333333"));//初始颜色
        main_tv_myselft.setTextColor(Color.parseColor("#333333"));//初始颜色
        main_tv_find.setTextColor(Color.parseColor("#333333"));//初始颜色
        ly_find_select.setOnClickListener(this);
        ly_chanel_select.setOnClickListener(this);
        ly_myselft_select.setOnClickListener(this);
        main_imv_index.setSelected(true);//初始化页面时默认index显示
        main_fragment_viewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        main_imv_index.setSelected(true);
                        main_imv_chanel.setSelected(false);
                        main_imv_find.setSelected(false);
                        main_imv_myselft.setSelected(false);
                        //选中字体
                        main_tv_index.setTextColor(Color.parseColor("#9DB830"));
                        main_tv_chanel.setTextColor(Color.parseColor("#333333"));
                        main_tv_find.setTextColor(Color.parseColor("#333333"));
                        main_tv_myselft.setTextColor(Color.parseColor("#333333"));

                        login_title.setText("享享我好吗");
                        search.setVisibility(View.VISIBLE);
                        addPost.setVisibility(View.GONE);
                        break;
                    case 1:
                        main_imv_index.setSelected(false);
                        main_imv_chanel.setSelected(true);
                        main_imv_find.setSelected(false);
                        main_imv_myselft.setSelected(false);
                        main_tv_chanel.setTextColor(Color.parseColor("#9DB830"));
                        main_tv_index.setTextColor(Color.parseColor("#333333"));
                        main_tv_find.setTextColor(Color.parseColor("#333333"));
                        main_tv_myselft.setTextColor(Color.parseColor("#333333"));

                        login_title.setText("共享");
                        search.setVisibility(View.GONE);
                        addPost.setVisibility(View.GONE);
                        break;
                    case 2:
                        main_imv_index.setSelected(false);
                        main_imv_chanel.setSelected(false);
                        main_imv_find.setSelected(true);
                        main_imv_myselft.setSelected(false);
                        main_tv_find.setTextColor(Color.parseColor("#9DB830"));
                        main_tv_chanel.setTextColor(Color.parseColor("#333333"));
                        main_tv_index.setTextColor(Color.parseColor("#333333"));
                        main_tv_myselft.setTextColor(Color.parseColor("#333333"));

                        login_title.setText("圈子");
                        search.setVisibility(View.GONE);
                        addPost.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        main_imv_index.setSelected(false);
                        main_imv_chanel.setSelected(false);
                        main_imv_find.setSelected(false);
                        main_imv_myselft.setSelected(true);
                        main_tv_myselft.setTextColor(Color.parseColor("#9DB830"));
                        main_tv_find.setTextColor(Color.parseColor("#333333"));
                        main_tv_chanel.setTextColor(Color.parseColor("#333333"));
                        main_tv_index.setTextColor(Color.parseColor("#333333"));

                        login_title.setText("我的");
                        search.setVisibility(View.GONE);
                        addPost.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_index_select:
                main_fragment_viewPage.setCurrentItem(0);
                break;
            case R.id.ly_chanel_select:
                main_fragment_viewPage.setCurrentItem(1);
                break;
            case R.id.ly_find_select:
                main_fragment_viewPage.setCurrentItem(2);
                break;
            case R.id.ly_myselft_select:
                main_fragment_viewPage.setCurrentItem(3);
                break;
            case R.id.search:
                Intent goMainIntent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(goMainIntent);
                break;
            case R.id.add_post:
                Intent goPostRelease = new Intent(MainActivity.this, PostRelease.class);
                startActivity(goPostRelease);
                break;
        }
    }

}