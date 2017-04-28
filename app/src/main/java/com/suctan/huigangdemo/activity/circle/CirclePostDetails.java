package com.suctan.huigangdemo.activity.circle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidbase.BaseApplication;
import com.example.androidbase.LoadImageManager;
import com.example.androidbase.mvp.MvpActivity;
import com.example.androidbase.widget.CircleImageView;
import com.jaeger.library.StatusBarUtil;
import com.sqk.emojirelease.Emoji;
import com.sqk.emojirelease.EmojiUtil;
import com.sqk.emojirelease.FaceFragment;
import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.acache.CurrentUser;
import com.suctan.huigangdemo.acache.TokenManager;
import com.suctan.huigangdemo.adapter.topic.CirclePostAdapter;
import com.suctan.huigangdemo.adapter.topic.TopicRecycleAdapter;
import com.suctan.huigangdemo.bean.topic.AddCommentBean;
import com.suctan.huigangdemo.bean.topic.AddCommentReturn;
import com.suctan.huigangdemo.bean.topic.DellCommentBean;
import com.suctan.huigangdemo.bean.topic.TopicBean;
import com.suctan.huigangdemo.bean.topic.TopicCommentBean;
import com.suctan.huigangdemo.fragment.FragmentFind;
import com.suctan.huigangdemo.fragment.FragmentIndex;
import com.suctan.huigangdemo.mvp.login.postRelease.PostPublishPresenter;
import com.suctan.huigangdemo.mvp.login.postRelease.PostPublishView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B-305 on 2017/4/8.
 */

