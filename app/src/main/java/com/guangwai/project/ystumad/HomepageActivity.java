package com.guangwai.project.ystumad;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomepageActivity extends Activity implements View.OnClickListener {
    private LinearLayout tab1; //底部导航栏
    private LinearLayout tab2;
    private LinearLayout tab3;
    private ImageView tab1Image;
    private ImageView tab2Image;
    private ImageView tab3Image;
    private TextView tab1Text;
    private TextView tab2Text;
    private TextView tab3Text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_homepage);
        initTabView();
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
        //初始化tab2
        tab2Text = tab2.findViewById(R.id.tab_text);
        tab2Text.setText(R.string.tabs_item_tab2);
        tab2Text.setTextColor(0xffCCCCCC);
        tab2Image = tab2.findViewById(R.id.tab_image);
        tab2Image.setImageDrawable(getResources().getDrawable(R.drawable.analyse_unselecet));
        //初始化tab3
        tab3Text = tab3.findViewById(R.id.tab_text);
        tab3Text.setText(R.string.tabs_item_tab3);
        tab3Text.setTextColor(0xffCCCCCC);
        tab3Image = tab3.findViewById(R.id.tab_image);
        tab3Image.setImageDrawable(getResources().getDrawable(R.drawable.error_unselect));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab1:
                initTab(); //初始化图标
                tab1Image.setImageDrawable(getResources().getDrawable(R.drawable.exercise));
                tab1Text.setTextColor(0xff9AC0CD);
                break;
            case R.id.tab2:
                initTab(); //初始化图标
                tab2Image.setImageDrawable(getResources().getDrawable(R.drawable.analyse_seleceted));
                tab2Text.setTextColor(0xff9AC0CD);
                break;
            case R.id.tab3:
                initTab(); //初始化图标
                tab3Image.setImageDrawable(getResources().getDrawable(R.drawable.error_selected));
                tab3Text.setTextColor(0xff9AC0CD);
                break;
            default:
                break;
        }
    }

    /**
     * 全部设置为未选中的图标和字体颜色
     */
    private void initTab() {
        tab1Image.setImageDrawable(getResources().getDrawable(R.drawable.exercise));
        tab2Image.setImageDrawable(getResources().getDrawable(R.drawable.analyse_unselecet));
        tab3Image.setImageDrawable(getResources().getDrawable(R.drawable.error_unselect));

        tab1Text.setTextColor(0xffCCCCCC);
        tab2Text.setTextColor(0xffCCCCCC);
        tab3Text.setTextColor(0xffCCCCCC);


    }
}
