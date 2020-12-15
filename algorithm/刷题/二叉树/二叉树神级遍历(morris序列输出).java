/*
题目描述
分别按照二叉树先序，中序和后序打印所有的节点。
输入描述:
第一行输入两个整数 n 和 root，n 表示二叉树的总节点个数，root 表示二叉树的根节点。
以下 n 行每行三个整数 fa，lch，rch，表示 fa 的左儿子为 lch，右儿子为 rch。(如果 lch 为 0 则表示 fa 没有左儿子，rch同理)
输出描述:
输出三行分别表示二叉树的前序，中序和后序遍历。
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
1 \leq fa,lch,rch,root \leq n1≤fa,lch,rch,root≤n
7 4
1 0 0
2 1 3
3 0 0
4 2 6
5 0 0
6 5 7
7 0 0
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
    static TreeNode root;
    static Map<Integer, TreeNode> treeNodeMap;
    static int treeNodeCount;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        System.out.println(morrrisSquence(root));
    }

    private static String morrrisSquence(TreeNode root) {
        String result;
        StringBuilder stringBuilder = new StringBuilder();
        TreeNode cur = root;
        while (cur != null) {
            stringBuilder.append(cur.id + " ");
            if (treeNodeMap.get(cur.left) == null) {
                cur = treeNodeMap.get(cur.right);
            } else {
                TreeNode mostRight = mostRight(cur);
                if (treeNodeMap.get(mostRight.right) == null) {
                    mostRight.right = cur.id;
                    cur = treeNodeMap.get(cur.left);
                } else {
                    mostRight.right = 0;
                    cur = treeNodeMap.get(cur.right);
                }
            }

        }
        result = stringBuilder.toString();
        return result;
    }

    private static TreeNode mostRight(TreeNode node) {
        TreeNode result;
        TreeNode cur = treeNodeMap.get(node.left);
        while (treeNodeMap.get(cur.right) != null && treeNodeMap.get(cur.right) != node) {
            cur = treeNodeMap.get(cur.right);
        }
        result = cur;
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        treeNodeMap = new HashMap<>();
        treeNodeCount = scanner.nextInt();
        int rootValue = scanner.nextInt();
        treeNodeMap.put(0, null);

        for (int i = 0; i < treeNodeCount; i++) {
            TreeNode temp = new TreeNode(scanner.nextInt());
            temp.left = scanner.nextInt();
            temp.right = scanner.nextInt();
            treeNodeMap.put(temp.id, temp);
        }
        root = treeNodeMap.get(rootValue);
    }
}