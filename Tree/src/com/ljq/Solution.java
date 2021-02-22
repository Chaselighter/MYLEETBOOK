package com.ljq;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


/**
 * @author <liujianqiang@kuaishou.com>
 * Created on 2021-02-06
 */
public class Solution {
    public static void main(String[] args){
        TreeNode t1 = new TreeNode(4);
        TreeNode t2 = new TreeNode(1);
        TreeNode t3 = new TreeNode(5);
        TreeNode t4 = new TreeNode(2);
        TreeNode t5 = new TreeNode(3);
        TreeNode t6 = new TreeNode(5);
        TreeNode t7 = new TreeNode(7);
        t1.left=t2;
//        t1.right=t3;
        t2.left=t4;
        t4.left=t5;

//        t2.right=t5;
//        t3.left=t6;
//        t3.right=t7;
        Solution solution = new Solution();
        int secondMinimumValue = solution.rob(t1);
        System.out.println("");


    }
    public List<Integer> preorderTraversal(TreeNode root){
        ArrayList<Integer> res = new ArrayList<>();
        Stack<colorTree> s = new Stack<>();
        s.add(new colorTree("white",root));
        while (!s.isEmpty()){
            colorTree pop = s.pop();
            if (pop.node==null)
                continue;
            if (pop.color.equals("white")){

                s.add(new colorTree("white",pop.node.right));
                s.add(new colorTree("white",pop.node.left));
                pop.color="gray";
                s.add(pop);
            }else{
                res.add(pop.node.val);
            }
        }
        return res;

    }
    public List<Integer> inorderTraversal(TreeNode root){
        ArrayList<Integer> res = new ArrayList<>();
        Stack<colorTree> s = new Stack<>();
        s.add(new colorTree("white",root));
        while (!s.isEmpty()){
            colorTree pop = s.pop();
            if (pop.node==null)
                continue;
            if (pop.color.equals("white")){
                s.add(new colorTree("white",pop.node.right));
                pop.color="gray";
                s.add(pop);
                s.add(new colorTree("white",pop.node.left));
            }else{
                res.add(pop.node.val);
            }
        }
        return res;

    }
    public List<Integer> afterorderTraversal(TreeNode root){
        ArrayList<Integer> res = new ArrayList<>();
        Stack<colorTree> s = new Stack<>();
        s.add(new colorTree("white",root));
        while (!s.isEmpty()){
            colorTree pop = s.pop();
            if (pop.node==null)
                continue;
            if (pop.color.equals("white")){
                pop.color="gray";
                s.add(pop);
                s.add(new colorTree("white",pop.node.right));
                s.add(new colorTree("white",pop.node.left));
            }else{
                res.add(pop.node.val);
            }
        }
        return res;

    }
    public class colorTree{
        String color;
        TreeNode node;

