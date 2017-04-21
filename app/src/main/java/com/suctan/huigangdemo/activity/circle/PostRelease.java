package com.suctan.huigangdemo.activity.circle;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.androidbase.Constants;
import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.RceycleImageTool;
import com.example.androidbase.utils.SdCardTool;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.bean.user.CourseBean;
import com.suctan.huigangdemo.bean.user.MyChikenFoodBean;
import com.suctan.huigangdemo.mvp.login.postRelease.postReleasePresenter;
import com.suctan.huigangdemo.mvp.login.postRelease.postReleaseView;
import com.suctan.huigangdemo.widget.TipDialog;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by B-305 on 2017/4/9.
 */

public class PostRelease extends MvpActivity<postReleasePresenter> implements postReleaseView {
    private static final int CHOSE_PICTRUE = 1;// 选择本地图片
    private static final int CROP_SMALL_PICTURE = 2;// 裁剪
    private static final int OPEN_SETTING = 0x1001;//打开应用信息
    private Uri tempUri;//图片Uri
    private Bitmap tempBitmap;//当前选中的图片
    private File tempImageFile;//当前选中的图片


    private ImageButton selectImg, deleteImg, postBack;
    private ImageView imageView;
    private FrameLayout imgLayout;
    private LinearLayout picLy;
    private EditText post_title, post_content;

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
        picLy = (LinearLayout) findViewById(R.id.pic_ly);
    }


    //初始化图片选择控件,返回
    private void initSelectImg() {
        postBack = (ImageButton) findViewById(R.id.post_back);
        Button post_fb = (Button) findViewById(R.id.post_fb);
        imgLayout = (FrameLayout) findViewById(R.id.img_layout);
        selectImg = (ImageButton) findViewById(R.id.select_img);
        deleteImg = (ImageButton) findViewById(R.id.delete_img);
        post_title = (EditText) findViewById(R.id.post_title);
        post_content = (EditText) findViewById(R.id.post_content);

        selectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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


        post_fb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                postVariety();
            }


        });


    }

    //实现把数据传给Presenter进行处理
    private void postVariety() {
        String topic_title = post_title.getText().toString().trim();
        String topic_content = post_content.getText().toString().trim();

        //上传图片,有些问题,这个接口,晚点再写

    }


    @Override
    public void getDataFail(String msg) {

    }


    @Override
    public void loadCourseDone(CourseBean courseBean) {
        ToastTool.showToast("发布帖子成功", 1);
    }


    /**
     * 从本地相册获取更改
     */
    private void changeByPic() {
        Intent chosePic = new Intent(
                Intent.ACTION_GET_CONTENT);
        chosePic.setType("image/*");
        startActivityForResult(chosePic, CHOSE_PICTRUE);
    }

    /**
     * 检查是否有读写外置SD卡的权限
     */
    private void checkWriteExternalPermission() {

        if (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            changeByPic();
        } else {
            //请求权限
            requestPermission(Constants.WRITE_EXTERNAL_CODE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    /**
     * 裁剪图片
     *
     * @param uri
     */
    private void startPhotoZoom(Uri uri) {
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    //将bitmap转化成file类型
    public File saveBitmapFile(Bitmap bitmap, String fileName) {
        String filePath = SdCardTool.getRootFilePath();
        File file = new File(filePath, fileName);//将要保存图片的路径
//        System.err.println(file.getAbsolutePath() + "\n" + file.getAbsoluteFile());
        if (file.exists()) {
            file.delete();
            file = null;
        }
        File file1 = new File(filePath, fileName);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file1));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file1.exists()) {
            System.out.println("头像上传路径" + file1.getPath());
        }
        return file1;
    }


    // 设置头像
    private void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            if (tempBitmap != null) {
                RceycleImageTool.rceycleBitmap(tempBitmap);
            }
            this.tempBitmap = photo;
            deleteImg.setImageBitmap(tempBitmap);
            tempImageFile = saveBitmapFile(tempBitmap, getNowTime() + ".jpg");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CHOSE_PICTRUE:
                    startPhotoZoom(data.getData());
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data);
                    }
                    break;
            }
        } else if (requestCode == OPEN_SETTING) {
            if (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                changeByPic();
            } else {
                ToastTool.showToast("未获得需要的权限", 2);
            }
        }
    }


    /**
     * 根据回调结果，做业务逻辑处理
     */
    @Override
    public void doSDCardPermission() {
        if (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            changeByPic();
        } else {
            showRequestPermissionReason();
        }
    }

    /**
     * 向用户解析需要该权限的原因
     */
    private void showRequestPermissionReason() {
        final TipDialog dialog = new TipDialog(this);
        dialog.setTipClickLisener(new TipDialog.OnTipLisetner() {
            @Override
            public void comfirm() {
                openSetting();
                dialog.dismiss();
            }

            @Override
            public void cancel() {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * 打开该app的应用信息
     */
    private void openSetting() {
        String scheme = "package";
        String packageName = getPackageName();
        Intent it = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri data = Uri.fromParts(scheme, packageName, null);
        it.setData(data);
        startActivityForResult(it, OPEN_SETTING);
    }

    public String getNowTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD_hh_mm_ss");
        String system = dateFormat.format(new Date());
        return system;
    }
}
