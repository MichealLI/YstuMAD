package com.guangwai.project.ystumad.util;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLite帮助类
 * Created by Ming on 2018/3/19.
 */

public class MADSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS subject" +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, firstNum INTEGER" +
            ",secondNum INTEGER,operation INTEGER,resultNum INTEGER,mode INTEGER,date VARCHAR)";

    public MADSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists subject");
        onCreate(sqLiteDatabase);
    }
}
