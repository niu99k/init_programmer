/*
题目描述
给定一颗二叉树，已知所有节点的值都不一样， 返回其中最大的且符合搜索二叉树条件的最大拓扑结构的大小。
拓扑结构是指树上的一个联通块。
输入描述:
第一行输入两个整数 n 和 root，n 表示二叉树的总节点个数，root 表示二叉树的根节点。

以下 n 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)

ps:节点的编号就是节点的值。
输出描述:
输出一个整数表示满足条件的最大拓扑结构的大小。
示例1
输入
复制
3 2
2 1 3
1 0 0
3 0 0

15 6
6 1 12
1 -100 3
-100 0 0
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
15 0 0

4 2
2 3 4
3 0 5
5 0 0
4 0 0
输出
复制
3
备注:
1\leq n \leq 2000001≤n≤200000
1 \leq fa,lch,rch,root \leq n1≤fa,lch,rch,root≤n
 */

import java.util.*;

public class Main {
    static class TreeNode {
        int id;
        int left;
        int right;
        int leftContribution;
        int rightContribution;

        TreeNode(int id, int left, int right) {
            this.id = id;
            this.left = left;
            this.right = right;
        }
    }

    static class ReturnType {
        int maxCount;
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
        System.out.println(maxTopologyStructrurCount(rootId).maxCount);
    }

    private static ReturnType maxTopologyStructrurCount(int rootId) {
        ReturnType result = new ReturnType();
        if (rootId == 0) {
            result.maxCount = 0;
        } else {
            ReturnType leftResult = maxTopologyStructrurCount(treeMap.get(rootId).left);
            ReturnType rightResult = maxTopologyStructrurCount(treeMap.get(rootId).right);
            ReturnType maxCountIfRootIdAsTLSRootId = maxCountIfRootIdAsTLSRootId(rootId);
            result.maxCount = Math.max(leftResult.maxCount, rightResult.maxCount);
            result.maxCount = Math.max(result.maxCount, maxCountIfRootIdAsTLSRootId.maxCount);
        }
        return result;
    }

    private static ReturnType maxCountIfRootIdAsTLSRootId(int rootId) {
        ReturnType result = new ReturnType();
        refreshLeftTopoloyContribution(rootId);
        refreshRightTopoloyContribution(rootId);
        int allLeftContribution = 0;
        int allRightContribution = 0;
        if (treeMap.get(rootId).left == 0) {
            allLeftContribution = 0;
        } else {
            TreeNode left = treeMap.get(treeMap.get(rootId).left);
            if (left.id < rootId) {
                allLeftContribution = left.leftContribution + left.rightContribution + 1;
            } else {
                allLeftContribution = 0;
                left.leftContribution = 0;
                left.rightContribution = 0;
            }
        }
        if (treeMap.get(rootId).right == 0) {
            allRightContribution = 0;
        } else {
            TreeNode right = treeMap.get(treeMap.get(rootId).right);
            if (right.id > rootId) {
                allRightContribution = right.leftContribution + right.rightContribution + 1;
            } else {
                allRightContribution = 0;
                right.leftContribution = 0;
                right.rightContribution = 0;
            }

        }
        result.maxCount = allLeftContribution + allRightContribution + 1;
        treeMap.get(rootId).leftContribution = allLeftContribution;
        treeMap.get(rootId).rightContribution = allRightContribution;
        return result;
    }

    private static void refreshRightTopoloyContribution(int rootId) {
        if (treeMap.get(rootId).right == 0) {
            return;
        } else {
            int tranversId = treeMap.get(rootId).right;
            while ((treeMap.get(tranversId).leftContribution != 0 || treeMap.get(tranversId).rightContribution != 0) &&
                    tranversId > rootId) {
                tranversId = treeMap.get(tranversId).left;
            }
            int endId = tranversId;

            if (endId != 0) {
                int contributionShouldRemove = endId != 0 && endId < rootId ?
                        treeMap.get(endId).leftContribution + treeMap.get(endId).rightContribution + 1 :
                        treeMap.get(endId).leftContribution + treeMap.get(endId).rightContribution;
                tranversId = treeMap.get(rootId).right;
                while ((treeMap.get(tranversId).leftContribution != 0 || treeMap.get(tranversId).rightContribution != 0) &&
                        tranversId > rootId &&
                        tranversId != endId
                ) {
                    treeMap.get(tranversId).leftContribution = treeMap.get(tranversId).leftContribution - contributionShouldRemove;
                    tranversId = treeMap.get(tranversId).left;
                }
            }
        }
    }

    private static void refreshLeftTopoloyContribution(int rootId) {
        if (treeMap.get(rootId).left == 0) {
            return;
        } else {
//            int leftId = treeMap.get(rootId).left;
//            int tranversId = treeMap.get(leftId).right;
            int tranversId = treeMap.get(rootId).left;

            while ((treeMap.get(tranversId).leftContribution != 0 || treeMap.get(tranversId).rightContribution != 0) &&
                    tranversId < rootId) {
                tranversId = treeMap.get(tranversId).right;
            }
            int endId = tranversId;

            if (endId != 0) {
                int contributionShouldRemove = endId != 0 && endId > rootId ?
                        treeMap.get(endId).leftContribution + treeMap.get(endId).rightContribution + 1 :
                        treeMap.get(endId).leftContribution + treeMap.get(endId).rightContribution;
                tranversId = treeMap.get(rootId).left;
//                tranversId = treeMap.get(leftId).right;
                while ((treeMap.get(tranversId).leftContribution != 0 || treeMap.get(tranversId).rightContribution != 0) &&
                        tranversId < rootId &&
                        tranversId != endId
                ) {
                    treeMap.get(tranversId).rightContribution = treeMap.get(tranversId).rightContribution - contributionShouldRemove;
                    tranversId = treeMap.get(tranversId).right;
                }
            }
        }
    }

    private static void init() {
        scanner = new Scanner(System.in);
        treeMap = new HashMap<>();
        nodeCount = scanner.nextInt();
        rootId = scanner.nextInt();

        treeMap.put(0, new TreeNode(0, 0, 0));
        for (int i = 0; i < nodeCount; i++) {
            int nodeId = scanner.nextInt();
            treeMap.put(nodeId, new TreeNode(nodeId, scanner.nextInt(), scanner.nextInt()));
        }
    }
}