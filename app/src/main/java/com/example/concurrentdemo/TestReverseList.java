package com.example.concurrentdemo;

import java.util.List;

public class TestReverseList {
    public static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val, ListNode listNode) {
            this.val = val;
            this.next = listNode;
        }
    }
    public static void main(String[] args) {
        ListNode listNode = generateList();
//        printList(listNode);
//        ListNode l istNode2 = swapPairs(listNode);
        ListNode listNode3 = reverseList(listNode);
        printList(listNode3);
    }

    private static void printList(ListNode listNode) {
        System.out.println(" val = "+listNode.val);
        while (listNode.next != null){
            listNode = listNode.next;
            System.out.println("val ="+listNode.val);
        }
    }

    private static ListNode generateList() {
        ListNode head = new ListNode(0,null);
        ListNode currentNode = head;
        //构建列表
        for (int i = 1; i < 100; i++) {
                currentNode.next = new ListNode(i,null);
                currentNode = currentNode.next;
        }
        return head;
    }

/*    public static ListNode swapPairs(ListNode head){
        // 递归出口
        if (head == null || head.next == null){
            return head;
        }
        ListNode newNode = head.next;// newNode就是前边的一个节点
        head.next = swapPairs(head.next.next);// 尾节点指向后边的
        // 每组相邻节点的第二个节点的next指针
        newNode.next = head;//head
        return newNode;
    }*/
    public static ListNode swapPairs(ListNode head){//1 -> 2 -> 3
        if (head == null || head.next == null){
            return head;
        }
        ListNode newNode = head.next;// 下一个节点 newNode = 2   1 -> 2  -> 3
        head.next = swapPairs(head.next.next);// 首节点的下一个节点为 1-> (4->3)
        newNode.next = head;// 2 -> 1
        return newNode;
    }
    public static ListNode reverseList(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode preHead = reverseList(head.next.next);
        head.next.next = head;
        head.next = null;
        return preHead;
    }
/*    public static ListNode reverseList(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode reHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return reHead;

        //A---->B
        // newNode = B; B = null
    }*/
}
