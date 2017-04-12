package com.example.androidbase.widget.loading;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.androidbase.R;

/**
 * Created by chenxiaozhou on 15/10/8.
 * 等待条
 */
public class WaitDialog extends AlertDialog {

    private Context context;

    public WaitDialog(Context context) {
        super(context);
        this.context = context;
    }

    public WaitDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.waitdialog, null);
        setContentView(layout);
    }
}
