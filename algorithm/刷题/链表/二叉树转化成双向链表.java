/*
题目描述
对二叉树的节点来说，有本身的值域，有指向左孩子节点和右孩子节点的两个指针；对双向链表的节点来说，有本身的值域，有指向上一个节点和下一个节点的指针。
在结构上，两种结构有相似性，现在有一棵搜索二叉树，请将其转换成一个有序的双向链表。
输入描述:
第一行一个数字 n 表示二叉树的总结点数。
以下 n 行每行三个整数 fa lch rch，表示节点 fa 的左儿子节点为 lch，右儿子节点为 rch。(若 lch 为 0 则表示 fa 没有左儿子，rch同理)

第一行的 fa 为根节点。

ps:节点的标号就是节点的值。
输出描述:
在给定的函数中返回双向链表的头指针。
示例1
输入
复制
9
6 4 7
4 2 5
2 1 3
5 0 0
1 0 0
3 0 0
7 0 9
9 8 0
8 0 0
输出
复制
1 2 3 4 5 6 7 8 9
备注:
1 \leq n \leq 10000001≤n≤1000000
1 \leq fa , lch, rch \leq 10000001≤fa,lch,rch≤1000000
 */

import java.util.*;

public class Main {
    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }
    }

    static class TreeNodeMap {
        int value;
        int leftValue;
        int rightValue;

        TreeNodeMap(int value, int left, int right) {
            this.value = value;
            this.leftValue = left;
            this.rightValue = right;
        }
    }

    static class DNode {
        int value;
        DNode pre;
        DNode next;

        DNode(int value) {
            this.value = value;
        }
    }

    static Scanner scanner;
    static int listLength;
    static TreeNode tHead;
    static DNode dHead;

    public static void main(String args[]) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        solveWithInOrderQueue();
        printResult();
    }

    private static void printResult() {
        DNode temp = dHead;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
    }

    private static void solveWithInOrderQueue() {
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        inOrderTranverse(tHead, treeNodeQueue);

        dHead = new DNode(treeNodeQueue.poll().value);
        DNode pre = dHead;
        while (!treeNodeQueue.isEmpty()) {
            TreeNode treeTempNode = treeNodeQueue.poll();
            DNode dTempNode = new DNode(treeTempNode.value);
            pre.next = dTempNode;
            dTempNode.pre = pre;
            pre = dTempNode;
        }
    }

    private static void inOrderTranverse(TreeNode tHead, Queue<TreeNode> treeNodeQueue) {
        if (tHead == null || tHead.value == 0) {
            return;
        } else {
            inOrderTranverse(tHead.left, treeNodeQueue);
            treeNodeQueue.offer(tHead);
            inOrderTranverse(tHead.right, treeNodeQueue);
        }
    }

    private static void init() {
        scanner = new Scanner(System.in);
        listLength = scanner.nextInt();

        List<TreeNodeMap> treeNodeMapList = new ArrayList<>();
        for (int i = 0; i < listLength; i++) {
            treeNodeMapList.add(new TreeNodeMap(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        }

        Map<Integer, TreeNode> treeNodeMap4Init = new HashMap<>();
        for (int i = 0; i < listLength; i++) {
            TreeNodeMap map = treeNodeMapList.get(i);
            treeNodeInMap(map.value, treeNodeMap4Init).left = treeNodeInMap(map.leftValue, treeNodeMap4Init);
            treeNodeInMap(map.value, treeNodeMap4Init).right = treeNodeInMap(map.rightValue, treeNodeMap4Init);
        }

        tHead = treeNodeMap4Init.get(treeNodeMapList.get(0).value);
    }

    private static TreeNode treeNodeInMap(int value, Map<Integer, TreeNode> treeNodeMap4Init) {
        TreeNode result;
        if (treeNodeMap4Init.containsKey(value)) {
            result = treeNodeMap4Init.get(value);
        } else {
            result = new TreeNode(value);
            treeNodeMap4Init.put(value, result);
        }
        return result;
    }

}