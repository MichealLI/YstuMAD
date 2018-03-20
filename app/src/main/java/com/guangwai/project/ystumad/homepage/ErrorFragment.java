package com.guangwai.project.ystumad.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guangwai.project.ystumad.R;

/**
 * 错题集的fragment
 * Created by Ming on 2018/3/14.
 */

public class ErrorFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_error, container, false);
        return view;
    }

    public static ErrorFragment newInstance() {

        ErrorFragment fragment = new ErrorFragment();
        return fragment;
    }
}
