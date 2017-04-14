package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.suctan.huigangdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B-305 on 2017/4/13.
 */

public class release_new_todayfoodActivity  extends Activity {

    @BindView(R.id.new_food_ideas)   //定义 添加新菜谱
    LinearLayout new_food_idea;
    @BindView(R.id.new_food_back)   //定义返回按钮
    ImageView new_food_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_kitchen_release);
        ButterKnife.bind(this);  //绑定布局


        //  实现  添加新菜色页面，返回按钮实现点击事件
        new_food_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        // 实现  在新菜色里面，由于菜谱的后来添加即可，即时添加也可以，所以增加一个跳转页面
        new_food_idea.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent gotofoodbook= new Intent(release_new_todayfoodActivity.this,foobBookActivity.class);
                startActivity(gotofoodbook);
            }
        });

    }

}
