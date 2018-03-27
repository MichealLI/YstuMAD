package com.guangwai.project.ystumad.util;

/**
 * 语音结果返回不准确的调整工具类
 * Created by Ming on 2018/3/27.
 */

public class AsrUtil {

    /**
     * 把返回的中文数字转为对应的阿拉伯数字
     */
    public static String convertChineseNumToNumber(String content) {

        if (content.contains("十一")) {
            content = content.replace("十一", "11");
        }
        if (content.contains("十二")) {
            content = content.replace("十二", "12");
        }
        if (content.contains("十三")) {
            content = content.replace("十三", "13");
        }
        if (content.contains("十四")) {
            content = content.replace("十四", "14");
        }
        if (content.contains("十五")) {
            content = content.replace("十五", "15");
        }
        if (content.contains("十六")) {
            content = content.replace("十六", "16");
        }
        if (content.contains("十七")) {
            content = content.replace("十七", "17");
        }
        if (content.contains("十八")) {
            content = content.replace("十八", "18");
        }
        if (content.contains("十九")) {
            content = content.replace("十九", "19");
        }
        if (content.contains("二十")) {
            content = content.replace("二十", "20");
        }
        if (content.contains("十")) {
            content = content.replace("十", "10");
        }

        if (content.contains("一")) {
            content = content.replace("一", "1");
        }
        if (content.contains("二")) {
            content = content.replace("二", "2");
        }
        if (content.contains("三")) {
            content = content.replace("三", "3");
        }
        if (content.contains("四")) {
            content = content.replace("四", "4");
        }
        if (content.contains("五")) {
            content = content.replace("五", "5");
        }
        if (content.contains("六")) {
            content = content.replace("六", "6");
        }
        if (content.contains("七")) {
            content = content.replace("七", "7");
        }
        if (content.contains("八")) {
            content = content.replace("八", "8");
        }
        if (content.contains("九")) {
            content = content.replace("九", "9");
        }
        return content;
    }

    /**
     * 把返回的读音相似的中文转为对应的阿拉伯数字
     *
     * @param content
     */
    public static String convertChineseToNumber(String content) {
        if (content.contains("阿")) {
            content = content.replace("阿", "2");
        }
        if (content.contains("啊")) {
            content = content.replace("啊", "2");
        }
        if (content.contains("r")) {
            content = content.replace("r", "2");
        }
        if (content.contains("石榴")) {
            content = content.replace("石榴", "16");
        }
        if (content.contains("石")) {
            content = content.replace("石", "10");
        }
        return content;
    }
}
