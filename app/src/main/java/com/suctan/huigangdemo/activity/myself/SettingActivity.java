package com.suctan.huigangdemo.activity.myself;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.setting.SeetingForGetPwd;
import com.suctan.huigangdemo.activity.setting.SeetingUserAge;
import com.suctan.huigangdemo.activity.setting.SeetingUserDegree;
import com.suctan.huigangdemo.activity.setting.SeetingUserFogetPwd;
import com.suctan.huigangdemo.activity.setting.SeetingUserHoppy;
import com.suctan.huigangdemo.activity.setting.SeetingUserKnowArea;
import com.suctan.huigangdemo.activity.setting.SeetingUserName;
import com.suctan.huigangdemo.activity.setting.SeetingUserSex;

/**
 * Created by B-305 on 2017/4/13.
 */

public class SettingActivity extends Activity implements View.OnClickListener {
    private static final int requestCamera = 1001;//获取拍照
    private static final int requestPhoto = 1002;//获取图库
    private static final int requestUserName = 1003;//获取用户名
    private static final int requestUserVeriatyBody = 1004;//验证身份
    private static final int requestUserSex = 1005;//获取性别
    private static final int requestUserAge = 1006;//获取年龄段
    private static final int requestUserDegree = 1007;//获取学历
    private static final int requestUserKnowArea = 1008;//获取熟悉领域
    private static final int requestUserHoppy = 1009;//获取业余爱好


    private LinearLayout ly_head_setting;//设置头像
    private LinearLayout ly_chose_photo;//选择图库
    private LinearLayout ly_chose_camera;//选择拍照

    private Dialog dialogHead;//选择图库拍照弹出框


    private ImageView imv_head;//头像显示
    private LinearLayout ly_userName;//用户名点击修改
    private TextView tv_userName;//用户名显示
    private LinearLayout ly_varityBody;//身份验证点击
    private TextView tv_varityBody;//身份验证显示
    private LinearLayout ly_sex;//性别点击
    private TextView tv_sex;//性别显示
    private LinearLayout ly_ageDegree;//学历点击
    private TextView tv_ageDegree;//学历显示
    private LinearLayout ly_KnowArea;//熟悉领域点击
    private TextView tv_knowArea;//熟悉领域显示
    private LinearLayout ly_degree;//学历点击
    private TextView tv_degree;//学历显示
    private LinearLayout ly_hobby;//业余爱好点击
    private TextView tv_hobby;//业余爱好显示
    private LinearLayout ly_changPwd;//修改密码点击
    private LinearLayout ly_loginQuit;//退出登录点击

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_setting);
        initView();
    }

    private void initView() {
        ly_head_setting = (LinearLayout) findViewById(R.id.ly_head_setting);
        imv_head = (ImageView) findViewById(R.id.imv_head);
        ly_userName = (LinearLayout) findViewById(R.id.ly_userName);
        tv_userName = (TextView) findViewById(R.id.tv_userName);
        ly_varityBody = (LinearLayout) findViewById(R.id.ly_varityBody);
        tv_varityBody = (TextView) findViewById(R.id.tv_varityBody);
        ly_sex = (LinearLayout) findViewById(R.id.ly_sex);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        ly_ageDegree = (LinearLayout) findViewById(R.id.ly_ageDegree);
        tv_ageDegree = (TextView) findViewById(R.id.tv_ageDegree);
        ly_KnowArea = (LinearLayout) findViewById(R.id.ly_KnowArea);
        tv_knowArea = (TextView) findViewById(R.id.tv_knowArea);
        ly_degree = (LinearLayout) findViewById(R.id.ly_degree);
        tv_degree = (TextView) findViewById(R.id.tv_degree);
        ly_hobby = (LinearLayout) findViewById(R.id.ly_hobby);
        tv_hobby = (TextView) findViewById(R.id.tv_hobby);
        ly_changPwd = (LinearLayout) findViewById(R.id.ly_changPwd);
        ly_loginQuit = (LinearLayout) findViewById(R.id.ly_loginQuit);


        //设置监听
        ly_head_setting.setOnClickListener(this);
        ly_userName.setOnClickListener(this);
        ly_varityBody.setOnClickListener(this);
        ly_sex.setOnClickListener(this);
        ly_ageDegree.setOnClickListener(this);
        ly_KnowArea.setOnClickListener(this);
        ly_degree.setOnClickListener(this);
        ly_hobby.setOnClickListener(this);
        ly_changPwd.setOnClickListener(this);
        ly_loginQuit.setOnClickListener(this);

    }

    private void showHeadSetDialog() {
        View viewHeadChose = LayoutInflater.from(SettingActivity.this).inflate(R.layout.activity_picture_dialog, null, false);
        ly_chose_photo = (LinearLayout) viewHeadChose.findViewById(R.id.ly_chose_photo);
        ly_chose_camera = (LinearLayout) viewHeadChose.findViewById(R.id.ly_chose_camera);
        //监听
        ly_chose_photo.setOnClickListener(this);
        ly_chose_camera.setOnClickListener(this);
        dialogHead = new AlertDialog.Builder(this)
                .setView(viewHeadChose)
                .setInverseBackgroundForced(false)
                .create();
        dialogHead.show();
    }

    //显示拍照系统
    private void showCameraSystem() {
        Intent intentCamare = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentCamare, requestCamera);
    }

    //显示图库选择
    private void showPhotoSystem() {
        Intent intentPhoto = new Intent(Intent.ACTION_GET_CONTENT, null);
        intentPhoto.setType("image/*");
        startActivityForResult(intentPhoto, requestPhoto);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_head_setting:
                showHeadSetDialog();
                break;
            case R.id.ly_chose_photo:
                showPhotoSystem();
                dialogHead.cancel();
                break;
            case R.id.ly_chose_camera:
                showCameraSystem();
                dialogHead.cancel();
                break;
            case R.id.ly_userName:
                goClass(SeetingUserName.class, requestUserName);
                break;
            case R.id.ly_varityBody:
                goClass(SeetingUserName.class, requestUserVeriatyBody);
                break;
            case R.id.ly_sex:
                goClass(SeetingUserSex.class, requestUserSex);
                break;
            case R.id.ly_ageDegree:
                goClass(SeetingUserAge.class, requestUserAge);
                break;
            case R.id.ly_KnowArea:
                goClass(SeetingUserKnowArea.class, requestUserKnowArea);
                break;
            case R.id.ly_degree:
                goClass(SeetingUserDegree.class, requestUserDegree);
                break;
            case R.id.ly_hobby:
                goClass(SeetingUserHoppy.class, requestUserHoppy);
                break;
            case R.id.ly_changPwd:
                Intent intentLogPwd = new Intent(this, SeetingForGetPwd.class);
                startActivity(intentLogPwd);

                break;
            case R.id.ly_loginQuit:

                break;
        }
    }

    private void goClass(Class<?> goClass, int requestKey) {
        Intent intent = new Intent(SettingActivity.this, goClass);
        startActivityForResult(intent, requestKey);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);




    }
}
