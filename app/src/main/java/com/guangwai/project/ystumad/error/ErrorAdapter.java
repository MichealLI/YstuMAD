package com.guangwai.project.ystumad.error;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import com.guangwai.project.ystumad.R;

/**
 * Created by XiaoXue on 2018/3/21.
 */

public class ErrorAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ErrorData> errorList = new ArrayList<ErrorData>(); //对应的错题数据
    private LayoutInflater mInflater;

    public ErrorAdapter(Context context, ArrayList<ErrorData> errorList){
        this.context = context;
        this.errorList = errorList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return errorList.size();
    }

    @Override
    public Object getItem(int position) {
        return errorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ErrorView holder;
        if(convertView == null){
            holder = new ErrorView();
            convertView = mInflater.inflate(R.layout.error_item, null);
            holder.error_text1 = (TextView)convertView.findViewById(R.id.error_textView1);
            holder.error_text2 = (TextView)convertView.findViewById(R.id.error_textView2);
            holder.error_text3 = (TextView)convertView.findViewById(R.id.error_textView3);
            convertView.setTag(holder);
        }else{
            holder = (ErrorView)convertView.getTag();
        }
        // 将控件要显示的内容对应数据
        holder.error_text1.setText(errorList.get(position).getError_text1());
        holder.error_text2.setText(errorList.get(position).getError_text2());
        holder.error_text3.setText(errorList.get(position).getError_text3());
        return convertView;
    }

    //创建类对应每个item的布局
    class ErrorView{
        protected TextView error_text1, error_text2, error_text3;
        public ErrorView(){
            super();
        }
        public ErrorView(TextView error_text1, TextView error_text2, TextView error_text3){
            this.error_text1 = error_text1;
            this.error_text2 = error_text2;
            this.error_text3 = error_text3;
        }
    }

}
