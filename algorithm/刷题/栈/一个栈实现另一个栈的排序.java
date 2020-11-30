/*
题目描述
一个栈中元素的类型为整型，现在想将该栈从顶到底按从大到小的顺序排序，只许申请一个栈。除此之外，可以申请新的变量，但不能申请额外的数据结构。如何完成排序？
输入描述:
第一行输入一个N，表示栈中元素的个数
第二行输入N个整数ai表示栈顶到栈底的各个元素
输出描述:
输出一行表示排序后的栈中栈顶到栈底的各个元素。
示例1
输入
复制
5
5 8 4 3 6
输出
复制
8 6 5 4 3
备注:
1 <= N <= 10000
-1000000 <= an<= 1000000
 */

import java.util.*;

public class Main {
    static Stack<Integer> input;
    static Stack<Integer> support;
    static Scanner scanner;
    static int inputSize;

    public static void main(String strs[]) {
        init();
        result();
        deInit();
    }

    private static void deInit() {
        scanner.close();
    }

    private static void result() {
        while (!input.isEmpty()) {
            disposeInputTop(input.pop());
        }
        printResult();
    }

    private static void printResult() {
        while (!support.isEmpty()) {
            System.out.print(support.pop()+" ");
        }
    }

    private static void disposeInputTop(Integer pop) {
        if (support.isEmpty()) {
            support.push(pop);
        } else if (pop >= support.peek()) {
            support.push(pop);
        } else {
            transferAllFromSupport2Input();
            support.push(pop);
        }
    }

    private static void transferAllFromSupport2Input() {
        while (!support.isEmpty()) {
            input.push(support.pop());
        }
    }

    private static void init() {
        scanner = new Scanner(System.in);
        input = new Stack<>();
        support = new Stack<>();

        inputSize = scanner.nextInt();
        for (int i = 0; i < inputSize; i++) {
            input.push(scanner.nextInt());
        }
    }
}