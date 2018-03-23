package com.guangwai.project.ystumad.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Button;

import com.guangwai.project.ystumad.R;
import com.guangwai.project.ystumad.error.ErrorAdapter;
import com.guangwai.project.ystumad.exercise.PracticeActivity;
import com.guangwai.project.ystumad.util.Constant;
import com.guangwai.project.ystumad.util.MADDBManager;
import com.guangwai.project.ystumad.util.OperationModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 错题集的fragment
 * Created by Ming on 2018/3/14.
 */

public class ErrorFragment extends Fragment {

    private ListView listView;
    private Button practice_again;

    private LinearLayout mainContent;
    private LinearLayout emptyContent;

    private ArrayList<OperationModel> modelList;

    private MADDBManager manager;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_error, container, false);
        initView(view);

        manager = new MADDBManager(getContext());
        modelList = manager.queryTable();

        if (modelList.size() > 0) {
            //有错题的情况下
            ErrorAdapter eradapter = new ErrorAdapter(getContext(), modelList);
            listView.setAdapter(eradapter);
        } else {
            //没错题的情况下
            mainContent.setVisibility(View.GONE);
            emptyContent.setVisibility(View.VISIBLE);
        }


        //设置监听
        practice_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //随机抽取10题进行再次练习

                ArrayList<OperationModel> newErrorData = new ArrayList<>();
                if (modelList.size() > 10) {
                    HashSet<Integer> numSet = new HashSet<>();
                    randomSet(0, modelList.size() - 1, 10, numSet);
                    Iterator iterator = numSet.iterator();
//                    for (int index = 0; index < indexArray.length; index++) {
                    while (iterator.hasNext()) {
                        int index = (int) iterator.next();
                        newErrorData.add(modelList.get(index));
                    }
                } else {
                    newErrorData = modelList;
                    for (int i = 0; i < modelList.size(); i++) {
                    }
                }

                //进行页面的跳转
                Intent intent = new Intent(getActivity(), PracticeActivity.class);
                intent.putExtra("mode", Constant.ERROR_MODE);
                intent.putParcelableArrayListExtra("error_subject", newErrorData);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

    /**
     * 初始化控件
     */
    public void initView(View view) {
        listView = view.findViewById(R.id.error_listView);
        practice_again = view.findViewById(R.id.practice_again);
        mainContent = view.findViewById(R.id.main_content);
        emptyContent = view.findViewById(R.id.empty_content);
    }


    public static ErrorFragment newInstance() {
        ErrorFragment fragment = new ErrorFragment();
        return fragment;
    }

    /**
     * 随机指定范围内N个不重复的数
     * 在初始化的无重复待选数组中随机产生一个数放入结果中，
     * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换
     * 然后从len-2里随机产生下一个随机数，如此类推
     *
     * @param max 指定范围最大值
     * @param min 指定范围最小值
     * @param n   随机数个数
     * @return int[] 随机数结果集
     */
    public static void randomSet(int min, int max, int n, HashSet<Integer> set) {
        if (n > (max - min + 1) || max < min) {
            return;
        }
        for (int i = 0; i < n - set.size(); i++) {
            // 调用Math.random()方法
            int num = (int) (Math.random() * (max - min)) + min;
            set.add(num);// 将不同的数存入HashSet中
        }
        int setSize = set.size();
        // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小
        if (setSize < n) {
            randomSet(min, max, n, set);// 递归
        }
    }

}
