package com.suctan.huigangdemo.widget;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.lang.reflect.Field;

public class MnumberPicker extends NumberPicker{

	    public MnumberPicker(Context context) {
	        super(context);
	    }

	    public MnumberPicker(Context context, AttributeSet attrs) {
	        super(context, attrs);
	    }

	    public MnumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
	        super(context, attrs, defStyleAttr);
	    }
	    @Override
	    public void addView(View child) {
	        super.addView(child);
	        updateView(child);
	    }

	    @Override
	    public void addView(View child, int index,
	                        android.view.ViewGroup.LayoutParams params) {
	        super.addView(child, index, params);
	        updateView(child);
	    }

	    @Override
	    public void addView(View child, android.view.ViewGroup.LayoutParams params) {
	        super.addView(child, params);
	        updateView(child);
	    }

	    public void updateView(View view) {
	        if (view instanceof EditText) {
	            //�����޸����������
//	            ((EditText) view).setTextColor(Color.parseColor("#FFFF0000"));
//	            ((EditText) view).setTextSize(30);
	        }
	    }

	    @SuppressWarnings("unused")  
	    public void setNumberPickerDividerColor(NumberPicker numberPicker) {  
	        NumberPicker picker = numberPicker;  
	        Field[] pickerFields = NumberPicker.class.getDeclaredFields();  
	        for (Field pf : pickerFields) {  
	            if (pf.getName().equals("mSelectionDivider")) {  
	                pf.setAccessible(true);  
	                try {  
	                    pf.set(picker, new ColorDrawable(Color.parseColor("#00000000")));  
	                } catch (IllegalArgumentException e) {  
	                    e.printStackTrace();  
	                } catch (Resources.NotFoundException e) {  
	                    e.printStackTrace();  
	                } catch (IllegalAccessException e) {  
	                    e.printStackTrace();  
	                }  
	                break;  
	            }   
	        }  
	}
	 @Override
	protected void onDraw(Canvas canvas) {
	super.onDraw(canvas);
	
	
	
	}   
}

