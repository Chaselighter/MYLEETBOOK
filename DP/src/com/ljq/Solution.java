package com.ljq;

import java.util.Arrays;

/**
 * @author <liujianqiang@kuaishou.com>
 * Created on 2021-02-01
 */
public class Solution {
    public static void main(String[] args)
    {
        Solution solution = new Solution();
        double maxAverage = solution.findMaxAverage(new int[] {1, 12, -5, -6, 50, 3}, 4);
        System.out.println("  ");
    }
    //正则表达式匹配
    public boolean isMatch(String s, String p) {
        int m = s.length()+1;
        int n = p.length()+1;
        boolean[][] dp = new boolean[m][n];
        dp[0][0]=true;
        for (int i = 2; i < n; i+=2) {
            dp[0][i]=dp[0][i-2]&&p.charAt(i-1)=='*';
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j]=p.charAt(j-1)=='*'?dp[i][j-2]||dp[i][j-1]||dp[i-1][j]&&(p.charAt(j-2)=='.'||s.charAt(i-1)==p.charAt(j-2))
            :dp[i-1][j-1]&&(p.charAt(j-1)==s.charAt(i-1)||p.charAt(j-1)=='.');
            }
            
        }
        return dp[m-1][n-1];
        

    }
    //礼物的最大价值
    public static int maxValue(int[][] grid) {
        if (grid.length==0)
            return 0;
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0]=grid[0][0];
        for (int i = 1; i < grid[0].length; i++) {

            dp[0][i]=dp[0][i-1]+grid[0][i];

        }
        for (int i = 1; i < grid.length; i++) {

            dp[i][0]=dp[i-1][0]+grid[i][0];

        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1])+grid[i][j];

            }

        }
        return dp[grid.length-1][grid[0].length-1];
    }
    //翻译数字为字符串
    public static int translateNum(int num) {

        String s = String.valueOf(num);
        //char[] chars = s.toCharArray();
        //int[] dp = new int[chars.length];
        int a=1,b=1;
        for (int i = 2; i <= s.length(); i++) {
            String temp = s.substring(i - 2, i);
            int c= temp.compareTo("10")>=0&&temp.compareTo("25")<=0?a+b:a;
            b=a;
            a=c;

        }
        return a;
    }
    //最大连续字数组
    public static int maxSubArray(int[] nums) {
        if (nums.length==0)
            return 0;
        int[] dp = new int[nums.length];
        int res=nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < dp.length; i++) {
            if (dp[i-1]<=0){
                dp[i]=nums[i];
            }else{
                dp[i]=dp[i-1]+nums[i];
            }
            res=Math.max(dp[i],res);


        }
        return res;
    }
    //股票最大利润
    //买卖股票的最佳时机
    public int maxProfit2(int[] prices){
        if (prices.length==0)
            return 0;
        int res=0;
        for (int i = 0; i < prices.length; i++) {

            if (prices[i]>prices[i-1]){
                res += prices[i]-prices[i-1];
            }

        }
        return res;
    }
    public static int maxProfit(int[] prices) {
        if (prices.length==0)
            return 0;
        //int[] dp = new int[prices.length];
        //dp[0]=0;
        int res=0;
        int cost=prices[0];
        for (int i = 1; i < prices.length; i++) {
            cost=Math.min(cost,prices[i]);
            res=Math.max(res,prices[i]-cost);
        }
        return res;
    }
    //最大回文子串
    public String longestPalindrome(String s) {
        if (s.length()==0||s.length()==1)
            return s;
        int maxlen=1;
        int start=0;
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i]=1;
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <s.length() ; j++) {
                if (ishwc(s.substring(i,j+1))){
                    dp[i][j]=j-i+1;
                    if (dp[i][j]>maxlen){
                        maxlen=dp[i][j];
                        start=i;
                    }


                }
            }

        }
        return s.substring(start,start+maxlen);
    }
    public String longestPalindromedp(String s) {
        if (s.length()==0||s.length()==1)
            return s;
        int maxlen=1;
        int start=0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i]=true;
        }
        for (int j = 1; j < s.length(); j++) {
            for (int i = 0; i <j ; i++) {
                if (s.charAt(j)!=s.charAt(i)){
                    dp[i][j]=false;
                }else {
                    if (j-i<3){
                        dp[i][j]=true;
                    }else {
                        dp[i][j]=dp[i+1][j-1];
                    }
                }
                if (dp[i][j]&&j-i+1>maxlen){
                    start=i;
                    maxlen=j-i+1;
                }
            }

        }
        return s.substring(start,start+maxlen);
    }
    public boolean ishwc(String s){
        if (s.length()==1||s.length()==0) return true;
        int left=0,right=s.length()-1;
        while (left<right){
            if (s.charAt(left)!=s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public double findMaxAverage(int[] nums, int k) {
        if (k==0||nums.length==0)
            return 0;
        if (nums.length<k){
            return Arrays.stream(nums).sum();
        }
        int start=0;
        int end=k-1;
        double sum=0;
        double min=Double.MIN_EXPONENT;
        for (int i = 0; i < k; i++) {
            sum+=nums[i];

        }
        min=sum;
        while(end< nums.length-1){
            sum=sum+nums[end+1]-nums[start];
            min=Math.max(min,sum);
            start++;end++;


        }
        return min/k;
    }



}
