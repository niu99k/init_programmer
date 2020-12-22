/*
题目描述
给定彼此独立的两棵二叉树，判断 t1 树是否包含 t2 树全部的拓扑结构。
设 t1 树的边集为 E1，t2 树的边集为 E2，若 E2 是 E1 的子集，则表示 t1 树包含 t2 树全部的拓扑结构。
输入描述:
第一行输入两个整数 n 和 root，n 表示二叉树 t1 的总节点个数，root 表示二叉树 t1 的根节点。

以下 n 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)

第 n+2 行输入两个整数 m 和 root，n 表示二叉树 t2 的总节点个数，root 表示二叉树 t2 的根节点。

以下 m 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)
输出描述:
如果 t1 树包含 t2 树全部的拓扑结构，则输出 "true"，否则输出 "false"。
示例1
输入
复制
10 1
1 2 3
2 4 5
4 8 9
8 0 0
9 0 0
5 10 0
10 0 0
3 6 7
6 0 0
7 0 0
4 2
2 4 5
5 0 0
4 8 0
8 0 0
输出
复制
true
备注:
1 \leq n,m\leq 20001≤n,m≤2000
1 \leq fa,lch,rch,root \leq n1≤fa,lch,rch,root≤n
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
    static int nodeCount1;
    static int nodeCount2;
    static int rootId1;
    static int rootId2;
    static Map<Integer, TreeNode> treeMap1;
    static Map<Integer, TreeNode> treeMap2;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        if (!treeMap1.containsKey(rootId2)) {
            System.out.println(false);
        } else {
            System.out.println(preOrderJudge(rootId2, rootId2));
        }
    }

    private static boolean preOrderJudge(int rootId1, int rootId2) {
        boolean result;
        if (rootId2 == 0) {
            result = true;
        } else if (rootId1 == 0 && rootId2 != 0) {
            result = false;
        } else {
            if (rootId1 != rootId2) {
                result = false;
            } else {
                result = true;
            }
            boolean leftResult = preOrderJudge(treeMap1.get(rootId1).leftId, treeMap2.get(rootId2).leftId);
            boolean rightResult = preOrderJudge(treeMap1.get(rootId1).rightId, treeMap2.get(rootId2).rightId);

            result = result && leftResult && rightResult;
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        treeMap1 = new HashMap<>();
        treeMap2 = new HashMap<>();

        nodeCount1 = scanner.nextInt();
        rootId1 = scanner.nextInt();
        for (int i = 0; i < nodeCount1; i++) {
            int nodeId = scanner.nextInt();
            treeMap1.put(nodeId, new TreeNode(nodeId, scanner.nextInt(), scanner.nextInt()));
        }


        nodeCount2 = scanner.nextInt();
        rootId2 = scanner.nextInt();
        for (int i = 0; i < nodeCount2; i++) {
            int nodeId = scanner.nextInt();
            treeMap2.put(nodeId, new TreeNode(nodeId, scanner.nextInt(), scanner.nextInt()));
        }
    }
}