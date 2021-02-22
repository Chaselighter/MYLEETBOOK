package com.ljq;

import java.util.ArrayList;

/**
 * @author <liujianqiang@kuaishou.com>
 * Created on 2021-02-12
 */
public class Solution {
    public static void main(String args[]){
        ListNode l1 = new ListNode(4);
        ListNode l2 = new ListNode(5);
        ListNode l3 = new ListNode(1);
        ListNode l4 = new ListNode(9);
        ListNode l5 = new ListNode(4);
        l1.next=l2;l2.next=l3;l3.next=l4;
        Solution solution = new Solution();
        int[] exchange = solution.exchange(new int[] {2,1,2});
        System.out.println("sassa");
    }
    public ListNode deleteNode(ListNode head, int val) {
        ListNode pre = new ListNode(-1);
        pre.next=head;
        ListNode cur = head;
        while (cur.next!=null){
            if (cur.val==val) pre.next=cur.next;
            pre=pre.next;
            cur=cur.next;
        }
        return head;
    }
    //输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
    ArrayList<Integer> reversePrintres = new ArrayList<Integer> ();
    public int[] reversePrint(ListNode head) {
        reversePrintdfs(head);
        int[] ints = new int[reversePrintres.size()];
        for (int i = 0; i < reversePrintres.size(); i++) {
            ints[i]=reversePrintres.get(i);
        }
        return ints;

    }

    private void reversePrintdfs(ListNode head) {
        if (head==null)
            return;
        reversePrintdfs(head.next);
        reversePrintres.add(head.val);

    }
    //输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
//    1-2-3-4-5-6-7-8
    public int[] exchange(int[] nums) {

        if (nums.length==1||nums.length==0)
            return nums;
//        int flag=1;
//        int left=1,right=nums.length-2;
//        while (left<right){
//            if (flag%2!=0) swap(nums,left,right);
//            left++;right--;flag++;
//        }
//        return nums;
        //left,right 均从头开始；
//        int left=0;
//        while (left<nums.length&&nums[left]%2!=0){
//            left++;
//        }
//        int right=left+1;
//
//        while (right<nums.length){
//            if (nums[right]%2!=0){
//                swap(nums,left,right);
//                left++;
//            }else right++;
//        }
//        return nums;
        //一头一尾开始
        int left=0,right=nums.length-1;
        while (left<right){
            while (left<right&&(nums[left]&1)==1) left++;
            while (left<right&&(nums[right]&1)==1) right++;
            swap(nums,left,right);
        }
        return nums;
    }
    //链表中倒数第 k 个节点
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head==null)
            return null;
        ListNode pre=head;
        ListNode cur =head;
        int i=0;
        while (i<k){
            pre=pre.next;
        }
        while (pre!=null){
            pre=pre.next;cur=cur.next;
        }
        return cur;
    }
    public void swap(int[]a,int i,int j){
        int t = a[i];
        a[i]=a[j];
        a[j]=t;
    }

}
