package com.guangwai.project.ystumad.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动管理类
 * Created by Ming on 2018/3/26.
 */

public class ActivityCollector {
    /**
     * 单例模式
     */
    private static ActivityCollector collector;

    private ActivityCollector() {

    }

    public static ActivityCollector getInstance() {
        if (collector == null) {
            synchronized (ActivityCollector.class) {
                if (collector == null) {
                    collector = new ActivityCollector();
                }
            }
        }
        return collector;
    }


    public static List<Activity> activities = new ArrayList<>();

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public void finishAll() {
        for (Activity activity : activities) {
            if (activity.isFinishing()) {
                activity.finish();
            }
        }
        System.exit(0);
    }
}
