package com.ljq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * @author <liujianqiang@kuaishou.com>
 * Created on 2021-02-02
 */

public class Solution {
    public static void main(String[] args){
        char[][] board = {{'O','X','X','O','X'},{'X','O','O','X','O'},{'X','O','X','O','X'},{'O','X','O','O','O'},{'X','X','O','X','O'},};
//        String[]words = {"oath","pea","eat","rain"};
//        char[][] board = {{'a','a'},{'a','a'}};
//        String[]words = {"aaaaa"};

//                char[][] board =
//                        {
//                                {'A', 'B', 'C', 'E'},
//                                {'S', 'F', 'C', 'S'},
//                                {'A', 'D', 'E', 'E'}
//                        };
//
//                String word = "ABCCED";
//        TreeNode n1 = new TreeNode(1);
//        TreeNode n2 = new TreeNode(2);
//        TreeNode n3 = new TreeNode(3);
//        TreeNode n4 = new TreeNode(4);
//        TreeNode n5 = new TreeNode(5);
//        TreeNode n6 = new TreeNode(6);
//        n1.left=n2;n1.right=n3;n2.left=n4;n3.right=n5;n4.left=n6;
        Solution solution = new Solution();
        solution.checkPossibility(new int[]{3,4,2,3});
        System.out.println("treeserialize");

    }
    /*
    * 单词搜索比较模版的写法soluiton1
    *
    * */
    private boolean[][] marked;
    private int[][] direction = {{-1,0},{1,0},{0,1},{0,-1}};
    private int m;
    private int n;
    private String word;
    private char[][] board;
    public  boolean exist2(char[][] board, String word) {
        this.word=word;
        this.board=board;
        m=board.length;
        n=board[0].length;
        marked=new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (existdfs2(i,j,0))
                    return true;
                
            }
        }
        return false;
    }
    boolean existdfs2(int i,int j,int start){
        if (start==word.length()-1)
            return board[i][j]==word.charAt(start);
        if (board[i][j]==word.charAt(start)){
            marked[i][j]=true;
            for (int k = 0; k < 4; k++) {
                int newx=i+direction[k][0];
                int newy=j+direction[k][1];
                if (inArea(newx,newy)&&!marked[newx][newy]){
                    if (existdfs2(newx,newy,start+1))
                        return true;
                }
            }
            marked[i][j]=false;
        }
        return false;
    }
    private boolean inArea(int x, int y) {
        return x >= 0 && x < findWordsm && y >= 0 && y < findWordsn;
    }
    /*单词搜索写法soluiton2
     *
     *
     * */
    public static boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existdfs(board,words,i,j,0)) return true;
            }
        }
        return false;

    }
    static boolean existdfs(char[][]board,char[]word,int i,int j,int k){
        if (i>=board.length||i<0||j>=board[0].length||j<0||board[i][j]!=word[k]) return false;
        if (k==word.length-1) return true;
        board[i][j]='\0';
        boolean res = existdfs(board,word,i+1,j,k+1)||existdfs(board,word,i,j,k+1)||existdfs(board,word,i-1,j,k+1)
                ||existdfs(board,word,i,j+1,k+1)||existdfs(board,word,i,j-1,k+1);
        board[i][j]=word[k];
        return res;
    }

    /*
     * 机器人的运动范围
     *
     * */
    private boolean[][] movingCountvisit;
    private int mm;
    private int nn;
    private int count=0;

    public int movingCount(int m, int n, int k) {
        if (k==0)
            return 1;
        this.mm=m;
        this.nn=n;
        movingCountvisit=new boolean[m][n];
        return movingCountdfs(0,0,k);
    }
    public int movingCountdfs(int m,int n,int k){
        if (!inAreamovingcount(m,n)||movingCountvisit[m][n]||movingCounthelper(m)+movingCounthelper(n)>k){
            return 0;
        }
        movingCountvisit[m][n]=true;
        return 1+movingCountdfs(m+1,n,k)+movingCountdfs(m,n+1,k);
    }
    private boolean inAreamovingcount(int x, int y) {
        return x >= 0 && x < mm && y >= 0 && y < nn;
    }
    public int movingCounthelper(int m){
        int a=0;
        while(m!=0){
            a+=m%10;
            m=m/10;
        }
        return a;

    }
    //字符串全排列
