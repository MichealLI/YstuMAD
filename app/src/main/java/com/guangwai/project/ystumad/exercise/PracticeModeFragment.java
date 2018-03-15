package com.guangwai.project.ystumad.exercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guangwai.project.ystumad.R;

/**
 * 练习模式
 * Created by Ming on 2018/3/14.
 */

public class PracticeModeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exercise_practice_mode, container, false);
        return view;
    }
}
