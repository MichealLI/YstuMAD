package com.guangwai.project.ystumad.homepage;


import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guangwai.project.ystumad.R;
import com.guangwai.project.ystumad.exercise.PracticeActivity;
import com.guangwai.project.ystumad.util.ActivityCollector;
import com.guangwai.project.ystumad.util.Constant;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * app主页
 */
public class HomepageActivity extends FragmentActivity implements View.OnClickListener {

    private LinearLayout tab1; //底部导航栏
    private LinearLayout tab2;
    private LinearLayout tab3;
    private ImageView tab1Image;
    private ImageView tab2Image;
    private ImageView tab3Image;
    private TextView tab1Text;
    private TextView tab2Text;
    private TextView tab3Text;

    private Fragment fragmentTab1;
    private Fragment fragmentTab2;
    private Fragment fragmentTab3;

    private int currentIndex = Constant.TAB1_INDEX; //当前所在的fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_homepage);
        ActivityCollector.getInstance().addActivity(this);
        initTabView();
        setCurrentTab(Constant.TAB1_INDEX);

    }

    /**
     * 初始化导航栏
     */
    private void initTabView() {
        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);

        //初始化tab1
        tab1Text = tab1.findViewById(R.id.tab_text);
        tab1Image = tab1.findViewById(R.id.tab_image);
        tab1Image.setImageDrawable(getResources().getDrawable(R.drawable.exercise_selected));
        //初始化tab2
        tab2Text = tab2.findViewById(R.id.tab_text);
        tab2Text.setText(R.string.tabs_item_tab2);
        tab2Text.setTextColor(0xffAAAAAA);
        tab2Image = tab2.findViewById(R.id.tab_image);
        tab2Image.setImageDrawable(getResources().getDrawable(R.drawable.analyse_unselecet));
        //初始化tab3
        tab3Text = tab3.findViewById(R.id.tab_text);
        tab3Text.setText(R.string.tabs_item_tab3);
        tab3Text.setTextColor(0xffAAAAAA);
        tab3Image = tab3.findViewById(R.id.tab_image);
        tab3Image.setImageDrawable(getResources().getDrawable(R.drawable.error_unselect));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab1:
                if (currentIndex != Constant.TAB1_INDEX) {
                    setCurrentTab(Constant.TAB1_INDEX);
                    currentIndex = Constant.TAB1_INDEX;
                }
                break;
            case R.id.tab2:
                if (currentIndex != Constant.TAB2_INDEX) {
                    setCurrentTab(Constant.TAB2_INDEX);
                    currentIndex = Constant.TAB2_INDEX;
                }
                break;
            case R.id.tab3:
                if (currentIndex != Constant.TAB3_INDEX) {
                    setCurrentTab(Constant.TAB3_INDEX);
                    currentIndex = Constant.TAB3_INDEX;
                }
                break;
            default:
                break;
        }
    }

    /**
     * 设置当前所在tab的fragment
     */
    private void setCurrentTab(int index) {
        resetTab(); //初始化图标
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction); //首先把所有的fragment隐藏了
        switch (index) {
            case Constant.TAB1_INDEX:
                tab1Image.setImageDrawable(getResources().getDrawable(R.drawable.exercise_selected));
                tab1Text.setTextColor(0xff3e6c91);
                int mode = getIntent().getIntExtra("mode", Constant.PRATICE_MODE);
                if (fragmentTab1 == null) {
                    fragmentTab1 = ExerciseFragment.newInstance(mode);
                    transaction.add(R.id.fragment_content, fragmentTab1);
                } else {
                    transaction.show(fragmentTab1);
                }
                break;
            case Constant.TAB2_INDEX:
                tab2Image.setImageDrawable(getResources().getDrawable(R.drawable.analyse_seleceted));
                tab2Text.setTextColor(0xff3e6c91);
                if (fragmentTab2 == null) {
                    fragmentTab2 = AnalyseFragment.newInstance();
                    transaction.add(R.id.fragment_content, fragmentTab2);
                } else {
                    transaction.show(fragmentTab2);
                }
                break;
            case Constant.TAB3_INDEX:
                tab3Image.setImageDrawable(getResources().getDrawable(R.drawable.error_selected));
                tab3Text.setTextColor(0xff3e6c91);
                if (fragmentTab3 == null) {
                    fragmentTab3 = ErrorFragment.newInstance();
                    transaction.add(R.id.fragment_content, fragmentTab3);
                } else {
                    transaction.show(fragmentTab3);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏所有的fragment
     *
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (fragmentTab1 != null) {
            transaction.hide(fragmentTab1);
        }
        if (fragmentTab2 != null) {
            transaction.hide(fragmentTab2);
        }
        if (fragmentTab3 != null) {
            transaction.hide(fragmentTab3);
        }
    }

    /**
     * 全部设置为未选中的图标和字体颜色
     */
    private void resetTab() {
        tab1Image.setImageDrawable(getResources().getDrawable(R.drawable.exercise_unselect));
        tab2Image.setImageDrawable(getResources().getDrawable(R.drawable.analyse_unselecet));
        tab3Image.setImageDrawable(getResources().getDrawable(R.drawable.error_unselect));

        tab1Text.setTextColor(0xffAAAAAA);
        tab2Text.setTextColor(0xffAAAAAA);
        tab3Text.setTextColor(0xffAAAAAA);

    }

    @Override
    public void onBackPressed() {
        final MaterialDialog breakDialog = new MaterialDialog(this);
        breakDialog.setMessage(R.string.app_sure_exit).setPositiveButton(R.string.commit, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                breakDialog.dismiss();
                ActivityCollector.getInstance().finishAll();
            }
        }).setNegativeButton(R.string.cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                breakDialog.dismiss();
            }
        });
        breakDialog.setCanceledOnTouchOutside(true);
        breakDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
    }
}
