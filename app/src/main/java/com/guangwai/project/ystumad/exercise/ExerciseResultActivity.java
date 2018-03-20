package com.guangwai.project.ystumad.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.guangwai.project.ystumad.R;
import com.guangwai.project.ystumad.base.BaseActivity;
import com.guangwai.project.ystumad.util.OperationModel;

import java.util.ArrayList;

public class ExerciseResultActivity extends BaseActivity implements View.OnClickListener {
    private TextView subjectNum;
    private TextView subjectRightNum;
    private TextView usedTime;
    private TextView totalScore;
    private Button errorAnalysis;
    private Button allAnalysis;
    private Button continueDo;
    private static ArrayList<OperationModel> modelList; //保存题目数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_result);
        Intent intent = getIntent();
        modelList = intent.getParcelableArrayListExtra("subject");
        int wrongNum = intent.getIntExtra("wrongNum", 0);
        String time = intent.getStringExtra("usedTime");
        initView(modelList.size(), wrongNum, time);
    }

    /**
     * 初始化View
     */
    private void initView(int sumNum, int wrongNum, String time) {
        subjectNum = findViewById(R.id.subject_num);
        subjectRightNum = findViewById(R.id.subject_right_num);
        usedTime = findViewById(R.id.used_time);
        errorAnalysis = findViewById(R.id.error_analysis);
        allAnalysis = findViewById(R.id.all_analysis);
        continueDo = findViewById(R.id.continue_do);
        totalScore = findViewById(R.id.total_score);

        subjectNum.setText(sumNum + "");
        subjectRightNum.setText(sumNum - wrongNum + "");
        usedTime.setText(time);
        int score = (int) ((sumNum - wrongNum) * 1.0 / sumNum * 100);
        String content = totalScore.getText().toString();
        totalScore.setText(String.format(content, score));

        errorAnalysis.setOnClickListener(this);
        allAnalysis.setOnClickListener(this);
        continueDo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.error_analysis:
                //错题解析

                break;
            case R.id.all_analysis:
                //所有解析

                break;
            case R.id.continue_do:
                //继续练习

                break;
        }
    }
}
