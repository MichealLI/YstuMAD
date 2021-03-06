package com.guangwai.project.ystumad.homepage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.guangwai.project.ystumad.R;
import com.guangwai.project.ystumad.exercise.BreakModeFragment;
import com.guangwai.project.ystumad.exercise.ExercisePagerAdapter;
import com.guangwai.project.ystumad.exercise.PracticeModeFragment;
import com.guangwai.project.ystumad.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 练习页的fragment
 * Created by Ming on 2018/3/14.
 */

public class ExerciseFragment extends Fragment {
    private ViewPager exerciseViewPager;

    private List<Fragment> fragmentList; //保存fragment

    private Context mContext;

    private int mode = Constant.PRATICE_MODE;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mode = getArguments().getInt("mode", Constant.PRATICE_MODE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_exercise, container, false);
        exerciseViewPager = view.findViewById(R.id.exercise_pager);

        //初始化fragment
        fragmentList = new ArrayList<>();
        Fragment practiceMode = PracticeModeFragment.newInstance();
        Fragment breakMode = BreakModeFragment.newInstance();
        fragmentList.add(practiceMode);
        fragmentList.add(breakMode);

        //初始化adapter
        FragmentManager fm = getActivity().getSupportFragmentManager();
        ExercisePagerAdapter adapter = new ExercisePagerAdapter(fm, fragmentList);
        exerciseViewPager.setAdapter(adapter);
        exerciseViewPager.setCurrentItem(mode);

        //初始化顶部tabs
        PagerSlidingTabStrip tabs = view.findViewById(R.id.top_tabs);
        tabs.setTextColor(0xffffffff);
        tabs.setShouldExpand(true);
        tabs.setViewPager(exerciseViewPager);


        return view;
    }


    public static ExerciseFragment newInstance(int mode) {
        ExerciseFragment fragment = new ExerciseFragment();
        Bundle args = new Bundle();
        args.putInt("mode", mode);
        fragment.setArguments(args);
        return fragment;
    }
}
