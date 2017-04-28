package com.suctan.huigangdemo.activity.myself;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.Constants;
import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.utils.RceycleImageTool;
import com.example.androidbase.utils.SdCardTool;
import com.example.androidbase.utils.ToastTool;
import com.roger.catloadinglibrary.CatLoadingView;
import com.sevenheaven.segmentcontrol.SegmentControl;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.bean.user.MyChikenFoodBean;
import com.suctan.huigangdemo.bean.user.MykitchenBean;
import com.suctan.huigangdemo.bean.user.MykitchenReturn;
import com.suctan.huigangdemo.mvp.login.myChiken.MyChikenPresenter;
import com.suctan.huigangdemo.mvp.login.myChiken.MyChikenView;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.RetrofitError;
import retrofit.mime.TypedFile;

/**
 * Created by B-305 on 2017/4/13.
 */

public class release_new_todayfoodActivity extends MvpActivity<MyChikenPresenter> implements View.OnClickListener, MyChikenView, SegmentControl.OnSegmentControlClickListener {
    private static final int CHOSE_PICTRUE = 1;// 选择本地图片
    private static final int CROP_SMALL_PICTURE = 2;// 裁剪
    private static final int getFoodMatria = 1018;//获取材料，过程等信息
    private static final int OPEN_SETTING = 0x1001;//打开应用信息
    private Uri tempUri;//图片Uri
    private Bitmap tempBitmap;//当前选中的图片
    private File tempImageFile;//当前选中的图片
    private MyChikenFoodBean tempMyChikenBean = new MyChikenFoodBean();
    private CatLoadingView catLoadingView;


    @BindView(R.id.new_food_ideas)   //定义 添加新菜谱
            LinearLayout new_food_idea;
    @BindView(R.id.new_food_back)   //定义返回按钮
            ImageView new_food_back;

    @BindView(R.id.imv_addFood)
    ImageView imv_addFood;
    @BindView(R.id.edt_addFood_name)
    EditText edt_addFood_name;
    @BindView(R.id.edt_addFood_moneny)
    EditText edt_addFood_moneny;
    @BindView(R.id.edt_addFood_detail)
    EditText edt_addFood_detail;