//    输入：s = "abc"
//    输出：["abc","acb","bac","bca","cab","cba"]
    char[] c;
    List<String> res = new LinkedList<>();
    public String[] permutation(String s) {
        c = s.toCharArray();
        permutationdfs(0);
        return res.toArray(new String[res.size()]);
    }
    public void permutationdfs(int x){
        if (x== c.length-1){
            res.add(String.valueOf(c));
            return;
        }
        HashSet<Object> set = new HashSet<>();
        for (int i = x; i < c.length; i++) {
            if (set.contains(c[i])) continue;
            set.add(c[i]);
            swap(i,x);
            permutationdfs(x+1);
            swap(i,x);
        }
    }
    public void swap(int a,int b){
        char temp=c[a];
        c[a]=c[b];
        c[b]=temp;
    }
    //层序遍历
    public List<List<Integer>> bfs(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if (root==null)
            return res;

        LinkedList<TreeNode> s = new LinkedList<>();
        s.add(root);
        while(!s.isEmpty()){
            int size = s.size();
            List<Integer> integers = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode pop = s.pop();
                integers.add(pop.val);
                if (pop.left!=null) s.add(pop.left);
                if (pop.right!=null) s.add(pop.right);
            }
            res.add(integers);
        }
        return res;
    }
    //数的序列化
    public String Treeserialize(TreeNode root) {
        if (root==null)
            return "[]";
        StringBuilder res = new StringBuilder("[");
        LinkedList<TreeNode> q = new LinkedList<>() {{
            add(root);
        }};
        while (!q.isEmpty()){
            TreeNode poll = q.poll();
            if (poll!=null){
                res.append(poll.val+",");
                q.add(poll.left);
                q.add(poll.right);

            }else
                res.append("null,");

        }
        res.deleteCharAt(res.length()-1);
        res.append("]");
        return res.toString();


    }
    public TreeNode Treedeserialize(String data){
        if (data.equals("[]")) return null;
        String[] vals = data.substring(1, data.length() - 1).split(",");
        LinkedList<TreeNode> q = new LinkedList<>();
        TreeNode node = new TreeNode(Integer.parseInt(vals[0]));
        q.add(node);
        int i=1;
        while(!q.isEmpty()){
            TreeNode poll = q.poll();
            if (!vals[i].equals("null")){
                poll.left=new TreeNode(Integer.parseInt(vals[i]));
                q.add(poll.left);

            }
            i++;
            if (!vals[i].equals("null")){
                poll.right=new TreeNode(Integer.parseInt(vals[i]));
                q.add(poll.right);

            }
            i++;


        }
        return node;

    }
    //搜索单词
    List<String>findWordsres =  new ArrayList<String>();
    private boolean[][] findWordsvisited;
    private char[][] findWordsboard;
    char[] findWordswords;
    int findWordsm;
    int findWordsn;
    public List<String> findWords(char[][] board, String[] words) {
        findWordsm=board.length;
        findWordsn=board[0].length;
        findWordsboard=board;
        findWordsvisited=new boolean[board.length][board[0].length];
        if (board.length==0||words.length==0)
            return findWordsres;
        for (String word : words) {
            findWordswords=word.toCharArray();

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    StringBuilder tempres = new StringBuilder("");
                    findWordsdfs(i,j,0,tempres);
                }
            }
        }
        return findWordsres;

    }

    private void findWordsdfs(int i, int j,int start,StringBuilder tempres) {
        if (i==findWordsm||j==findWordsn||start==findWordswords.length) return;
        if (findWordsboard[i][j]==findWordswords[start]){
            findWordsvisited[i][j]=true;
            tempres.append(findWordswords[start]);
            if (String.valueOf(findWordswords).equals(tempres.toString())){
                if (!findWordsres.contains(tempres.toString()))
                    findWordsres.add(tempres.toString());
            }
            for (int k = 0; k < 4; k++) {
                int newx = i+direction[k][0];
                int newy = j+direction[k][1];
                if (inArea(newx,newy)&&!findWordsvisited[newx][newy]){
                    findWordsdfs(newx,newy,start+1,tempres);
                }
            }
            findWordsvisited[i][j]=false;
            tempres.deleteCharAt(tempres.length()-1);
        }else
            return;


    }
    //130. 被围绕的区域
//    X X X X
//    X O O X
//    X X O X
//    X O X X
    //运行你的函数后，矩阵变为：
