package com.ljq;



import java.math.BigInteger;
import java.util.Stack;

/**
 * @author <liujianqiang@kuaishou.com>
 * Created on 2021-02-05
 */
public class Solution {
    public static void main(String[] args){
        Solution solution = new Solution();
        String aabc = solution.reverseWords("a good   example");
        System.out.println("  sad");
    }
        public boolean isUnique(String astr) {
            int[] ints = new int[256];
            for (int i = 0; i < astr.length(); i++) {
                if (ints[astr.charAt(i)]==0)
                    ints[astr.charAt(i)]=1;
                else
                    return false;
            }
            return true;
        }
    //输入：s = "We are happy."
    //输出："We%20are%20happy."
    public String replaceSpace(String s) {
        if (s.length()==0)
            return new String();
        int index =0;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)==' '){
                res.append(s.substring(index,i));
                res.append("%20");
                index=i+1;

            }
        }
        return res.append(s.substring(index,s.length())).toString();
    }
    //字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
    public String reverseLeftWords(String s, int n) {
        String substring = s.substring(0, n);
        String substring1 = s.substring(n, s.length());
        return substring1+substring;

    }
    //写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
    public int strToInt(String str) {
        char[] c = str.trim().toCharArray();
        if (c.length==0) return 0;
        int res =0 ,bndry = Integer.MAX_VALUE/10;
        int i=1,sign = 1;
        if (c[0]=='-') sign=-1;
        else if (c[0]!='+') i=0;
        for (int j = i; j < c.length; j++) {
            if (c[j]<'0'||c[j]>'9') break;
            if (res>bndry||res==bndry&&c[j]>'7') return sign==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
            res = res*10+(c[j]-'0');

        }
        return sign*res;


    }
    //输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"
    public String reverseWords(String s) {
        int i=0,j=s.length()-1;
        char[] sc = s.toCharArray();
        while (i<j){
            swap(sc,i,j);
            i++;
            j--;
        }
        i=0;j=0;
        while (j<s.length()){
            while (j<s.length()&&sc[j]!=' ') j++;
            reversechange(sc,i,j);
            j++;
            i=j;
        }
        String trim = new String(sc).trim();
        String[] s1 = trim.split(" ");
        String res = new String();
        for (int i1 = 0; i1 < s1.length; i1++) {
            if (s1[i1].length()!=0)
                res=res+s1[i1]+" ";
        }

        return res.trim();

    }

    private void reversechange(char[] sc, int i, int j) {
        int left=i,right=j-1;
        while (left<right){
            swap(sc,left,right);
            left++;right--;
        }
    }

    private void swap(char[] sc, int i, int j) {
        char c=sc[i];
        sc[i]=sc[j];
        sc[j]=c;
    }

    public boolean isDigit(char c){
        if (c>='0'&&c<='9')
            return true;
        return false;
    }
    //请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2
    // .3"、"+-5"及"12e+5.4"都不是。
//    public boolean isNumber(String s) {
//
//    }
}
