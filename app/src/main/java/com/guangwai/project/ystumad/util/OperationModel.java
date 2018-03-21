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

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(firstNum);
        parcel.writeInt(secondNum);
        parcel.writeInt(operation);
        parcel.writeInt(resultNum);
        parcel.writeInt(mode);
        parcel.writeByte((byte) (isRight ? 1 : 0));

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
            return model;
        }

        @Override
        public OperationModel[] newArray(int size) {
            return new OperationModel[size];
        }
    };
}
