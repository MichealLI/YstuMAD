package com.guangwai.project.ystumad.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.Window;

import com.guangwai.project.ystumad.util.ActivityCollector;

/**
 * 封装的activity基类
 * Created by Ming on 2018/3/16.
 */

public class BaseActivity extends Activity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActivityCollector.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
    }
}
