package com.suctan.huigangdemo.activity.circle;

import android.content.Context;
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
import android.widget.TextView;

import com.example.androidbase.widget.CircleImageView;
import com.sqk.emojirelease.Emoji;
import com.sqk.emojirelease.EmojiUtil;
import com.sqk.emojirelease.FaceFragment;
import com.suctan.huigangdemo.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by B-305 on 2017/4/8.
 */

public class CirclePostDetails extends AppCompatActivity implements FaceFragment.OnEmojiClickListener,View.OnClickListener{

    ImageButton post_emoticon;
    EditText et_reply;
    FrameLayout emojicons_layout;
    ImageButton postDetailsBack;
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private CirclePostAdapter mAdapter;
    @BindView(R.id.btn_fs)     //发送消息按钮
    Button btnfs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_details);
        ButterKnife.bind(this);
        initEmotion();
        mRecyclerView = (RecyclerView) findViewById(R.id.post_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new CirclePostAdapter());
        mRecyclerView.setFocusable(false);
        initData();
        postBack();
    }


    //返回
    private void postBack() {
        postDetailsBack = (ImageButton)findViewById(R.id.post_details_back);
        postDetailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                btnfs.setOnClickListener(this);
            }
        });
    }


    //初始化数据
    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }


    }
     //按钮的点击事件,并且对数据进行传输
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_fs:
                fsVariety();
        }

    }
   //里面map的数据集合,把数据封装起来,传输给另外一处,待完成
    private void fsVariety() {

    }

    class CirclePostAdapter extends RecyclerView.Adapter<CirclePostAdapter.MyViewHolder> {

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    CirclePostDetails.this).inflate(R.layout.post_reply_item, parent,
                    false));
            return holder;
        }


        public void onBindViewHolder(MyViewHolder holder, int position) {

            holder.item_name.setText(mDatas.get(position));
            holder.item_time.setText(mDatas.get(position));
            holder.item_reply.setText(mDatas.get(position));
            //LoadImageManager.getImageLoader().displayImage("",holder.item_tx);

        }


        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView item_name,item_time,item_reply;
            CircleImageView item_tx;

            public MyViewHolder(View view) {
                super(view);
                item_name = (TextView) view.findViewById(R.id.item_name);
                item_time = (TextView) view.findViewById(R.id.item_time);
                item_reply = (TextView) view.findViewById(R.id.item_reply);
                item_tx = (CircleImageView) view.findViewById(R.id.item_tx);
            }
        }


    }





    /**
     * 表情键盘
     */
    private void initEmotion(){
        post_emoticon = (ImageButton) findViewById(R.id.post_emoticon);
        et_reply = (EditText) findViewById(R.id.et_reply);
        emojicons_layout = (FrameLayout) findViewById(R.id.emojicons_layout);

        FaceFragment faceFragment = FaceFragment.Instance();
        getSupportFragmentManager().beginTransaction().add(R.id.emojicons_layout,faceFragment).commit();


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        post_emoticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emojicons_layout.isShown()){
                    hideEmotionView(false);
                }else{
                    showEmotionView();
                }

            }
        });
        et_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideEmotionView(true);
            }
        });

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
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_reply.getWindowToken(),0);
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
        if(null != this.getCurrentFocus()){
            /**
             * 点击空白位置 隐藏软键盘
             */
            hideEmotionView(false);
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super .onTouchEvent(event);
    }

}