//    X X X X
//    X X X X
//    X X X X
//    X O X X
    private boolean[][] solvevisited;
    private boolean flag;
    private void solve(char[][] board) {
        if (board.length==0) return;
        //solvevisited=new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((i==0||j==0||i==board.length-1||j==board[0].length-1)&&board[i][j]=='O')
                    solvedfs(i,j,board);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j]=='O') board[i][j]='X';
                if (board[i][j]=='#') board[i][j]='O';
            }
        }
    }
    public void solvedfs(int i, int j,char[][] board) {

        if (i < 0 || j < 0 || i >= board.length  || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '#')
            return;
        board[i][j]='#';
        solvedfs( i - 1, j,board); // 上
        solvedfs( i + 1, j,board); // 下
        solvedfs( i, j - 1,board); // 左
        solvedfs( i, j + 1,board); // 右
    }
    //200. 岛屿数量
    //给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    private int numIslandscount=0;
    public int numIslands(char[][] grid) {
        if (grid.length==0)
            return 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]=='1'){
                    numIslandsdfs(i,j,grid);
                    numIslandscount++;
                }


            }
        }
        return numIslandscount;
    }

    public void numIslandsdfs(int i, int j, char[][] grid) {
        if (i<0||j<0||i>=grid.length||j>=grid[0].length||grid[i][j]=='0')
            return;
        grid[i][j]='0';
        numIslandsdfs(i+1,j,grid);
        numIslandsdfs(i,j+1,grid);
        numIslandsdfs(i-1,j,grid);
        numIslandsdfs(i,j-1,grid);
//        grid[i][j]='1';

    }
    //给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
    //
    //返回 s 所有可能的分割方案。
