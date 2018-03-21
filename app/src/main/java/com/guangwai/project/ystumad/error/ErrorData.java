package com.guangwai.project.ystumad.error;

/**
 * Created by XiaoXue on 2018/3/21.
 */

public class ErrorData {
    private String error_text1, error_text2, error_text3;

    public ErrorData(String error_text1, String error_text2, String error_text3){
        super();
        this.error_text1 = error_text1;
        this.error_text2 = error_text2;
        this.error_text3 = error_text3;
    }

    public String getError_text1(){
        return error_text1;
    }
    public void setError_text1(String error_text1) {
        this.error_text1 = error_text1;
    }
    public String getError_text2(){
        return error_text2;
    }
    public void setError_text2(String error_text2) {
        this.error_text2 = error_text2;
    }
    public String getError_text3(){
        return error_text3;
    }
    public void setError_text3(String error_text3) {
        this.error_text3 = error_text3;
    }

}
