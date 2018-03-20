package com.guangwai.project.ystumad.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.guangwai.project.ystumad.R;
import com.guangwai.project.ystumad.base.BaseActivity;
import com.guangwai.project.ystumad.homepage.HomepageActivity;
import com.guangwai.project.ystumad.util.Constant;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextView praticeMode;
    private TextView breakMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        praticeMode = findViewById(R.id.practice_mode);
        breakMode = findViewById(R.id.break_mode);

        //设置点击事件
        praticeMode.setOnClickListener(this);
        breakMode.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.practice_mode:
                intent = new Intent(this, HomepageActivity.class);
                intent.putExtra("mode", Constant.PRATICE_MODE);
                startActivity(intent);
                finish();
                break;
            case R.id.break_mode:
                intent = new Intent(this, HomepageActivity.class);
                intent.putExtra("mode", Constant.BREAK_MODE);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
