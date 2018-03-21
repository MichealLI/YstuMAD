package com.guangwai.project.ystumad.util;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 自定义固定长度的队列
 * Created by Ming on 2018/3/21.
 */

public class LimitQueueUtil {
    private int limit; //长度

    private Queue queue = null;

    public LimitQueueUtil(int limit, Queue queue) {
        this.limit = limit;
        this.queue = queue;
    }

    public boolean offer(int data) {
        if (queue.size() >= limit) {
            queue.poll();
        }
        return queue.offer(data);
    }
}

