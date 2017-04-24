package com.suctan.huigangdemo.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.androidbase.utils.ScreenTools;
import com.suctan.huigangdemo.R;

/**
 * Create by kushanmao on 2017/2/28 0028 10:29
 */

public class TimePickDialog extends AlertDialog {

    private Context context;

    private MnumberPicker numberpick_hour;
    private MnumberPicker numpick_mimute;
    private int windowWidth;
    private Button btn_cancel;
    private Button btn_comfirm;

    public TimePickDialog(Context context, int windowWidth) {
        super(context);
        this.context = context;
        this.windowWidth = windowWidth;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_respones);
        Window win = getWindow();
        win.setBackgroundDrawableResource(android.R.color.transparent);
        win.setLayout(windowWidth - 120, WindowManager.LayoutParams.WRAP_CONTENT);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_comfirm = (Button) findViewById(R.id.btn_comfirm);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Listener.cancel();
            }
        });
        btn_comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Listener.comfirm();
            }
        });

        numberpick_hour = (MnumberPicker) findViewById(R.id.numberpick_hour);
        numberpick_hour.setMinValue(1);
        numberpick_hour.setValue(1);
        numberpick_hour.setMaxValue(24);
        numpick_mimute = (MnumberPicker) findViewById(R.id.numpick_mimute);
        numpick_mimute.setMinValue(1);
        numpick_mimute.setValue(1);
        numpick_mimute.setMaxValue(60);


        numberpick_hour.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                Listener.onHourSeletTime(i%24+1);
            }
        });
        numpick_mimute.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                Listener.onMinuteSelectTime(i%60+1);
            }
        });


    }

    OnHourPickListener Listener;

    public void setOnTimePickOnclick(OnHourPickListener Listener) {
        this.Listener = Listener;
    }

    public interface OnHourPickListener {

        void onHourSeletTime(int selectHour);

        void onMinuteSelectTime(int selectMinute);

        void comfirm();

        void cancel();
    }

}
