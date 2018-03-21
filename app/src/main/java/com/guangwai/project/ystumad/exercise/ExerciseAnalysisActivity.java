package com.guangwai.project.ystumad.exercise;
/**
 * 答案解析页面
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.guangwai.project.ystumad.R;
import com.guangwai.project.ystumad.base.BaseActivity;
import com.guangwai.project.ystumad.util.Constant;
import com.guangwai.project.ystumad.util.OperationModel;

import java.util.ArrayList;

public class ExerciseAnalysisActivity extends BaseActivity implements View.OnClickListener {
    private static ArrayList<OperationModel> modelList; //保存题目数据
    private int mode; //什么模式

    private ListView subjectListView;
    private ImageView back;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_analysis);
        Intent intent = getIntent();
        mode = intent.getIntExtra("mode", Constant.ANALYSE_ALL);
        modelList = intent.getParcelableArrayListExtra("subject");
        initView(mode);
    }

    /**
     * 初始化控件
     */
    private void initView(int mode) {
        subjectListView = findViewById(R.id.error_analysis);
        back = findViewById(R.id.practice_back);
        title = findViewById(R.id.title);

        back.setOnClickListener(this);

        if (mode == Constant.ANALYSE_ALL) {
            title.setText(R.string.all_analysis);
        } else if (mode == Constant.ANALYSE_ERROR) {
            title.setText(R.string.error_analysis);
        }

        //配置适配器
        ExerciseAnalysisAdapter adapter = new ExerciseAnalysisAdapter(modelList, this, mode);
        subjectListView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.practice_back:
                finish();
                break;
        }
    }
}
