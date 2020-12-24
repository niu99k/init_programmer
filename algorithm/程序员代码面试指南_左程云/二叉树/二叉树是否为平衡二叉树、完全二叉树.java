/*
题目描述
给定一棵二叉树，已经其中没有重复值的节点，请判断该二叉树是否为搜索二叉树和完全二叉树。
输入描述:
第一行输入两个整数 n 和 root，n 表示二叉树的总节点个数，root 表示二叉树的根节点。

以下 n 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)

ps:节点的标号就是节点的值
输出描述:
第一行输出该二叉树是否为搜索二叉树的答案，如果是则输出 "true"，否则输出 "false"。

第二行输出该二叉树是否为完全二叉树的答案，如果是则输出 "true"，否则输出 "false"。
示例1
输入
复制
3 2
2 1 3
1 0 0
3 0 0

7 1
1 2 3
2 4 5
3 6 0
4 7 0
5 0 0
6 0 0
7 0 0
输出
复制
true
true
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
        int max;
        int min;
        boolean isBST;
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
        System.out.println(isBST(rootId).isBST);
        System.out.println(isCBT());
    }

    private static boolean isCBT() {
        boolean result = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(rootId);
        boolean isLeaf = false;
        while (!queue.isEmpty()) {
            int nodeId = queue.poll();

            int leftId = treeMap.get(nodeId).leftId;
            int rightId = treeMap.get(nodeId).rightId;
            if (!(leftId == 0 && rightId == 0)) {
                if (isLeaf) {
                    result = false;
                    break;
                }
            }
            if (leftId == 0 && rightId != 0) {
                result = false;
                break;
            } else if (!(leftId != 0 && rightId != 0)) {
                isLeaf = true;
            }
            if (leftId != 0) {
                queue.offer(leftId);
            }
            if (rightId != 0) {
                queue.offer(rightId);
            }
        }
        return result;
    }

    private static ReturnType isBST(int rootId) {
        ReturnType result = new ReturnType();
        result.isBST = true;

        if (rootId == 0) {
            result.isBST = true;
            result.min = 0;
            result.max = 0;
        } else {
            int leftId = treeMap.get(rootId).leftId;
            int rightId = treeMap.get(rootId).rightId;
            ReturnType leftResult = isBST(leftId);
            ReturnType rightResult = isBST(rightId);

            boolean isChildrenSatisfy = leftResult.isBST && rightResult.isBST;
            if (isChildrenSatisfy) {
                boolean isLeftValueSatisfy;
                boolean isRightValueSatisfy;
                if (leftId == 0) {
                    isLeftValueSatisfy = true;
                } else {
                    isLeftValueSatisfy = rootId > leftResult.max;
                }
                if (rightId == 0) {
                    isRightValueSatisfy = true;
                } else {
                    isRightValueSatisfy = rootId < rightResult.min;
                }

                if (isLeftValueSatisfy && isRightValueSatisfy) {
                    if (rightId == 0) {
                        result.max = rootId;
                    } else {
                        result.max = rightResult.max;
                    }

                    if (leftId == 0) {
                        result.min = rootId;
                    } else {
                        result.min = leftResult.min;
                    }
                } else {
                    result.isBST = false;
                }
            } else {
                result.isBST = false;
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