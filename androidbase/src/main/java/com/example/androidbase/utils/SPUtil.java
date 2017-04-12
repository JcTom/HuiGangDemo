package com.example.androidbase.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * author: lyfei
 * date:   2016/8/16.
 * <p/>
 * describe:
 */
public class SPUtil {

    private SPUtil() {}

    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "data";

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object
     */
    public static void put(Context context, String key, Object object)
    {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        //存null的话就remove掉了
        if(object==null)
            editor.remove(key);
        else{

            if (object instanceof String)
            {
                editor.putString(key, (String) object);
            } else if (object instanceof Integer)
            {
                editor.putInt(key, (Integer) object);
            } else if (object instanceof Boolean)
            {
                editor.putBoolean(key, (Boolean) object);
            } else if (object instanceof Float)
            {
                editor.putFloat(key, (Float) object);
            } else if (object instanceof Long)
            {
                editor.putLong(key, (Long) object);
            } else
            {
                editor.putString(key, object.toString());
            }
        }
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get(Context context, String key, Object defaultObject)
    {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);

        if (defaultObject instanceof String)
        {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer)
        {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean)
        {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float)
        {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long)
        {
            return sp.getLong(key, (Long) defaultObject);
        }

        return defaultObject;
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key)
    {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }


    public static  void remove(Context context  ,String... key){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        for(String k :key)
            editor.remove(k);
        SharedPreferencesCompat.apply(editor);
    }

    /***
     * 移除某个对象
     * @param context
     * @param c
     * @param <T>
     */
    public static <T>void remove2Object(Context context , Class<T> c){
        remove(context , c.getName());
    }
    /**
     * 清除所有数据
     *
     * @param context
     */
    public static void clear(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key)
    {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 查询某个对象是否已经存在
     *
     * @param context
     * @param c
     * @return
     */
    public static <T> boolean contains2Object(Context context, Class<T> c){
        return contains(context,c.getName());
    }
    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     *
     */
    private static class SharedPreferencesCompat
    {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({ "unchecked", "rawtypes" })
        private static Method findApplyMethod()
        {
            try
            {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e)
            {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor)
        {
            try
            {
                if (sApplyMethod != null)
                {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e)
            {
            } catch (IllegalAccessException e)
            {
            } catch (InvocationTargetException e)
            {
            }
            editor.commit();
        }
    }


    /***
     * 从sd中存入对象
     * @param context
     * @param object
     * @param <T>
     */
    public static <T> void save2Object(Context context , T object ){
        if(object==null)
            return;
        SharedPreferences.Editor ed = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).edit();
        String stringJson =  new Gson().toJson(object);
        ed.putString(object.getClass().getName(), stringJson);
        ed.apply();
    }

    /***
     * 从sd中获取名字
     * @param context
     * @param <T>
     */
    public static  <T> T get2Object(Context context   , Class<T> classOfT){
        String jsonStr =context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getString(classOfT.getName(), "");
        if(!TextUtils.isEmpty(jsonStr))
            return new Gson().fromJson(jsonStr ,classOfT);
        return null;
    }
}