public class CirclePostDetails extends MvpActivity<PostPublishPresenter> implements FaceFragment.OnEmojiClickListener, View.OnClickListener, PostPublishView,CirclePostAdapter.OnCirclePostListenter {
    private ImageButton post_emoticon;
    private EditText et_reply;
    private FrameLayout emojicons_layout;
    private ImageButton postDetailsBack;
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private CircleImageView circleImv_psb_userHead;
    private TextView tv_pbs_userName;
    private TextView tv_pbs_title;
    private TextView tv_pbs_commentTime;
    private TextView tv_pbs_content;
    private ImageView imv_pbs_imvdetail;
    private ImageView imv_pbs_delete;
    private Button btn_send_comment;
    private ArrayList<TopicCommentBean> topicCommentList = new ArrayList<>();
    private CirclePostAdapter mAdapter;
    private ScrollView post_details_ScrollView;
    private TopicBean mTopicBean;//当前的话题对象
    private boolean isFirstCreateRecycleAdatper;
    private String tempContent;
    private DellCommentBean mDellCommentBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_details);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 0);

        initView();
        getIntentData();
        getCommmentList();
        /*deletetppiceSSS();*/
        /*deletetppiceSS();*/

    }

    /*获取当前话题的评论*/
    private void getCommmentList() {
        Map map = new HashMap();
        map.put("topic_id", mTopicBean.getTopic_id());
        mvpPresenter.getTopicCommentList(map);
    }

    private void getIntentData() {
        mTopicBean = (TopicBean) getIntent().getSerializableExtra("nowTopic");
        if (mTopicBean != null) {
            initData(mTopicBean);
        }

    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.post_recyclerview);
        postDetailsBack = (ImageButton) findViewById(R.id.post_details_back);
        circleImv_psb_userHead = (CircleImageView) findViewById(R.id.circleImv_psb_userHead);
        tv_pbs_userName = (TextView) findViewById(R.id.tv_pbs_userName);
        tv_pbs_commentTime = (TextView) findViewById(R.id.tv_pbs_commentTime);
        tv_pbs_content = (TextView) findViewById(R.id.tv_pbs_content);
        imv_pbs_imvdetail = (ImageView) findViewById(R.id.imv_pbs_imvdetail);
        imv_pbs_delete = (ImageView) findViewById(R.id.imv_pbs_delete);
        btn_send_comment = (Button) findViewById(R.id.btn_send_comment);
        tv_pbs_title = (TextView) findViewById(R.id.tv_pbs_title);
        post_details_ScrollView = (ScrollView) findViewById(R.id.post_details_ScrollView);

        post_emoticon = (ImageButton) findViewById(R.id.post_emoticon);
        et_reply = (EditText) findViewById(R.id.et_reply);
        emojicons_layout = (FrameLayout) findViewById(R.id.emojicons_layout);

        et_reply.setOnClickListener(this);
        postDetailsBack.setOnClickListener(this);
        btn_send_comment.setOnClickListener(this);
        imv_pbs_delete.setOnClickListener(this);
        post_emoticon.setOnClickListener(this);

        initEmotion();
        initRecycleView();
    }


    private void initRecycleView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setFocusable(false);
    }

    @Override
    protected PostPublishPresenter createPresenter() {
        return new PostPublishPresenter(this);
    }

    //初始化数据
    protected void initData(TopicBean mTopicBean) {
        if (mTopicBean.getUser_icon() != null) {
            LoadImageManager.getImageLoader().displayImage(mTopicBean.getUser_icon(), circleImv_psb_userHead);
        }
        tv_pbs_userName.setText(mTopicBean.getUser_name());
        if (mTopicBean.getTopic_picture() != null) {
            LoadImageManager.getImageLoader().displayImage(mTopicBean.getTopic_picture(), imv_pbs_imvdetail);
        }
        tv_pbs_commentTime.setText(mTopicBean.getPub_topic_time());
        tv_pbs_content.setText(mTopicBean.getTopic_content());
        tv_pbs_title.setText(mTopicBean.getTopic_title());
    }

    //按钮的点击事件,并且对数据进行传输
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_send_comment:
                toolgleSendComment(false);
                postCommentVearity();
                hideEmotionView(true);
                break;
            case R.id.post_details_back:
                finish();
                break;
            case R.id.imv_pbs_delete:
                Toast.makeText(this,"已删除",Toast.LENGTH_SHORT).show();
                deletetppiceSSS();
                delete();
                break;
            case R.id.et_reply:
                hideEmotionView(true);
                break;
            case R.id.post_emoticon:
                if (emojicons_layout.isShown()) {
                    hideEmotionView(false);
                } else {
                    showEmotionView();
                }
                break;
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


    @Override
    public void getTopicListSrc(ArrayList<TopicBean> topicBeenList) {

    }

    @Override
    public void getTopicListFail() {

    }

    @Override
    public void postPublishCommentSuc(AddCommentReturn addCommentBean) {
        addNewComemntObject(addCommentBean);
        toolgleSendComment(true);
    }

    //添加评论在列表中
    private void addNewComemntObject(AddCommentReturn addCommentBean) {
        TopicCommentBean mTopicComment = new TopicCommentBean();
        mTopicComment.setContent(tempContent);
        String commentId = addCommentBean.getComment_id();
        if (commentId != null) {
            mTopicComment.setComment_id(Integer.parseInt(commentId));
        }
        mTopicComment.setUser_icon("http://119.29.137.109/tp/uploads/" + CurrentUser.getInstance().getUserBean().getUser_icon());
        mTopicComment.setUser_name(CurrentUser.getInstance().getUserBean().getUser_name());
        mTopicComment.setComment_time(addCommentBean.getComment_time());
        topicCommentList.add(0, mTopicComment);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void postPublishCommentFail(String msg) {
        Toast.makeText(BaseApplication.getContext(), msg, Toast.LENGTH_LONG).show();
        toolgleSendComment(true);
    }

    @Override
    public void getCommentListSuc(ArrayList<TopicCommentBean> topicCommentBeen) {
        topicCommentList.addAll(topicCommentBeen);
        if (!isFirstCreateRecycleAdatper) {
            isFirstCreateRecycleAdatper = true;
            initRecycleAdaper(topicCommentList);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getComemtnListFail() {

    }

    @Override
    public void getdeleteTopicdel(String msg) {

    }

    private void initRecycleAdaper(ArrayList<TopicCommentBean> topicCommentList) {
        mAdapter = new CirclePostAdapter(this, topicCommentList);
        mAdapter.setOnClickCirclePostListenter((CirclePostAdapter.OnCirclePostListenter) this);
        mRecyclerView.setAdapter(mAdapter);
    }
    /**
     * 表情键盘
     */
    private void initEmotion() {
        FaceFragment faceFragment = FaceFragment.Instance();
        getSupportFragmentManager().beginTransaction().add(R.id.emojicons_layout, faceFragment).commit();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

    }

    private void toolgleSendComment(boolean isClick) {
        if (isClick) {
            btn_send_comment.setClickable(false);
        } else {
            btn_send_comment.setClickable(true);
        }
    }


    /**
     * 隐藏emoji
     **/
    private void hideEmotionView(boolean showKeyBoard) {
        if (emojicons_layout.isShown()) {
            if (showKeyBoard) {
                emojicons_layout.setVisibility(View.GONE);
                //etWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            } else {
                emojicons_layout.setVisibility(View.GONE);
            }
        }
    }

    private void showEmotionView() {
        emojicons_layout.setVisibility(View.VISIBLE);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_reply.getWindowToken(), 0);
    }

    @Override
    public void onEmojiDelete() {
        String text = et_reply.getText().toString();
        if (text.isEmpty()) {
            return;
        }
        if ("]".equals(text.substring(text.length() - 1, text.length()))) {
            int index = text.lastIndexOf("[");
            if (index == -1) {
                int action = KeyEvent.ACTION_DOWN;
                int code = KeyEvent.KEYCODE_DEL;
                KeyEvent event = new KeyEvent(action, code);
                et_reply.onKeyDown(KeyEvent.KEYCODE_DEL, event);
                //displayTextView();
                return;
            }
            et_reply.getText().delete(index, text.length());
            //displayTextView();
            return;
        }
        int action = KeyEvent.ACTION_DOWN;
        int code = KeyEvent.KEYCODE_DEL;
        KeyEvent event = new KeyEvent(action, code);
        et_reply.onKeyDown(KeyEvent.KEYCODE_DEL, event);
        //displayTextView();
    }

    private void displayTextView() {
        try {
            EmojiUtil.handlerEmojiText(et_reply, et_reply.getText().toString(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEmojiClick(Emoji emoji) {
        if (emoji != null) {
            int index = et_reply.getSelectionStart();
            Editable editable = et_reply.getEditableText();
            if (index < 0) {
                editable.append(emoji.getContent());
            } else {
                editable.insert(index, emoji.getContent());
            }
        }
        displayTextView();
        et_reply.setSelection(et_reply.getText().length());
        //Log.i("onEmojiClick", "onEmojiClick: "+et_reply.getText().toString());
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            /**
             * 点击空白位置 隐藏软键盘
             */
            hideEmotionView(false);
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);

    }

    //上传前进行判断
    private void postCommentVearity() {
        if (et_reply.getText().toString().isEmpty()) {
            toolgleSendComment(true);
            return;
        }
        publishComment();

    }

    private void publishComment() {

        Map<String, Object> map = new HashMap<>();
        map.put("user_token", TokenManager.getToken());
        map.put("topic_id", mTopicBean.getTopic_id());
        map.put("content", et_reply.getText().toString());
        tempContent = et_reply.getText().toString();
        mvpPresenter.postPublishComment(map);

    }

    private void delete() {
        finish();
        System.out.println("setUserVisibleHint执行了---finish");
    }



    private void deletetppiceSSS() {
        Map<String, Object> map = new HashMap<>();
        map.put("topic_id", mTopicBean.getTopic_id());
        mvpPresenter.getdeletetopicSSS(map);
    }
    @Override
    public void onPost_reply_item(int position) {
        Toast.makeText(this,"删除",Toast.LENGTH_SHORT).show();
    }

    private void deletetppiceSS() {
        Map<String, Object> map = new HashMap<>();
        map.put("topic_id", mTopicBean.getTopic_id());
        mvpPresenter.getdeletetopicSS(map);
    }

}
