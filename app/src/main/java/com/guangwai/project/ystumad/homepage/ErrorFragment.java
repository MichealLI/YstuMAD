package com.guangwai.project.ystumad.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.guangwai.project.ystumad.R;
import com.guangwai.project.ystumad.error.ErrorAdapter;
import com.guangwai.project.ystumad.error.ErrorData;

import java.util.ArrayList;

/**
 * 错题集的fragment
 * Created by Ming on 2018/3/14.
 */

public class ErrorFragment extends Fragment {

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_error, container, false);
        listView = (ListView)view.findViewById(R.id.error_listView);
        ErrorAdapter eradapter = new ErrorAdapter(this.getContext(), getData());
        listView.setAdapter(eradapter);
        return view;
    }

    private ArrayList<ErrorData> getData(){
        ArrayList<ErrorData> listitem = new ArrayList<>();
        listitem.add(new ErrorData("4+5", "[练习模式]", "2018-03-15 12:00"));
        listitem.add(new ErrorData("4+9", "[练习模式]", "2018-03-15 12:00"));
        listitem.add(new ErrorData("4-2", "[练习模式]", "2018-03-15 12:00"));
        listitem.add(new ErrorData("9-6", "[闯关模式]", "2018-03-15 11:00"));
        listitem.add(new ErrorData("1*7", "[闯关模式]", "2018-03-15 11:00"));
        listitem.add(new ErrorData("4*5", "[闯关模式]", "2018-03-15 11:00"));
        listitem.add(new ErrorData("6/2", "[闯关模式]", "2018-03-15 11:00"));
        listitem.add(new ErrorData("15-8", "[闯关模式]", "2018-03-15 11:00"));
        listitem.add(new ErrorData("8-1", "[闯关模式]", "2018-03-15 11:00"));
        listitem.add(new ErrorData("16/4", "[闯关模式]", "2018-03-15 11:00"));
        return listitem;
    }

    public static ErrorFragment newInstance() {

        ErrorFragment fragment = new ErrorFragment();
        return fragment;
    }
}
