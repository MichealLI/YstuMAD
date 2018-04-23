package com.guangwai.project.ystumad.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.guangwai.project.ystumad.R;
import com.guangwai.project.ystumad.util.Constant;

/**
 * 练习模式
 * Created by Ming on 2018/3/14.
 */

public class PracticeModeFragment extends Fragment implements View.OnClickListener {
    private Button begin;
    private EditText subjectNum;
    private Spinner subjectRange;
    private RadioGroup modeChoose;
    private RadioButton singleMode;
    private RadioButton mixedMode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exercise_practice_mode, container, false);
        initView(view);
        return view;
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        begin = view.findViewById(R.id.do_begin);
        subjectNum = view.findViewById(R.id.subject_number);
        subjectRange = view.findViewById(R.id.subject_range);
        modeChoose = view.findViewById(R.id.mode_choose);
        singleMode = view.findViewById(R.id.single_mode);
        mixedMode = view.findViewById(R.id.mixed_mode);

        begin.setOnClickListener(this);
        subjectNum.setSelection(subjectNum.getText().length());

    }

    public static PracticeModeFragment newInstance() {
        PracticeModeFragment fragment = new PracticeModeFragment();
        return fragment;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.do_begin:
                if (!TextUtils.isEmpty(subjectNum.getText())) { //空值判断
                    String content = subjectNum.getText().toString();
                    int num = Integer.parseInt(content); //题目数量
                    int max = subjectRange.getSelectedItemPosition() * 10 + 10;
                    if (num > 0) {
                        Intent intent = new Intent(getContext(), PracticeActivity.class);
                        intent.putExtra("mode", Constant.PRATICE_MODE);
                        intent.putExtra("practice_num", num);
                        intent.putExtra("practice_max", max);
                        if (modeChoose.getCheckedRadioButtonId() == R.id.single_mode) {
                            //简单模式
                            intent.putExtra("practice_mode", Constant.SINGLE_MODE);
                        } else if (modeChoose.getCheckedRadioButtonId() == R.id.mixed_mode) {
                            //复杂模式
                            intent.putExtra("practice_mode", Constant.MIXED_MODE);
                        }
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        Toast.makeText(getContext(), R.string.subject_num_must_positive, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    //输入框为空，不能跳转去做题
                    Toast.makeText(getContext(), R.string.subject_is_empty, Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }
}
