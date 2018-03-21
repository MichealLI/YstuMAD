package com.guangwai.project.ystumad.exercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.guangwai.project.ystumad.R;
import com.guangwai.project.ystumad.util.Constant;
import com.guangwai.project.ystumad.util.OperationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目分析listView的适配器
 * Created by Ming on 2018/3/21.
 */

public class ExerciseAnalysisAdapter extends BaseAdapter {
    private List<OperationModel> mDatas;
    private Context mContext;
    private int mode;

    public ExerciseAnalysisAdapter(List<OperationModel> datas, Context context, int mode) {
        mContext = context;
        this.mode = mode;
        if (mode == Constant.ANALYSE_ALL) {
            mDatas = datas;
        } else if (mode == Constant.ANALYSE_ERROR) {
            mDatas = getTheErrorSuject(datas);
        }
    }

    /**
     * 获取答案错误的list
     *
     * @param datas
     * @return
     */
    private List<OperationModel> getTheErrorSuject(List<OperationModel> datas) {
        List<OperationModel> mDatas = new ArrayList<>();
        for (OperationModel model : datas) {
            if (!model.isRight()) {
                mDatas.add(model);
            }
        }
        return mDatas;
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OperationModel model = mDatas.get(position);
        ViewHolder holder;
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.error_analysis_item, null);
            holder = new ViewHolder();
            holder.content = view.findViewById(R.id.error_analysis_content);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        holder.content.setText(getContent(model));
        if (mode == Constant.ANALYSE_ALL && !model.isRight()) {
            holder.content.setTextColor(0xffEE0000);
        }
        return view;
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
        sb.append(" = ");
        sb.append(model.getResultNum());

        return sb.toString();
    }

    class ViewHolder {
        TextView content;
    }
}
