package com.suctan.huigangdemo.activity.circle;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.suctan.huigangdemo.R;

/**
 * Created by B-305 on 2017/4/9.
 */

public class PostRelease extends AppCompatActivity {

    //调用系统相册-选择图片
    private static final int IMAGE = 1;
    private ImageButton selectImg,deleteImg,postBack;
    private ImageView imageView;
    private FrameLayout imgLayout;
    private LinearLayout picLy;

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

        imgLayout = (FrameLayout) findViewById(R.id.img_layout);
        selectImg = (ImageButton) findViewById(R.id.select_img);
        deleteImg = (ImageButton) findViewById(R.id.delete_img);
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


}
