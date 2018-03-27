package com.guangwai.project.ystumad.exercise;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.guangwai.project.ystumad.R;
import com.guangwai.project.ystumad.base.BaseActivity;
import com.guangwai.project.ystumad.homepage.HomepageActivity;
import com.guangwai.project.ystumad.util.Constant;
import com.guangwai.project.ystumad.util.LimitQueueUtil;
import com.guangwai.project.ystumad.util.DataSaveUtil;
import com.guangwai.project.ystumad.util.OperationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ExerciseResultActivity extends BaseActivity implements View.OnClickListener {
    private TextView subjectNum;
    //  private TextView subjectRightNum;
    private TextView usedTime;
    private TextView totalScore;
    private LinearLayout back;
    private Button errorAnalysis;
    private Button allAnalysis;
    private Button continueDo;
    private PieChart pieChart;
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
        saveScoreInLocal(modelList.size(), wrongNum);
    }

    /**
     * 把分数保存到本地上
     */
    private void saveScoreInLocal(int sumNum, int wrongNum) {
        DataSaveUtil dataSave = new DataSaveUtil(this);
        Queue<Integer> queue = dataSave.getDataList(Constant.ANALYSE_NAME);
        LimitQueueUtil queueUtil = new LimitQueueUtil(7, queue);
        queueUtil.offer((int) ((sumNum - wrongNum) * 1.0 / sumNum * 100));
        dataSave.setDataList(Constant.ANALYSE_NAME, queue);
    }

    /**
     * 初始化View
     */
    private void initView(int sumNum, int wrongNum, String time) {
        subjectNum = findViewById(R.id.subject_num);
        //  subjectRightNum = findViewById(R.id.subject_right_num);
        usedTime = findViewById(R.id.used_time);
        errorAnalysis = findViewById(R.id.error_analysis);
        allAnalysis = findViewById(R.id.all_analysis);
        continueDo = findViewById(R.id.continue_do);
        totalScore = findViewById(R.id.total_score);
        back = findViewById(R.id.practice_back);
        pieChart = findViewById(R.id.pieChart);

        //初始化饼状图
        PieData pieData = getPieData(sumNum - wrongNum, wrongNum);
        showChart(pieChart, pieData, sumNum - wrongNum);

        subjectNum.setText(sumNum + "");
//        subjectRightNum.setText(sumNum - wrongNum + "");
        usedTime.setText(time);
        int score = (int) ((sumNum - wrongNum) * 1.0 / sumNum * 100);
        String content = totalScore.getText().toString();
        totalScore.setText(String.format(content, score));

        //初始化

        errorAnalysis.setOnClickListener(this);
        allAnalysis.setOnClickListener(this);
        continueDo.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.error_analysis:
                //错题解析
                intent = new Intent(this, ExerciseAnalysisActivity.class);
                intent.putExtra("mode", Constant.ANALYSE_ERROR);
                intent.putParcelableArrayListExtra("subject", modelList);
                startActivity(intent);
                break;
            case R.id.all_analysis:
                //所有解析
                intent = new Intent(this, ExerciseAnalysisActivity.class);
                intent.putExtra("mode", Constant.ANALYSE_ALL);
                intent.putParcelableArrayListExtra("subject", modelList);
                startActivity(intent);
                break;
            case R.id.continue_do:
                //继续练习
                intent = new Intent(this, HomepageActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.practice_back:
                intent = new Intent(this, HomepageActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }


    private void showChart(PieChart pieChart, PieData pieData, int rightNum) {
        // pieChart.setHoleColorTransparent(true);

        pieChart.setHoleRadius(80f);  //半径
        pieChart.setTransparentCircleRadius(100f); // 半透明圈
        //pieChart.setHoleRadius(0)  //实心圆

        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);

        // mChart.setDrawYValues(true);
        pieChart.setDrawCenterText(true);  //饼状图中间可以添加文字

        pieChart.setDrawHoleEnabled(true);

        pieChart.setRotationAngle(90); // 初始旋转角度

        pieChart.setUsePercentValues(true);

        // draws the corresponding description value into the slice
        // mChart.setDrawXValues(true);

        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true); // 可以手动旋转

        // display percentage values
        pieChart.setUsePercentValues(true);  //显示成百分比


        pieChart.setCenterText("正确数 " + rightNum);  //饼状图中间的文字
        pieChart.setCenterTextSize(20);

        //设置数据
        pieChart.setData(pieData);

        // undo all highlights
//      pieChart.highlightValues(null);
//      pieChart.invalidate();

        Legend mLegend = pieChart.getLegend();  //设置比例图
        mLegend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);  //中间显示
        mLegend.setForm(Legend.LegendForm.LINE);  //设置比例图的形状，默认是方形
        mLegend.setXEntrySpace(7f);
        mLegend.setYEntrySpace(5f);

        pieChart.animateXY(1000, 1000);  //设置动画
        // mChart.spin(2000, 0, 360);
    }


    /**
     * 分成几部分
     */
    private PieData getPieData(int rightNum, int wrongNum) {

        List<PieEntry> yVals = new ArrayList<PieEntry>();  //yVals用来表示封装每个饼块的实际数据

        // 饼图数据
        yVals.add(new PieEntry(wrongNum));
        yVals.add(new PieEntry(rightNum));

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yVals, "right");
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        colors.add(Color.rgb(205, 205, 205));
        colors.add(Color.rgb(114, 188, 223));
        pieDataSet.setColors(colors);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());

        return pieData;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomepageActivity.class);
        startActivity(intent);
        finish();
    }
}

