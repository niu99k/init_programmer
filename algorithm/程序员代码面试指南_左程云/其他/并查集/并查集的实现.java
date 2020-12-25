/*
题目描述
给定一个没有重复值的整形数组arr，初始时认为arr中每一个数各自都是一个单独的集合。请设计一种叫UnionFind的结构，并提供以下两个操作。
boolean isSameSet(int a, int b): 查询a和b这两个数是否属于一个集合
void union(int a, int b): 把a所在的集合与b所在的集合合并在一起，原本两个集合各自的元素以后都算作同一个集合
[要求]
如果调用isSameSet和union的总次数逼近或超过O(N)，请做到单次调用isSameSet或union方法的平均时间复杂度为O(1)
输入描述:
第一行两个整数N, M。分别表示数组大小、操作次数
接下来M行，每行有一个整数opt
若opt = 1，后面有两个数x, y，表示查询(x, y)这两个数是否属于同一个集合
若opt = 2，后面有两个数x, y，表示把x, y所在的集合合并在一起
输出描述:
对于每个opt = 1的操作，若为真则输出"Yes"，否则输出"No"
示例1
输入
复制
4 5
1 1 2
2 2 3
2 1 3
1 1 1
1 2 3

5 6
2 1 2
2 1 3
2 4 5
1 3 5
1 1 5
1 4 4
输出
复制
No
Yes
Yes
说明
每次2操作后的集合为
({1}, {2}, {3}, {4})
({1}, {2, 3}, {4})
({1, 2, 3}, {4})
备注:
1 \leqslant N, M \leqslant 10^61⩽N,M⩽10
6

保证1 \leqslant x, y \leqslant N保证1⩽x,y⩽N
 */

import java.util.*;

public class Main {


    static Scanner scanner;
    static int arrayLen;
    static int opCount;
    static int[] parantArray;
    static int[] countArray;

    public static void main(String[] args) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        for (int i = 0; i < opCount; i++) {
            int opType = scanner.nextInt();
            int node1 = scanner.nextInt();
            int node2 = scanner.nextInt();
            if (isSameDSU(node1, node2)) {
                if (opType == 1) {
                    System.out.println("Yes");
                }
            } else {
                if (opType == 1) {
                    System.out.println("No");
                } else {
                    mergeDSU(node1, node2);
                }
            }
        }
    }

    private static void mergeDSU(int node1, int node2) {
        int DSUCount1 = countArray[node1];
        int DSUCount2 = countArray[node2];

        int root1 = root(node1);
        int root2 = root(node2);
        if (DSUCount1 < DSUCount2) {
            parantArray[root1] = root2;
            countArray[root2] = DSUCount1 + DSUCount2;
        } else {
            parantArray[root2] = root1;
            countArray[root1] = DSUCount1 + DSUCount2;

        }
    }

    private static boolean isSameDSU(int node1, int node2) {
        boolean result;
        int root1 = root(node1);
        int root2 = root(node2);
        if (root1 == root2) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    private static int root(int index) {
        int result;
        Stack<Integer> DSUNodeNeed2CorrectFather = new Stack<>();
        while (true) {
            if (isRoot(index)) {
                result = index;
                break;
            } else {
                DSUNodeNeed2CorrectFather.push(index);
                index = parantArray[index];
            }
        }

        while (!DSUNodeNeed2CorrectFather.isEmpty()) {
            parantArray[DSUNodeNeed2CorrectFather.pop()] = result;
        }
        return result;
    }

    private static boolean isRoot(int index) {
        boolean result;
        result = index == parantArray[index];
        return result;
    }

    private static void init() {
        scanner = new Scanner(System.in);
        arrayLen = scanner.nextInt();
        parantArray = new int[arrayLen];
        countArray = new int[arrayLen];

        for (int i = 0; i < arrayLen; i++) {
            parantArray[i] = i;
        }
        for (int i = 0; i < arrayLen; i++) {
            countArray[i] = 1;
        }
        opCount = scanner.nextInt();
    }

}