        public colorTree(String color, TreeNode node) {
            this.color = color;
            this.node = node;
        }
    }
    List<List<Integer>> treebfsres = new ArrayList<>();
    public List<List<Integer>> treebfs(TreeNode root){
        if (root==null)
            return treebfsres;
        LinkedList<TreeNode> s = new LinkedList<>();
        s.add(root);
        while (!s.isEmpty()){
            int size = s.size();
            ArrayList<Integer> integers = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode pop = s.pop();
                integers.add(pop.val);
                if (pop.left!=null){
                    s.add(pop.left);
                }
                if (pop.right!=null){
                    s.add(pop.right);
                }
            }
            treebfsres.add(integers);
        }
        return treebfsres;
    }
    //二叉树中和为某一值的路径
    List<List<Integer>> pathSumres = new ArrayList<>();
    List<Integer> pathtemp = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root==null)
            return pathSumres;
        pathSumdfs(root,sum);
        return pathSumres;
    }

    private void pathSumdfs(TreeNode root, int sum) {
        if (root==null)
            return;

        pathtemp.add(root.val);

        if (sum==root.val&&root.left==null&&root.right==null)
            pathSumres.add(new ArrayList<>(pathtemp));
        pathSumdfs(root.left,sum-root.val);
        //pathtemp.remove(pathtemp.size()-1);
        pathSumdfs(root.right,sum-root.val);
        pathtemp.remove(pathtemp.size()-1);

    }
    public int longestZigZag(TreeNode root) {
        if (root==null)
            return 0;
        return Math.max(longestZigZag(root.left),longestZigZag(root.right))+1;
    }
    //构造二叉树
    //前序和中序遍历
    //前序遍历 preorder = [3,9,20,15,7]
    //中序遍历 inorder = [9,3,15,20,7]
    //返回如下的二叉树：
    //
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            integerIntegerHashMap.put(inorder[i],i);
        }
        return buildTreeHelper(preorder,0,preorder.length,inorder,0,inorder.length,integerIntegerHashMap);
    }

    private TreeNode buildTreeHelper(int[] preorder, int p_start, int p_end, int[] inorder, int i_start, int i_end,
            HashMap<Integer, Integer> integerIntegerHashMap) {
        if (p_end==p_start)
            return null;
        int val = preorder[p_start];
        TreeNode root = new TreeNode(val);
        int root_index =integerIntegerHashMap.get(val);
        int leftnum = root_index-i_start;
        root.left = buildTreeHelper(preorder,p_start+1,p_start+1+leftnum,inorder,i_start,root_index,
                integerIntegerHashMap);
        root.right = buildTreeHelper(preorder,p_start+1+leftnum,p_end,inorder,root_index+1,i_end, integerIntegerHashMap);
        return root;
    }
    //中序遍历 inorder = [9,3,15,20,7]
    //后序遍历 postorder = [9,15,7,20,3]
    //    3
    //   / \
    //  9  20
    //    /  \
    //   15   7
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            integerIntegerHashMap.put(inorder[i],i);
        }
        return buildTreeHelper2(postorder,0,postorder.length,inorder,0,inorder.length,integerIntegerHashMap);
    }

    private TreeNode buildTreeHelper2(int[] postorder, int po_start, int po_end, int[] inorder, int i_start, int i_end,
            HashMap<Integer, Integer> integerIntegerHashMap) {
        if (po_end==po_start)
            return null;
        int val = postorder[po_end - 1];
        TreeNode root = new TreeNode(val);
        Integer index = integerIntegerHashMap.get(val);
        int rightnum = i_end-index-1;
        root.right=buildTreeHelper2(postorder,po_end-rightnum-1,po_end-1,inorder,index+1,i_end,integerIntegerHashMap);
        root.left=buildTreeHelper2(postorder,po_start,po_end-rightnum-1,inorder,i_start,index,integerIntegerHashMap);
        return root;

    }
    //输入：pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
    //输出：[1,2,3,4,5,6,7]
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre.length==0)
            return null;
        if (pre.length==1)
            return new TreeNode(pre[0]);
        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            integerIntegerHashMap.put(post[i],i);
        }
        return constructFromPrePosthelper(pre,0,pre.length,post,0,post.length,integerIntegerHashMap);

    }

    private TreeNode constructFromPrePosthelper(int[] pre, int pre_start, int pre_end, int[] post, int po_start,
            int po_end, HashMap<Integer, Integer> integerIntegerHashMap) {
        if (pre_start==pre_end)
            return null;
        if (pre_end-pre_start==1)
            return new TreeNode(pre[pre_start]);
        int rootval = pre[pre_start];
        int val = pre[pre_start + 1];
        TreeNode root = new TreeNode(rootval);
        int index = integerIntegerHashMap.get(val);
        int leftnum = index-po_start+1;
        TreeNode left =
                constructFromPrePosthelper(pre, pre_start + 1, pre_start + 1 + leftnum, post, po_start, index + 1,
                        integerIntegerHashMap);
        root.left = left;
        root.right = constructFromPrePosthelper(pre,pre_start+1+leftnum,pre_end,post,index+1,po_end-1,integerIntegerHashMap);
        return root;
    }
    //              5
    //             / \
    //            4   8
    //           /   / \
    //          11  13  4
    //         /  \    / \
    //        7    2  5   1
    //3
    //解释：和为 22 的路径有：[5,4,11,2], [5,8,4,5], [4,11,7]


    public int pathSummultinode(TreeNode root, int sum) {
        if (root==null)
            return 0;
        int count=0;
        return pathSummultinodedfs(root,sum)+pathSummultinode(root.right,sum)+pathSummultinode(root.left,sum);

    }

    private int pathSummultinodedfs(TreeNode root,  int sum) {
        if (root==null)
            return 0;


        int i = pathSummultinodedfs(root.left, sum - root.val);
        int i1 = pathSummultinodedfs(root.right, sum - root.val);
        return (sum==root.val?1:0)+i + i1;

    }
    //二叉树的坡度
    int tilt =0 ;
    public int findTilt(TreeNode root) {
        findTilthelper(root);
        return tilt;
    }

    private int findTilthelper(TreeNode root) {
        if (root==null)
            return 0;
        int lefsum = findTilthelper(root.left);
        int rightsum = findTilthelper(root.right);
        tilt+=Math.abs(lefsum-rightsum);
        return root.val+lefsum+rightsum;


    }
    //给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
    //
    //例如，从根到叶子节点路径 1->2->3 代表数字 123。
    //
    //计算从根到叶子节点生成的所有数字之和。
    //
    int sum=0;
    int num=0;
    public int sumNumbers(TreeNode root) {
        return sumNumbersdfs(root,0);

    }

    private int sumNumbersdfs(TreeNode root, int i) {
        if (root==null)
            return 0;
        int temp = i*10+root.val;
        if (root.left==null&&root.right==null)
            return temp;
        return sumNumbersdfs(root.left,temp)+sumNumbersdfs(root.right,temp);

    }
    //给定一个二叉搜索树的根节点 root，返回树中任意两节点的差的最小值。
    public int minDiffInBST(TreeNode root) {
        int lower=Integer.MIN_VALUE;
        int upper = Integer.MAX_VALUE;
//        minDiffInBSTdfs(root,lower,upper);
        return minDiffInBSTdfs(root,lower,upper);
    }

    private int minDiffInBSTdfs(TreeNode root, int lower, int upper) {
        if (root==null)
            return upper-lower;
        int left = minDiffInBSTdfs(root.left,lower,root.val);
        int right = minDiffInBSTdfs(root.right,root.val,upper);
        return Math.min(left,right);
    }
    //给定二叉树的根节点 root，找出存在于 不同 节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。
    int maxAncestorDiffmax=Integer.MIN_VALUE;
    public int maxAncestorDiff(TreeNode root) {
        if(root==null)
            return 0;

        maxAncestorDiffdfs(root,root.val,root.val);
        return maxAncestorDiffmax;
    }

    private void maxAncestorDiffdfs(TreeNode root, int min, int max) {
        if (root==null)
            return;
        maxAncestorDiffmax=Math.max(maxAncestorDiffmax,Math.max(Math.abs(root.val-min),Math.abs(root.val-max)));
        min = Math.min(min,root.val);
        max= Math.max(max,root.val);
        maxAncestorDiffdfs(root.left, min, max);
        maxAncestorDiffdfs(root.right, min, max);

    }
    //给定一个根为 root 的二叉树，每个节点的深度是 该节点到根的最短距离 。
    //
    //如果一个节点在 整个树 的任意节点之间具有最大的深度，则该节点是 最深的 。
    //
    //一个节点的 子树 是该节点加上它的所有后代的集合。
    //
    //返回能满足 以该节点为根的子树中包含所有最深的节点 这一条件的具有最大深度的节点。
    //求深度一般通过形参加一传递

    TreeNode deepst=null;
    public class subtreeWithAllDeepestResult{
        int Deep;
        TreeNode treeNode;

        public subtreeWithAllDeepestResult(int deep, TreeNode treeNode) {
            Deep = deep;
            this.treeNode = treeNode;
        }

    }
    int maxdeep=0;

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root==null||(root.left==null&&root.right==null))
            return root;
        int maxdeeptemp=0;
        return subtreeWithAllDeepestdfs(root,maxdeeptemp).treeNode;
    }

    private subtreeWithAllDeepestResult subtreeWithAllDeepestdfs(TreeNode root, int maxdeeptemp) {
        if (root==null)
            return null;


        maxdeep=Math.max(maxdeep,maxdeeptemp);
        if (root.left==null&&root.right==null){
            if (maxdeeptemp>=maxdeep)
                return new subtreeWithAllDeepestResult(maxdeep,root);
            return null;
        }

        subtreeWithAllDeepestResult l = subtreeWithAllDeepestdfs(root.left, maxdeeptemp+1);

        subtreeWithAllDeepestResult r = subtreeWithAllDeepestdfs(root.right, maxdeeptemp+1);

        if(l!=null&&r!=null){
            if (l.Deep>r.Deep)
                return l;
            else if (l.Deep==r.Deep)
                return new subtreeWithAllDeepestResult(maxdeep,root);
            else return r;
        }

        return r==null?l:r;

    }
    //给你二叉树的根节点 root 和一个整数 distance 。
    //
    //如果二叉树中两个 叶 节点之间的 最短路径长度 小于或者等于 distance ，那它们就可以构成一组 好叶子节点对 。
    //
    //返回树中 好叶子节点对的数量 。
    int countPairsres=0;
    public int countPairs(TreeNode root, int distance) {
        countPairsdfs(root,distance);
        return countPairsres;
    }

    private List<Integer> countPairsdfs(TreeNode root, int distance) {
        if (root==null)
            return new ArrayList<>();
        if (root.left==null&&root.right==null){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(0);
            return list;

        }
        ArrayList<Integer> list = new ArrayList<>();
        List<Integer> left = countPairsdfs(root.left, distance);
        for (Integer it : left) {
            if (++it>distance)
                continue;
            list.add(it);
        }
        List<Integer> right = countPairsdfs(root.right, distance);
        for (Integer it : right) {
            if (++it>distance)
                continue;
            list.add(it);
        }
        for (Integer l : left) {
            for (Integer r : right) {
                if (l+r+2<=distance)
                    countPairsres++;
            }
        }
        return list;
    }
    //. 二叉树的深度
    public int maxDepth(TreeNode root) {
        if (root==null)
            return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
    //给定一个二叉树，检查它是否是镜像对称的。
    //迭代怎么？？？？？？？？？？？？？？？？？？？？？？？？
    public boolean isSymmetric(TreeNode root) {
        if (root==null)
            return true;

        return isSymmetrichelper(root.left,root.right);

    }

    private boolean isSymmetrichelper(TreeNode left, TreeNode right) {
        if (left==null&&right==null)
            return true;
        if (left==null||right==null)
            return false;
        if (left.val!=right.val)
            return false;
        return isSymmetrichelper(left.left,right.right)&&isSymmetrichelper(left.right,right.left);
    }
    //翻转一棵二叉树。
    public TreeNode invertTree(TreeNode root) {
        if (root==null)
            return root;
        invertTreeswap(root);
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    private void invertTreeswap(TreeNode root) {
        if (root.left==null&&root.right==null)
            return;
        TreeNode t =root.left;
        root.left=root.right;
        root.right=t;
    }
    //二叉树的直径
    int diameterOfBinaryTreemax;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null)
            return 0;
        diameterOfBinaryTreehelper(root);
        return diameterOfBinaryTreemax;
    }

    private int diameterOfBinaryTreehelper(TreeNode root) {
        if (root==null)
            return 0;
        int i = diameterOfBinaryTreehelper(root.left);
        int j = diameterOfBinaryTreehelper(root.right);
        diameterOfBinaryTreemax = Math.max(diameterOfBinaryTreemax,i+j);
        return Math.max(i,j)+1;
    }

    //二叉树宽度
    public int widthOfBinaryTree(TreeNode root) {
        if (root==null)
            return 0;
        int res=1;
        LinkedList<TreeNode> s = new LinkedList<>();
        s.add(root);
        while (!s.isEmpty()){
            int size = s.size();
            for (int i = 0; i < size; i++) {
                int count=0;
                TreeNode poll = s.poll();
                if (poll==null)
                    continue;
                res=Math.max(res,i+1);
                s.add(poll.left);
                s.add(poll.right);

            }
        }
        return res;
    }
    //找出和指定节点距离为K的所有节点
    //思路：修改二叉树，使得指定节点变为根节点然后遍历
    List<Integer> distanceKres = new ArrayList<>();
    int kdeep;
    Map<TreeNode,TreeNode> parent;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        if (root==null)
            return null;
        parent=new HashMap<>();
        distanceKdfs(root,null);
        LinkedList<TreeNode> s = new LinkedList<>();
        s.add(null);
        s.add(target);

        HashSet<TreeNode> seen = new HashSet<>();
        seen.add(target);
        seen.add(null);
        int dist=0;
        while (!s.isEmpty()){
            TreeNode poll = s.poll();
            //用null节点来分层————————————————dist++
            if (poll==null){
                if (dist==K){
                    ArrayList<Integer> ans = new ArrayList<>();
                    for (TreeNode treeNode : s) {
                        ans.add(treeNode.val);
                    }
                    return ans;
                }
                s.add(null);
                dist++;
            }else {
                if (!seen.contains(poll.left)){
                    seen.add(poll.left);
                    s.add(poll.left);
                }
                if (!seen.contains(poll.right)){
                    seen.add(poll.right);
                    s.add(poll.right);
                }
                TreeNode p = parent.get(poll);
                if (!seen.contains(p)){
                    seen.add(p);
                    s.offer(p);
                }
            }
        }
        return new ArrayList<Integer>();

    }


    private void distanceKdfs(TreeNode root, TreeNode par) {
        if (root==null)
            return;
        parent.put(root,par);
        distanceKdfs(root.left,root);
        distanceKdfs(root.right,root);


    }
    //设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
    //
    //如果指定节点没有对应的“下一个”节点，则返回null。
