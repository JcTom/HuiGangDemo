package com.suctan.huigangdemo.activity.SQLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Tom on 2017/4/6.
 */

public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {

    private static String name = "my.db";
    private static Integer version = 1;

    public SQLiteOpenHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table records(id integer primary key autoincrement,name varchar(200))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
