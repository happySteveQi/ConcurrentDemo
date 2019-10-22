package com.example.concurrentdemo;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class TestDemo {
    public static void main(String[] args) {

    }

    public int myMaxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int currentCount = 0;
        int preCount = 1;
        int level = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            preCount--;
            TreeNode tempNode = q.poll();
            if (tempNode.left != null) {
                q.offer(tempNode.left);
                currentCount++;
            }
            if (tempNode.right != null) {
                q.offer(tempNode.right);
                currentCount++;
            }
            if (preCount == 0) {
                level++;
                preCount = currentCount;
            }
        }

        return level;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int preCount = 1;// 前一层的节点数
        int pCount = 0;//当前层的节点数
        int level = 0;
        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            preCount--;
            if (temp.left != null) {
                q.offer(temp.left);
                pCount++;
            }
            if (temp.right != null) {
                q.offer(temp.right);
                pCount++;
            }
            if (preCount == 0) {
                preCount = pCount;
                pCount = 0;
                level++;
            }
        }
        return level;
    }

    public void travelFile(String path) {
        Queue<File> linkedList = new LinkedList<>();
        File file = new File(path);
        if (!file.exists()) {
            return;//
        }
        int folderNum = 0;
        int fileNum = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                if (file1.isDirectory()) {//文件夹
                    System.out.println("----- folderPath = " + file1.getAbsolutePath());
                    linkedList.offer(file1);
                    folderNum++;
                } else {
                    System.out.println("----- filePath = " + file1.getAbsolutePath());
                    fileNum++;
                }
            }

        }
        while (!linkedList.isEmpty()) {
            File tempFile = linkedList.remove();
            if (tempFile.isDirectory()) {
                for (File tempFile0 :
                        tempFile.listFiles()) {
                    if (tempFile0.isDirectory()) {
                        linkedList.offer(tempFile0);
                        System.out.println(" ------------ " + tempFile0.getAbsolutePath());
                    } else {
                        System.out.println(" ------------ " + tempFile0.getAbsolutePath());
                    }

                }
            } else {
                System.out.println(" ------------ " + tempFile.getAbsolutePath());
            }

        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
