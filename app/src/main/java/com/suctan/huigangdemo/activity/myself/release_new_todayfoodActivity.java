package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.suctan.huigangdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B-305 on 2017/4/13.
 */

public class release_new_todayfoodActivity  extends Activity{

    @BindView(R.id.new_food_ideas)
    LinearLayout new_food_idea;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_kitchen_release);
        ButterKnife.bind(this);
        new_food_idea.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent gotofoodbook= new Intent(release_new_todayfoodActivity.this,foobBookActivity.class);
                startActivity(gotofoodbook);
            }
        });

    }
}
