package com.suctan.huigangdemo.activity.search;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.suctan.huigangdemo.R;
import com.suctan.huigangdemo.activity.MainActivity;
import com.suctan.huigangdemo.activity.SQLiteHelper.SQLiteOpenHelper;

/**
 * Created by Tom on 2017/4/6.
 */

public class Search_View extends LinearLayout{

    private Context context;

    /*UI组件*/
    private TextView tv_clear;
    private EditText et_search;
    private TextView tv_tip;
    private ImageView iv_search;
    /*列表及其适配器*/
    private Search_ListView listView;
    private BaseAdapter adapter;
    /*数据库变量*/
    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;

    public Search_View(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public Search_View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public Search_View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    private void init() {
        //初始化UI组件
    initView();
        /*实例化数据库SQLiteOpenHelper子类对象*/
        helper = new SQLiteOpenHelper(context);
        // 第一次进入时查询所有的历史记录
        queryData("");
        //"清空搜索历史"按钮//"清空搜索历史"按钮
        tv_clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空数据库
                deleteData();
                queryData("");

            }
        });
        //搜索框的文本变化实时监听
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().trim().length() == 0) {
                    //若搜索框为空,则模糊搜索空字符,即显示所有的搜索历史
                    tv_tip.setText("搜索历史");
                } else {
                    tv_tip.setText("搜索结果");
                }
                //每次输入后都查询数据库并显示
                //根据输入的值去模糊查询数据库中有没有数据
                String tempName = et_search.getText().toString();
                queryData(tempName);
            }
        });

        et_search.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){

                    boolean hasData = hasData(et_search.getText().toString().trim());
                    
                    if (!hasData){

                        insertData(et_search.getText().toString().trim());
                        //搜索后显示数据库里所有搜索历史是为了测试
                        queryData("");
                    }
                    //根据输入的内容模糊查询商品，并跳转到另一个界面，这个需要根据需求实现
                    Toast.makeText(context, "点击搜索", Toast.LENGTH_SHORT).show();

                }
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取到用户点击列表里的文字,并自动填充到搜索框内
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                String name = textView.getText().toString();
                et_search.setText(name);
                Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
            }
        });

        iv_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean hasData = hasData(et_search.getText().toString().trim());
                if (!hasData) {
                    insertData(et_search.getText().toString().trim());

                    //搜索后显示数据库里所有搜索历史是为了测试
                    queryData("");
                }
                //根据输入的内容模糊查询商品，并跳转到另一个界面，这个根据需求实现
                Toast.makeText(context, "clicked!", Toast.LENGTH_SHORT).show();

            }
        });

    }



    /*插入数据*/
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }
    /*检查数据库中是否已经有该条记录*/
    private boolean hasData(String trim) {
        //从Record这个表里找到name=tempName的id
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{trim});
        //判断是否有下一个
        return cursor.moveToNext();
    }

    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }

    /*模糊查询数据 并显示在ListView列表上*/
    private void queryData(String s) {

        //模糊搜索
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + s + "%' order by id desc ", null
        );
        // 创建adapter适配器对象,装入模糊搜索的结果
        adapter = new SimpleCursorAdapter(context, android.R.layout.simple_list_item_1, cursor, new String[] { "name" },
                new int[] { android.R.id.text1 }, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 设置适配器
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initView() {

       LayoutInflater.from(context).inflate(R.layout.search_layout,this);
        et_search = (EditText) findViewById(R.id.et_search);
        tv_clear = (TextView) findViewById(R.id.tv_clear);
        tv_tip = (TextView) findViewById(R.id.tv_tip);
        listView  = (Search_ListView) findViewById(R.id.listView);
        iv_search = (ImageView)findViewById(R.id.iv_search);

    }

}
