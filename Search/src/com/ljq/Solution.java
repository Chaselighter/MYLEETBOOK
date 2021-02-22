package com.ljq;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author <liujianqiang@kuaishou.com>
 * Created on 2021-02-12
 */
public class Solution {
    public static void main(String args[]){
        Solution solution = new Solution();
        int search = solution.missingNumber(new int[] {0,1,3});
        System.out.println("ssss");
    }
    public int findRepeatNumber(int[] nums) {
        //哈希表
//        HashSet<Integer> integers = new HashSet<>();
//        for (int num : nums) {
//            if (integers.contains(num)) return num;
//            integers.add(num);
//        }
//        return -1;
        //原地交换利用条件啊
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]==i)
                continue;
            if (nums[nums[i]]==nums[i])
                return nums[i];

            int t = nums[i];
            nums[i]=nums[t];
            nums[t]=t;
        }
        return -1;
    }
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length==0)
            return false;
        int i=0,j=matrix[0].length-1;
        while (i>=0&&i<=matrix.length-1&&j>=0&&j<=matrix[0].length-1){
            if (matrix[i][j]>target){
                j--;

            }else if (matrix[i][j]<target){
                i++;
            }else{
                return true;
            }

        }
        return false;
    }
    public int minArray(int[] numbers) {
        if (numbers.length==1)
            return numbers[0];
        if (numbers.length==0){
            return -1;
        }
        return BinarySearch(numbers);
    }
    public int BinarySearch(int[] a){
        int l=0,r=a.length-1;
        while (l<r){
            int mid = l+(r-l)/2;
            if (a[mid]>=a[r]){
                l=mid+1;
            }else if (a[mid]<a[r]){
                r=mid;
            }
        }
        return a[l];
    }
    public char firstUniqChar(String s) {
        HashMap<Character,Boolean> characters = new HashMap<>();
        char[] sc = s.toCharArray();
        for (char c : sc) {
            characters.put(c,!characters.containsKey(c));
        }
        for (char c : sc) {
            if (characters.get(c)) return  c;
        }
        return ' ';

    }
    public int search(int[] nums, int target) {
        return searchRight(nums,target)-searchRight(nums,target-1);
    }
    //搜索左边界
    private int  searchLeft(int[] nums, int target) {
        int l=0,r=nums.length-1;
        while (l<=r){
            int m = l+(r-l)/2;
            if (nums[m]<target){
                l=m+1;
            }else if (nums[m]>=target){
                r=m-1;
            }
        }
        return r;
    }
    //搜索右边界
    private int  searchRight(int[] nums, int target) {
        int l=0,r=nums.length-1;
        while (l<=r){
            int mid = l+(r-l)/2;
            if (nums[mid]<=target) l=mid+1;
            else r=mid-1;
        }
        return l;
    }
    public int missingNumber(int[] nums) {
        int l=0,r=nums.length-1;
        while (l<=r){
            int m = l+(r-l)/2;
            if (nums[m]==m){
                l=m+1;
            }else if (nums[m]>m){
                r=m-1;
            }
        }
        return r+1;
    }

}
