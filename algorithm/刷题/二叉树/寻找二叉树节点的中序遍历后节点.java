/*
题目描述
二叉树中一个节点的后继节点指的是，二叉树的中序遍历的序列中的下一个节点。
输入描述:
第一行输入两个整数 n 和 root，n 表示二叉树的总节点个数，root 表示二叉树的根节点。

以下 n 行每行四个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)

最后一行输入要询问的节点 node。
输出描述:
输出一个整数表示答案。(如果 node 是最后一个节点，则输出 0 )
示例1
输入
复制
10 6
6 3 9
3 1 4
1 0 2
2 0 0
4 0 5
5 0 0
9 8 10
10 0 0
8 7 0
7 0 0
10
输出
复制
0
备注:
1 \leq n \leq 5000001≤n≤500000
1 \le fa,lch,rch,root,node \le n1≤fa,lch,rch,root,node≤n
 */

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

    static Scanner scanner;
    static int nodeCount;
    static int rootId;
    static Map<Integer, TreeNode> treeMap;
    static Map<Integer, Integer> parentMap;
    static int problemId;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        System.out.println(nextInOrderId4ProblemId(problemId));
    }

    private static int nextInOrderId4ProblemId(int problemId) {
        int result;
        if (isNodeHaveRightTree(problemId)) {
            result = mostLeftInRight(problemId);
        } else {
            result = parentIdIfFindPreIsInLeftTree(problemId);
        }
        return result;
    }

    private static int parentIdIfFindPreIsInLeftTree(int problemId) {
        int result = problemId;
        while (true) {
            if (isNodeLeftChild(result)) {
                result = parentMap.get(result);
                break;
            }
            result = parentMap.get(result);
        }
        return result;
    }

    private static boolean isNodeLeftChild(int nodeId) {
        boolean result;
        if (nodeId == rootId) {
            result = true;
        } else {
            result = nodeId == treeMap.get(parentMap.get(nodeId)).leftId;
        }
        return result;
    }

    private static int mostLeftInRight(int problemId) {
        int result;
        result = treeMap.get(problemId).rightId;
        while (true) {
            if (treeMap.get(result).leftId == 0) {
                break;
            }
            result = treeMap.get(result).leftId;
        }
        return result;
    }

    private static boolean isNodeHaveRightTree(int problemId) {
        boolean result;
        result = treeMap.get(problemId).rightId != 0;
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        treeMap = new HashMap<>();
        parentMap = new HashMap<>();

        nodeCount = scanner.nextInt();
        rootId = scanner.nextInt();
        parentMap.put(rootId, 0);
        for (int i = 0; i < nodeCount; i++) {
            int nodeId = scanner.nextInt();
            int leftId = scanner.nextInt();
            int rightId = scanner.nextInt();
            treeMap.put(nodeId, new TreeNode(nodeId, leftId, rightId));
            parentMap.put(leftId, nodeId);
            parentMap.put(rightId, nodeId);
        }
        problemId = scanner.nextInt();
    }
}