/*
题目描述
给出一棵二叉树的先序和中序数组，通过这两个数组直接生成正确的后序数组。
输入描述:
第一行一个整数 n，表示二叉树的大小。

第二行 n 个整数 a_i，表示二叉树的先序遍历数组。

第三行 n 个整数 b_i，表示二叉树的中序遍历数组。
输出描述:
输出一行 n 个整数表示二叉树的后序遍历数组。
示例1
输入
复制
3
1 2 3
2 1 3
输出
复制
2 3 1
备注:
1 \leq n \leq 100001≤n≤10000
1 \leq a_i , b_i \leq n1≤a
i
​
 ,b
i
​
 ≤n
数据保证合法
 */

import java.util.*;

public class Main {
    static Scanner scanner;
    static int arrayCount;
    static int[] preOrderArray;
    static int[] inOrderArray;
    static int[] postOrderArray;

    static class IndexType {
        int preOrderStartIndex;
        int preOrderEndtIndex;
        int inOrderStartIndex;
        int inOrderEndtIndex;
        int postOrderStartIndex;
        int postOrderEndtIndex;
    }

    static public void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        IndexType initIndex = new IndexType();
        initIndex.preOrderStartIndex = 0;
        initIndex.preOrderEndtIndex = arrayCount - 1;
        initIndex.inOrderStartIndex = 0;
        initIndex.inOrderEndtIndex = arrayCount - 1;
        initIndex.postOrderStartIndex = 0;
        initIndex.postOrderEndtIndex = arrayCount - 1;
        postOrderArray(inOrderMap(), initIndex);
        for (int i = 0; i < arrayCount; i++) {
            System.out.print(postOrderArray[i] + " ");
        }
    }

    private static void postOrderArray(Map<Integer, Integer> inOrderMap, IndexType initIndex) {
        if (initIndex.inOrderStartIndex > initIndex.inOrderEndtIndex) {
            return;
        }

        postOrderArray[initIndex.postOrderEndtIndex] = preOrderArray[initIndex.preOrderStartIndex];
        int rootInInOrderIndex = inOrderMap.get(preOrderArray[initIndex.preOrderStartIndex]);
        int leftNodeCount = rootInInOrderIndex - initIndex.inOrderStartIndex;
        int rightCount = initIndex.inOrderEndtIndex - rootInInOrderIndex;

        IndexType initIndexLeft = new IndexType();
        initIndexLeft.inOrderEndtIndex = rootInInOrderIndex - 1;
        initIndexLeft.inOrderStartIndex = initIndex.inOrderStartIndex;
        initIndexLeft.preOrderStartIndex = initIndex.preOrderStartIndex + 1;
        initIndexLeft.preOrderEndtIndex = initIndex.preOrderStartIndex + leftNodeCount;
        initIndexLeft.postOrderEndtIndex = initIndex.postOrderEndtIndex - rightCount - 1;

        IndexType initIndexRight = new IndexType();
        initIndexRight.inOrderEndtIndex = initIndex.inOrderEndtIndex;
        initIndexRight.inOrderStartIndex = rootInInOrderIndex + 1;
        initIndexRight.preOrderStartIndex = initIndex.preOrderEndtIndex - rightCount + 1;
        initIndexRight.preOrderEndtIndex = initIndex.preOrderEndtIndex;
        initIndexRight.postOrderEndtIndex = initIndex.postOrderEndtIndex - 1;

        postOrderArray(inOrderMap, initIndexLeft);
        postOrderArray(inOrderMap, initIndexRight);
    }

    private static Map<Integer, Integer> inOrderMap() {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < arrayCount; i++) {
            result.put(inOrderArray[i], i);
        }
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        arrayCount = scanner.nextInt();
        preOrderArray = new int[arrayCount];
        inOrderArray = new int[arrayCount];
        postOrderArray = new int[arrayCount];
        for (int i = 0; i < arrayCount; i++) {
            preOrderArray[i] = scanner.nextInt();
        }
        for (int i = 0; i < arrayCount; i++) {
            inOrderArray[i] = scanner.nextInt();
        }
    }
}