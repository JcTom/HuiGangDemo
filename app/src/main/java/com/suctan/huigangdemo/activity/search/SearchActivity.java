package com.suctan.huigangdemo.activity.search;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.jaeger.library.StatusBarUtil;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.Home_Page.HomeActivity;

/**
 * Created by Tom on 2017/4/6.
 */

public class SearchActivity extends HomeActivity {

   private ImageView search_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.search_layout);
        Search_View searchView = new Search_View(this);
        setContentView(searchView);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);

        search_back = (ImageView) findViewById(R.id.search_back);
        search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
