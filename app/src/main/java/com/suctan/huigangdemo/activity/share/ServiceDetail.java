package com.suctan.huigangdemo.activity.share;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidbase.LoadImageManager;
import com.suctan.huigangdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.suctan.huigangdemo.R.id.login_title;

public class ServiceDetail extends AppCompatActivity {

    @BindView(R.id.login_back)
    ImageView loginBack;
    @BindView(login_title)
    TextView loginTitle;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.phone)
    TextView user_phone;
    @BindView(R.id.user_icon)
    ImageView user_icon;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.service_money)
    TextView price;
    @BindView(R.id.pub_time)
    TextView pub_time;
    @BindView(R.id.pub_user)
    TextView pub_user;
    @BindView(R.id.help)
    Button help;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_details);
        ButterKnife.bind(this);
        initView();
    }

    /*
 * 初始化标题栏
 * */
    private void initView() {
        Bundle need = getIntent().getBundleExtra("need");
        user_name.setText(need.getString("name"));
        pub_user.setText(need.getString("name"));
        final String phone = need.getString("phone");
        user_phone.setText(phone);
        address.setText(need.getString("address"));
        LoadImageManager.getImageLoader().displayImage(need.getString("icon"),user_icon);
//        LoadImageManager.getImageLoader().displayImage(myAssessGoBean.getImage_evaluate_User(),holder.evaluate_hand_Image);
//        icon.setText(need.getString("icon"));
        content.setText(need.getString("content"));
        price.setText(need.getString("price"));
        pub_time.setText(need.getString("pub_time"));
        search.setVisibility(View.GONE);
        loginTitle.setText(need.getString("title"));
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
