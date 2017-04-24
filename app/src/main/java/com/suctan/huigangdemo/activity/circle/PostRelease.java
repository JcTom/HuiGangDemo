package com.suctan.huigangdemo.activity.circle;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.Constants;
import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.RceycleImageTool;
import com.example.androidbase.utils.SdCardTool;
import com.example.androidbase.utils.ToastTool;
import com.jaeger.library.StatusBarUtil;
import com.roger.catloadinglibrary.CatLoadingView;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.bean.topic.AddCommentReturn;
import com.suctan.huigangdemo.bean.topic.TopicBean;
import com.suctan.huigangdemo.bean.topic.TopicCommentBean;
import com.suctan.huigangdemo.bean.user.CourseBean;
import com.suctan.huigangdemo.mvp.login.postRelease.PostPublishPresenter;
import com.suctan.huigangdemo.mvp.login.postRelease.PostPublishView;
import com.suctan.huigangdemo.net.FileUploadService;
import com.suctan.huigangdemo.net.ServiceGenerator;
import com.suctan.huigangdemo.widget.TipDialog;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit.RetrofitError;
import retrofit.mime.TypedFile;

/**
 * Created by B-305 on 2017/4/9.
 */

public class PostRelease extends MvpActivity<PostPublishPresenter> implements PostPublishView, View.OnClickListener {
    private static final int CHOSE_PICTRUE = 1;// 选择本地图片
    private static final int CROP_SMALL_PICTURE = 2;// 裁剪
    private static final int OPEN_SETTING = 0x1001;//打开应用信息
    private Uri tempUri;//图片Uri
    private Bitmap tempBitmap;//当前选中的图片
    private File tempImageFile;//当前选中的图片
    private CatLoadingView catLoadingView;

    private ImageButton selectImg, deleteImg, postBack;
    private ImageView imageView;
    private FrameLayout imgLayout;
    private LinearLayout picLy;
    private EditText post_title, post_content;
    private Button post_fb;

    /**
     * 所需权限
     * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_release);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initSelectImg();
        initView();
    }

    @Override
    protected PostPublishPresenter createPresenter() {
        return new PostPublishPresenter(this);
    }


    //初始化界面
    private void initView() {
        catLoadingView = new CatLoadingView();
        picLy = (LinearLayout) findViewById(R.id.pic_ly);
    }

    private void toogleShowCatLoading(boolean isShow) {
        if (isShow) {
            post_fb.setClickable(false);
            catLoadingView.show(getSupportFragmentManager(), "");
        } else {
            post_fb.setClickable(true);
            catLoadingView.dismiss();
        }
    }


    //初始化图片选择控件,返回
    private void initSelectImg() {
        postBack = (ImageButton) findViewById(R.id.post_back);
        post_fb = (Button) findViewById(R.id.post_fb);
        imgLayout = (FrameLayout) findViewById(R.id.img_layout);
        selectImg = (ImageButton) findViewById(R.id.select_img);
        deleteImg = (ImageButton) findViewById(R.id.delete_img);
        post_title = (EditText) findViewById(R.id.post_title);
        post_content = (EditText) findViewById(R.id.post_content);
        post_fb.setOnClickListener(this);
        postBack.setOnClickListener(this);
        selectImg.setOnClickListener(this);
        deleteImg.setOnClickListener(this);

    }

    @Override
    public void getDataFail(String msg) {

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
            tempImageFile = saveBitmapFile(tempBitmap, getNowTime() + ".png");
            selectImg.setImageBitmap(tempBitmap);
            toggleShowDeleteImag(true);
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

    private void postVeriaty() {
        if (post_title.getText().toString().isEmpty()) {
            Toast.makeText(BaseApplication.getContext(), "请输入标题", Toast.LENGTH_LONG).show();
            toogleShowCatLoading(false);
            return;
        }
        if (post_content.getText().toString().isEmpty()) {
            Toast.makeText(BaseApplication.getContext(), "请输入描述内容", Toast.LENGTH_LONG).show();
            toogleShowCatLoading(false);
            return;
        }
        if (tempImageFile == null) {
            toogleShowCatLoading(false);

            Toast.makeText(BaseApplication.getContext(), "请您选择需要发布的图片", Toast.LENGTH_LONG).show();
            return;
        } else if (!tempImageFile.exists()) {
            toogleShowCatLoading(false);
            Toast.makeText(BaseApplication.getContext(), "请您选择需要发布的图片", Toast.LENGTH_LONG).show();
            return;
        }
        AddFoodPublic(tempImageFile);//上传文件附带其它参数
    }

    //控制显示图片删除按钮
    private void toggleShowDeleteImag(boolean showImage) {
        if (showImage) {
            deleteImg.setVisibility(View.VISIBLE);
        } else {
            deleteImg.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.post_back:
                finish();
                break;
            case R.id.post_fb:
                toogleShowCatLoading(true);
                postVeriaty();
                break;
            case R.id.select_img:
                checkWriteExternalPermission();
                break;
            case R.id.delete_img:
                toggleShowDeleteImag(false);
                if (tempBitmap != null) {
                    RceycleImageTool.rceycleBitmap(tempBitmap);
                }
                if (tempImageFile != null) {
                    tempImageFile.delete();
                }
                selectImg.setImageResource(R.mipmap.upload_img);
                break;
        }
    }

    /*, */
    //将信息发布到服务器中
    private void AddFoodPublic(File file) {
        FileUploadService serviceGenerator = ServiceGenerator.createService(FileUploadService.class);
        TypedFile typedFile = new TypedFile("multipart/form-data", file);
        serviceGenerator.TopicPublic(typedFile, TokenManager.getToken(), post_title.getText().toString(), post_content.getText().toString()
                , new retrofit.Callback<String>() {
                    @Override
                    public void success(String s, retrofit.client.Response response) {
                        System.out.println("发布成功" + s);
                        toogleShowCatLoading(false);
                        Toast.makeText(BaseApplication.getContext(), "发布成功！", Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        System.out.println("发布成功" + retrofitError.toString());
                        Toast.makeText(BaseApplication.getContext(), "发布失败！", Toast.LENGTH_LONG).show();
                        toogleShowCatLoading(false);
                    }
                });
    }


    @Override
    public void getTopicListSrc(ArrayList<TopicBean> topicBeenList) {

    }

    @Override
    public void getTopicListFail() {

    }

    @Override
    public void postPublishCommentSuc(AddCommentReturn addCommentBean) {

    }

    @Override
    public void postPublishCommentFail(String msg) {

    }

    @Override
    public void getCommentListSuc(ArrayList<TopicCommentBean> topicCommentBeen) {

    }

    @Override
    public void getComemtnListFail() {

    }
}
