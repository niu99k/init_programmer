/*
求给定二叉树的最小深度。最小深度是指树的根结点到最近叶子结点的最短路径上结点的数量。

示例1
输入
{1,2,3,4,5}
输出
2
 */

import javax.crypto.Mac;
import java.util.*;

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
}

public class Solution {
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
        if (root == null) {
            result = 0;
        } else {
            if (root.left == null && root.right == null) {
                result = 1;
            } else if (root.left != null && root.right == null) {
                result = minDepth(root.left) + 1;
            } else if (root.left == null && root.right != null) {
                result = minDepth(root.right) + 1;
            } else {
                result = Math.min(minDepth(root.left), minDepth(root.right)) + 1;
            }
        }
        return result;
    }
}