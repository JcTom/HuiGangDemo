package com.suctan.huigangdemo.activity.myself;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.Constants;
import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.ACache;
import com.example.androidbase.utils.ScreenTools;
import com.example.androidbase.utils.SdCardTool;
import com.example.androidbase.utils.ToastTool;
import com.example.androidbase.widget.loading.WaitDialog;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.activity.login.LoginActivity;
import com.suctan.huigangdemo.activity.setting.SeetingForGetPwd;
import com.suctan.huigangdemo.activity.setting.SeetingUserAge;
import com.suctan.huigangdemo.activity.setting.SeetingUserDegree;
import com.suctan.huigangdemo.activity.setting.SeetingUserHoppy;
import com.suctan.huigangdemo.activity.setting.SeetingUserKnowArea;
import com.suctan.huigangdemo.activity.setting.SeetingUserName;
import com.suctan.huigangdemo.activity.setting.SeetingUserSex;
import com.suctan.huigangdemo.bean.user.CourseBean;
import com.suctan.huigangdemo.bean.user.CurrentUser;
import com.suctan.huigangdemo.bean.user.Users;
import com.suctan.huigangdemo.mvp.login.ModifityUser.ModifityUserPresenter;
import com.suctan.huigangdemo.mvp.login.ModifityUser.ModifityUserView;
import com.suctan.huigangdemo.widget.TipDialog;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by B-305 on 2017/4/13.
 */

public class SettingActivity extends MvpActivity<ModifityUserPresenter> implements View.OnClickListener, ModifityUserView {
    private static final int requestCamera = 1001;//获取拍照
    private static final int requestPhoto = 1002;//获取图库
    private static final int requestUserName = 1003;//获取用户名
    private static final int requestUserVeriatyBody = 1004;//验证身份
    private static final int requestUserSex = 1005;//获取性别
    private static final int requestUserAge = 1006;//获取年龄段
    private static final int requestUserDegree = 1007;//获取学历
    private static final int requestUserKnowArea = 1008;//获取熟悉领域
    private static final int requestUserHoppy = 1009;//获取业余爱好
    private static final int CHOSE_PICTRUE = 1;// 选择本地图片
    private int pic;//0 拍照 1 相册文件
    private Uri tempUri;//图片Uri
    private static final int TAKE_PICTRUE = 0;// 拍照
    private static final int OPEN_SETTING = 0x1001;//打开应用信息
    private static final int CROP_SMALL_PICTURE = 2;// 裁剪
    /**
     * 可取消的任务
     */
    private Callback.Cancelable cancelable;
    private Callback.CacheCallback cachecallback;

    private LinearLayout ly_head_setting;//设置头像
    private LinearLayout ly_chose_photo;//选择图库
    private LinearLayout ly_chose_camera;//选择拍照

    private Dialog dialogHead;//选择图库拍照弹出框

