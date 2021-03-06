package com.guangwai.project.ystumad.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库管理类（封装调用）
 * Created by Ming on 2018/3/19.
 */

public class MADDBManager {
    private MADSQLiteOpenHelper dbHelper;
    private SQLiteDatabase database;

    public MADDBManager(Context context) {
        dbHelper = new MADSQLiteOpenHelper(context, Constant.DB_NAME, null, 1);
        database = dbHelper.getWritableDatabase();
    }

    /**
     * 添加错题集
     *
     * @param modelList
     */
    public void addSujbect(List<OperationModel> modelList) {
        database.beginTransaction();
        //   String sql = "INSERT INTO subject VALUES(null, ?, ?, ?, ?, ?)";
        try {
            for (OperationModel model : modelList) {
                if (!model.isRight()) {
                    //如果是错误的就写入数据库
//                    database.execSQL(sql, new Object[]{model.getFirstNum(), model.getSecondNum(), model.getOperation(), model.getResultNum(), model.getMode()});
                    ContentValues values = new ContentValues();
                    values.put("firstNum", model.getFirstNum());
                    values.put("secondNum", model.getSecondNum());
                    values.put("operation", model.getOperation());
                    values.put("resultNum", model.getResultNum());
                    values.put("mode", model.getMode());
                    values.put("date", model.getDate());
                    database.insert("subject", null, values);
                }
            }
        } catch (Exception e) {
            Log.e("Ming", "add is failed!!");
            e.printStackTrace();
        } finally {
            //一定要写（这是个大坑）
            database.setTransactionSuccessful();
            database.endTransaction();
        }
    }

    /**
     * 删除表格
     */
    public void deleteSubjectTable() {
        database.beginTransaction();
        try {
            database.delete("subject", null, null);
        } catch (Exception e) {
            Log.e("ming", "delete table is failed!");
        } finally {
            database.endTransaction();
        }

    }

    /**
     * 通过index来删除对应的数据
     *
     * @param index
     */
    public void deleteSubjectDataFromIndex(int index) {
        database.delete("subject", "_id = ?", new String[]{index + " "});
    }

    /**
     * 遍历表格，查询错题
     */
    public ArrayList<OperationModel> querySubjectTable() {
        ArrayList<OperationModel> result = new ArrayList<>();
        String sql = "select * from subject order by _id desc";
        Cursor cursor = null;
        try {
            cursor = database.rawQuery(sql, null);
//            cursor = database.query("subject", null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                OperationModel model = new OperationModel();
                model.setFirstNum(cursor.getInt(cursor.getColumnIndex("firstNum")));
                model.setSecondNum(cursor.getInt(cursor.getColumnIndex("secondNum")));
                model.setOperation(cursor.getInt(cursor.getColumnIndex("operation")));
                model.setResultNum(cursor.getInt(cursor.getColumnIndex("resultNum")));
                model.setMode(cursor.getInt(cursor.getColumnIndex("mode")));
                model.setDate(cursor.getString(cursor.getColumnIndex("date")));
                model.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                result.add(model);
            }
        } catch (Exception e) {
            Log.e("ming", "query table is failed!");
        } finally {
            cursor.close();
        }
        return result;
    }
}
