package com.guangwai.project.ystumad.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guangwai.project.ystumad.R;
import com.guangwai.project.ystumad.base.BaseActivity;
import com.guangwai.project.ystumad.homepage.HomepageActivity;
import com.guangwai.project.ystumad.util.ActivityCollector;
import com.guangwai.project.ystumad.util.Constant;

import me.drakeet.materialdialog.MaterialDialog;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextView praticeMode;
    private TextView breakMode;
    private LinearLayout loginContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示引导页
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        praticeMode = findViewById(R.id.practice_mode);
        breakMode = findViewById(R.id.break_mode);
        loginContent = findViewById(R.id.login_content);

        //设置点击事件
        praticeMode.setOnClickListener(this);
        breakMode.setOnClickListener(this);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.login_enter_anim);
        loginContent.startAnimation(animation);
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

    @Override
    public void onBackPressed() {
        final MaterialDialog breakDialog = new MaterialDialog(this);
        breakDialog.setMessage(R.string.app_sure_exit).setPositiveButton(R.string.commit, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                breakDialog.dismiss();
                ActivityCollector.getInstance().finishAll();
            }
        }).setNegativeButton(R.string.cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                breakDialog.dismiss();
            }
        });
        breakDialog.setCanceledOnTouchOutside(true);
        breakDialog.show();
    }
}