//    Map<TreeNode,TreeNode> inorderSuccessorm = new HashMap<TreeNode,TreeNode>();
//    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
//        inorderSuccessordfs(root);
//        return inorderSuccessorm.get(p);
//    }

    private void inorderSuccessordfs(TreeNode root,TreeNode pre) {
        if (root==null)
            return;

        inorderSuccessordfs(root.left,null);

        inorderSuccessordfs(root.right,root);
    }
    //
    int SecondMinimumValue=Integer.MAX_VALUE;
    Queue<Integer> findSecondMinimumValueq = new PriorityQueue<Integer>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1-o2;
        }
    });
    public int findSecondMinimumValue(TreeNode root) {
        if (root==null||(root.left==null&&root.right==null))
            return -1;
        findSecondMinimumValueq.add(SecondMinimumValue);
        findSecondMinimumValuehelpere(root,root.val);
        if (findSecondMinimumValueq.size()<2)
            return -1;
        return findSecondMinimumValueq.peek();

    }

    private void findSecondMinimumValuehelpere(TreeNode root,int val) {
        if (root==null)
            return;
        if (root.val>val){
            if (root.val<findSecondMinimumValueq.peek()){
                findSecondMinimumValueq.add(root.val);
                if (findSecondMinimumValueq.size()>2){
                    findSecondMinimumValueq.poll();
                }
            }

        }
        findSecondMinimumValuehelpere(root.left,val);
        findSecondMinimumValuehelpere(root.right,val);
    }
    //间隔遍历
    public int rob(TreeNode root) {
        HashMap<TreeNode, Integer> memo = new HashMap<>();
        return robinternal(root,memo);
    }

    private int robinternal(TreeNode root, HashMap<TreeNode, Integer> memo) {
        if (root==null)
            return 0;
        if (memo.containsKey(root)) return memo.get(root);
        int money = root.val;
        if (root.left!=null) money+=(robinternal(root.left.left,memo)+robinternal(root.left.right,memo));
        if (root.right!=null) money+=(robinternal(root.right.left,memo)+robinternal(root.right.right,memo));
        int res = Math.max(money,robinternal(root.left,memo)+robinternal(root.right,memo));
        return res;

    }
    //给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