    private ImageView imv_setting_back;
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
    private WaitDialog waitDialog;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_setting);
        x.view().inject(this);
        initView();
    }

    @Override
    protected ModifityUserPresenter createPresenter() {
        return new ModifityUserPresenter(this);
    }

    private void initView() {
        imv_setting_back = (ImageView) findViewById(R.id.imv_setting_back);
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
        imv_setting_back.setOnClickListener(this);
        initViewData();//设置布局的data
//        initWaitDialog();
    }


    private void initViewData() {
        Users mUser = CurrentUser.getInstance().getUserBean();
        if (mUser.getUser_name() != null) {
            tv_userName.setText(mUser.getUser_name());
        }
//        tv_varityBody
        if (mUser.getUser_sex() != -1) {
            if (mUser.getUser_sex() == 0) {
                tv_sex.setText("男");
            } else {
                tv_sex.setText("女");
            }
        }
        if (mUser.getUser_education() != null) {
            tv_degree.setText(mUser.getUser_education());
        }
        tv_ageDegree.setText(mUser.getUser_age() + "");
//        tv_knowArea.setText(mUser.get);
        if (mUser.getUser_hobby() != null) {
            tv_hobby.setText(mUser.getUser_hobby());
        }
    }

    private void initWaitDialog() {
        waitDialog = new WaitDialog(this, 0);
        ProgressBar bar = new ProgressBar(this);

        waitDialog.setContentView(bar);
        waitDialog.show();
    }

    private void showHeadSetDialog() {
        View viewHeadChose = LayoutInflater.from(SettingActivity.this).inflate(R.layout.activity_picture_dialog, null, false);
        ly_chose_photo = (LinearLayout) viewHeadChose.findViewById(R.id.ly_chose_photo);
        ly_chose_camera = (LinearLayout) viewHeadChose.findViewById(R.id.ly_chose_camera);

        //监听
        ly_chose_photo.setOnClickListener(this);
        ly_chose_camera.setOnClickListener(this);
        dialogHead = new AlertDialog.Builder(this).create();
        dialogHead.show();
        Window window = dialogHead.getWindow();
        int windowWidth = ScreenTools.getWindowsWidth(this);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(windowWidth - 100, WindowManager.LayoutParams.WRAP_CONTENT);

        window.setContentView(viewHeadChose, layoutParams);
        window.setBackgroundDrawableResource(android.R.color.transparent);//去除自定义是保留的棱角

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_setting_back:
                finish();
                break;
            case R.id.ly_head_setting:
                showHeadSetDialog();
                break;
            case R.id.ly_chose_photo:
                //检查是否有读写外置SD卡的权限
                checkWriteExternalPermission();
                dialogHead.cancel();
                break;
            case R.id.ly_chose_camera:

                //检查是否有拍照和读写外置SD卡的权限
                checkCameraAndWriteExternalPermission();
                dialogHead.dismiss();
                break;
            case R.id.ly_userName:
                goClass(SeetingUserName.class, requestUserName, tv_userName.getText().toString());
                break;
            case R.id.ly_varityBody:
                goClass(SeetingUserName.class, requestUserVeriatyBody, tv_varityBody.getText().toString());
                break;
            case R.id.ly_sex:
                goClass(SeetingUserSex.class, requestUserSex, tv_sex.getText().toString());
                break;
            case R.id.ly_ageDegree:
                goClass(SeetingUserAge.class, requestUserAge, tv_ageDegree.getText().toString());
                break;
            case R.id.ly_KnowArea:
                goClass(SeetingUserKnowArea.class, requestUserKnowArea, tv_knowArea.getText().toString());
                break;
            case R.id.ly_degree:
                goClass(SeetingUserDegree.class, requestUserDegree, tv_degree.getText().toString());
                break;
            case R.id.ly_hobby:
                goClass(SeetingUserHoppy.class, requestUserHoppy, tv_hobby.getText().toString());
                break;
            case R.id.ly_changPwd:
                Intent intentLogPwd = new Intent(this, SeetingForGetPwd.class);
                startActivity(intentLogPwd);
                break;
            case R.id.ly_loginQuit:
                showLoginQuitTip();
                break;
        }
    }

    private void showLoginQuitTip() {
        final TipDialog tipDialog = new TipDialog(this);
        tipDialog.setTipTitle("")
                .setTipContent("确定退出当前帐号！")
                .setTipClickLisener(new TipDialog.OnTipLisetner() {
                    @Override
                    public void comfirm() {
                        LoginQuitRequest();//暂时放着，通过dialog点击事件触发
                        tipDialog.dismiss();
                    }

                    @Override
                    public void cancel() {
                        tipDialog.dismiss();
                    }
                });


    }


    //注销登录
    private void LoginQuitRequest() {
        Map<String, Object> mapQuit = new HashMap<>();
        mapQuit.put("user_token", TokenManager.getToken());
        mvpPresenter.LoginQuit(mapQuit);
    }


    //更新和设置服务器数据
    private void setServiceData(int resultDataCode, String tempData) {
        Map<String, Object> mapModifyUser = new HashMap<>();
        mapModifyUser.put("user_token", TokenManager.getToken());

        switch (resultDataCode) {
            case requestUserName:
                mapModifyUser.put("user_info", "user_name");
                break;
            case requestUserVeriatyBody:

                break;
            case requestUserSex:
                mapModifyUser.put("user_info", "user_sex");
                break;
            case requestUserAge:
                mapModifyUser.put("user_info", "user_age");
                break;
            case requestUserDegree:
                mapModifyUser.put("user_info", "user_education");

                break;
            case requestUserKnowArea:
//                mapModifyUser.put();
                break;
            case requestUserHoppy:
                mapModifyUser.put("user_info", "user_hoppy");
                break;

        }
        if (resultDataCode == requestUserAge) {
            mapModifyUser.put("user_data", Integer.parseInt(tempData));
        } else if (resultDataCode == requestUserSex) {
            if (tempData.equals("男")) {
                mapModifyUser.put("user_data", 0);
            } else {
                mapModifyUser.put("user_data", 1);
            }
        } else {
            mapModifyUser.put("user_data", tempData);
        }
        mvpPresenter.MoidifytyUser(mapModifyUser);
    }

    private void goClass(Class<?> goClass, int requestKey, String oldData) {
        Intent intent = new Intent(SettingActivity.this, goClass);
        intent.putExtra("oldData", oldData);
        startActivityForResult(intent, requestKey);
    }

    //设置回调后页面信息修改
    private void setViewResultData(int nowResultCode, String tempDataString) {
        switch (nowResultCode) {
            case requestUserName:
                if (!tempDataString.equals(tv_userName.getText().toString())) {
                    tv_userName.setText(tempDataString);
                    setServiceData(nowResultCode, tempDataString);
                }
                break;
            case requestUserVeriatyBody:
                if (!tempDataString.equals(tv_varityBody.getText().toString())) {
                    tv_varityBody.setText(tempDataString);
                    setServiceData(nowResultCode, tempDataString);
                }
                break;
            case requestUserSex:
                if (!tempDataString.equals(tv_sex.getText().toString())) {
                    tv_sex.setText(tempDataString);
                    setServiceData(nowResultCode, tempDataString);
                }
                break;
            case requestUserAge:
                if (!tempDataString.equals(tv_ageDegree.getText().toString())) {
                    tv_ageDegree.setText(tempDataString);
                    setServiceData(nowResultCode, tempDataString);
                }
                break;
            case requestUserDegree:
                if (!tempDataString.equals(tv_userName.getText().toString())) {
                    tv_userName.setText(tempDataString);
                    setServiceData(nowResultCode, tempDataString);
                }
                break;
            case requestUserKnowArea:
                if (!tempDataString.equals(tv_knowArea.getText().toString())) {
                    tv_knowArea.setText(tempDataString);
                    setServiceData(nowResultCode, tempDataString);
                }
                break;
            case requestUserHoppy:
                if (!tempDataString.equals(tv_hobby.getText().toString())) {
                    tv_hobby.setText(tempDataString);
                    setServiceData(nowResultCode, tempDataString);
                }
                break;
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
                case TAKE_PICTRUE:
                    startPhotoZoom(tempUri);
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data);
                    }
                    break;
            }
        } else if (requestCode == OPEN_SETTING) {

            if (pic == 0) {//相机

                if (hasPermission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    changeByCamera();
                } else if (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    ToastTool.showToast("为获得相机的权限", 2);
                } else if (hasPermission(Manifest.permission.CAMERA)) {

                    ToastTool.showToast("为获得读写手机存储的权限", 2);
                } else {
                    ToastTool.showToast("未获得需要的权限", 2);
                }

            } else {//相册
                if (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    changeByPic();
                } else {
                    ToastTool.showToast("未获得需要的权限", 2);
                }
            }
        } else {
            if (data != null) {
                String tempData = data.getStringExtra("newData");
                if (tempData == null | tempData.isEmpty()) {
                    return;
                }
                setViewResultData(resultCode, tempData);
            }
        }

    }

    // 设置头像
    private void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            imv_head.setImageBitmap(photo);
