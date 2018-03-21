package com.guangwai.project.ystumad.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 将list转为json数据，保持到SharedPreferences
 * Created by Ming on 2018/3/21.
 */

public class DataSaveUtil {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public DataSaveUtil(Context mContext) {
        preferences = mContext.getSharedPreferences(Constant.SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    /**
     * 保存近七次得分纪录的List
     *
     * @param tag
     * @param dataList
     */
    public <T> void setDataList(String tag, Queue<T> dataList) {
        if (null == dataList || dataList.size() <= 0)
            return;

        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(dataList);
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
        LinkedList<Integer> dataList = new LinkedList<>();
        String strJson = preferences.getString(tag, null);
        if (null == strJson) {
            return dataList;
        }
        Gson gson = new Gson();
        dataList = gson.fromJson(strJson, new TypeToken<LinkedList<Integer>>() {
        }.getType());
        return dataList;
    }

    /**
     * 保存闯关题目的List
     *
     * @param tag
     * @param dataList
     */
    public void setBreakSubjectList(String tag, List<OperationModel> dataList) {
        String data = preferences.getString(tag, null); //看下是否有保存
        if (data != null) {
            return;
        }
        if (null == dataList || dataList.size() <= 0)
            return;

        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(dataList);
        editor.putString(tag, strJson);
        editor.commit();
    }

    /**
     * 获取闯关题目的List
     *
     * @param tag
     * @return
     */
    public ArrayList<OperationModel> getBreakSubjectList(String tag) {
        ArrayList<OperationModel> dataList = new ArrayList<>();
        String strJson = preferences.getString(tag, null);
        if (null == strJson) {
            return dataList;
        }
        Gson gson = new Gson();
        dataList = gson.fromJson(strJson, new TypeToken<ArrayList<OperationModel>>() {
        }.getType());
        return dataList;
    }
}
