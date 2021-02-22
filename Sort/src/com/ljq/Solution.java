package com.ljq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @author <liujianqiang@kuaishou.com>
 * Created on 2021-02-13
 */
public class Solution {
    public static void main(String args[]){
        Solution solution = new Solution();
        int[] leastNumbers = solution.getLeastNumbers(new int[] {1, 2, 3, 426, 7, 3, 4}, 4);
        System.out.println("dsa");
    }
    public int[] getLeastNumbers(int[] arr, int k) {
//        int[] ints = {};
//        PriorityQueue<Integer> integers = new PriorityQueue<>(((o1, o2) -> o2-o1));
//        for (int i : arr) {
//
//            integers.add(i);
//            if (integers.size()>k) integers.poll();
//
//        }
//        int[] ints1 = new int[integers.size()];
//        for (int i = 0; i < ints1.length; i++) {
//            ints1[i]=integers.poll();
//        }
//        return ints1;
        if (k >= arr.length) return arr;
        return quickSort(arr,k,0,arr.length-1);
    }
    private int[] quickSort(int[] a,int k,int l,int r){
        int i=l,j=r;
        while (i<j){
            while (i<j&&a[j]>=a[l]) j--;
            while (i<j&&a[i]<=a[l]) i++;
            swap(a,i,j);
        }
        swap(a,i,l);
        if (i>k) return quickSort(a,k,l,i-1);
        if (i<k) return quickSort(a,k,i+1,r);
        return Arrays.copyOf(a,k);
    }
    private void swap(int[] a,int i,int j){
        int t=a[i];
        a[i]=a[j];
        a[j]=t;
    }
    public String minNumber(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i]=String.valueOf(nums[i]);
            
        }
        minNumberquicksort(strings,0,strings.length-1);
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }
    void minNumberquicksort(String[] s, int l,int r){
        int i=l,j=r;
        String ss = s[i];
        while (i<j){
            while (i<j&&(s[j]+s[l]).compareTo(s[l]+s[j])>=0) j--;
            while (i<j&&(s[i]+s[l]).compareTo(s[l]+s[i])<=0) i++;
            String sss = s[i];
            s[i]=s[j];
            s[j]=sss;
        }
        s[i]=s[l];
        s[l]=ss;
        minNumberquicksort(s,l,i-1);
        minNumberquicksort(s,i+1,r);
    }
    public boolean isStraight(int[] nums) {
        int joker = 0;
        Arrays.sort(nums); // 数组排序
        for(int i = 0; i < 4; i++) {
            if(nums[i] == 0) joker++; // 统计大小王数量
            else if(nums[i] == nums[i + 1]) return false; // 若有重复，提前返回 false
        }
        return nums[4] - nums[joker] < 5; // 最大牌 - 最小牌 < 5 则可构成顺子
    }

}
