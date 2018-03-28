package com.guangwai.project.ystumad.exercise;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.guangwai.project.ystumad.R;
import com.guangwai.project.ystumad.util.Constant;
import com.guangwai.project.ystumad.util.DataSaveUtil;
import com.guangwai.project.ystumad.util.OperationModel;
import com.guangwai.project.ystumad.util.RandomNumberFactory;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * 闯关模式
 * Created by Ming on 2018/3/14.
 */

public class BreakModeFragment extends Fragment implements View.OnClickListener {
    private ImageView break01, break02, break03, break04, break05, break06, break07, break08, break09, break10;
    private ImageView break11, break12, break13, break14, break15, break16, break17, break18, break19, break20;

    private int breakNum; //目前的闯关数

    private Button resetBreak; //重置关卡

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exercise_break_mode, container, false);
        breakNum = getBreakNumber();
        initView(view);
        return view;
    }

    /**
     * 获取本地保存的闯关数
     *
     * @return
     */
    private int getBreakNumber() {
        SharedPreferences pre = getActivity().getSharedPreferences(Constant.SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        breakNum = pre.getInt("breakNum", 0);
        return breakNum;
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        break01 = view.findViewById(R.id.break_1);
        break02 = view.findViewById(R.id.break_2);
        break03 = view.findViewById(R.id.break_3);
        break04 = view.findViewById(R.id.break_4);
        break05 = view.findViewById(R.id.break_5);
        break06 = view.findViewById(R.id.break_6);
        break07 = view.findViewById(R.id.break_7);
        break08 = view.findViewById(R.id.break_8);
        break09 = view.findViewById(R.id.break_9);
        break10 = view.findViewById(R.id.break_10);
        break11 = view.findViewById(R.id.break_11);
        break12 = view.findViewById(R.id.break_12);
        break13 = view.findViewById(R.id.break_13);
        break14 = view.findViewById(R.id.break_14);
        break15 = view.findViewById(R.id.break_15);
        break16 = view.findViewById(R.id.break_16);
        break17 = view.findViewById(R.id.break_17);
        break18 = view.findViewById(R.id.break_18);
        break19 = view.findViewById(R.id.break_19);
        break20 = view.findViewById(R.id.break_20);

        resetBreak = view.findViewById(R.id.reset_break);

        break01.setOnClickListener(this);
        break02.setOnClickListener(this);
        break03.setOnClickListener(this);
        break04.setOnClickListener(this);
        break05.setOnClickListener(this);
        break06.setOnClickListener(this);
        break07.setOnClickListener(this);
        break08.setOnClickListener(this);
        break09.setOnClickListener(this);
        break10.setOnClickListener(this);
        break11.setOnClickListener(this);
        break12.setOnClickListener(this);
        break13.setOnClickListener(this);
        break14.setOnClickListener(this);
        break15.setOnClickListener(this);
        break16.setOnClickListener(this);
        break17.setOnClickListener(this);
        break18.setOnClickListener(this);
        break19.setOnClickListener(this);
        break20.setOnClickListener(this);

        resetBreak.setOnClickListener(this);

        setBreakImage(); // 设置关卡的图片
    }


    /**
     * 设置各个关卡的图片
     */
    private void setBreakImage() {
        if (breakNum >= 1) {
            break02.setImageDrawable(getResources().getDrawable(R.drawable.break2));
        }
        if (breakNum >= 2) {
            break03.setImageDrawable(getResources().getDrawable(R.drawable.break3));
        }
        if (breakNum >= 3) {
            break04.setImageDrawable(getResources().getDrawable(R.drawable.break4));
        }
        if (breakNum >= 4) {
            break05.setImageDrawable(getResources().getDrawable(R.drawable.break5));
        }
        if (breakNum >= 5) {
            break06.setImageDrawable(getResources().getDrawable(R.drawable.break6));
        }
        if (breakNum >= 6) {
            break07.setImageDrawable(getResources().getDrawable(R.drawable.break7));
        }
        if (breakNum >= 7) {
            break08.setImageDrawable(getResources().getDrawable(R.drawable.break8));
        }
        if (breakNum >= 8) {
            break09.setImageDrawable(getResources().getDrawable(R.drawable.break9));
        }
        if (breakNum >= 9) {
            break10.setImageDrawable(getResources().getDrawable(R.drawable.break10));
        }
        if (breakNum >= 10) {
            break11.setImageDrawable(getResources().getDrawable(R.drawable.break11));
        }
        if (breakNum >= 11) {
            break12.setImageDrawable(getResources().getDrawable(R.drawable.break12));
        }
        if (breakNum >= 12) {
            break13.setImageDrawable(getResources().getDrawable(R.drawable.break13));
        }
        if (breakNum >= 13) {
            break14.setImageDrawable(getResources().getDrawable(R.drawable.break14));
        }
        if (breakNum >= 14) {
            break15.setImageDrawable(getResources().getDrawable(R.drawable.break15));
        }
        if (breakNum >= 15) {
            break16.setImageDrawable(getResources().getDrawable(R.drawable.break16));
        }
        if (breakNum >= 16) {
            break17.setImageDrawable(getResources().getDrawable(R.drawable.break17));
        }
        if (breakNum >= 17) {
            break18.setImageDrawable(getResources().getDrawable(R.drawable.break18));
        }
        if (breakNum >= 18) {
            break19.setImageDrawable(getResources().getDrawable(R.drawable.break19));
        }
        if (breakNum >= 19) {
            break20.setImageDrawable(getResources().getDrawable(R.drawable.break20));
        }
    }

    /**
     * 重置各个关卡的图片
     *
     * @return
     */
    public void resetBreakImage() {
        break02.setImageDrawable(getResources().getDrawable(R.drawable.lock));
        break03.setImageDrawable(getResources().getDrawable(R.drawable.lock));
        break04.setImageDrawable(getResources().getDrawable(R.drawable.lock));
        break05.setImageDrawable(getResources().getDrawable(R.drawable.lock));
        break06.setImageDrawable(getResources().getDrawable(R.drawable.lock));
        break07.setImageDrawable(getResources().getDrawable(R.drawable.lock));
        break08.setImageDrawable(getResources().getDrawable(R.drawable.lock));
        break09.setImageDrawable(getResources().getDrawable(R.drawable.lock));
        break10.setImageDrawable(getResources().getDrawable(R.drawable.lock));
        break11.setImageDrawable(getResources().getDrawable(R.drawable.lock));
        break12.setImageDrawable(getResources().getDrawable(R.drawable.lock));
        break13.setImageDrawable(getResources().getDrawable(R.drawable.lock));
        break14.setImageDrawable(getResources().getDrawable(R.drawable.lock));
        break15.setImageDrawable(getResources().getDrawable(R.drawable.lock));
        break16.setImageDrawable(getResources().getDrawable(R.drawable.lock));
        break17.setImageDrawable(getResources().getDrawable(R.drawable.lock));
        break18.setImageDrawable(getResources().getDrawable(R.drawable.lock));
        break19.setImageDrawable(getResources().getDrawable(R.drawable.lock));
        break20.setImageDrawable(getResources().getDrawable(R.drawable.lock));

    }

    public static BreakModeFragment newInstance() {
        BreakModeFragment fragment = new BreakModeFragment();
        return fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.break_1:
                //肯定可以跳转的
                getBreakSubjectAndJump("break1", 1);
                break;
            case R.id.break_2:
                if (breakNum >= 1) {
                    //可以进行闯关，页面跳转
                    getBreakSubjectAndJump("break2", 2);
                    break;
                } else {
                    Toast.makeText(getContext(), R.string.no_break, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.break_3:
                if (breakNum >= 2) {
                    //可以进行闯关，页面跳转
                    getBreakSubjectAndJump("break3", 3);
                    break;
                } else {
                    Toast.makeText(getContext(), R.string.no_break, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.break_4:
                if (breakNum >= 3) {
                    //可以进行闯关，页面跳转
                    getBreakSubjectAndJump("break4", 4);
                    break;
                } else {
                    Toast.makeText(getContext(), R.string.no_break, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.break_5:
                if (breakNum >= 4) {
                    //可以进行闯关，页面跳转
                    getBreakSubjectAndJump("break5", 5);
                    break;
                } else {
                    Toast.makeText(getContext(), R.string.no_break, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.break_6:
                if (breakNum >= 5) {
                    //可以进行闯关，页面跳转
                    getBreakSubjectAndJump("break6", 6);
                    break;
                } else {
                    Toast.makeText(getContext(), R.string.no_break, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.break_7:
                if (breakNum >= 6) {
                    //可以进行闯关，页面跳转
                    getBreakSubjectAndJump("break7", 7);
                    break;
                } else {
                    Toast.makeText(getContext(), R.string.no_break, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.break_8:
                if (breakNum >= 7) {
                    //可以进行闯关，页面跳转
                    getBreakSubjectAndJump("break8", 8);
                    break;
                } else {
                    Toast.makeText(getContext(), R.string.no_break, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.break_9:
                if (breakNum >= 8) {
                    //可以进行闯关，页面跳转
                    getBreakSubjectAndJump("break9", 9);
                    break;
                } else {
                    Toast.makeText(getContext(), R.string.no_break, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.break_10:
                if (breakNum >= 9) {
                    //可以进行闯关，页面跳转
                    getBreakSubjectAndJump("break10", 10);
                    break;
                } else {
                    Toast.makeText(getContext(), R.string.no_break, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.break_11:
                if (breakNum >= 10) {
                    //可以进行闯关，页面跳转
                    getBreakSubjectAndJump("break11", 11);
                    break;
                } else {
                    Toast.makeText(getContext(), R.string.no_break, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.break_12:
                if (breakNum >= 11) {
                    //可以进行闯关，页面跳转
                    getBreakSubjectAndJump("break12", 12);
                    break;
                } else {
                    Toast.makeText(getContext(), R.string.no_break, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.break_13:
                if (breakNum >= 12) {
                    //可以进行闯关，页面跳转
                    getBreakSubjectAndJump("break13", 13);
                    break;
                } else {
                    Toast.makeText(getContext(), R.string.no_break, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.break_14:
                if (breakNum >= 13) {
                    //可以进行闯关，页面跳转
                    getBreakSubjectAndJump("break14", 14);
                    break;
                } else {
                    Toast.makeText(getContext(), R.string.no_break, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.break_15:
                if (breakNum >= 14) {
                    //可以进行闯关，页面跳转
                    getBreakSubjectAndJump("break15", 15);
                    break;
                } else {
                    Toast.makeText(getContext(), R.string.no_break, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.break_16:
                if (breakNum >= 15) {
                    //可以进行闯关，页面跳转
                    getBreakSubjectAndJump("break16", 16);
                    break;
                } else {
                    Toast.makeText(getContext(), R.string.no_break, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.break_17:
                if (breakNum >= 16) {
                    //可以进行闯关，页面跳转
                    getBreakSubjectAndJump("break17", 17);
                    break;
                } else {
                    Toast.makeText(getContext(), R.string.no_break, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.break_18:
                if (breakNum >= 17) {
                    //可以进行闯关，页面跳转
                    getBreakSubjectAndJump("break18", 18);
                    break;
                } else {
                    Toast.makeText(getContext(), R.string.no_break, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.break_19:
                if (breakNum >= 18) {
                    //可以进行闯关，页面跳转
                    getBreakSubjectAndJump("break19", 19);
                    break;
                } else {
                    Toast.makeText(getContext(), R.string.no_break, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.break_20:
                if (breakNum >= 19) {
                    //可以进行闯关，页面跳转
                    getBreakSubjectAndJump("break20", 20);
                    break;
                } else {
                    Toast.makeText(getContext(), R.string.no_break, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.reset_break:
                final MaterialDialog dialog = new MaterialDialog(getContext());
                dialog.setMessage(R.string.break_sure_reset).setPositiveButton(R.string.commit, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        SharedPreferences pre = getContext().getSharedPreferences(Constant.SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
                        int num = pre.getInt("breakNum", 0);
                        SharedPreferences.Editor editor = pre.edit();
                        //把相关数据都删除
                        editor.remove("breakNum");
                        for (int i = 1; i <= num; i++) {
                            String tag = "break" + i;
                            editor.remove(tag);
                        }
                        editor.commit();
                        //重置关卡图片
                        breakNum = 0; //置0
                        resetBreakImage();
                    }
                }).setNegativeButton(R.string.cancel, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
                break;
        }
    }

    public void getBreakSubjectAndJump(String subjectName, int currentNum) {
        //首先从本地获取题库，如果能获取到题库，就不随机生成题目，否则随机生成题库
        DataSaveUtil saveUtil = new DataSaveUtil(getContext());
        ArrayList<OperationModel> modelList = saveUtil.getBreakSubjectList(subjectName);
        if (modelList.size() <= 0) {
            //本地没保持到，需要随机生成
//            int countSum = 1 + currentNum / 2 * 5; // 题数
//            int max = 10 * (currentNum + 1) / 2; //最大数
            int countSum = 1; //测试用例
            int max = 2;
            int count = 0; //当前生成的题数
            while (count < countSum) {
                int operation = RandomNumberFactory.getRandomOperation();
                OperationModel model = RandomNumberFactory.getRandomModel(operation, max, Constant.BREAK_MODE);
                modelList.add(model);
                count++;
            }
        }
        Intent intent = new Intent(getActivity(), PracticeActivity.class);
        intent.putExtra("mode", Constant.BREAK_MODE);
        intent.putExtra("currentBreakNum", currentNum);
        intent.putParcelableArrayListExtra("break_subject", modelList);
        startActivity(intent);
        getActivity().finish();
    }

}