    @BindView(R.id.btn_addFood_comfirm)
    Button btn_addFood_comfirm;
    @BindView(R.id.segmentView_OrderType_choose)
    SegmentControl segmentViewControlChoose;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_kitchen_release);
        ButterKnife.bind(this);  //绑定布局
        initViewClickListener();
    }

    //控制进度条的显示
    private void toogleShowCatLoading(boolean isShowLoad) {
        if (isShowLoad) {
            btn_addFood_comfirm.setClickable(false);
            catLoadingView.show(getSupportFragmentManager(), "");
        } else {
            btn_addFood_comfirm.setClickable(true);
            catLoadingView.dismiss();
        }
    }


    private void initViewClickListener() {
        catLoadingView = new CatLoadingView();
        new_food_idea.setOnClickListener(this);
        new_food_back.setOnClickListener(this);
        btn_addFood_comfirm.setOnClickListener(this);
        imv_addFood.setOnClickListener(this);
        segmentViewControlChoose.setOnSegmentControlClickListener(this);
        segmentViewControlChoose.setSelectedIndex(0);
    }

    //获得发布的数据
    private void getPublishData() {
        if (edt_addFood_name.getText().toString() == null | edt_addFood_name.getText().toString().isEmpty()) {
            Toast.makeText(BaseApplication.getContext(), "请添加菜名！", Toast.LENGTH_LONG).show();
            toogleShowCatLoading(false);
            return;
        } else {
            tempMyChikenBean.setOrder_title(edt_addFood_name.getText().toString());
        }
        if (edt_addFood_moneny.getText().toString() == null | edt_addFood_moneny.getText().toString().isEmpty()) {
            Toast.makeText(BaseApplication.getContext(), "请添加菜的金额！", Toast.LENGTH_LONG).show();
            toogleShowCatLoading(false);
            return;

        } else {
            tempMyChikenBean.setOrder_price(Double.parseDouble(edt_addFood_moneny.getText().toString()));

        }
        if (tempImageFile == null) {
            toogleShowCatLoading(false);
            Toast.makeText(BaseApplication.getContext(), "请添加菜的图片！", Toast.LENGTH_LONG).show();
            return;
        }
        if (edt_addFood_detail.getText().toString() == null | edt_addFood_detail.getText().toString().isEmpty()) {
            Toast.makeText(BaseApplication.getContext(), "请添加菜的描述！", Toast.LENGTH_LONG).show();
            toogleShowCatLoading(false);
            return;
        } else {
            tempMyChikenBean.setFood_description(edt_addFood_detail.getText().toString());
        }
        if (tempMyChikenBean.getMakeFood_res() == null | tempMyChikenBean.getMakeFood_float() == null | tempMyChikenBean.getMakeFood_note() == null) {
            toogleShowCatLoading(false);
            Toast.makeText(BaseApplication.getContext(), "请前往添加菜的详细制作流程,材料和注意事项", Toast.LENGTH_LONG);
            return;
        }
        AddFoodPublic(tempImageFile);

    }

    @Override
    protected MyChikenPresenter createPresenter() {
        return new MyChikenPresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.new_food_back:
                finish();
                break;
            case R.id.imv_addFood:
                checkWriteExternalPermission();
                break;
            case R.id.new_food_ideas:
                Intent gotofoodbook = new Intent(release_new_todayfoodActivity.this, foobBookActivity.class);
                startActivityForResult(gotofoodbook, getFoodMatria);
                break;
            case R.id.btn_addFood_comfirm:
                toogleShowCatLoading(true);
                getPublishData();
                break;
        }
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
    public void getDataFail(String msg) {

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
            imv_addFood.setImageBitmap(tempBitmap);
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
        } else {
            if (data != null) {
                if (requestCode == getFoodMatria) {
                    MyChikenFoodBean mFoodBean = (MyChikenFoodBean) data.getSerializableExtra("newFoodData");
                    if (mFoodBean != null) {
                        if (mFoodBean.getMakeFood_float() != null) {
                            tempMyChikenBean.setMakeFood_float(mFoodBean.getMakeFood_float());
                        }
                        if (mFoodBean.getMakeFood_note() != null) {
                            tempMyChikenBean.setMakeFood_note(mFoodBean.getMakeFood_note());
                        }
                        if (mFoodBean.getMakeFood_res() != null) {
                            tempMyChikenBean.setMakeFood_res(mFoodBean.getMakeFood_res());
                        }
                    }
                }
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

    @Override
    public void onSegmentControlClick(int index) {
        tempMyChikenBean.setOrder_type(index);
    }

    //将信息发布到服务器中
    private void AddFoodPublic(File file) {
        FileUploadService serviceGenerator = ServiceGenerator.createService(FileUploadService.class);
        TypedFile typedFile = new TypedFile("multipart/form-data", file);
        serviceGenerator.FoodPublic(typedFile, TokenManager.getToken(), tempMyChikenBean.getOrder_title(), tempMyChikenBean.getOrder_price()
                , tempMyChikenBean.getOrder_type(), tempMyChikenBean.getMakeFood_res(), tempMyChikenBean.getMakeFood_float(), tempMyChikenBean.getMakeFood_note()
                , new retrofit.Callback<String>() {
                    @Override
                    public void success(String s, retrofit.client.Response response) {
                        Toast.makeText(BaseApplication.getContext(), "上传成功！", Toast.LENGTH_LONG).show();
                        toogleShowCatLoading(false);
                        finish();
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        toogleShowCatLoading(false);
                    }
                });
    }

    @Override
    public void getMakeOrderList(ArrayList<MykitchenBean> mykitchenBeenlist) {

    }
}
