/*
题目描述
二叉树被记录为文件的过程叫作二叉树的序列化，通过文件内容重建原来二叉树的过程叫作二叉树的反序列化，给定一颗二叉树，请将该二叉树先序序列化和层序序列化。
假设序列化的结果字符串为 str，初始时 str = ""，遍历二叉树时，遇到 null 节点，则在 str 的末尾加上 "#!"，否则加上"当前的节点值!"。
输入描述:
第一行输入两个整数 n 和 root，n 表示二叉树的总节点个数，root 表示二叉树的根节点。
以下 n 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)
输出描述:
输出两行分别表示该二叉树的先序序列化和层序序列化
示例1
输入
复制
3 1
1 2 3
2 0 0
3 0 0
输出
复制
1!2!#!#!#!
1!2!#!#!#!
备注:
1\leq n\leq 10000001≤n≤1000000
1 \leq fa,lch,rch,root \leq n1≤fa,lch,rch,root≤n
 */

import java.util.*;

public class Main {
    static class TreeNode {
        int id;
        int left;
        int right;

        TreeNode(int id) {
            this.id = id;
        }
    }

    static Scanner scanner;
    static int treeNodeCount;
    static TreeNode head;
    static Map<Integer, TreeNode> treeMap;
    static StringBuffer preOrderPrint;
    static StringBuffer byLevelPrint;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        preOrderTranverse(head);
        byLevelTranverse();
        printResult();
    }

    private static void printResult() {
        System.out.println(preOrderPrint.toString());
        System.out.println(byLevelPrint.toString());
    }

    private static void byLevelTranverse() {
        Queue<TreeNode> levelQueue = new LinkedList<>();
        levelQueue.offer(head);
        while (!levelQueue.isEmpty()) {
            TreeNode temp = levelQueue.poll();
            if (temp != null) {
                byLevelPrint.append(temp.id + "!");
                levelQueue.offer(treeMap.get(temp.left));
                levelQueue.offer(treeMap.get(temp.right));
            } else {
                byLevelPrint.append("#!");
            }
        }
    }

    private static void preOrderTranverse(TreeNode head) {
        if (head == null) {
            preOrderPrint.append("#!");
        } else {
            preOrderPrint.append(head.id + "!");
            preOrderTranverse(treeMap.get(head.left));
            preOrderTranverse(treeMap.get(head.right));
        }
    }

    private static void init() {
        scanner = new Scanner(System.in);
        preOrderPrint = new StringBuffer("");
        byLevelPrint = new StringBuffer("");
        treeNodeCount = scanner.nextInt();
        int headId = scanner.nextInt();
        treeMap = new HashMap<>();
        treeMap.put(0, null);

        for (int i = 0; i < treeNodeCount; i++) {
            TreeNode temp = new TreeNode(scanner.nextInt());
            temp.left = scanner.nextInt();
            temp.right = scanner.nextInt();
            treeMap.put(temp.id, temp);
        }
        head = treeMap.get(headId);
    }
}