//    HashSet<Integer> s = new HashSet<>();
//    public int longestUnivaluePath(TreeNode root) {
//
//        if (s.contains(root.val))
//            return 0;
//        longdfs(root);
//        s.add(root.val);
//        longestUnivaluePath(root.left);
//        longestUnivaluePath(root.right);
//    }
//
//    private int longdfs(TreeNode root) {
//        if (root==null)
//            return 0;
//        if (root.val==root.left.val)
//            longdfs(root.left);
//        if (root.val==root.right.val)
//            longdfs(root.right);
//        return
//
//    }
    //    int robmax = Integer.MIN_VALUE;
//    public int rob(TreeNode root) {
//        if (root==null)
//            return 0;
//        if(root.left==null&&root.right==null)
//            return root.val;
//        return Math.max(robhelper(root,true,0),robhelper(root,false,0));
//
//    }
//
//    private int robhelper(TreeNode root, boolean b,int res) {
//        if (root==null)
//            return 0;
//        if (!b){
//
//            int robhelper3 = robhelper(root.left, b, res);
//            int robhelper4 = robhelper(root.right, b, res);
//
//        }
//        int robhelper1 = robhelper(root.left, !b, res);
//        int robhelper = robhelper(root.right, !b, res);
//
//        return b?root.val+robhelper+robhelper1:robhelper+robhelper1;
//
//    }
    //    int leftw=Integer.MIN_VALUE;
//    int rightw=Integer.MAX_VALUE;
//    public int widthOfBinaryTree(TreeNode root) {
//        if (root==null)
//            return 0;
//        widthOfBinaryTreehelper(root,0);
//        return leftw-rightw;
//    }
//
//    private void widthOfBinaryTreehelper(TreeNode root, int width) {
//        if (root==null)
//            return ;
//        leftw=Math.max(leftw,width);
//        rightw=Math.min(rightw,width);
//        widthOfBinaryTreehelper(root.left,width+1);
//        widthOfBinaryTreehelper(root.right,width-1);
//
//    }



}
