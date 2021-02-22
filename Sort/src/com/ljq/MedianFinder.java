package com.ljq;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author <liujianqiang@kuaishou.com>
 * Created on 2021-02-13
 */
public class MedianFinder {
    /** initialize your data structure here. */
    Queue<Integer> A,B;
    public MedianFinder() {
        A =new PriorityQueue<>();
        A =new PriorityQueue<>((x,y)->(y-x));

    }

    public void addNum(int num) {
        if (A.size()!=B.size()){
            A.add(num);
            B.add(A.poll());
        }else {
            B.add(num);
            A.add(B.poll());
        }
    }

    public double findMedian() {
        return A.size()==B.size()?A.peek():(A.peek()+B.peek())/2.0;
    }
}
