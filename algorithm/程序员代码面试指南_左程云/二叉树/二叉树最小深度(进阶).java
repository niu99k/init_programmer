/*
求给定二叉树的最小深度。最小深度是指树的根结点到最近叶子结点的最短路径上结点的数量。（进阶 时间复杂度O(n),空间复杂度O(1)）
 */

import org.junit.Test;

import java.util.*;

public class Solution {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * @param root TreeNode类
     * @return int整型
     */
    public int run(TreeNode root) {
        int result;
        result = minDepth(root);
        return result;
    }

    private int minDepth(TreeNode root) {
        int result;
        int min = Integer.MAX_VALUE;
        int level = 1;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null && cur.right != null) {
                cur = cur.right;
                level++;
            } else if (cur.left == null && cur.right == null) {
                cur = cur.right;
            } else if (cur.left != null) {
                TreeNode mostRight = mostRight(cur);
                int mostRightCount = mostRightCount(cur);
                if (mostRight.right == null) {
                    level++;
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    //如果是叶节点
                    if (mostRight.left == null) {
                        min = Math.min(min, level - 1);
                    }
                    mostRight.right = null;
                    level -= (mostRightCount + 1);
                    TreeNode temp = cur;
                    level++;
                    cur = cur.right;
                }
            }
        }
        if (root != null) {
            cur = root;
            level = 0;
            while (cur.right != null) {
                level++;
                cur = cur.right;
            }
            if (cur.left == null && cur.right == null) {
                min = Math.min(level + 1, min);
            }
        } else {
            min = 0;
        }


        result = min;
        return result;
    }

    private int mostRightCount(TreeNode node) {
        int result;
        int count = 0;
        TreeNode temp = node.left;
        while (temp != null && temp != node) {
            count++;
            temp = temp.right;
        }
        result = count;
        return result;
    }

    private TreeNode mostRight(TreeNode node) {
        TreeNode result;
        TreeNode temp = node.left;
        while (temp.right != null && temp.right != node) {
            temp = temp.right;
        }
        result = temp;
        return result;
    }

    @Test
    public void test() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        TreeNode t9 = new TreeNode(9);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.right = t8;
        t4.left = t6;
        t6.right = t7;
        t8.left = t9;

        System.out.println(run(t1));
    }
}