//    输入: "aab"
//    输出:
//            [
//            ["aa","b"],
//            ["a","a","b"]
//            ]
    public List<List<String>> partition(String s) {
        int n=s.length();
        List<List<String>> res = new ArrayList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i]=true;
        }
        for (int j = 1; j < s.length(); j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i)!=s.charAt(j)){
                    dp[i][j]=false;
                }else{
                    if (j-i<3){
                        dp[i][j]=true;
                    }else {
                        dp[i][j]=dp[i+1][j-1];
                    }
                }
            }
        }
        partitiondfs(res,dp,0,n,s,new ArrayList<String>());
        return res;
    }

    public void partitiondfs( List<List<String>> res, boolean[][] dp, int i, int n, String s, ArrayList<String> tmp) {
        if (i==n) res.add(new ArrayList<>(tmp));
        for (int j = i; j < n; j++) {
            if (dp[i][j]){
                tmp.add(s.substring(i,j+1));
                partitiondfs(res,dp,j+1,n,s,tmp);
                tmp.remove(tmp.size()-1);
            }
            
        }

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
    //最少分割次数
    private int mincut=Integer.MAX_VALUE;
    private int minCutres=0;
    public int minCut(String s) {
        if (s.length()==0||s.length()==1)
            return 0;
        int n = s.length();
        HashMap<Integer, Integer> map = new HashMap<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i]=true;
        }
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i)!=s.charAt(j)){
                    dp[i][j]=false;
                }else {
                    if (j-i<3){
                        dp[i][j]=true;
                    }else {
                        dp[i][j]=dp[i+1][j-1];
                    }
                }
            }
        }
        minCutdfs(s,0,n,dp,map,0);
        return mincut;

    }

    private void minCutdfs(String s, int i, int n,boolean[][] dp,HashMap<Integer, Integer> map,int num) {
        if (map.containsKey(i)) {
            mincut = Math.min(mincut, num + map.get(i));
            return;
        }
        if (dp[i][n-1]){
            mincut=Math.min(mincut,minCutres);
        }
        for (int j = i; j < s.length()-1; j++) {
            if (dp[i][j]){
                minCutres++;
                minCutdfs(s,j+1,n,dp,map,num+1);
                minCutres--;
            }

        }
        if (mincut > num) {
            map.put(i, mincut - num);
        }


    }
    public int numDecodings(String s) {
        if (s.charAt(0)=='0')
            return 0;
        int pre =1,cur=1;
        for (int i = 1; i < s.length(); i++) {
            int tem=cur;
            if (s.charAt(i)=='0'){
                if (s.charAt(i-1)=='1'||s.charAt(i-1)=='2'){
                    cur=pre;
                }else{
                    return 0;
                }
            }else if (s.charAt(i-1)=='1'||(s.charAt(i-1)=='2'&&Integer.parseInt(String.valueOf(s.charAt(i)))>=1&&Integer.parseInt(String.valueOf(s.charAt(i)))<=6)){
                cur=cur+pre;
            }
            pre=tem;


        }
        return cur;
    }
    HashSet<String> perres = new HashSet<String>();
    public String[] permutation2(String S) {
        if (S.length()==0)
            return perres.toArray(new String[]{});
        char[] sc = S.toCharArray();
        permutation2dfs(sc,0);
        return perres.toArray(new String[]{});

    }

    public void permutation2dfs(char[] sc, int j) {
        if (j==sc.length-1){
            perres.add(new String(sc));
            return;
        }

        for (int i = j; i < sc.length; i++) {
            swap(i,j,sc);
            permutation2dfs(sc,j+1);
            swap(i,j,sc);
        }
    }

    private void swap(int i, int j, char[] sc) {
        char t=sc[i];
        sc[i]=sc[j];
        sc[j]=t;

    }
    //幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
    //
    //说明：解集不能包含重复的子集。
    List<List<Integer>> subsetsres =  new ArrayList<List<Integer>>();
    ArrayList<Integer> integers = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        if (nums.length==0)
            return subsetsres;
        subsetdfs(0,nums);
        subsetsres.add(new ArrayList<>());
        return subsetsres;
    }

    private void subsetdfs(int i,int[] nums) {
        if (i==nums.length){
            return;
        }


        for (int j = i; j < nums.length; j++) {
            integers.add(nums[j]);
            subsetsres.add(new ArrayList<>(integers));
            subsetdfs(j+1,nums);
            integers.remove(integers.size()-1);
        }
    }
    List<String> generateParenthesisres = new ArrayList<>();
    StringBuilder genStr = new StringBuilder();
    int left=0;
    int right=0;
    public List<String> generateParenthesis(int n) {
        if(n==0) return generateParenthesisres;
        String temp = new String("((()))");
        generateParenthesisdfs(n);
        return generateParenthesisres;
    }

    private void generateParenthesisdfs(int n) {
        if (right>left)
            return;

        if (left==n&&right==left){
            generateParenthesisres.add(new String(genStr));
        }
        if (left+1<=n){
            left++;
            genStr.append("(");
            generateParenthesisdfs(n);
            genStr.deleteCharAt(genStr.length()-1);
            left--;
        }
        if (right+1<=n){
            right++;
            genStr.append(")");
            generateParenthesisdfs(n);
            genStr.deleteCharAt(genStr.length()-1);
            right--;
        }
    }
    //给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
    //
    //说明：解集不能包含重复的子集。
    List<List<Integer>> subsetsWithDupres = new ArrayList<>();
    List<Integer> subsetsWithDuptemp = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length==0)
            return subsetsWithDupres;

        Arrays.sort(nums);
        subsetsWithDupdfs(0,nums);
        return subsetsWithDupres;
    }


    private void subsetsWithDupdfs(int i, int[] nums) {
        if (i==nums.length)
            return;
        HashSet<Integer> set = new HashSet<>();
        for (int j = i; j < nums.length; j++) {


            if (set.contains(nums[j]))
                continue;
            subsetsWithDuptemp.add(nums[j]);
            set.add(nums[j]);
            subsetsWithDupres.add(new ArrayList<>(subsetsWithDuptemp));
            subsetsWithDupdfs(j+1,nums);
            subsetsWithDuptemp.remove(subsetsWithDuptemp.size()-1);

        }
    }

    HashSet<String> res33 = new HashSet<>();
    StringBuilder ss = new StringBuilder();
    public String[] permutation3(String S) {
        if(S.length()==0)
            return res33.toArray(new String[S.length()]);
        char[] chars = S.toCharArray();
        permutationdfs(0,chars);
        return res33.toArray(new String[]{});
    }
    public void permutationdfs(int i,char[] s){
        if(i==s.length)
            return;
        HashSet<Character> objects = new HashSet<>();
        for(int j = i; j< s.length; j++){
            if (objects.contains(s[j]))
                continue;
            if (i!=j)
                swap(i,j,s);
            res33.add(new String(s));

            permutationdfs(i+1,s);
            if (i!=j)
                swap(i,j,s);
        }
    }
    List<List<Integer>> pathWithObstaclesres = new ArrayList<>();
    List<Integer> temp  = new ArrayList<>();
    boolean isarrive=false;
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length==0)
            return pathWithObstaclesres;
        ArrayList<Integer> ii = new ArrayList<>();
        ii.add(0);
        ii.add(0);
        pathWithObstaclesres.add(ii);
        pathWithObstaclesdfs(obstacleGrid,0,0);
        if (!isarrive)
            return new ArrayList<>();
        return pathWithObstaclesres;
    }

    private void pathWithObstaclesdfs(int[][] obstacleGrid,int i,int j) {
        if (i==obstacleGrid.length-1&&j==obstacleGrid[0].length-1&&obstacleGrid[i][j]==0){
//            ArrayList<Integer> integers = new ArrayList<>();
//            integers.add(i);
//            integers.add(j);
//            pathWithObstaclesres.add(integers);
            isarrive=true;
            return;
        }

        if (!isarrive){
            if (i+1>=0&&j>=0&&i+1<obstacleGrid.length&&j<obstacleGrid[0].length&&obstacleGrid[i][j]==0){
                ArrayList<Integer> objects = new ArrayList<>();
                objects.add(i+1);
                objects.add(j);
                pathWithObstaclesres.add(objects);
                pathWithObstaclesdfs(obstacleGrid,i+1,j);
                if (!isarrive)
                    pathWithObstaclesres.remove(pathWithObstaclesres.size()-1);
            }

            if (i>=0&&j+1>=0&&i<obstacleGrid.length&&j+1<obstacleGrid[0].length&&!isarrive&&obstacleGrid[i][j]==0){
                ArrayList<Integer> objects = new ArrayList<>();
                objects.add(i);
                objects.add(j+1);
                pathWithObstaclesres.add(objects);
                pathWithObstaclesdfs(obstacleGrid,i,j+1);
                if (!isarrive)
                    pathWithObstaclesres.remove(pathWithObstaclesres.size()-1);
            }

        }
        return;

    }
    List<Integer> splitintoFobnaccires= new ArrayList<>();
    StringBuilder Fobotemp = new StringBuilder();
    public List<Integer> splitintoFobnacci(String s){
        if (s.length()==0)
            return splitintoFobnaccires;
        splitintoFobnaccidfs(0,s);

        return splitintoFobnaccires;
    }

    private void splitintoFobnaccidfs(int i, String s) {
        if (i==s.length())
            return;

        for (int j = i; j < s.length(); j++) {
            Fobotemp.append(s.charAt(j));
            if (splitintoFobnaccires.size()==0||splitintoFobnaccires.size()==1)
                splitintoFobnaccires.add(Integer.parseInt(Fobotemp.toString()));
            if (splitintoFobnaccires.get(splitintoFobnaccires.size()-1)+splitintoFobnaccires.get(splitintoFobnaccires.size()-2)==Integer.parseInt(Fobotemp.toString()))
                splitintoFobnaccires.add(Integer.parseInt(Fobotemp.toString()));
            splitintoFobnaccidfs(j+1,s);
            Fobotemp.deleteCharAt(Fobotemp.length()-1);

        }
    }
