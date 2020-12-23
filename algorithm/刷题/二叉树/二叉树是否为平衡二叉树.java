/*
题目描述
平衡二叉树的性质为: 要么是一棵空树，要么任何一个节点的左右子树高度差的绝对值不超过 1。给定一棵二叉树，判断这棵二叉树是否为平衡二叉树。
一颗树的高度指的是树的根节点到所有节点的距离中的最大值。

输入描述:
第一行输入两个整数 n 和 root，n 表示二叉树的总节点个数，root 表示二叉树的根节点。
以下 n 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)
输出描述:
如果是平衡二叉树则输出 "true"，否则输出 "false"。
示例1
输入
复制
3 1
1 2 3
2 0 0
3 0 0
输出
复制
true
示例2
输入
复制
6 1
1 2 3
2 4 5
4 6 0
3 0 0
5 0 0
6 0 0
输出
复制
false
备注:
1 \leq n \leq 5000001≤n≤500000
1 \leq fa,lch,rch,root \leq n1≤fa,lch,rch,root≤n
 */

import sun.reflect.generics.tree.ReturnType;

import java.util.*;

public class Main {
    static class TreeNode {
        int id;
        int leftId;
        int rightId;

        TreeNode(int id, int leftId, int rightId) {
            this.id = id;
            this.leftId = leftId;
            this.rightId = rightId;
        }
    }

    static class ReturnType {
        int height;
        boolean isBT;
    }

    static Scanner scanner;
    static int nodeCount;
    static int rootId;
    static Map<Integer, TreeNode> treeMap;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        System.out.println(isTreeBT(rootId).isBT);
    }

    private static ReturnType isTreeBT(int rootId) {
        ReturnType result = new ReturnType();
        if (rootId == 0) {
            result.height = 0;
            result.isBT = true;
        } else {
            ReturnType leftResult = isTreeBT(treeMap.get(rootId).leftId);
            ReturnType rightResult = isTreeBT(treeMap.get(rootId).rightId);

            boolean isLeftTreeAndRightTreeBalance;
            if (Math.abs(leftResult.height - rightResult.height) > 1) {
                isLeftTreeAndRightTreeBalance = false;
            } else {
                isLeftTreeAndRightTreeBalance = true;
            }

            result.isBT = leftResult.isBT && rightResult.isBT && isLeftTreeAndRightTreeBalance;
            result.height = Math.max(leftResult.height, rightResult.height) + 1;

        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        treeMap = new HashMap<>();

        nodeCount = scanner.nextInt();
        rootId = scanner.nextInt();
        for (int i = 0; i < nodeCount; i++) {
            int nodeId = scanner.nextInt();
            treeMap.put(nodeId, new TreeNode(nodeId, scanner.nextInt(), scanner.nextInt()));
        }
    }
}