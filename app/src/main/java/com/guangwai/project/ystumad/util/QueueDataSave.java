package com.guangwai.project.ystumad.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 将list转为json数据，保持到SharedPreferences
 * Created by Ming on 2018/3/21.
 */

public class QueueDataSave {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public QueueDataSave(Context mContext) {
        preferences = mContext.getSharedPreferences(Constant.SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    /**
     * 保存List
     *
     * @param tag
     * @param datalist
     */
    public <T> void setDataList(String tag, Queue<T> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;

        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        editor.clear();
        editor.putString(tag, strJson);
        editor.commit();

    }

    /**
     * 获取List
     *
     * @param tag
     * @return
     */
    public LinkedList getDataList(String tag) {
        LinkedList<Integer> datalist = new LinkedList<>();
        String strJson = preferences.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<LinkedList<Integer>>() {
        }.getType());
        return datalist;

    }
}
