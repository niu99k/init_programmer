/*
题目描述
给定一颗二叉树，已知其中所有节点的值都不一样，找到含有节点最多的搜索二叉子树，输出该子树总节点的数量。
搜索二叉树是指对于二叉树的任何一个节点，如果它有儿子，那么左儿子的值应该小于它的值，右儿子的值应该大于它的值。
输入描述:
第一行输入两个整数 n 和 root，n 表示二叉树的总节点个数，root 表示二叉树的根节点。

以下 n 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)

ps:节点的编号就是节点的值。
输出描述:
1 \leq n \leq 10000001≤n≤1000000
1 \leq fa,lch,rch,root \leq n1≤fa,lch,rch,root≤n
示例1
输入
复制
3 2
2 1 3
1 0 0
3 0 0
输出
复制
3

16 6
6 1 12
1 100 3
100 0 0
3 0 0
2 0 0
5 0 0
11 0 0
20 0 0
16 0 0
12 10 13
10 4 14
13 20 16
4 2 5
14 11 15
15 0 99
99 0 0

5 4
4 1 3
1 0 0
3 2 9
2 0 0
9 0 0

4 2
2 1 3
1 0 0
3 0 99
99 0 0
 */

import java.util.*;

public class Main {
    static class TreeNode {
        int id;
        int leftId;
        int rightId;

        TreeNode(int id, int left, int right) {
            this.id = id;
            this.leftId = left;
            this.rightId = right;
        }
    }

    static class ReturnType {
        int BSTId;
        int maxCount;
        int minId;
        int maxId;

        ReturnType(int BSTId, int maxCount, int minId, int maxId) {
            this.BSTId = BSTId;
            this.maxCount = maxCount;
            this.minId = minId;
            this.maxId = maxId;
        }
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
        ReturnType maxBSTReturnType = maxBSTNodeCount(rootId);
        System.out.println(maxBSTReturnType.maxCount);
    }

    private static ReturnType maxBSTNodeCount(int rootId) {
        ReturnType result = new ReturnType(0, 0, 0, 0);
        if (rootId == 0) {
            result.BSTId = 0;
            result.maxCount = 0;
            result.minId = 0;
            result.maxId = 0;
        } else if (treeMap.get(rootId).leftId == 0 && treeMap.get(rootId).rightId == 0) {
            result.BSTId = rootId;
            result.maxCount = 1;
            result.minId = rootId;
            result.maxId = rootId;
        } else {
            ReturnType leftBST = maxBSTNodeCount(treeMap.get(rootId).leftId);
            ReturnType rightBST = maxBSTNodeCount(treeMap.get(rootId).rightId);

            int leftMax = treeMap.get(rootId).leftId == 0 ? 0 : leftBST.maxId;
            int rightMin = treeMap.get(rootId).rightId == 0 ? Integer.MAX_VALUE : rightBST.minId;
            if (leftBST.BSTId == treeMap.get(rootId).leftId &&
                    rightBST.BSTId == treeMap.get(rootId).rightId &&
                    rootId > leftMax &&
                    rootId < rightMin
            ) {
                result.BSTId = rootId;
                result.maxId = treeMap.get(rootId).rightId == 0 ? rootId : rightBST.maxId;
                result.minId = treeMap.get(rootId).leftId == 0 ? rootId : leftBST.minId;
                result.maxCount = leftBST.maxCount + rightBST.maxCount + 1;
            } else if (leftBST.maxCount > rightBST.maxCount) {
                result = leftBST;
            } else {
                result = rightBST;
            }
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