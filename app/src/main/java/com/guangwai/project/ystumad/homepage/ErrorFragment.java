package com.guangwai.project.ystumad.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Button;

import com.guangwai.project.ystumad.R;
import com.guangwai.project.ystumad.error.ErrorAdapter;
import com.guangwai.project.ystumad.error.ErrorData;
import com.guangwai.project.ystumad.util.MADDBManager;
import com.guangwai.project.ystumad.util.OperationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 错题集的fragment
 * Created by Ming on 2018/3/14.
 */

public class ErrorFragment extends Fragment {

    private ListView listView;
    private Button practice_again;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_error, container, false);
        initView(view);

        MADDBManager manager = new MADDBManager(getContext());
        List<OperationModel> modelList = manager.queryTable();

        ErrorAdapter eradapter = new ErrorAdapter(getContext(), modelList);
        listView.setAdapter(eradapter);

        //设置监听
        practice_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.practice_again:   //还没有完成的监听

                        break;
                }
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
    }


    public static ErrorFragment newInstance() {
        ErrorFragment fragment = new ErrorFragment();
        return fragment;
    }

}
