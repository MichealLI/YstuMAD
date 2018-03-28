package com.guangwai.project.ystumad.homepage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.guangwai.project.ystumad.R;
import com.guangwai.project.ystumad.util.Constant;
import com.guangwai.project.ystumad.util.DataSaveUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 分析页的fragment
 * Created by Ming on 2018/3/14.
 */

public class AnalyseFragment extends Fragment {
    private LineChart lineChart;
    private TextView subjectNum;
    private TextView subjectRightNum;
    private TextView rightRate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_analyse, container, false);
        initView(view);
        initData();
        return view;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //获取前七次的数据
        DataSaveUtil saveUtil = new DataSaveUtil(getContext());
        LinkedList dataList = saveUtil.getDataList(Constant.ANALYSE_NAME);

        List<Entry> entries = new ArrayList<Entry>();
        for (int i = 0; i < dataList.size(); i++) {
            entries.add(new Entry(i + 1, Float.valueOf(dataList.get(i) + "")));
        }
        //进行一系列基本配置
        if (entries.size() > 0) {
            LineDataSet dataSet = new LineDataSet(entries, "得分数据");
            dataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

            //设置data
            LineData data = new LineData(dataSet);

            lineChart.setData(data);
        }

        //设置描述信息
        Description description = new Description();
        description.setText("");
        lineChart.setDescription(description);


        XAxis xAxis = lineChart.getXAxis();
        lineChart.getAxisRight().setEnabled(false); // 隐藏右边 的坐标轴
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 让x轴在下面

        xAxis.setDrawGridLines(false);
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.setNoDataText(getResources().getString(R.string.analyse_no_data));//没有数据时显示的文字

        final String[] quarters = new String[]{"", "Q1", "Q2", "Q3", "Q4", "Q5", "Q6", "Q7"};

        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int) value];
            }

        };


        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

        lineChart.invalidate(); //refresh


    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        lineChart = view.findViewById(R.id.chart);
        subjectNum = view.findViewById(R.id.analyse_subject_num);
        subjectRightNum = view.findViewById(R.id.analyse_subject_right_num);
        rightRate = view.findViewById(R.id.right_rate);

        SharedPreferences pre = getContext().getSharedPreferences(Constant.SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        int wrongNum = pre.getInt("subject_wrong_sum", 0);
        int sum = pre.getInt("subject_sum", 0);
        int rightNum = sum - wrongNum;
        String strRightNum = String.valueOf(rightNum);
        subjectNum.setText(sum + "");
        subjectRightNum.setText(strRightNum);
        double rate = rightNum * 1.0 / sum * 100;
        rate = (double) (Math.round(rate * 100)) / 100;
        rightRate.setText(rate + "%");
    }

    public static AnalyseFragment newInstance() {
        AnalyseFragment fragment = new AnalyseFragment();
        return fragment;
    }
}
