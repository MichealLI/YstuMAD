package com.guangwai.project.ystumad.error;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.guangwai.project.ystumad.R;
import com.guangwai.project.ystumad.exercise.ExerciseAnalysisAdapter;
import com.guangwai.project.ystumad.util.Constant;
import com.guangwai.project.ystumad.util.OperationModel;

/**
 * Created by XiaoXue on 2018/3/21.
 */

public class ErrorAdapter extends BaseAdapter {
    private Context context;
    private List<OperationModel> errorList; //对应的错题数据
    private LayoutInflater mInflater;

    public ErrorAdapter(Context context, List<OperationModel> errorList) {
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
        OperationModel model = errorList.get(position);
        ErrorView holder;
        if (convertView == null) {
            holder = new ErrorView();
            convertView = mInflater.inflate(R.layout.error_item, null);
            holder.error_text1 = convertView.findViewById(R.id.error_textView1);
            holder.error_text2 = convertView.findViewById(R.id.error_textView2);
            holder.error_text3 = convertView.findViewById(R.id.error_textView3);
            convertView.setTag(holder);
        } else {
            holder = (ErrorView) convertView.getTag();
        }
        // 将控件要显示的内容对应数据
        //题目
        String content = getContent(model);
        holder.error_text1.setText(content);

        //模式
        if (model.getMode() == Constant.BREAK_MODE) {
            holder.error_text2.setText("[闯关模式]");
        } else if (model.getMode() == Constant.PRATICE_MODE) {
            holder.error_text2.setText("[练习模式]");
        }

        //时间
        holder.error_text3.setText(model.getDate());
        return convertView;
    }

    //创建类对应每个item的布局
    class ErrorView {
        protected TextView error_text1, error_text2, error_text3;

    }

    /**
     * 拼接题目内容
     *
     * @param model
     * @return
     */
    private String getContent(OperationModel model) {
        StringBuilder sb = new StringBuilder();
        sb.append(model.getFirstNum());
        sb.append(" ");
        switch (model.getOperation()) {
            case Constant.OPERATION_ADD:
                sb.append("+");
                break;
            case Constant.OPERATION_REDUCE:
                sb.append("-");
                break;
            case Constant.OPERATION_MULTIPLY:
                sb.append("*");
                break;
            case Constant.OPERATION_DIVIDE:
                sb.append("/");
                break;
        }
        sb.append(" ");
        sb.append(model.getSecondNum());
        return sb.toString();
    }
}