//    List<List<Integer>> qplres = new ArrayList<>();
//    ArrayList<Integer> integersqpl = new ArrayList<>();
//    public List<List<Integer>> qpl(int[] nums){
//        if (nums.length==0)
//            return qplres;
//        qpldfs(0,nums);
//
//    }
//    public void swap(int a,int b,int[]nums){
//        int temo = nums[a];
//        nums[a]=nums[b];
//        nums[b]=temo;
//    }
//    private void qpldfs(int i, int[] nums) {
//        if (i==nums.length)
//            return;
//        for (int j = i; j < nums.length; j++) {
//            swap(i,j,nums);
//            qplres.add(Arrays.asList(nums,new ArrayList<Integer>()));
//            qpldfs(i+1,nums);
//            swap(i,j,nums);
//
//        }
//    }
//nums
    List<List<Integer>> combinationSumres = new ArrayList<>();
    int sum=0;
    List<Integer> combinationSumtemp = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[]candidates,int target){
        if (candidates.length==0|| Arrays.stream(candidates).sum()<target)
            return combinationSumres;
        Arrays.sort(candidates);
        combinationSumdfs(candidates,target,0);
        return combinationSumres;
    }

    private void combinationSumdfs(int[] candidates, int target, int i) {
        if (target==0)
            combinationSumres.add(new ArrayList<>(combinationSumtemp));
        if (target<0)
            return;
        HashSet<Integer> integers = new HashSet<>();
        for (int j = i; j < candidates.length; j++) {
            if (integers.contains(candidates[j]))
                continue;
            integers.add(candidates[j]);
            sum+=candidates[j];
            combinationSumtemp.add(candidates[j]);
            combinationSumdfs(candidates,target-candidates[j],j+1);
            combinationSumtemp.remove(combinationSumtemp.size()-1);
            sum-=candidates[j];
        }

    }
    public boolean checkPossibility(int[] nums) {
        if (nums.length==1)
            return true;
        for (int i = 0; i < nums.length-1; i++) {
            int x = nums[i], y = nums[i + 1];
            if (nums[i]>nums[i+1]){
                nums[i]=nums[i+1];
                if (isSorted(nums)){
                    return true;
                }
                nums[i]=x;
                nums[i+1]=x;
                return isSorted(nums);
            }
        }
        return true;
    }
    public boolean isSorted(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; ++i) {
            if (nums[i - 1] > nums[i]) {
                return false;
            }
        }
        return true;
    }


}
