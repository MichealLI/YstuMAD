package com.guangwai.project.ystumad.util;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Ming on 2018/3/15.
 */

public class OperationModel implements Parcelable {

    private int firstNum;
    private int secondNum;
    private int operation;
    private int resultNum;
    private int mode; //哪种模式下的
    private boolean isRight; //是否做对了
    private String date; //做题的日期
    private int id; //数据库中保存的id

    public int getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public int getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(int secondNum) {
        this.secondNum = secondNum;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public int getResultNum() {
        return resultNum;
    }

    public void setResultNum(int resultNum) {
        this.resultNum = resultNum;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(firstNum);
        parcel.writeInt(secondNum);
        parcel.writeInt(operation);
        parcel.writeInt(resultNum);
        parcel.writeInt(mode);
        parcel.writeByte((byte) (isRight ? 1 : 0));
        parcel.writeString(date);
        parcel.writeInt(id);
    }

    public static final Parcelable.Creator<OperationModel> CREATOR = new Creator() {


        @Override
        public OperationModel createFromParcel(Parcel parcel) {
            OperationModel model = new OperationModel();
            model.setFirstNum(parcel.readInt());
            model.setSecondNum(parcel.readInt());
            model.setOperation(parcel.readInt());
            model.setResultNum(parcel.readInt());
            model.setMode(parcel.readInt());
            model.setRight(parcel.readByte() != 0);
            model.setDate(parcel.readString());
            model.setId(parcel.readInt());
            return model;
        }

        @Override
        public OperationModel[] newArray(int size) {
            return new OperationModel[size];
        }
    };
}
