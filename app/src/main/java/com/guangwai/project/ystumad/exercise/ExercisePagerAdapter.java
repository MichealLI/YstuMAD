package com.guangwai.project.ystumad.exercise;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Ming on 2018/3/14.
 */

public class ExercisePagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public static final String[] TITILES = {"练习模式", "闯关模式"};

    public ExercisePagerAdapter(FragmentManager fm, List<Fragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITILES[position];
    }
}