//            initWaitDialog();
            upload(photo);
        }
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


    /**
     * 检查是否有拍照和读写外置SD卡的权限
     */
    private void checkCameraAndWriteExternalPermission() {

        if (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)) {
            changeByCamera();
        } else {
            //请求权限
            requestPermission(Constants.CAMERA_CODE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA);
        }
    }

    @Override
    public void doCameraPermission() {

        if (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)) {

            changeByCamera();
        } else {

//            showRequestPermissionReason("当前操作需要相机、读写手机存储的权限");
        }
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

    /**
     * 根据回调结果，做业务逻辑处理
     */
    @Override
    public void doSDCardPermission() {
        if (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            changeByPic();
        } else {
//            showRequestPermissionReason("当前操作需要读写手机存储的权限");
        }
    }


    /**
     * 从本地相册获取更改
     */
    private void changeByPic() {
        pic = 1;
        Intent chosePic = new Intent(
                Intent.ACTION_GET_CONTENT);
        chosePic.setType("image/*");
        startActivityForResult(chosePic, CHOSE_PICTRUE);
    }

    /**
     * 拍照更换头像
     */
    private void changeByCamera() {

        pic = 0;
        Intent takePic = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        tempUri = Uri.fromFile(new File(SdCardTool.getRootFilePath(), "image.jpg"));
        takePic.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(takePic, TAKE_PICTRUE);
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


    @Override
    public void loadCourseDone(CourseBean courseBean) {

    }

    @Override
    public void LoginQuitSuc() {
        ACache aCache = ACache.get(BaseApplication.getContext());
        //判断缓存中是否存在该对象
        String userStr = aCache.getAsString("User");
        if (userStr != null) {
            aCache.remove("User");
            CurrentUser.getInstance().setUserBean(null);
            TokenManager.clearToken();
            Intent intentLogin = new Intent(this, LoginActivity.class);
            startActivity(intentLogin);
            finish();
        }
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

    //将bitmap转化成file类型
    public File saveBitmapFile(Bitmap bitmap) {
        String filePath = SdCardTool.getRootFilePath();
        File file = new File(filePath);//将要保存图片的路径
//        System.err.println(file.getAbsolutePath() + "\n" + file.getAbsoluteFile());
        if (file.exists()) {
            file.delete();
            file = null;
        }
        File file1 = new File(filePath);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file1));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file1.exists()) {
//            System.out.println("头像上传路径" + fileName);
        }
        return file1;
    }

    private void upload(Bitmap bitmap) {
//        http://119.29.137.109/tp/index.php/home/index/upload
//        String url = "http://112.74.195.131:9899/api/vatar.ashx?" + "action=" + "uploadavatar";
        String url = "http://119.29.137.109/tp/index.php/home/index/uploadTest";
        RequestParams params = new RequestParams(url);
//        params.setMultipart(true);
//       params.addBodyParameter("token", CurrentUser.getInstance().getUserBean().getToken());
        params.addBodyParameter("file", saveBitmapFile(bitmap));//设置上传的文件路径
        cancelable = x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                waitDialog.cancel();
                ToastTool.showToast("头像上传成功", 2);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                hideLoading();
            }
        });
    }
}
