package com.suctan.huigangdemo.activity.circle;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.user.CourseBean;
import com.suctan.huigangdemo.mvp.login.postRelease.postReleasePresenter;
import com.suctan.huigangdemo.mvp.login.postRelease.postReleaseView;

/**
 * Created by B-305 on 2017/4/9.
 */

public class PostRelease extends MvpActivity<postReleasePresenter> implements postReleaseView{

    //调用系统相册-选择图片
    private static final int IMAGE = 1;
    private ImageButton selectImg,deleteImg,postBack;
    private ImageView imageView;
    private FrameLayout imgLayout;
    private LinearLayout picLy;
    private EditText  post_title,post_content;

    /**
     * 所需权限
     * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_release);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initSelectImg();
        initView();
    }

    @Override
    protected postReleasePresenter createPresenter() {
        return new postReleasePresenter(this);
    }


    //初始化界面
    private void initView() {
        picLy = (LinearLayout)findViewById(R.id.pic_ly);
        Intent it=PostRelease.this.getIntent();
        Boolean isPic =it.getBooleanExtra("isPic",true);
        if (isPic==false){
            picLy.setVisibility(View.GONE);
        }

    }


    //初始化图片选择控件,返回
    private void initSelectImg(){
        postBack = (ImageButton) findViewById(R.id.post_back);
        Button post_fb = (Button) findViewById(R.id.post_fb);
        imgLayout = (FrameLayout) findViewById(R.id.img_layout);
        selectImg = (ImageButton) findViewById(R.id.select_img);
        deleteImg = (ImageButton) findViewById(R.id.delete_img);
        post_title= (EditText) findViewById(R.id.post_title);
        post_content= (EditText) findViewById(R.id.post_content);

        selectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);
            }
        });
        deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setWillNotDraw(true);
                imgLayout.setVisibility(View.GONE);
                selectImg.setVisibility(View.VISIBLE);
            }
        });

        postBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        post_fb.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                postVariety();
            }


        });
    }
     //实现把数据传给Presenter进行处理
    private void postVariety() {
         String topic_title =post_title.getText().toString().trim();
         String topic_content=post_content.getText().toString().trim();
        //上传图片,有些问题,这个接口,晚点再写

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
    }

    //加载图片
    private void showImage(String imagePath){

        //显示图片控件
        imgLayout.setVisibility(View.VISIBLE);

        //显示图片
        Bitmap bm = BitmapFactory.decodeFile(imagePath);
        imageView = (ImageView)findViewById(R.id.image);
        imageView.setWillNotDraw(false);
        imageView.setImageBitmap(bm);

        //隐藏选择图片按钮
        selectImg.setVisibility(View.GONE);

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
    public void loadCourseDone(CourseBean courseBean) {
        ToastTool.showToast("发布帖子成功",1);
    }
}
