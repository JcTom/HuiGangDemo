package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.suctan.huigangdemo.R;

/**
 * Created by B-305 on 2017/4/13.
 */

public class foobBookActivity extends Activity implements View.OnClickListener{

    private ImageView food_book_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_book);
        initView();
    }

    private void initView() {
        food_book_back = (ImageView) findViewById(R.id.food_book_back);
        food_book_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.food_book_back:
                finish();
        }

    }
}
