/*
题目描述
分别按照二叉树先序，中序和后序打印所有的节点。
输入描述:
第一行输入两个整数 n 和 root，n 表示二叉树的总节点个数，root 表示二叉树的根节点。

以下 n 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。
(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)
输出描述:
输出三行，分别表示二叉树的先序，中序和后序。
示例1
输入
复制
3 1
1 2 3
2 0 0
3 0 0
输出
复制
1 2 3
2 1 3
2 3 1
备注:
1 \leq n \leq 10000001≤n≤1000000
1 \leq root, fa, lch, rch \leq n1≤root,fa,lch,rch≤n
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

    static Scanner scanner;
    static int nodeCount;
    static TreeNode root;

    public static void main(String args[]) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
        System.out.println();
    }

    private static void postOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        postOrder(head.left);
        postOrder(head.right);
        System.out.print(head.value + " ");
    }

    private static void inOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        inOrder(head.left);
        System.out.print(head.value + " ");
        inOrder(head.right);
    }

    private static void preOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        preOrder(head.left);
        preOrder(head.right);
    }

    private static void init() {
        scanner = new Scanner(System.in);
        nodeCount = scanner.nextInt();
        root = new TreeNode(scanner.nextInt());

        Map<Integer, TreeNode> treeMap = new HashMap<>();
        treeMap.put(0, null);
        treeMap.put(root.value, root);
        for (int i = 0; i < nodeCount; i++) {
            int value1 = scanner.nextInt();
            int value2 = scanner.nextInt();
            int value3 = scanner.nextInt();
            TreeNode node1;
            TreeNode node2;
            TreeNode node3;

            if (treeMap.containsKey(value1)) {
                node1 = treeMap.get(value1);
            } else {
                node1 = new TreeNode(value1);
                treeMap.put(node1.value, node1);
            }
            if (treeMap.containsKey(value2)) {
                node2 = treeMap.get(value2);
            } else {
                node2 = new TreeNode(value2);
                treeMap.put(node2.value, node2);
            }
            if (treeMap.containsKey(value3)) {
                node3 = treeMap.get(value3);
            } else {
                node3 = new TreeNode(value3);
                treeMap.put(node3.value, node3);
            }

            node1.left = node2;
            node1.right = node3;
        }
    }
}