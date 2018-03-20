package com.guangwai.project.ystumad.exercise;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.google.gson.Gson;
import com.guangwai.project.ystumad.R;
import com.guangwai.project.ystumad.base.BaseActivity;
import com.guangwai.project.ystumad.util.Constant;
import com.guangwai.project.ystumad.util.OperationModel;
import com.guangwai.project.ystumad.util.RandomNumberFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * 做题的界面
 */

public class PracticeActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back;
    private LinearLayout mainContent;
    private TextView subjectContent;
    private TextView subjectResult;
    private ImageButton microphone;

    private TextView numOne;
    private TextView numTwo;
    private TextView numThree;
    private TextView numFour;
    private TextView numFive;
    private TextView numSix;
    private TextView numSeven;
    private TextView numEight;
    private TextView numNine;
    private TextView numZero;

    private TextView lastOne;
    private TextView nextOne;
    private TextView clear;

    private LinearLayout pause;
    private TextView page;

    private Chronometer practiceTimer;

    private Animation nextInAnimation;  //下一题的进入动画
    private Animation nextOutAnimation; //下一题的离开动画

    private Animation lastInAnimation;  //上一题的进入动画
    private Animation lastOutAnimation; //上一题的离开动画

    private static List<OperationModel> modelList; //保存题目数据
    private List<Integer> resultList; //保存用户输入的结果

    private static int currentIndex = 0; //当前所在的题数

    private int num; //题量

    private EventManager asr;

    private String arsContent; //语音输入的内容

    @SuppressLint("HandlerLeak")
    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.NEXT_ONE:
                    currentIndex++;
                    setSubjectContent(currentIndex);
                    setCurrentPage(currentIndex, num);
                    if (resultList.get(currentIndex) != -1) {
                        subjectResult.setText(resultList.get(currentIndex) + "");
                    } else {
                        //清空
                        subjectResult.setText("");
                    }

                    mainContent.startAnimation(nextInAnimation);
                    break;
                case Constant.LAST_ONE:
                    currentIndex--;
                    setSubjectContent(currentIndex);
                    setCurrentPage(currentIndex, num);
                    if (resultList.get(currentIndex) != -1) {
                        subjectResult.setText(resultList.get(currentIndex) + "");
                    } else {
                        //清空
                        subjectResult.setText("");
                    }
                    mainContent.startAnimation(lastInAnimation);
                    break;
                case Constant.ASR_END:
                    //把语音内容筛选数字
                    String numContent = getTheNumberFromAsr(arsContent);
                    if (numContent != null) {
                        if (numContent.length() > 3) {
                            subjectResult.setText(numContent.substring(numContent.length() - 3, numContent.length()));
                        } else {
                            subjectResult.setText(numContent);
                        }

                    }
                    break;
            }
        }
    };

    /**
     * 从语音中筛选数字出来
     *
     * @return
     */
    private String getTheNumberFromAsr(String arsContent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arsContent.length(); i++) {
            if (arsContent.charAt(i) >= 48 && arsContent.charAt(i) <= 57) {
                sb.append(arsContent.charAt(i));
            }
        }
        return sb.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        Intent intent = getIntent();
        num = intent.getIntExtra("practice_num", 10);
        int max = intent.getIntExtra("practice_max", 10);
        int mode = intent.getIntExtra("practice_mode", Constant.SINGLE_MODE);
        initData(num, mode, max); //根据题量、题型和范围，随机生成题目
        initView();
        initAsr();
        setSubjectContent(currentIndex);
        setCurrentPage(currentIndex, num);
        initAnimation();

        //计时器开始
        practiceTimer.setBase(SystemClock.elapsedRealtime());//计时器清零
        practiceTimer.start();
    }


    /**
     * 随机生成题目
     *
     * @param num
     * @param mode
     * @param max
     */
    private void initData(int num, int mode, int max) {
        modelList = new ArrayList<>();
        resultList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            //对其进行初始化，默认值-1
            resultList.add(-1);
        }
        int count = 0;
        int opera1 = Constant.OPERATION_ADD;
        int opera2 = Constant.OPERATION_REDUCE;
        if (mode == Constant.SINGLE_MODE) {
            opera1 = Constant.OPERATION_MULTIPLY;
            opera2 = Constant.OPERATION_DIVIDE;
        }
        while (count < num) {
            int operation = RandomNumberFactory.getRandomOperation();
            if (operation == opera1 || operation == opera2) {
                //符合当前模式
                OperationModel model = RandomNumberFactory.getRandomModel(operation, max, Constant.SINGLE_MODE);
                modelList.add(model);
                count++;
            }
        }
    }


    /**
     * 初始化View
     */
    private void initView() {
        back = findViewById(R.id.practice_back);
        mainContent = findViewById(R.id.main_content);
        subjectContent = findViewById(R.id.subject_content);
        subjectResult = findViewById(R.id.subject_result);
        microphone = findViewById(R.id.microphone);
        practiceTimer = findViewById(R.id.practice_timer);

        //0 - 9
        numOne = findViewById(R.id.num_one);
        numTwo = findViewById(R.id.num_two);
        numThree = findViewById(R.id.num_three);
        numFour = findViewById(R.id.num_four);
        numFive = findViewById(R.id.num_five);
        numSix = findViewById(R.id.num_six);
        numSeven = findViewById(R.id.num_seven);
        numEight = findViewById(R.id.num_eight);
        numNine = findViewById(R.id.num_nine);
        numZero = findViewById(R.id.num_zero);

        lastOne = findViewById(R.id.last_one);
        nextOne = findViewById(R.id.next_one);
        clear = findViewById(R.id.clear);

        pause = findViewById(R.id.pause);
        page = findViewById(R.id.page);

        nextOne.setOnClickListener(this);
        lastOne.setOnClickListener(this);
        clear.setOnClickListener(this);

        numOne.setOnClickListener(this);
        numTwo.setOnClickListener(this);
        numThree.setOnClickListener(this);
        numFour.setOnClickListener(this);
        numFive.setOnClickListener(this);
        numSix.setOnClickListener(this);
        numSeven.setOnClickListener(this);
        numEight.setOnClickListener(this);
        numNine.setOnClickListener(this);
        numZero.setOnClickListener(this);

        microphone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //开始识别
                        String json = "{\"accept-audio-data\":false,\"disable-punctuation\":false,\"accept-audio-volume\":true,\"pid\":1536}";
                        asr.send(SpeechConstant.ASR_START, json, null, 0, 0);
                        break;
                    case MotionEvent.ACTION_UP:
                        //结束识别过程
                        asr.send(SpeechConstant.ASR_STOP, null, null, 0, 0); // 发送停止录音事件，提前结束录音等待识别结果
                        break;
                    default:
                        break;
                }
                return false;
            }
        });


    }

    /**
     * 初始化语音
     */
    private void initAsr() {
        asr = EventManagerFactory.create(this, "asr");
        EventListener listener = new EventListener() {
            @Override
            public void onEvent(String name, String params, byte[] data, int offset, int length) {

                if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_READY)) {
                    // 引擎就绪，可以说话，一般在收到此事件后通过UI通知用户可以说话了
                    Toast.makeText(PracticeActivity.this, "用户可以说话了", Toast.LENGTH_SHORT).show();
                } else if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL)) {
                    // 识别结束
                    //解析json数据
                    Log.d("ming", params);
                    if (parseJsonFromAsr(params) != null && parseJsonFromAsr(params) != "") {
                        arsContent = parseJsonFromAsr(params);
                    }
                } else if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_FINISH)) {
                    uiHandler.sendEmptyMessage(Constant.ASR_END);
                }
                // ... 支持的输出事件和事件支持的事件参数见“输入和输出参数”一节
            }

        };
        asr.registerListener(listener);
    }

    /**
     * 解析百度语音返回的结果
     *
     * @param params
     * @return
     */
    private String parseJsonFromAsr(String params) {
        try {
            JSONObject object = new JSONObject(params);
            return object.getString("results_recognition");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 初始化动画
     */
    private void initAnimation() {
        //初始化动画
        nextInAnimation = AnimationUtils.loadAnimation(this, R.anim.subject_next_in_anim);
        nextOutAnimation = AnimationUtils.loadAnimation(this, R.anim.subject_next_out_anim);
        nextOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                uiHandler.sendEmptyMessage(Constant.NEXT_ONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        lastInAnimation = AnimationUtils.loadAnimation(this, R.anim.subject_last_in_anim);
        lastOutAnimation = AnimationUtils.loadAnimation(this, R.anim.subject_last_out_anim);
        lastOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                uiHandler.sendEmptyMessage(Constant.LAST_ONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_one:
                if (currentIndex < num - 1) {
                    //首先保存输入的结果
                    if (!TextUtils.isEmpty(subjectResult.getText())) {
                        int reuslt = Integer.parseInt(subjectResult.getText().toString());
                        resultList.set(currentIndex, reuslt);
                    }
                    mainContent.startAnimation(nextOutAnimation);
                } else {
                    //跳转到结果页面
                    //首先弹出是否提交答案的dialog
                    final MaterialDialog mDialog = new MaterialDialog(this).setMessage(R.string.the_last_page).setPositiveButton(R.string.commit, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //mDialog.dismiss();
                            //确认提交答案

                            SharedPreferences preferences = getSharedPreferences("MAD", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();

                            int subjectSum = preferences.getInt("subject_sum", 0);
                            subjectSum = subjectSum + num;      //记录做的总题数
                            editor.putInt("subject_sum", subjectSum);

                            int wrongNum = checkTheAnswer();
                            int wrongSum = preferences.getInt("subject_wrong_sum", 0);
                            wrongSum = wrongSum + wrongNum; //加上这次错的题数
                            editor.putInt("subject_wrong_sum", wrongSum);

                            editor.commit();

                            //进行页面跳转

                        }
                    }).setNegativeButton(R.string.cancel, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                    mDialog.show();


                }

                break;
            case R.id.last_one:
                if (currentIndex > 0) {
                    if (!TextUtils.isEmpty(subjectResult.getText())) {
                        int reuslt = Integer.parseInt(subjectResult.getText().toString());
                        resultList.set(currentIndex, reuslt);
                    }
                    mainContent.startAnimation(lastOutAnimation);
                } else {
                    Toast.makeText(this, R.string.the_first_page, Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.clear:
                if (!TextUtils.isEmpty(subjectResult.getText())) {
                    String content = subjectResult.getText().toString();
                    content = content.substring(0, content.length() - 1);
                    subjectResult.setText(content);
                }
                break;
            case R.id.pause:

                break;
            case R.id.num_one:
                addToReusltContent("1");
                break;
            case R.id.num_two:
                addToReusltContent("2");
                break;
            case R.id.num_three:
                addToReusltContent("3");
                break;
            case R.id.num_four:
                addToReusltContent("4");
                break;
            case R.id.num_five:
                addToReusltContent("5");
                break;
            case R.id.num_six:
                addToReusltContent("6");
                break;
            case R.id.num_seven:
                addToReusltContent("7");
                break;
            case R.id.num_eight:
                addToReusltContent("8");
                break;
            case R.id.num_nine:
                addToReusltContent("9");
                break;
            case R.id.num_zero:
                addToReusltContent("0");
                break;
        }
    }

    /**
     * 检查用户输入的答案是否正常，返回错误数量
     *
     * @return
     */
    private int checkTheAnswer() {
        int wrongCount = 0;
        for (int i = 0; i < num; i++) {
            OperationModel model = modelList.get(i);
            if (model.getResultNum() != resultList.get(i)) {
                wrongCount++;
                model.setRight(false);
            } else {
                model.setRight(true);
            }
        }
        return wrongCount;
    }

    /**
     * 把参数添加到结果的输入框中
     *
     * @param data
     */
    private void addToReusltContent(String data) {
        String content = subjectResult.getText().toString();
        if (content.length() <= 2) {
            content = content + data;
            subjectResult.setText(content);
        } else {
            Toast.makeText(this, R.string.max_num_number, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 设置当前题目的内容
     */
    private void setSubjectContent(int index) {
        OperationModel model = modelList.get(index);
        int firstNum = model.getFirstNum();
        int secondNum = model.getSecondNum();
        int operation = model.getOperation();
        String opera = "";
        switch (operation) {
            case Constant.OPERATION_ADD:
                opera = "+";
                break;
            case Constant.OPERATION_REDUCE:
                opera = "-";
                break;
            case Constant.OPERATION_MULTIPLY:
                opera = "*";
                break;
            case Constant.OPERATION_DIVIDE:
                opera = "/";
                break;
        }
        String content = firstNum + " " + opera + " " + secondNum + " = ";
        subjectContent.setText(content);
    }

    /**
     * 设置当前所在的题数
     */
    private void setCurrentPage(int currentIndex, int num) {
        int index = currentIndex + 1; //实际的页数从1开始
        page.setText(index + "/" + num);
    }

}
