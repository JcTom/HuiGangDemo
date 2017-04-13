package com.suctan.huigangdemo.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidbase.mvp.MvpFragment;
import com.jude.rollviewpager.RollPagerView;
import com.suctan.huigangdemo.activity.do_want.DoWant;
import com.suctan.huigangdemo.activity.recommend.RecommendActivity;
import com.suctan.huigangdemo.activity.want.Want;
import com.suctan.huigangdemo.adapter.IndexGridAdapter;
import com.suctan.huigangdemo.bean.user.CompanyInfoBean;
import com.suctan.huigangdemo.bean.user.HomeBean;
import com.suctan.huigangdemo.bean.user.Recommend_indexBean;
import com.suctan.huigangdemo.mvp.login.index.home.HomePresenter;
import com.suctan.huigangdemo.mvp.login.index.home.HomeView;
import com.suctan.huigangdemo.R;
import com.yalantis.phoenix.PullToRefreshView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.badgeview.BGABadgeRadioButton;

public class FragmentIndex extends MvpFragment<HomePresenter> implements ViewPager.OnPageChangeListener, View.OnClickListener, HomeView {
    View viewIndex;
    @BindView(R.id.tab_index)
    BGABadgeRadioButton tab_index;
    @BindView(R.id.tab_want)
    BGABadgeRadioButton tab_want;
    @BindView(R.id.tab_do)
    BGABadgeRadioButton tab_do;
    @BindView(R.id.rollViewpager)
    RollPagerView rollPagerView;
    @BindView(R.id.more_texts)
    TextView more_text;
    @BindView(R.id.pull_to_refresh)
    PullToRefreshView pullToRefreshView;
    @BindView(R.id.gridview)
    GridView gridView;
    ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
    ArrayList<Recommend_indexBean>companyList;

    /*ListView listView;
    List<Map<String, ?>> data;
    String str[] = { "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492068213958&di=530b4e4796309358cc9f3d4ab750b9d7&imgtype=0&src=http%3A%2F%2Fstatic.leiphone.com%2Fuploads%2Fnew%2Farticle%2Fpic%2F201509%2F5602525bec148.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492068213958&di=530b4e4796309358cc9f3d4ab750b9d7&imgtype=0&src=http%3A%2F%2Fstatic.leiphone.com%2Fuploads%2Fnew%2Farticle%2Fpic%2F201509%2F5602525bec148.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492068213958&di=530b4e4796309358cc9f3d4ab750b9d7&imgtype=0&src=http%3A%2F%2Fstatic.leiphone.com%2Fuploads%2Fnew%2Farticle%2Fpic%2F201509%2F5602525bec148.jpg"
            };*/
    private Context context;

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
        if (this.viewIndex == null)
            this.viewIndex = paramLayoutInflater.inflate(R.layout.fragment_index, paramViewGroup, false);
        ViewGroup localViewGroup = (ViewGroup) this.viewIndex.getParent();
        if (localViewGroup != null)
            localViewGroup.removeView(this.viewIndex);
        ButterKnife.bind(this, viewIndex);
        initGridData();
//        item();
        return this.viewIndex;
    }


    private void rollPagerViewSet() {
        rollPagerView.setPlayDelay(3000);//*播放间隔
        rollPagerView.setAnimationDurtion(500);//透明度
        ArrayList<CompanyInfoBean>companyList=new ArrayList<>();
        for(int i=0;i<=3;i++){
            CompanyInfoBean companyInfoBean=new CompanyInfoBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492091993911&di=804ff682760b588e56abfc96f9d43ecd&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F82%2F51%2F77P58PICFKD_1024.jpg");
            companyList.add(companyInfoBean);
        }
        rollPagerView.setAdapter(new RollViewpagerAdapter(getActivity(),companyList));//配置适配器
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rollPagerViewSet();
       /* Toast.makeText(getContext(),"你好",Toast.LENGTH_LONG).show();*/
        Check();
    }

    private void initGridData(){
        ArrayList<CompanyInfoBean>companyList=new ArrayList<>();
        for(int i=0;i<=2;i++){
            CompanyInfoBean companyInfoBean=new CompanyInfoBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492091993911&di=804ff682760b588e56abfc96f9d43ecd&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F82%2F51%2F77P58PICFKD_1024.jpg");
            companyList.add(companyInfoBean);
        }
        IndexGridAdapter adapter=new IndexGridAdapter(getActivity(),companyList);
        gridView.setAdapter(adapter);

    }


//    private void item() {
//
//        ArrayList<Recommend_indexBean>mrecommend_indexBean=new ArrayList<>();
//        /*Recommend_indexBean mrecommend = companyList.get(pos);*/
//        for (int i = 0; i < 2; i++) {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//
//            for (int j=0;j<2;j++){
//
//                /*map.put("image",R.mipmap.ic_launcher);
//                map.put("Name", "No" + String.valueOf(i));*/
//            }
//            lstImageItem.add(map);
//        }
//
//        SimpleAdapter saImageItems = new SimpleAdapter(getActivity(),
//                lstImageItem,
//                R.layout.recommend_item,
//                new String[]{"image", "Name"},
//                new int[]{R.id.ItemImage, R.id.ItemText});

        /*saImageItems.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {

                if (view instanceof ImageView&& data instanceof Bitmap){

                    ImageView iv= (ImageView) view;
                    iv.setImageBitmap((Bitmap) data);
                    return true;

                }
                return false;
            }
        });*/
//        gridView.setAdapter(saImageItems);
//       /* getData();*/
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                HashMap<String, Object> item = (HashMap<String, Object>) arg0.getItemAtPosition(arg2);
//
//            }
//        });

//    }

    /*private List<Map<String,?>> getData() {
        data = new ArrayList<Map<String,?>>();
        for (int i=0;i< str.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
        }
        return data;
    }*/

    /*private Bitmap  returnBitMap(String url) {
        Toast.makeText(getContext(),"你好",Toast.LENGTH_LONG).show();
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl
                    .openConnection();
            conn.setDoInput(true);
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }*/

    private void Check() {
        tab_index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goRecommend = new Intent(getActivity(), RecommendActivity.class);
                startActivity(goRecommend);
            }
        });

        tab_want.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goWant = new Intent(getActivity(), Want.class);
                startActivity(goWant);
            }
        });

        tab_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goDoWant = new Intent(getActivity(), DoWant.class);
                startActivity(goDoWant);
            }
        });

        more_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goRecommend = new Intent(getActivity(), RecommendActivity.class);
                startActivity(goRecommend);
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {

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
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void homeInfoDone(HomeBean homeBean) {
    }
}